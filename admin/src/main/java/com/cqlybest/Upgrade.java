package com.cqlybest;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.FriendlyLink;
import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.Link;
import com.cqlybest.common.bean.MaldivesDining;
import com.cqlybest.common.bean.MaldivesIsland;
import com.cqlybest.common.bean.MaldivesRoom;
import com.cqlybest.common.bean.MauritiusDining;
import com.cqlybest.common.bean.MauritiusHotel;
import com.cqlybest.common.bean.MauritiusRoom;
import com.cqlybest.common.bean.Page;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.Version;
import com.cqlybest.common.dao.MongoDb;
import com.cqlybest.common.service.SettingsService;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.net.CallRet;
import com.qiniu.api.net.Client;
import com.qiniu.api.rs.PutPolicy;

@Component
public class Upgrade implements ServletContextAware {

  private static final Logger LOGGER = LoggerFactory.getLogger(Upgrade.class);

  @Autowired
  Version version;
  @Autowired
  private SettingsService settingsService;
  @Autowired
  private MongoDb mongoDb;

  @Override
  public void setServletContext(ServletContext servletContext) {
    new Timer().schedule(new TimerTask() {
      @Override
      public void run() {
        try {
          nullToV1();
          v1ToV2();
          setNewVersion();
          LOGGER.info("Upgrade finished");
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, 5000);
  }

  private void nullToV1() throws Exception {// 上传图片到七牛
    Version version = mongoDb.createQuery("Version").findObject(Version.class);
    if (version == null) {
      String accessKey = System.getProperty(Constant.QINIU_AK, System.getenv(Constant.QINIU_AK));
      String secretKey = System.getProperty(Constant.QINIU_SK, System.getenv(Constant.QINIU_SK));
      Mac mac = new Mac(accessKey, secretKey);

      long total = mongoDb.createQuery("Image").countObjects();
      LOGGER.info("Upgrade image, total {}", total);
      Client client = new Client();
      for (int i = 0; i < total; i++) {
        Image image =
            mongoDb.createQuery("Image").addSort("_id", 1).setFirstDocument(i).setMaxDocuments(1)
                .findObject(Image.class);
        mongoDb.createQuery("Image").eq("_id", image.get_id()).modify().set("id", image.get_id())
            .update();
        byte[] data = image.getData();
        if (data != null) {
          LOGGER.info("Upgrade image {}: {}", i, image.getId());
          String key = image.getId() + "." + image.getExtension();
          PutPolicy putPolicy =
              new PutPolicy(System.getProperty("qiniu.bk", System.getenv("qiniu.bk")) + ":" + key);
          putPolicy.endUser = "0";
          putPolicy.callbackUrl =
              "http://" + System.getProperty("qiniu.callback") + "/image/upload/callback";
          putPolicy.callbackBody =
              "token=$(x:token)&uid=$(x:uid)&imageId=$(x:id)" + "&etag=$(etag)&fname=$(fname)"
                  + "&fsize=$(fsize)&mimeType=$(mimeType)" + "&imageInfo=$(imageInfo)&exif=$(exif)"
                  + "&width=$(imageInfo.width)&height=$(imageInfo.height)";
          String token = putPolicy.token(mac);
          MultipartEntity entity = new MultipartEntity();
          entity.addPart("key", new StringBody(key));
          entity.addPart("token", new StringBody(token));
          entity.addPart("file", new ByteArrayBody(data, key));
          entity.addPart("x:id", new StringBody(image.getId()));
          entity.addPart("x:uid", new StringBody("0"));
          String imageToken = RandomStringUtils.randomAlphanumeric(16);
          entity.addPart("x:token", new StringBody(imageToken));
          mongoDb.createQuery("Image").eq("_id", image.getId()).modify().set("token", imageToken)
              .set("qiniuKey", key).update();
          CallRet ret = client.callWithMultiPart("http://up.qiniu.com/", entity);
          LOGGER.info("Ret {}", ret);
          if (ret.ok()) {
            mongoDb.createQuery("Image").eq("_id", image.getId()).modify().unset("data").update();
          }
        }
      }
    }
  }

  @SuppressWarnings( {"unchecked", "rawtypes"})
  private void v1ToV2() throws Exception {// 历史图片数据处理
    Version version = mongoDb.createQuery("Version").findObject(Version.class);
    if (version == null || version.getId() < 2) {
      // 产品
      long total = mongoDb.createQuery("Product").countObjects();
      for (int i = 0; i < total; i++) {
        Product product =
            mongoDb.createQuery("Product").addSort("_id", 1).setFirstDocument(i).setMaxDocuments(1)
                .findObject(Product.class);
        for (Image image : product.getPhotos()) {
          updateImage(image);
        }
        for (Image image : product.getPosters()) {
          updateImage(image);
        }
        mongoDb.updateObject("Product", product.getId(), product);
      }

      // 毛求
      total = mongoDb.createQuery("MauritiusHotel").countObjects();
      for (int i = 0; i < total; i++) {
        MauritiusHotel hotel =
            mongoDb.createQuery("MauritiusHotel").addSort("_id", 1).setFirstDocument(i)
                .setMaxDocuments(1).findObject(MauritiusHotel.class);
        for (Image image : hotel.getHotelPictures()) {
          updateImage(image);
        }
        for (Image image : hotel.getPictures()) {
          updateImage(image);
        }
        for (MauritiusRoom room : hotel.getRooms()) {
          for (Image image : room.getPictures()) {
            updateImage(image);
          }
        }
        for (MauritiusDining dining : hotel.getDinings()) {
          for (Image image : dining.getPictures()) {
            updateImage(image);
          }
        }
        mongoDb.updateObject("MauritiusHotel", hotel.getId(), hotel);
      }

      // 马代
      total = mongoDb.createQuery("MaldivesIsland").countObjects();
      for (int i = 0; i < total; i++) {
        MaldivesIsland island =
            mongoDb.createQuery("MaldivesIsland").addSort("_id", 1).setFirstDocument(i)
                .setMaxDocuments(1).findObject(MaldivesIsland.class);
        for (Image image : island.getHotelPictures()) {
          updateImage(image);
        }
        for (Image image : island.getPictures()) {
          updateImage(image);
        }
        for (MaldivesRoom room : island.getRooms()) {
          for (Image image : room.getPictures()) {
            updateImage(image);
          }
        }
        for (MaldivesDining dining : island.getDinings()) {
          for (Image image : dining.getPictures()) {
            updateImage(image);
          }
        }
        mongoDb.updateObject("MaldivesIsland", island.getId(), island);
      }

      // 友情链接
      total = mongoDb.createQuery("FriendlyLink").countObjects();
      for (int i = 0; i < total; i++) {
        FriendlyLink link =
            mongoDb.createQuery("FriendlyLink").addSort("_id", 1).setFirstDocument(i)
                .setMaxDocuments(1).findObject(FriendlyLink.class);
        if (link.getImage() != null) {
          updateImage(link.getImage());
        }
        mongoDb.updateObject("FriendlyLink", link.getId(), link);
      }

      // 页面
      total = mongoDb.createQuery("Page").countObjects();
      for (int i = 0; i < total; i++) {
        Page page =
            mongoDb.createQuery("Page").addSort("_id", 1).setFirstDocument(i).setMaxDocuments(1)
                .findObject(Page.class);
        for (Link link : page.getPosters()) {
          if (link.getImage() != null) {
            updateImage(link.getImage());
          }
        }
        mongoDb.updateObject("Page", page.getId(), page);
      }
    }

    // 设置
    Map settings = mongoDb.createQuery("Settings").findObject(Map.class);
    Map watermark = (Map) settings.get("watermark");
    Map watermarkImg = (Map) watermark.get("img");
    Image newImage = mongoDb.findById("Image", Image.class, (String) watermarkImg.get("id"));
    watermarkImg.put("contentType", newImage.getContentType());
    watermarkImg.put("qiniuKey", newImage.getQiniuKey());
    watermarkImg.put("size", newImage.getSize());
    watermarkImg.put("width", newImage.getWidth());
    watermarkImg.put("height", newImage.getHeight());

    Map basic = (Map) settings.get("basic");
    Map logo = (Map) basic.get("logo");
    newImage = mongoDb.findById("Image", Image.class, (String) watermarkImg.get("id"));
    logo.put("contentType", newImage.getContentType());
    logo.put("qiniuKey", newImage.getQiniuKey());
    logo.put("size", newImage.getSize());
    logo.put("width", newImage.getWidth());
    logo.put("height", newImage.getHeight());

    mongoDb.createQuery("Settings").modify().delete();
    settings.remove("_id");
    mongoDb.createObject("Settings", settings);
  }

  private void setNewVersion() {
    mongoDb.createObject("Version", this.version);
  }

  private void updateImage(Image old) {
    Image newImage = mongoDb.findById("Image", Image.class, old.getId());
    old.set_id(old.get_id());
    if (newImage != null) {
      old.setExtension(newImage.getExtension());
      old.setContentType(newImage.getContentType());
      old.setQiniuKey(newImage.getQiniuKey());
      old.setSize(newImage.getSize());
      old.setWidth(newImage.getWidth());
      old.setHeight(newImage.getHeight());
    } else {
      old.setQiniuKey(old.getId() + "." + old.getExtension());
    }
  }

}

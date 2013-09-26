package com.cqlybest.admin;

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
import com.cqlybest.common.mongo.bean.Image;
import com.cqlybest.common.mongo.bean.Version;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.cqlybest.common.mongo.service.SettingsService;
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
          Upgrade.this.nullToV1();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }, 60000);
  }

  private void nullToV1() throws Exception {
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
        LOGGER.info("Upgrade image {}: {}", i, image.getId());
        byte[] data = image.getData();
        if (data != null) {
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
    mongoDb.createObject("Version", this.version);
  }

}

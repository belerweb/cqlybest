package com.cqlybest.common.mongo.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.http.HttpServletRequest;

import net.coobird.thumbnailator.filters.Watermark;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.commons.lang.RandomStringUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.Constant;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.Image;
import com.cqlybest.common.mongo.service.ImageService;
import com.cqlybest.common.mongo.service.SettingsService;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

@Controller
public class ImageController extends ControllerHelper {

  @Autowired
  private ImageService mongoImageService;
  @Autowired
  private SettingsService settingsService;

  @RequestMapping(method = RequestMethod.GET, value = "/image/upload")
  public void upload() {}

  /**
   * 上传图片的Token
   */
  @RequestMapping("/image/upload/token")
  public Object upload(@RequestParam String name) {
    String accessKey = System.getProperty(Constant.QINIU_AK, System.getenv(Constant.QINIU_AK));
    String secretKey = System.getProperty(Constant.QINIU_SK, System.getenv(Constant.QINIU_SK));
    Mac mac = new Mac(accessKey, secretKey);
    String userId = getUser().getId();
    String imageId = UUID.randomUUID().toString();
    String extension = name.substring(name.lastIndexOf(".")).toLowerCase();
    String key = imageId + extension;
    PutPolicy putPolicy = new PutPolicy(getQiniuBucket() + ":" + key);
    putPolicy.endUser = userId;
    putPolicy.callbackUrl =
        "http://" + System.getProperty("qiniu.callback") + "/image/upload/callback";
    putPolicy.callbackBody =
        "token=$(x:token)&uid=$(x:uid)&imageId=$(x:id)" + "&etag=$(etag)&fname=$(fname)"
            + "&fsize=$(fsize)&mimeType=$(mimeType)" + "&imageInfo=$(imageInfo)&exif=$(exif)"
            + "&width=$(imageInfo.width)&height=$(imageInfo.height)";
    try {
      String token = putPolicy.token(mac);
      Map<String, String> result = new HashMap<>();
      result.put("key", key);
      result.put("token", token);
      result.put("x:id", imageId);
      result.put("x:uid", userId);
      String imageToken = RandomStringUtils.randomAlphanumeric(16);
      result.put("x:token", imageToken);

      Image image = new Image();
      image.setId(imageId);
      image.setName(name);
      // image.setTitle(name);
      image.setUserId(getUser().getId());

      image.setExtension(extension.substring(1));
      Date current = new Date();
      image.setCreatedTime(current);
      image.setLastUpdated(current);
      image.setToken(imageToken);
      image.setQiniuKey(key);
      mongoImageService.addImage(image);

      return json(result);
    } catch (Exception e) {
      e.printStackTrace();
      return error(e.getMessage());
    }
  }

  /**
   * 七牛上传图片回调
   */
  @RequestMapping(method = RequestMethod.POST, value = "/image/upload/callback")
  public Object upload(@RequestParam String token, @RequestParam String uid,
      @RequestParam String imageId, @RequestParam String etag, @RequestParam String fname,
      @RequestParam long fsize, @RequestParam String mimeType, @RequestParam String imageInfo,
      @RequestParam int width, @RequestParam int height, @RequestParam String exif,
      HttpServletRequest request) {
    Image image = mongoImageService.getImage(imageId);
    if (!token.equals(image.getToken())) {
      return illegal();
    }

    image.setUploaded(Boolean.TRUE);
    image.setSize(fsize);
    image.setContentType(mimeType);
    image.setWidth(width);
    image.setHeight(height);
    mongoImageService.updateImage(image);
    return json(image);
  }

  @RequestMapping(value = "/image/update", method = RequestMethod.POST)
  @ResponseBody
  public void update(@RequestParam String pk, @RequestParam(required = false) String name,
      @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "name[]") List<String> names,
      @RequestParam(required = false, value = "value[]") List<String> values) {
    if (name != null && value != null) {
      // TODO imageService.update(pk, name, value);
    }
    if (names != null && values != null && names.size() == values.size()) {
      for (int i = 0; i < names.size(); i++) {
        // TODO imageService.update(pk, names.get(i), values.get(i));
      }
    }
  }

  @RequestMapping(value = "/image/delete", method = RequestMethod.POST)
  @ResponseBody
  public void delete(@RequestParam String id) {
  // TODO imageService.delete(id);
  }

  @RequestMapping(value = "/image/{imageId}.{extention:jpg|png|gif}", method = RequestMethod.GET)
  public Object view(@PathVariable String imageId, @PathVariable String extention,
      @RequestHeader(value = "If-Modified-Since", required = false) String ifModifiedSince)
      throws IOException {
    if (ifModifiedSince != null) {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    Image image = mongoImageService.getImage(imageId);
    if (!extention.equals(image.getExtension())) {
      return new ResponseEntity<byte[]>(null, null, HttpStatus.NOT_FOUND);
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setDate(System.currentTimeMillis());
    headers.setLastModified(0);
    headers.setExpires(System.currentTimeMillis() + 31536000000L);
    headers.setCacheControl("max-age=31536000000");
    headers.setContentType(MediaType.valueOf(image.getContentType()));

    Map<?, ?> watermark = (Map<?, ?>) settingsService.getSettings().get("watermark");
    String watermarkId = (String) ((Map<?, ?>) watermark.get("img")).get("id");
    String watermarkPosition = (String) watermark.get("position");
    Image img = mongoImageService.getImage(watermarkId);

    if (watermark != null && watermarkPosition != null) {
      BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image.getData()));
      BufferedImage watermarkImage = ImageIO.read(new ByteArrayInputStream(img.getData()));
      if (bufferedImage.getWidth() > watermarkImage.getWidth()
          && bufferedImage.getHeight() > watermarkImage.getHeight()) {
        // 水印
        Watermark watermarkFilter =
            new Watermark(Positions.valueOf(watermarkPosition), watermarkImage, 1);
        bufferedImage = watermarkFilter.apply(bufferedImage);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        write(extention, bufferedImage, out);
        return new ResponseEntity<byte[]>(out.toByteArray(), headers, HttpStatus.OK);
      }
    }
    return new ResponseEntity<byte[]>(image.getData(), headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/image/{width}/{height}/{imageId}.{extention:jpg|png|gif}", method = RequestMethod.GET)
  public Object view(@PathVariable String imageId, @PathVariable String extention,
      @PathVariable Integer width, @PathVariable Integer height,
      @RequestHeader(value = "If-Modified-Since", required = false) String ifModifiedSince)
      throws IOException {
    if (ifModifiedSince != null) {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    Image image = mongoImageService.getImage(imageId);
    if (!extention.equals(image.getExtension())) {
      return new ResponseEntity<byte[]>(null, null, HttpStatus.NOT_FOUND);
    }
    HttpHeaders headers = new HttpHeaders();
    headers.setDate(System.currentTimeMillis());
    headers.setLastModified(0);
    headers.setExpires(System.currentTimeMillis() + 31536000000L);
    headers.setCacheControl("max-age=31536000000");
    headers.setContentType(MediaType.valueOf(image.getContentType()));

    // 缩放
    BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image.getData()));
    boolean fixToWidth =
        (double) bufferedImage.getWidth() / (double) bufferedImage.getHeight() < (double) width
            / (double) height;
    BufferedImage scaleImage =
        Scalr.resize(bufferedImage, Scalr.Method.QUALITY, fixToWidth
            ? Scalr.Mode.FIT_TO_WIDTH
            : Scalr.Mode.FIT_TO_HEIGHT, width, height, Scalr.OP_ANTIALIAS);
    scaleImage =
        Scalr.crop(scaleImage, (scaleImage.getWidth() - width) / 2,
            (scaleImage.getHeight() - height) / 2, width, height);
    ByteArrayOutputStream scaleOut = new ByteArrayOutputStream();
    write(extention, scaleImage, scaleOut);
    return new ResponseEntity<byte[]>(scaleOut.toByteArray(), headers, HttpStatus.OK);
  }

  private void write(String type, BufferedImage in, ByteArrayOutputStream out) throws IOException {
    ImageWriter writer = ImageIO.getImageWritersBySuffix(type).next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(out);
    writer.setOutput(ios);
    if ("jpg".equals(type)) {
      ImageWriteParam param = writer.getDefaultWriteParam();
      param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
      param.setCompressionQuality(1.0F);
      writer.write(null, new IIOImage(in, null, null), param);
    } else {
      writer.write(in);
    }
  }
}

package com.cqlybest.common.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Image;
import com.cqlybest.common.service.CentralConfig;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.SettingsService;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

@Controller
public class ImageController extends ControllerHelper {

  @Autowired
  private ImageService imageService;
  @Autowired
  private SettingsService settingsService;

  @RequestMapping(method = RequestMethod.GET, value = "/image/upload")
  public void upload() {}

  /**
   * 上传图片的Token
   */
  @RequestMapping("/image/upload/token")
  public Object upload(@RequestParam String name) {
    String accessKey = centralConfig.get(CentralConfig.QINIU_AK);
    String secretKey = centralConfig.get(CentralConfig.QINIU_SK);
    Mac mac = new Mac(accessKey, secretKey);
    String userId = getUser().getId();
    String imageId = UUID.randomUUID().toString();
    String extension = name.substring(name.lastIndexOf(".")).toLowerCase();
    String key = imageId + extension;
    PutPolicy putPolicy = new PutPolicy(getQiniuBucket() + ":" + key);
    putPolicy.endUser = userId;
    putPolicy.callbackUrl =
        "http://" + centralConfig.get(CentralConfig.QINIU_CALLBACK) + "/image/upload/callback";
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
      imageService.addImage(image);

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
    Image image = imageService.getImage(imageId);
    if (!token.equals(image.getToken())) {
      return illegal();
    }

    image.setUploaded(Boolean.TRUE);
    image.setSize(fsize);
    image.setContentType(mimeType);
    image.setWidth(width);
    image.setHeight(height);
    imageService.updateImage(image);
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

}

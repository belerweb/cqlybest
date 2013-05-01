package com.cqlybest.common.service;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cqlybest.common.bean.Image;
import com.cqlybest.common.dao.ImageDao;

import eu.medsea.util.MimeUtil;

@Service
public class ImageService {

  @Autowired
  private ImageDao imageDao;

  public void add(Image image) {
    imageDao.saveOrUpdate(image);
  }

  public JSONObject validate(MultipartFile imageFile) throws JSONException {
    JSONObject result = new JSONObject();
    result.put("code", 0);
    if (imageFile == null) {
      result.put("code", 11);
      result.put("message", "Image is must.");
      return result;
    }
    String contentType = imageFile.getContentType();
    if (contentType == null
        || !(contentType.equals("image/jpeg") || contentType.equals("image/png") || contentType
            .equals("image/gif"))) {
      result.put("code", 12);
      result.put("message", "Unsupported file.");
      return result;
    }
    if (imageFile.getSize() > 32000 * 1024) {
      result.put("code", 13);
      result.put("message", "File is too big (over 32M).");
      return result;
    }
    return result;
  }

  public Image get(String imageId) {
    return imageDao.findById(imageId);
  }

  public Image multipartFileToImage(MultipartFile file) throws IOException {
    String name = file.getOriginalFilename();
    Image image = new Image();
    image.setId(UUID.randomUUID().toString());
    image.setTitle(name);
    image.setImageData(file.getBytes());
    image.setImageType(MimeUtil.getFileExtension(name));
    return image;
  }

}

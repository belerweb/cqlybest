package com.cqlybest.common.mongo.service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cqlybest.common.mongo.bean.Image;
import com.cqlybest.common.mongo.dao.MongoDb;

import eu.medsea.util.MimeUtil;

@Service("mongoImageService")
public class ImageService {

  @Autowired
  private MongoDb mongoDb;

  public Image upload(MultipartFile file) throws IOException {
    String name = file.getOriginalFilename();
    Image image = new Image();
    image.setId(UUID.randomUUID().toString());
    image.setExtension(MimeUtil.getFileExtension(name).toLowerCase());
    image.setContentType(MimeUtil.getMimeType(name).toLowerCase().split(",")[0]);
    image.setData(file.getBytes());

    Date now = new Date();
    image.setCreatedTime(now);
    image.setLastUpdated(now);

    return mongoDb.getMongoDao().createObject("Image", image);
  }

  public Image getImage(String imageId) {
    return mongoDb.createQuery("Image").eq("id", imageId).findObject(Image.class);
  }

}

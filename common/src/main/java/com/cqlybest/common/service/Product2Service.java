package com.cqlybest.common.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Product2;
import com.cqlybest.common.dao.ImageDao;
import com.cqlybest.common.dao.Product2Dao;

@Service
public class Product2Service {

  @Autowired
  private Product2Dao product2Dao;
  @Autowired
  private ImageDao imageDao;

  @Transactional
  public void add(Product2 product) {
    product.setId(UUID.randomUUID().toString());
    Date now = new Date();
    product.setCreatedTime(now);
    product.setLastUpdated(now);
    product2Dao.saveOrUpdate(product);
  }

  public Product2 get(String id) {
    Product2 product = product2Dao.findById(id);
    product.setPosters(imageDao.queryImagesWithoutData("product-poster", id));
    product.setPhotos(imageDao.queryImagesWithoutData("product-photo", id));
    return product;
  }

  @Transactional
  public void update(String id, String name, Object value) {
    product2Dao.update(id, name, value);
  }

}

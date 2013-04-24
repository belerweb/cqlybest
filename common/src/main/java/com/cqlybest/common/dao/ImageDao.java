package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Image;

@Repository
public class ImageDao extends AbstractDao<Image, String> {

  protected ImageDao() {
    super(Image.class);
  }

}

package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Advertorial;

@Repository
public class AdvertorialDao extends AbstractDao<Advertorial, Integer> {

  protected AdvertorialDao() {
    super(Advertorial.class);
  }

}

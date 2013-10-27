package com.cqlybest.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.bean.Id;
import com.cqlybest.common.dao.MongoDao;

@Component
public class IdGenerator {

  @Autowired
  private MongoDao mongoDao;

  public Long getId() {
    Id id = mongoDao.createQuery("Id").findObject(Id.class);
    if (id == null) {
      id = new Id();
      id.setId(100000L);
      mongoDao.createObject("Id", id);
    } else {
      id.setId(id.getId() + 1);
    }
    mongoDao.createQuery("Id").modify().set("id", id.getId()).update();
    return id.getId();
  }

  public String getStringId() {
    return getId().toString();
  }
}

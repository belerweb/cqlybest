package com.cqlybest.common.mongo.dao;

import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.spring.MongoDBDaoSupport;
import com.mongodb.DBObject;

public class MongoDb extends MongoDBDaoSupport {

  public DaoQuery createQuery(String collection) {
    return getMongoDao().createQuery().setCollection(collection);
  }

  public <T> DBObject unmap(T object) {
    return getObjectMapper().unmap(object);
  }

}

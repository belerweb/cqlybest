package com.cqlybest.common.mongo.dao;

import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.spring.MongoDBDaoSupport;
import com.mongodb.DBObject;

public class MongoDb extends MongoDBDaoSupport {

  public DaoQuery createQuery(String collection) {
    return getMongoDao().createQuery().setCollection(collection);
  }

  public <T> T createObject(String collection, T obj) {
    return getMongoDao().createObject(collection, obj);
  }

  public <T> T findById(String collection, Class<T> cls, String id) {
    return createQuery(collection).eq("_id", id).findObject(cls);
  }

  public DBObject findById(String collection, String id) {
    return createQuery(collection).eq("_id", id).findObject();
  }

  public <T> DBObject unmap(T object) {
    return getObjectMapper().unmap(object);
  }

}

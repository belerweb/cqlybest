package com.cqlybest.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.googlecode.mjorm.ObjectMapper;
import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.spring.MongoDBDaoSupport;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;

public class MongoDb extends MongoDBDaoSupport {

  public DaoQuery createQuery(String collection) {
    return getMongoDao().createQuery().setCollection(collection);
  }

  public <T> T createObject(String collection, T obj) {
    return getMongoDao().createObject(collection, obj);
  }

  public <T> T[] createObjects(String collection, T[] objects) {
    return getMongoDao().createObjects(collection, objects);
  }

  public void updateObject(String collection, Object id, Object o) {
    getMongoDao().updateObject(collection, id, o);
  }

  public WriteResult deleteObject(String collection, Object id) {
    return createQuery(collection).eq("_id", id).modify().delete();
  }

  public <T> T findById(String collection, Class<T> cls, String id) {
    return createQuery(collection).eq("_id", id).findObject(cls);
  }

  public DBObject findById(String collection, String id) {
    return createQuery(collection).eq("_id", id).findObject();
  }

  public DBObject findById(String collection, String id, Properties fields) {
    return createQuery(collection).eq("_id", id).findObject(unmap(fields));
  }

  public <T> DBObject unmap(T object) {
    return getObjectMapper().unmap(object);
  }

  public <T> T map(DBObject dbObject, Class<T> cls) {
    return getObjectMapper().map(dbObject, cls);
  }

  public <T> List<T> map(DBCursor dbCursor, Class<T> cls) {
    ObjectMapper objectMapper = getObjectMapper();
    List<T> result = new ArrayList<T>();
    while (dbCursor.hasNext()) {
      result.add(objectMapper.map(dbCursor.next(), cls));
    }
    return result;
  }


}

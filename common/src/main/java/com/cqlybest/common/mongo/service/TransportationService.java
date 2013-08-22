package com.cqlybest.common.mongo.service;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.mongo.bean.QueryResult;
import com.cqlybest.common.mongo.bean.Transportation;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.googlecode.mjorm.query.DaoQuery;

@Service
public class TransportationService {

  @Autowired
  private MongoDb mongoDb;

  public void updateTransportation(Transportation transportation) {
    String id = transportation.getId();
    if (StringUtils.isBlank(id)) {
      transportation.setId(UUID.randomUUID().toString());
      mongoDb.createObject("Transportation", transportation);
    } else {
      mongoDb.getMongoDao().updateObject("Transportation", id, transportation);
    }
  }

  public Transportation getTransportation(String id) {
    return mongoDb.findById("Transportation", Transportation.class, id);
  }

  public QueryResult<Transportation> queryTransportation(String lineType, int page, int pageSize) {
    QueryResult<Transportation> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDb.createQuery("Transportation");
    query.eq("lineType", lineType);
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Transportation.class).readAll());

    return result;
  }
}

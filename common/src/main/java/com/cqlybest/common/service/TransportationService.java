package com.cqlybest.common.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.bean.TransferComb;
import com.cqlybest.common.bean.Transportation;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;

@Service
public class TransportationService {

  @Autowired
  private MongoDao mongoDao;

  public void updateTransportation(Transportation transportation) {
    String id = transportation.getId();
    if (StringUtils.isBlank(id)) {
      transportation.setId(UUID.randomUUID().toString());
      mongoDao.createObject("Transportation", transportation);
    } else {
      mongoDao.updateObject("Transportation", id, transportation);
    }
  }

  public Transportation getTransportation(String id) {
    return mongoDao.findById("Transportation", Transportation.class, id);
  }

  public QueryResult<Transportation> queryTransportation(String lineType, int page, int pageSize) {
    QueryResult<Transportation> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("Transportation");
    query.eq("lineType", lineType);
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Transportation.class).readAll());

    return result;
  }

  public List<Transportation> queryTransportation(String lineType, String keyword, int pageSize) {
    DaoQuery query = mongoDao.createQuery("Transportation");
    query.eq("lineType", lineType);
    query.regex("number", ".*" + keyword + ".*");
    query.addSort("number", 1);
    query.setMaxDocuments(pageSize);
    return query.findObjects(Transportation.class).readAll();
  }

  public void updateTransferComb(TransferComb comb) {
    String id = comb.getId();
    if (StringUtils.isBlank(id)) {
      comb.setId(UUID.randomUUID().toString());
      mongoDao.createObject("TransferComb", comb);
    } else {
      mongoDao.updateObject("TransferComb", id, comb);
    }
  }

  public QueryResult<TransferComb> queryTransferComb(String lineType, int page, int pageSize) {
    QueryResult<TransferComb> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("TransferComb");
    query.eq("type", lineType);
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(TransferComb.class).readAll());

    return result;
  }

  public void deleteTransferComb(String id) {
    mongoDao.deleteObject("TransferComb", id);
  }

}

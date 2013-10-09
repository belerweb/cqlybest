package com.cqlybest.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.FriendlyLink;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;

@Service
public class FriendlyLinkService {

  @Autowired
  private MongoDao mongoDao;

  public void addLink(FriendlyLink link) {
    link.setId(UUID.randomUUID().toString());
    mongoDao.createObject("FriendlyLink", link);
  }

  public void updateLink(String linkId, String property, String value) {
    Object _value = value;
    if ("image".equals(property)) {
      if (StringUtils.isBlank(value)) {
        _value = null;
      } else {
        _value = mongoDao.findById("Image", value);
      }
    }
    if ("displayOrder".equals(property)) {
      _value = StringUtils.isBlank(value) ? null : Integer.valueOf(value);
    }

    mongoDao.createQuery("FriendlyLink").eq("_id", linkId).modify().set(property, _value).update();
  }

  public FriendlyLink getLink(String id) {
    return mongoDao.findById("FriendlyLink", FriendlyLink.class, id);
  }

  public QueryResult<FriendlyLink> queryLink(int page, int pageSize) {
    QueryResult<FriendlyLink> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("FriendlyLink");
    result.setTotal(query.countObjects());

    Map<String, Integer> sort = new HashMap<>();
    sort.put("displayOrder", 1);
    query.setSort(sort);
    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(FriendlyLink.class).readAll());

    return result;
  }

  public void deleteLink(String id) {
    mongoDao.deleteObject("FriendlyLink", id);
  }

}

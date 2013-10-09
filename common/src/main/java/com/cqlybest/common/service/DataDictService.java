package com.cqlybest.common.service;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.Cn2Spell;
import com.cqlybest.common.bean.DataDict;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.query.Query;

@Service
public class DataDictService {

  @Autowired
  private MongoDao mongoDao;

  public DataDict addDict(String type, String name) {
    DataDict dict = new DataDict();
    dict.setId(UUID.randomUUID().toString());
    dict.setType(type);
    dict.setName(name);
    dict.setPinyin(Cn2Spell.converterToSpell(dict.getName()));
    dict.setPy(Cn2Spell.converterToFirstSpell(dict.getName()));
    mongoDao.createObject("DataDict", dict);
    return dict;
  }

  public void deleteDict(String id) {
    mongoDao.deleteObject("DataDict", id);
  }

  public QueryResult<DataDict> queryDict(String type, String q, int page, int pageSize) {
    QueryResult<DataDict> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("DataDict");
    query.eq("type", type);
    if (StringUtils.isNotBlank(q)) {
      String pattern = ".*" + q + ".*";
      query.or(Query.start().regex("name", pattern), Query.start().regex("pinyin", pattern), Query
          .start().regex("py", pattern));
    }
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(DataDict.class).readAll());

    return result;
  }

  public void updateDict(String id, String property, Object value) {
    mongoDao.createQuery("DataDict").eq("_id", id).modify().set(property, value).update();
  }

}

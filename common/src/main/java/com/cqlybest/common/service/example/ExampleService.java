package com.cqlybest.common.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.bean.example.Company;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;

@Service
public class ExampleService {

  @Autowired
  private MongoDao mongoDao;

  public QueryResult<Company> queryCompany(int page, int pageSize) {
    QueryResult<Company> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("Company");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Company.class).readAll());

    return result;
  }

}

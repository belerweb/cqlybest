package com.cqlybest.common.service.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.bean.example.Case;
import com.cqlybest.common.bean.example.Company;
import com.cqlybest.common.dao.MongoDao;
import com.cqlybest.common.service.IdGenerator;
import com.googlecode.mjorm.query.DaoQuery;

@Service
public class ExampleService {

  @Autowired
  private MongoDao mongoDao;

  @Autowired
  private IdGenerator idGenerator;

  public QueryResult<Company> queryCompany(int page, int pageSize) {
    QueryResult<Company> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("ExampleCompany");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Company.class).readAll());

    return result;
  }

  public QueryResult<Case> queryCase(int page, int pageSize) {
    QueryResult<Case> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("ExampleCase");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Case.class).readAll());

    return result;
  }

  public void addCompany(Company company) {
    company.setId(idGenerator.getId().toString());
    mongoDao.createObject("ExampleCompany", company);
  }

  public void addCase(Case c) {
    c.setId(idGenerator.getId().toString());
    mongoDao.createObject("ExampleCase", c);
  }

  public void updateCompany(Company company) {
    mongoDao.updateObject("ExampleCompany", company.getId(), company);
  }

  public void updateCase(Case c) {
    mongoDao.updateObject("ExampleCase", c.getId(), c);
  }

  public Company getCompany(String id) {
    return mongoDao.findById("ExampleCompany", Company.class, id);
  }

  public Case getCase(String id) {
    return mongoDao.findById("ExampleCase", Case.class, id);
  }

  public void deleteCompany(String id) {
    mongoDao.deleteObject("ExampleCompany", id);
  }

  public void deleteCase(String id) {
    mongoDao.deleteObject("ExampleCase", id);
  }

}

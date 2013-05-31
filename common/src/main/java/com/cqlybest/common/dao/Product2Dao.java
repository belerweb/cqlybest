package com.cqlybest.common.dao;

import java.util.Date;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Product2;

@Repository
public class Product2Dao extends AbstractDao<Product2, String> {

  protected Product2Dao() {
    super(Product2.class);
  }

  public int update(String id, String name, Object value) {
    String hql = "UPDATE Product2 SET " + name + " = ?, lastUpdated = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, new Date());
    query.setParameter(2, id);
    return query.executeUpdate();
  }

}

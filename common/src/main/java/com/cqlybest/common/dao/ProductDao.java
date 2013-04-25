package com.cqlybest.common.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Product;

@Repository
public class ProductDao extends AbstractDao<Product, Integer> {

  protected ProductDao() {
    super(Product.class);
  }

  public Long findProductTotal() {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.setProjection(Projections.rowCount());
    return (Long) criteria.uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public List<Product> findProductTotal(int page, int pageSize) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    return criteria.list();
  }

}

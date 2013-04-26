package com.cqlybest.common.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
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

  public int togglePublished(Integer id, boolean published) {
    String hql = "UPDATE Product SET published = ?, lastUpdate = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, published);
    query.setParameter(1, new Date());
    query.setParameter(2, id);
    return query.executeUpdate();
  }

}

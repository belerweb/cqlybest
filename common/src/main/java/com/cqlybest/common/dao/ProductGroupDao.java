package com.cqlybest.common.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.ProductGroup;

@Repository
public class ProductGroupDao extends AbstractDao<ProductGroup, String> {

  protected ProductGroupDao() {
    super(ProductGroup.class);
  }

  public int togglePublished(String id, boolean published) {
    String hql = "UPDATE ProductGroup SET published = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, published);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

}

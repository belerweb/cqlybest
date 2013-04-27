package com.cqlybest.common.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Menu;

@Repository
public class MenuDao extends AbstractDao<Menu, String> {

  protected MenuDao() {
    super(Menu.class);
  }

  public Menu prev(Integer order) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.add(Restrictions.lt("displayOrder", order));
    criteria.addOrder(Order.desc("displayOrder"));
    criteria.setMaxResults(1);
    return (Menu) criteria.uniqueResult();
  }

  public Menu next(Integer order) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.add(Restrictions.gt("displayOrder", order));
    criteria.addOrder(Order.asc("displayOrder"));
    criteria.setMaxResults(1);
    return (Menu) criteria.uniqueResult();
  }

  public int togglePublished(String id, boolean published) {
    String hql = "UPDATE Menu SET published = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, published);
    query.setParameter(1, id);
    return query.executeUpdate();
  }
}

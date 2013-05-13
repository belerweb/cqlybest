package com.cqlybest.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.cqlybest.common.bean.NotMappedBean;

@SuppressWarnings("unchecked")
public abstract class BaseDao extends AbstractDao<NotMappedBean, NotMappedBean> {

  protected BaseDao() {
    super(NotMappedBean.class);
  }

  public <T> T find(Class<T> cls, Serializable id) {
    return (T) getCurrentSession().get(cls, id);
  }

  public void del(Object object) {
    getCurrentSession().delete(object);;
  }

  public <T> Long total(Class<T> cls) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.setProjection(Projections.rowCount());
    return (Long) criteria.uniqueResult();
  }

  public <T> List<T> findAll(Class<T> cls) {
    return getCurrentSession().createCriteria(cls).list();
  }

  public <T> List<T> find(Class<T> cls, Criterion criterion) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.add(criterion);
    return criteria.list();
  }

  public <T> List<T> find(Class<T> cls, Order order) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.addOrder(order);
    return criteria.list();
  }

  public <T> List<T> find(Class<T> cls, Criterion criterion, Order order) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.add(criterion);
    criteria.addOrder(order);
    return criteria.list();
  }

  public <T> List<T> find(Class<T> cls, Criterion criterion, int page, int pageSize) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.add(criterion);
    criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    return criteria.list();
  }

  public <T> List<T> find(Class<T> cls, Order order, int page, int pageSize) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.addOrder(order);
    criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    return criteria.list();
  }

}

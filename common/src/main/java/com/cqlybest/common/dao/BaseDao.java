package com.cqlybest.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;

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

  public <T> List<T> findAll(Class<T> cls) {
    return getCurrentSession().createCriteria(cls).list();
  }

  public <T> List<T> find(Class<T> cls, Criterion criterion) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    criteria.add(criterion);
    return criteria.list();
  }

}

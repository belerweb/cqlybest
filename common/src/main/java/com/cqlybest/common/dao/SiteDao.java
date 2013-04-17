package com.cqlybest.common.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Site;

@Repository
public class SiteDao extends AbstractDao<Site, Integer> {

  protected SiteDao() {
    super(Site.class);
  }

  public Site findLatestSite() {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.addOrder(Order.desc("lastUpdate"));
    criteria.setMaxResults(1);
    return (Site) criteria.uniqueResult();
  }

}
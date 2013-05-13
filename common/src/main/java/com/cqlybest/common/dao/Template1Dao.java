package com.cqlybest.common.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.template1.Template1Menu;

@Repository
public class Template1Dao extends BaseDao {

  public Template1Menu prevMenu(Integer order) {
    Criteria criteria = getCurrentSession().createCriteria(Template1Menu.class);
    criteria.add(Restrictions.lt("displayOrder", order));
    criteria.addOrder(Order.desc("displayOrder"));
    criteria.setMaxResults(1);
    return (Template1Menu) criteria.uniqueResult();
  }

  public Template1Menu nextMenu(Integer order) {
    Criteria criteria = getCurrentSession().createCriteria(Template1Menu.class);
    criteria.add(Restrictions.gt("displayOrder", order));
    criteria.addOrder(Order.asc("displayOrder"));
    criteria.setMaxResults(1);
    return (Template1Menu) criteria.uniqueResult();
  }

  public int toggleMenuPublished(String id, boolean published) {
    String hql = "UPDATE Template1Menu SET published = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, published);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

  public void updateProductGroupOrder(Integer id, Integer order) {
    String hql = "UPDATE Template1ProductGroup SET displayOrder = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, order);
    query.setParameter(1, id);
    query.executeUpdate();
  }

}

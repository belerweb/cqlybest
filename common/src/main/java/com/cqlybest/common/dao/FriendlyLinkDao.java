package com.cqlybest.common.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.FriendlyLink;

@Repository
public class FriendlyLinkDao extends AbstractDao<FriendlyLink, Integer> {

  protected FriendlyLinkDao() {
    super(FriendlyLink.class);
  }

  public int update(Integer id, String name, Object value) {
    String hql = "UPDATE FriendlyLink SET " + name + " = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

}

package com.cqlybest.common.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.WeiboAppAuth;

@Repository
@SuppressWarnings("unchecked")
public class UserDao extends AbstractDao<LoginUser, String> {

  protected UserDao() {
    super(LoginUser.class);
  }

  public Long findUserTotal(String role) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.setProjection(Projections.rowCount());
    criteria.createCriteria("roles").add(Restrictions.eq("role", role));
    return (Long) criteria.uniqueResult();
  }

  public List<LoginUser> findUser(String role, int page, int pageSize) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.createCriteria("roles").add(Restrictions.eq("role", role));
    criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    return criteria.list();
  }


  public int update(String id, String name, Object value) {
    String hql = "UPDATE LoginUser SET " + name + " = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

  public WeiboAppAuth find(String appId, String cid, String uid) {
    Criteria criteria = getCurrentSession().createCriteria(WeiboAppAuth.class);
    criteria.add(Restrictions.eq("appId", appId));
    if (StringUtils.isEmpty(cid)) {
      criteria.add(Restrictions.isNull("cid"));
    } else {
      criteria.add(Restrictions.eq("cid", cid));
    }
    criteria.add(Restrictions.eq("uid", uid));
    return (WeiboAppAuth) criteria.uniqueResult();
  }

}

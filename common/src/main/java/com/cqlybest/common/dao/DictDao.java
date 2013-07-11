package com.cqlybest.common.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Dict;

@Repository
@SuppressWarnings("unchecked")
public class DictDao extends AbstractDao<Dict, Integer> {

  protected DictDao() {
    super(Dict.class);
  }

  public List<Dict> findAllDict(String type) {
    return getCurrentSession().createCriteria(entityClass).add(Restrictions.eq("type", type))
        .addOrder(Order.asc("name")).list();
  }

  public List<Dict> findDict(String type, String keyword) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.add(Restrictions.eq("type", type));
    if (StringUtils.isNotEmpty(keyword)) {
      Disjunction or = Restrictions.disjunction();
      or.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
      or.add(Restrictions.like("pinyin", keyword, MatchMode.ANYWHERE));
      or.add(Restrictions.like("py", keyword, MatchMode.ANYWHERE));
      criteria.add(or);
    }
    return criteria.list();
  }

  public int update(Integer id, String name, Object value) {
    String hql = "UPDATE Dict SET " + name + " = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

}

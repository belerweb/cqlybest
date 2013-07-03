package com.cqlybest.common.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Dict;

@Repository
@SuppressWarnings("unchecked")
public class DictDao extends AbstractDao<Dict, Integer> {

  protected DictDao() {
    super(Dict.class);
  }

  public <T extends Dict> List<T> findAllDict(Class<T> cls) {
    return getCurrentSession().createCriteria(cls).list();
  }

  public <T extends Dict> List<T> findDict(Class<T> cls, String keyword) {
    Criteria criteria = getCurrentSession().createCriteria(cls);
    if (StringUtils.isNotEmpty(keyword)) {
      Disjunction or = Restrictions.disjunction();
      or.add(Restrictions.like("name", keyword, MatchMode.ANYWHERE));
      or.add(Restrictions.like("pinyin", keyword, MatchMode.ANYWHERE));
      or.add(Restrictions.like("py", keyword, MatchMode.ANYWHERE));
      criteria.add(or);
    }
    return criteria.list();
  }

}

package com.cqlybest.common.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Dict;

@Repository
public class DictDao extends AbstractDao<Dict, Integer> {

  protected DictDao() {
    super(Dict.class);
  }

  @SuppressWarnings("unchecked")
  public <T extends Dict> List<T> findAllDict(Class<T> cls) {
    return getCurrentSession().createCriteria(cls).list();
  }

}

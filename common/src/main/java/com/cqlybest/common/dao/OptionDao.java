package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Option;

@Repository
public class OptionDao extends AbstractDao<Option, String> {

  protected OptionDao() {
    super(Option.class);
  }

}

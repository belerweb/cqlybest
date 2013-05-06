package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Sms;

@Repository
public class SmsDao extends AbstractDao<Sms, Integer> {

  protected SmsDao() {
    super(Sms.class);
  }

}

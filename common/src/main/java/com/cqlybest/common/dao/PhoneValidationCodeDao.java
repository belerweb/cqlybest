package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.PhoneValidationCode;

@Repository
public class PhoneValidationCodeDao extends AbstractDao<PhoneValidationCode, String> {

  protected PhoneValidationCodeDao() {
    super(PhoneValidationCode.class);
  }

}

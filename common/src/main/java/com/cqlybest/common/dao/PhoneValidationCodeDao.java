package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

@Repository
public class PhoneValidationCodeDao extends AbstractDao<PhoneValidationCodeDao, String> {

  protected PhoneValidationCodeDao() {
    super(PhoneValidationCodeDao.class);
  }

}

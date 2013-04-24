package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Account;

@Repository
public class AccountDao extends AbstractDao<Account, Integer> {

  protected AccountDao() {
    super(Account.class);
  }

}

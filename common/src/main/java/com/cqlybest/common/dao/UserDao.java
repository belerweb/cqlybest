package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.LoginUser;

@Repository
public class UserDao extends AbstractDao<LoginUser, String> {

  protected UserDao() {
    super(LoginUser.class);
  }

}

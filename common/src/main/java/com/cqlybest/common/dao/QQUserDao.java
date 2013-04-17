package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.QQUser;

@Repository
public class QQUserDao extends AbstractDao<QQUser, String> {

  protected QQUserDao() {
    super(QQUser.class);
  }

}

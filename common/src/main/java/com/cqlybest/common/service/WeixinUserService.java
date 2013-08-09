package com.cqlybest.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.belerweb.weixin.mp.WeixinUser;
import com.cqlybest.common.dao.WeixinUserDao;

@Service
public class WeixinUserService {

  @Autowired
  private WeixinUserDao weixinUserDao;

  @Transactional
  public void save(WeixinUser user) {
    weixinUserDao.saveOrUpdate(user);
  }

  public WeixinUser get(String fakeid) {
    return weixinUserDao.findById(fakeid);
  }

}

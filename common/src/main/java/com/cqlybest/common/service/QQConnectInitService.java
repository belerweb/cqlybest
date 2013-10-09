package com.cqlybest.common.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qq.connect.utils.QQConnectConfig;

@Service
public class QQConnectInitService implements InitializingBean {

  @Autowired
  private CentralConfig centralConfig;

  public static final String APP_ID = "app_ID";
  public static final String APP_KEY = "app_KEY";
  public static final String REDIRECT_URI = "redirect_URI";
  public static final String SCOPE = "scope";

  @Override
  public void afterPropertiesSet() throws Exception {
    String appId = centralConfig.get(CentralConfig.QQ_APP_ID);
    String appKey = centralConfig.get(CentralConfig.QQ_APP_KEY);
    QQConnectConfig.updateProperties(APP_ID, appId);
    QQConnectConfig.updateProperties(APP_KEY, appKey);
  }


}

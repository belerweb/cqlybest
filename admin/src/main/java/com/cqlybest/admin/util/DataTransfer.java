package com.cqlybest.admin.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataTransfer implements InitializingBean {

  @Autowired
  private DataTransferService dataTransferService;


  @Override
  public void afterPropertiesSet() throws Exception {
    if (!Boolean.valueOf(System.getProperty("cqlybest.data.transfer"))) {
      return;
    }
    dataTransferService.transfer();
  }

}

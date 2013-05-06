package com.cqlybest.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.Sms;
import com.cqlybest.common.dao.SmsDao;

@Service
public class SmsService {

  private com.belerweb.sms._9nuo.Sms SMS = com.belerweb.sms._9nuo.Sms.init();

  @Autowired
  private SmsDao smsDao;

  public void send(String phone, String content) {
    Sms sms = new Sms();
    sms.setPhone(phone);
    sms.setContent(content);
    sms.setSuccess(SMS.send(phone, content).isSuccess());
    smsDao.saveOrUpdate(sms);
  }

}

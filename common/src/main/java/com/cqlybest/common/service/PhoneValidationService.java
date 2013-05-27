package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.PhoneValidationCode;
import com.cqlybest.common.dao.PhoneValidationCodeDao;

@Service
public class PhoneValidationService {

  @Autowired
  private PhoneValidationCodeDao phoneValidationCodeDao;

  @Transactional
  public void save(PhoneValidationCode code) {
    phoneValidationCodeDao.saveOrUpdate(code);
  }

  public boolean checkSendAvailable(String phone) {
    List<PhoneValidationCode> codes =
        phoneValidationCodeDao.find(Restrictions.eq("phone", phone), Order.desc("createdTime"), 1,
            1);
    return codes.isEmpty()
        || System.currentTimeMillis() - codes.get(0).getCreatedTime().getTime() > 60000;
  }
}

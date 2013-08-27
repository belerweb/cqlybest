package com.cqlybest.admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.cqlybest.common.mongo.dao.MongoDb;
import com.cqlybest.common.mongo.service.SettingsService;

@Component
public class Upgrade implements InitializingBean {

  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private SettingsService settingsService;
  @Autowired
  private MongoDb mongoDb;

  @Override
  public void afterPropertiesSet() throws Exception {
    if (!"true".equals(System.getProperty("upgrade"))) {
      return;
    }
    Session session = sessionFactory.openSession();
    SessionHolder sessionHolder = new SessionHolder(session);
    TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
    settingsService.updateSettings("cache.enabled", true);
    // TODO
    session.close();
  }


}

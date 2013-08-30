package com.cqlybest.admin;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.Version;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.cqlybest.common.mongo.service.SettingsService;

@Component
public class Upgrade implements InitializingBean {

  @Autowired
  Version version;
  @Autowired
  private SettingsService settingsService;
  @Autowired
  private MongoDb mongoDb;

  @Override
  public void afterPropertiesSet() throws Exception {
    upgradeVersion();
  }

  private void upgradeVersion() {
    settingsService.updateSettings("version.name", version.getName());
    settingsService.updateSettings("version.buildTime", version.getBuildTime());
  }

}

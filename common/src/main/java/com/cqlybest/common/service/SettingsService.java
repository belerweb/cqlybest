package com.cqlybest.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.dao.MongoDao;

@Service
public class SettingsService {

  private static Map<?, ?> cacheSettings = null;

  @Autowired
  private MongoDao mongoDao;

  public Map<?, ?> getSettings() {
    if (cacheSettings == null) {
      Map<?, ?> settings = mongoDao.createQuery("Settings").findObject(Map.class);
      if (settings == null) {
        settings = mongoDao.createObject("Settings", new HashMap<>());
      }
      cacheSettings = settings;
    }

    return cacheSettings;
  }

  public void updateSettings(String property, Object value) {
    mongoDao.createQuery("Settings").modify().set(property, value).update();
    cacheSettings = null;
  }

}

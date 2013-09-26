package com.cqlybest.common.mongo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.mongo.dao.MongoDb;

@Service
public class SettingsService {

  private static Map<?, ?> cacheSettings = null;

  @Autowired
  private MongoDb mongoDb;

  public Map<?, ?> getSettings() {
    if (cacheSettings == null) {
      Map<?, ?> settings = mongoDb.createQuery("Settings").findObject(Map.class);
      if (settings == null) {
        settings = mongoDb.createObject("Settings", new HashMap<>());
      }
      cacheSettings = settings;
    }

    return cacheSettings;
  }

  public void updateSettings(String property, Object value) {
    mongoDb.createQuery("Settings").modify().set(property, mongoDb.unmap(value)).update();
    cacheSettings = null;
  }

}

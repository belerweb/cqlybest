package com.cqlybest.common.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Option;
import com.cqlybest.common.dao.OptionDao;

@Service
public class OptionService {

  @Autowired
  private OptionDao optionDao;

  private Map<String, String> cachedOptions;
  private long lastCachedTime;

  public Map<String, String> getOptions() {
    if (cachedOptions == null || System.currentTimeMillis() - lastCachedTime > 0) {
      cachedOptions = new HashMap<>();
      for (Option option : optionDao.findAll()) {
        cachedOptions.put(option.getName(), option.getValue());
      }
    }

    String release = System.getProperty("cqlybest.release", System.getenv("cqlybest.release"));
    if (release != null) {
      cachedOptions.put("release", release);
    }

    return cachedOptions;
  }

  @Transactional
  public void setOption(String name, String value) {
    optionDao.saveOrUpdate(new Option(name, value));
    cachedOptions = null;
  }

}

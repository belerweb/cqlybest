package com.cqlybest.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.Cn2Spell;
import com.cqlybest.common.bean.Dict;
import com.cqlybest.common.dao.DictDao;

@Service
public class DictService {

  private static final Map<String, List<? extends Dict>> DICT_CACHE = new HashMap<>();
  private static final Map<String, Long> DICT_CACHE_TIME = new HashMap<>();

  @Autowired
  private DictDao dictDao;

  @Transactional
  public void addDict(Dict dict) {
    dict.setPinyin(Cn2Spell.converterToSpell(dict.getName()));
    dict.setPy(Cn2Spell.converterToFirstSpell(dict.getName()));
    dictDao.saveOrUpdate(dict);
  }

  @Transactional
  public void deleteDict(Dict dict) {
    Dict _dict = dictDao.findById(dict.getClass(), dict.getId());
    if (_dict != null) {
      dictDao.delete(_dict);
    }
  }

  public List<? extends Dict> getDict(Class<? extends Dict> cls) {
    String name = cls.getSimpleName();
    List<? extends Dict> result = DICT_CACHE.get(name);
    if (result == null || (System.currentTimeMillis() - DICT_CACHE_TIME.get(name)) > 3600000) {
      result = dictDao.findAllDict(cls);
      DICT_CACHE.put(name, result);
      DICT_CACHE_TIME.put(name, System.currentTimeMillis());
    }
    return result;
  }

}

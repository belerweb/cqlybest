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

  private static final Map<String, List<Dict>> DICT_CACHE = new HashMap<>();
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
  public void delete(Integer id) {
    Dict dict = dictDao.findById(id);
    if (dict != null) {
      dictDao.delete(dict);
    }
  }

  public List<Dict> getDict(String type) {
    List<Dict> result = DICT_CACHE.get(type);
    if (result == null || (System.currentTimeMillis() - DICT_CACHE_TIME.get(type)) > 0) {
      result = dictDao.findAllDict(type);
      DICT_CACHE.put(type, result);
      DICT_CACHE_TIME.put(type, System.currentTimeMillis());
    }
    return result;
  }

  public List<Dict> getDicts() {
    return dictDao.findAll();
  }

  public List<Dict> getDict(String type, String keyword) {
    return dictDao.findDict(type, keyword);
  }

  @Transactional
  public void update(Integer id, String name, String value) {
    dictDao.update(id, name, value);
  }

}

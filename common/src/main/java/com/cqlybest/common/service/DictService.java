package com.cqlybest.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.Cn2Spell;
import com.cqlybest.common.bean.Dict;
import com.cqlybest.common.dao.DictDao;

@Service
@Transactional(readOnly = true)
public class DictService {

  @Autowired
  private DictDao dictDao;

  @Transactional(readOnly = false)
  public void addDict(Dict dict) {
    dict.setPinyin(Cn2Spell.converterToSpell(dict.getName()));
    dict.setPy(Cn2Spell.converterToFirstSpell(dict.getName()));
    dictDao.saveOrUpdate(dict);
  }

  @Transactional(readOnly = false)
  public void deleteDict(Dict dict) {
    dictDao.delete(dict);
  }

  public <T extends Dict> List<T> getDict(Class<T> cls) {
    return dictDao.findAllDict(cls);
  }

}
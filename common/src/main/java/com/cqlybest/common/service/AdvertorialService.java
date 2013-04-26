package com.cqlybest.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Advertorial;
import com.cqlybest.common.dao.AdvertorialDao;

@Service
public class AdvertorialService {

  @Autowired
  private AdvertorialDao advertorialDao;

  /**
   * 增加软文
   * 
   * @param advertorial
   */
  @Transactional
  public void add(Advertorial advertorial) {
    advertorialDao.saveOrUpdate(advertorial);
  }

  /**
   * 删除软文
   * 
   * @param ccount
   */
  @Transactional
  public void delete(Integer id) {
    Advertorial advertorial = advertorialDao.findById(id);
    if (advertorial != null) {
      advertorialDao.delete(advertorial);
    }
  }

  /**
   * 获取所有软文
   * 
   * @return
   */
  public List<Advertorial> getAdvertorials() {
    return advertorialDao.findAll();
  }

  /**
   * 获取软文
   * 
   * @return
   */
  public Advertorial getAdvertorial(Integer id) {
    return advertorialDao.findById(id);
  }

  /**
   * 修改软文
   * 
   * @param advertorial
   */
  @Transactional
  public void modify(Advertorial advertorial) {
    advertorialDao.saveOrUpdate(advertorial);
  }


}

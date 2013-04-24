package com.cqlybest.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Advertorial;
import com.cqlybest.common.dao.AdvertorialDao;

@Service
@Transactional(readOnly = true)
public class AdvertorialService {

  @Autowired
  private AdvertorialDao advertorialDao;

  /**
   * 增加软文
   * 
   * @param advertorial
   */
  @Transactional(readOnly = false)
  public void add(Advertorial advertorial) {
    advertorialDao.saveOrUpdate(advertorial);
  }

  /**
   * 删除软文
   * 
   * @param ccount
   */
  @Transactional(readOnly = false)
  public void delete(Advertorial advertorial) {
    advertorialDao.delete(advertorial);
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
  @Transactional(readOnly = false)
  public void modify(Advertorial advertorial) {
    advertorialDao.saveOrUpdate(advertorial);
  }


}

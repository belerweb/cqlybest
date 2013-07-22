package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.FriendlyLink;
import com.cqlybest.common.dao.FriendlyLinkDao;
import com.cqlybest.common.dao.ImageDao;

@Service
public class FriendlyLinkService {

  @Autowired
  private FriendlyLinkDao friendlyLinkDao;
  @Autowired
  private ImageDao imageDao;

  @Transactional
  public void add(FriendlyLink link) {
    friendlyLinkDao.saveOrUpdate(link);
  }

  public FriendlyLink get(Integer id) {
    return friendlyLinkDao.findById(id);
  }

  @Transactional
  public void update(Integer pk, String name, Object value) {
    if (name.equals("image")) {
      FriendlyLink link = friendlyLinkDao.findById(pk);
      if (link.getImageId() != null) {
        imageDao.delete(link.getImageId());
      }
      link.setImage((String) value);
      link.setImageId(((String) value).split("\\.")[0]);
      friendlyLinkDao.saveOrUpdate(link);
    } else {
      friendlyLinkDao.update(pk, name, value);
    }
  }

  public Long total() {
    return friendlyLinkDao.total();
  }

  public List<FriendlyLink> list(int page, int pageSize) {
    return friendlyLinkDao.find(Order.asc("displayOrder"), page, pageSize);
  }

  @Transactional
  public void delete(Integer[] ids) {
    for (Integer id : ids) {
      FriendlyLink link = friendlyLinkDao.findById(id);
      friendlyLinkDao.delete(link);
      if (link.getImageId() != null) {
        imageDao.delete(link.getImageId());
      }
    }
  }

}

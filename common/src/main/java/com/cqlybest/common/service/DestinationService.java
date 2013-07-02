package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Destination;
import com.cqlybest.common.bean.TreeNode;
import com.cqlybest.common.dao.DestinationDao;

@Service
public class DestinationService {

  private static List<Destination> DESTINATION_CACHE = null;
  private static long DESTINATION_CACHE_TIME = 0;

  @Autowired
  private DestinationDao destinationDao;

  @Transactional
  public void add(Destination node) {
    Integer maxRgt = destinationDao.max("rgt");
    if (maxRgt == null) {
      maxRgt = 0;
    }
    node.setLft(maxRgt + 1);
    node.setRgt(maxRgt + 2);
    node.setPid(0);
    destinationDao.saveOrUpdate(node);
  }

  @Transactional
  public void add(Integer pid, Destination node) {
    TreeNode parent = destinationDao.findById(pid);
    Integer parentRgt = parent.getRgt();
    destinationDao.updateLft(parentRgt, 2);
    destinationDao.updateRgt(parentRgt - 1, 2);
    node.setLft(parentRgt);
    node.setRgt(parentRgt + 1);
    node.setPid(pid);
    destinationDao.saveOrUpdate(node);
  }

  @Transactional
  public void delete(Integer id) {
    TreeNode node = destinationDao.findById(id);
    if (node != null) {
      Integer rgt = node.getRgt();
      int increment = -(rgt - node.getLft() + 1);
      destinationDao.delete(node);
      destinationDao.updateLft(rgt, increment);
      destinationDao.updateRgt(rgt, increment);
    }
  }

  public List<Destination> getTree() {
    if (DESTINATION_CACHE == null || (System.currentTimeMillis() - DESTINATION_CACHE_TIME > 0)) {
      DESTINATION_CACHE = destinationDao.find(Order.asc("lft"));
      DESTINATION_CACHE_TIME = System.currentTimeMillis();
    }
    return DESTINATION_CACHE;
  }

}

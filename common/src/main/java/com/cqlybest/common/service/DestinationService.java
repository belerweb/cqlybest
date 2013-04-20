package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.DestinaionDao;
import com.cqlybest.common.bean.Destination;
import com.cqlybest.common.dao.DestinationDao;

@Service
@Transactional(readOnly = true)
public class DestinationService {

  @Autowired
  private DestinationDao destinationDao;

  @Transactional(readOnly = false)
  public void add(Destination node) {
    Integer maxRgt = destinationDao.max("rgt");
    if (maxRgt == null) {
      maxRgt = 0;
    }
    node.setLft(maxRgt + 1);
    node.setRgt(maxRgt + 2);
    destinationDao.saveOrUpdate(node);
  }

  @Transactional(readOnly = false)
  public void add(Integer pid, Destination node) {
    DestinaionDao parent = destinationDao.findById(pid);
    Integer parentRgt = parent.getRgt();
    destinationDao.updateLft(parentRgt, 2);
    destinationDao.updateRgt(parentRgt - 1, 2);
    node.setLft(parentRgt);
    node.setRgt(parentRgt + 1);
    destinationDao.saveOrUpdate(node);
  }

  @Transactional(readOnly = false)
  public void delete(Integer id) {
    DestinaionDao node = destinationDao.findById(id);
    Integer rgt = node.getRgt();
    int increment = -(rgt - node.getLft() + 1);
    destinationDao.delete(node);
    destinationDao.updateLft(rgt, increment);
    destinationDao.updateRgt(rgt, increment);
  }

  public List<Destination> getTree() {
    return destinationDao.find(Order.asc("lft"));
  }

}

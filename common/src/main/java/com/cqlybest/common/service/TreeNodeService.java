package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.TreeNode;
import com.cqlybest.common.dao.TreeNodeDao;

@Service
@Transactional(readOnly = true)
public class TreeNodeService {

  @Autowired
  private TreeNodeDao treeNodeDao;

  /**
   * 添加一级节点
   */
  @Transactional(readOnly = false)
  public void add(TreeNode node) {
    Integer maxRgt = treeNodeDao.max("rgt");
    if (maxRgt == null) {
      maxRgt = 0;
    }
    node.setLft(maxRgt + 1);
    node.setRgt(maxRgt + 2);
    treeNodeDao.saveOrUpdate(node);
  }

  @Transactional(readOnly = false)
  public void add(Integer pid, TreeNode node) {
    TreeNode parent = treeNodeDao.findById(pid);
    Integer parentRgt = parent.getRgt();
    treeNodeDao.updateLft(parentRgt, 2);
    treeNodeDao.updateRgt(parentRgt - 1, 2);
    node.setLft(parentRgt);
    node.setRgt(parentRgt + 1);
    treeNodeDao.saveOrUpdate(node);
  }

  @Transactional(readOnly = false)
  public void delete(Integer id) {
    TreeNode node = treeNodeDao.findById(id);
    Integer rgt = node.getRgt();
    int increment = -(rgt - node.getLft() + 1);
    treeNodeDao.delete(node);
    treeNodeDao.updateLft(rgt, increment);
    treeNodeDao.updateRgt(rgt, increment);
  }

  public List<TreeNode> getTree() {
    return treeNodeDao.find(Order.asc("lft"));
  }

}

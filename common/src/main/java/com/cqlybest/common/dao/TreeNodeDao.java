package com.cqlybest.common.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.TreeNode;

@Repository
public class TreeNodeDao extends AbstractDao<TreeNode, Integer> {

  protected TreeNodeDao() {
    super(TreeNode.class);
  }


  /**
   * 左值大于 number 的增加 increment
   * 
   * @param number
   * @param increment
   */
  public void updateLft(int number, int increment) {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("number", number);
    param.put("increment", increment);
    getSqlSession().update("TreeNodeDao.updateLft", param);;
  }


  /**
   * 右值大于 number 的增加 increment
   * 
   * @param number
   * @param increment
   */
  public void updateRgt(int number, int increment) {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("number", number);
    param.put("increment", increment);
    getSqlSession().update("TreeNodeDao.updateRgt", param);;
  }

  public void delete(TreeNode treeNode) {
    getSqlSession().delete("TreeNodeDao.delete", treeNode);
  }

}

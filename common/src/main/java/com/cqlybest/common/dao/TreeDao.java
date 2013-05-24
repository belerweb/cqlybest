package com.cqlybest.common.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.cqlybest.common.bean.TreeNode;

public abstract class TreeDao<T extends TreeNode, I extends Serializable> extends AbstractDao<T, I> {

  private String tableName;

  protected TreeDao(Class<T> entityClass, String tableName) {
    super(entityClass);
    this.tableName = tableName;
  }

  /**
   * 左值大于 number 的增加 increment
   * 
   * @param number
   * @param increment
   */
  public void updateLft(int number, int increment) {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("table", tableName);
    param.put("number", number);
    param.put("increment", increment);
    getSqlSession().update("TreeNodeDao.updateLft", param);
  }


  /**
   * 右值大于 number 的增加 increment
   * 
   * @param number
   * @param increment
   */
  public void updateRgt(int number, int increment) {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("table", tableName);
    param.put("number", number);
    param.put("increment", increment);
    getSqlSession().update("TreeNodeDao.updateRgt", param);
  }

  /**
   * 删除一个节点及其所有子节点
   * 
   * @param treeNode
   */
  public void delete(TreeNode treeNode) {
    Map<String, Object> param = new HashMap<String, Object>();
    param.put("table", tableName);
    param.put("lft", treeNode.getLft());
    param.put("rgt", treeNode.getRgt());
    getSqlSession().delete("TreeNodeDao.delete", param);
  }

}

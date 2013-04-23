package com.cqlybest.common.bean;

/**
 * 树结构节点
 * 
 * @author jun
 * 
 */
public class TreeNode {

  private Integer id;
  private String name;
  private String pinyin;
  private String py;
  private Integer lft;
  private Integer rgt;
  private Integer pid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public String getPy() {
    return py;
  }

  public void setPy(String py) {
    this.py = py;
  }

  public Integer getLft() {
    return lft;
  }

  public void setLft(Integer lft) {
    this.lft = lft;
  }

  public Integer getRgt() {
    return rgt;
  }

  public void setRgt(Integer rgt) {
    this.rgt = rgt;
  }

  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
  }

}

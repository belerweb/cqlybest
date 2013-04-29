package com.cqlybest.common.bean;

/**
 * 产品聚合元素
 */
public class ProductGroupItem {

  private Integer id;
  private Integer groupType;// 聚合类型
  private String groupValue;// 聚合条件值
  private String pgid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getGroupType() {
    return groupType;
  }

  public void setGroupType(Integer groupType) {
    this.groupType = groupType;
  }

  public String getGroupValue() {
    return groupValue;
  }

  public void setGroupValue(String groupValue) {
    this.groupValue = groupValue;
  }

  public String getPgid() {
    return pgid;
  }

  public void setPgid(String pgid) {
    this.pgid = pgid;
  }

}

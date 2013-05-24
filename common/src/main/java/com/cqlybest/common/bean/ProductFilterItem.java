package com.cqlybest.common.bean;

/**
 * 产品过滤条件
 */
public class ProductFilterItem {

  private Integer type;// 类型
  private Integer id;// 值ID

  public ProductFilterItem(Integer type, Integer id) {
    this.type = type;
    this.id = id;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


}

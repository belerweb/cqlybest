package com.cqlybest.common.bean;

/**
 * 产品聚合过滤条件
 */
public class ProductGroupFilterItem {

  private Integer id;
  private Integer filterType;// 过滤类型
  private String filterValue;// 过滤值
  private String pgid;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getFilterType() {
    return filterType;
  }

  public void setFilterType(Integer filterType) {
    this.filterType = filterType;
  }

  public String getFilterValue() {
    return filterValue;
  }

  public void setFilterValue(String filterValue) {
    this.filterValue = filterValue;
  }

  public String getPgid() {
    return pgid;
  }

  public void setPgid(String pgid) {
    this.pgid = pgid;
  }

}

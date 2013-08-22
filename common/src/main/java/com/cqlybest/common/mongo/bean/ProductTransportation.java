package com.cqlybest.common.mongo.bean;

/**
 * 产品详细交通
 */
public class ProductTransportation {

  private String id;
  private String name;
  private String extra;
  private Transportation detail;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public Transportation getDetail() {
    return detail;
  }

  public void setDetail(Transportation detail) {
    this.detail = detail;
  }

}

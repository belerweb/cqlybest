package com.cqlybest.common.mongo.bean;

/**
 * 简要行程，如3天，3沙
 */
public class ProductBriefTrip {

  private Integer num;
  private String unit;

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    this.unit = unit;
  }

  @Override
  public String toString() {
    return num + unit;
  }

}

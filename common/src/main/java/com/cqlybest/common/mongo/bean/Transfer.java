package com.cqlybest.common.mongo.bean;

/**
 * 交通
 */
public class Transfer {

  private String name;
  private Integer day;
  private Transportation trans;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public Transportation getTrans() {
    return trans;
  }

  public void setTrans(Transportation trans) {
    this.trans = trans;
  }
}

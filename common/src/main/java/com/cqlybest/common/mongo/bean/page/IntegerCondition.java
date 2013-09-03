package com.cqlybest.common.mongo.bean.page;

public class IntegerCondition {

  private Integer type;
  private Integer value;

  public IntegerCondition() {}

  public IntegerCondition(Integer type, Integer value) {
    this.type = type;
    this.value = value;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

}

package com.cqlybest.common.mongo.bean.page;

public class BooleanCondition {

  private Integer type;
  private Boolean value;

  public BooleanCondition() {}

  public BooleanCondition(Integer type, Boolean value) {
    this.type = type;
    this.value = value;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Boolean getValue() {
    return value;
  }

  public void setValue(Boolean value) {
    this.value = value;
  }

}

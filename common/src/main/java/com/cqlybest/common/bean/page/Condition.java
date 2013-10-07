package com.cqlybest.common.bean.page;

public class Condition {

  public static final int CONDITION_TYPE_EQ = 0; // 相等
  public static final int CONDITION_TYPE_IN = 1; // 包含
  public static final int CONDITION_TYPE_GT = 2; // 大于
  public static final int CONDITION_TYPE_LT = 3; // 小于

  public Condition() {}

  public Condition(Integer type, String value) {
    this.type = type;
    this.value = value;
  }

  private Integer type;
  private String value;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }



}

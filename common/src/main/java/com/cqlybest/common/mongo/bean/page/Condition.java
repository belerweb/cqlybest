package com.cqlybest.common.mongo.bean.page;

public class Condition {

  public static final int CONDITION_TYPE_EQ = 0; // 相等
  public static final int CONDITION_TYPE_IN = 1; // 包含
  public static final int CONDITION_TYPE_GT = 2; // 大于
  public static final int CONDITION_TYPE_LT = 3; // 小于

  public static final int VALUE_TYPE_STRING = 0; // 字符串
  public static final int VALUE_TYPE_INTEGER = 1; // 整数

  private Integer conditionType;
  private Integer valueType;
  private String value;

  public Integer getConditionType() {
    return conditionType;
  }

  public void setConditionType(Integer conditionType) {
    this.conditionType = conditionType;
  }

  public Integer getValueType() {
    return valueType;
  }

  public void setValueType(Integer valueType) {
    this.valueType = valueType;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

}

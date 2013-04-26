package com.cqlybest.common.bean;

/**
 * 产品聚合
 */
public class ProductGroup {

  private String id;
  private String name;// 聚合名称，如出境参团、邮轮旅游度假
  private Integer groupType1;// 聚合类型1
  private String groupValue1;// 聚合条件值1
  private Integer groupType2;// 聚合类型2
  private String groupValue2;// 聚合条件值2
  private Integer groupType3;// 聚合类型3
  private String groupValue3;// 聚合条件值3
  private Integer groupType4;// 聚合类型4
  private String groupValue4;// 聚合条件值4
  private Integer groupType5;// 聚合类型5
  private String groupValue5;// 聚合条件值5
  private boolean published;// 是否发布

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

  public Integer getGroupType1() {
    return groupType1;
  }

  public void setGroupType1(Integer groupType1) {
    this.groupType1 = groupType1;
  }

  public String getGroupValue1() {
    return groupValue1;
  }

  public void setGroupValue1(String groupValue1) {
    this.groupValue1 = groupValue1;
  }

  public Integer getGroupType2() {
    return groupType2;
  }

  public void setGroupType2(Integer groupType2) {
    this.groupType2 = groupType2;
  }

  public String getGroupValue2() {
    return groupValue2;
  }

  public void setGroupValue2(String groupValue2) {
    this.groupValue2 = groupValue2;
  }

  public Integer getGroupType3() {
    return groupType3;
  }

  public void setGroupType3(Integer groupType3) {
    this.groupType3 = groupType3;
  }

  public String getGroupValue3() {
    return groupValue3;
  }

  public void setGroupValue3(String groupValue3) {
    this.groupValue3 = groupValue3;
  }

  public Integer getGroupType4() {
    return groupType4;
  }

  public void setGroupType4(Integer groupType4) {
    this.groupType4 = groupType4;
  }

  public String getGroupValue4() {
    return groupValue4;
  }

  public void setGroupValue4(String groupValue4) {
    this.groupValue4 = groupValue4;
  }

  public Integer getGroupType5() {
    return groupType5;
  }

  public void setGroupType5(Integer groupType5) {
    this.groupType5 = groupType5;
  }

  public String getGroupValue5() {
    return groupValue5;
  }

  public void setGroupValue5(String groupValue5) {
    this.groupValue5 = groupValue5;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

}

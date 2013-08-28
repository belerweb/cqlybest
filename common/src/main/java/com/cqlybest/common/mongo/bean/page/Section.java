package com.cqlybest.common.mongo.bean.page;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面的一部分
 */
public class Section {

  public static final String TYPE_CODE = "code";
  public static final String TYPE_IMG = "img";
  public static final String TYPE_PRODUCT = "product";
  public static final String TYPE_MALDIVES = "maldives";

  private String id;
  private String name;
  private String type;// 类型
  private Margin margin = new Margin(); // 外边距
  private Padding padding = new Padding();// 内边距

  // TYPE: CODE
  private String code;// 代码

  // TYPE: IMAGE
  private Image img = new Image();// 图片

  private List<Condition> conditions = new ArrayList<>();
  private Integer number;// 结果集数量

  private Boolean more = Boolean.FALSE;// 是否显示更多

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Margin getMargin() {
    return margin;
  }

  public void setMargin(Margin margin) {
    this.margin = margin;
  }

  public Padding getPadding() {
    return padding;
  }

  public void setPadding(Padding padding) {
    this.padding = padding;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Image getImg() {
    return img;
  }

  public void setImg(Image img) {
    this.img = img;
  }

  public List<Condition> getConditions() {
    return conditions;
  }

  public void setConditions(List<Condition> conditions) {
    this.conditions = conditions;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Boolean getMore() {
    return more;
  }

  public void setMore(Boolean more) {
    this.more = more;
  }

}

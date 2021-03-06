package com.cqlybest.common.bean.page;

import com.cqlybest.common.bean.QueryResult;


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

  // TYPE: PRODUCT
  private ProductCondition pc; // 产品查询条件

  // TYPE: MALDIVES
  private MaldivesIslandCondition mdc; // 马儿代夫查询条件

  private QueryResult<?> queryResult;

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

  public MaldivesIslandCondition getMdc() {
    return mdc;
  }

  public void setMdc(MaldivesIslandCondition mdc) {
    this.mdc = mdc;
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

  public ProductCondition getPc() {
    return pc;
  }

  public void setPc(ProductCondition pc) {
    this.pc = pc;
  }

  public QueryResult<?> getQueryResult() {
    return queryResult;
  }

  public void setQueryResult(QueryResult<?> queryResult) {
    this.queryResult = queryResult;
  }

}

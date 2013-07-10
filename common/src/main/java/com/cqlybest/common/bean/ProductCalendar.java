package com.cqlybest.common.bean;

import java.util.Date;


/**
 * 产品日历
 */
public class ProductCalendar {

  private Integer id;
  private String productId;// 产品ID
  private Date date;// 日期
  private Integer price;// 价格
  private boolean special;// 是否特价
  private Integer childPrice;// 儿童价

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public boolean isSpecial() {
    return special;
  }

  public void setSpecial(boolean special) {
    this.special = special;
  }

  public Integer getChildPrice() {
    return childPrice;
  }

  public void setChildPrice(Integer childPrice) {
    this.childPrice = childPrice;
  }

}

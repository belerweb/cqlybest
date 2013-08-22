package com.cqlybest.common.mongo.bean;

import java.util.Date;


/**
 * 产品价格日历
 */
public class ProductPriceCalendar {

  private Date date;// 日期
  private Integer childPrice;// 儿童价
  private Integer price;// 价格
  private boolean special;// 是否特价

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Integer getChildPrice() {
    return childPrice;
  }

  public void setChildPrice(Integer childPrice) {
    this.childPrice = childPrice;
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

}

package com.cqlybest.common.bean;



/**
 * 产品价格日历
 */
public class ProductPriceCalendar {

  private String date;// 日期，如：2008-08-08
  private Integer childPrice;// 儿童价
  private Integer price;// 价格
  private Boolean special;// 是否特价

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
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

  public Boolean getSpecial() {
    return special;
  }

  public void setSpecial(Boolean special) {
    this.special = special;
  }

}

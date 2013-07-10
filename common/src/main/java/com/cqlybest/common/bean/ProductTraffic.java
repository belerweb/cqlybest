package com.cqlybest.common.bean;

import java.util.Date;


/**
 * 产品交通
 */
public class ProductTraffic {

  private Integer id;
  private String productId;// 产品ID
  private String name;// 如：第一天
  private String departure;// 出发地
  private String destination;// 目的地
  private Integer type;// 1：火车;2：飞机
  private String flights; // 班次
  private Date departureTime;// 出发时间
  private Date landingTime;// 到达时间
  private String extra;// 补充信息

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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getFlights() {
    return flights;
  }

  public void setFlights(String flights) {
    this.flights = flights;
  }

  public Date getDepartureTime() {
    return departureTime;
  }

  public void setDepartureTime(Date departureTime) {
    this.departureTime = departureTime;
  }

  public Date getLandingTime() {
    return landingTime;
  }

  public void setLandingTime(Date landingTime) {
    this.landingTime = landingTime;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

}

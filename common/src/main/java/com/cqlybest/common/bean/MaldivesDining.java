package com.cqlybest.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 马尔代夫餐饮设施
 */
public class MaldivesDining {

  private String id;
  private String zhName; // 中文名称
  private String enName; // 英文名称
  private String description; // 说明

  private String style;// 环境风格
  private String food;// 美食亮点
  private String openTime;// 开放时间
  private String location;// 就餐地点
  private Boolean reservation;// 是否预约
  private Integer displayOrder;
  private String ad;

  private List<Image> pictures = new ArrayList<>();// 图片

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getZhName() {
    return zhName;
  }

  public void setZhName(String zhName) {
    this.zhName = zhName;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public String getOpenTime() {
    return openTime;
  }

  public void setOpenTime(String openTime) {
    this.openTime = openTime;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Boolean getReservation() {
    return reservation;
  }

  public void setReservation(Boolean reservation) {
    this.reservation = reservation;
  }

  public List<Image> getPictures() {
    return pictures;
  }

  public void setPictures(List<Image> pictures) {
    this.pictures = pictures;
  }

  public Integer getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }

  public String getAd() {
    return ad;
  }

  public void setAd(String ad) {
    this.ad = ad;
  }

}

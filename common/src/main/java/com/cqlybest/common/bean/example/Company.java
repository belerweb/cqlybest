package com.cqlybest.common.bean.example;

import com.cqlybest.common.bean.Image;

/**
 * 标杆
 */
public class Company {

  private String id;
  private String name; // 标杆名称
  private String area; // 地区
  private Image logo; // 标杆LOGO
  private String description;// 标杆介绍

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

  public Image getLogo() {
    return logo;
  }

  public void setLogo(Image logo) {
    this.logo = logo;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

}

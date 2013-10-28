package com.cqlybest.common.bean.example;

import com.cqlybest.common.bean.Image;

/**
 * 标杆案例
 */
public class Case {

  private String id;
  private String name;
  private Image cover;
  private String description;

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

  public Image getCover() {
    return cover;
  }

  public void setCover(Image cover) {
    this.cover = cover;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}

package com.cqlybest.common.bean;

/**
 * 数据字典
 * 
 * @author jun
 * 
 */
public class Dict {

  private Integer id;
  private String name;
  private String pinyin;
  private String py;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPinyin() {
    return pinyin;
  }

  public void setPinyin(String pinyin) {
    this.pinyin = pinyin;
  }

  public String getPy() {
    return py;
  }

  public void setPy(String py) {
    this.py = py;
  }

}
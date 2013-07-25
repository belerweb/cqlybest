package com.cqlybest.common.bean;

public class ProductDetailMaldives implements ProductDetail {

  private String id;

  private Integer room1; // 2
  private String room1Unit; // 沙
  private Integer room2;// 2
  private String room2Unit; // 水
  private Integer room3;
  private String room3Unit;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getRoom1() {
    return room1;
  }

  public void setRoom1(Integer room1) {
    this.room1 = room1;
  }

  public String getRoom1Unit() {
    return room1Unit;
  }

  public void setRoom1Unit(String room1Unit) {
    this.room1Unit = room1Unit;
  }

  public Integer getRoom2() {
    return room2;
  }

  public void setRoom2(Integer room2) {
    this.room2 = room2;
  }

  public String getRoom2Unit() {
    return room2Unit;
  }

  public void setRoom2Unit(String room2Unit) {
    this.room2Unit = room2Unit;
  }

  public Integer getRoom3() {
    return room3;
  }

  public void setRoom3(Integer room3) {
    this.room3 = room3;
  }

  public String getRoom3Unit() {
    return room3Unit;
  }

  public void setRoom3Unit(String room3Unit) {
    this.room3Unit = room3Unit;
  }

}

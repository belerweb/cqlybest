package com.cqlybest.common.bean;


/**
 * 马尔代夫产品信息
 */
public class ProductMaldives {

  private Integer id;
  private String productId;// 产品ID
  private String name;// 如：第一天
  private String islandId;// 马尔代夫海岛ID
  private Integer roomId;// 房型ID
  private String extra;
  private String meal;

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

  public String getIslandId() {
    return islandId;
  }

  public void setIslandId(String islandId) {
    this.islandId = islandId;
  }

  public Integer getRoomId() {
    return roomId;
  }

  public void setRoomId(Integer roomId) {
    this.roomId = roomId;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public String getMeal() {
    return meal;
  }

  public void setMeal(String meal) {
    this.meal = meal;
  }

}

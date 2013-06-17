package com.cqlybest.common.bean;

/**
 * 产品行程
 */
public class ProductTravel {

  private Integer id;
  private String productId;
  private String name;// 如：第一天
  private String tour; // 游
  private String traffic; // 行
  private String eat; // 吃
  private String live; // 住
  private String purchase; // 购

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

  public String getTour() {
    return tour;
  }

  public void setTour(String tour) {
    this.tour = tour;
  }

  public String getTraffic() {
    return traffic;
  }

  public void setTraffic(String traffic) {
    this.traffic = traffic;
  }

  public String getEat() {
    return eat;
  }

  public void setEat(String eat) {
    this.eat = eat;
  }

  public String getLive() {
    return live;
  }

  public void setLive(String live) {
    this.live = live;
  }

  public String getPurchase() {
    return purchase;
  }

  public void setPurchase(String purchase) {
    this.purchase = purchase;
  }

}

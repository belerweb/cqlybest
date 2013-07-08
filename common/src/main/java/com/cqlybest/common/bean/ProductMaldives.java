package com.cqlybest.common.bean;


/**
 * 马尔代夫产品信息
 */
public class ProductMaldives {

  private String productId;// 产品ID
  private String maldivesIslandId;// 马尔代夫海岛ID
  private Integer maldivesRoomId;// 房型ID

  public String getProductId() {
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId;
  }

  public String getMaldivesIslandId() {
    return maldivesIslandId;
  }

  public void setMaldivesIslandId(String maldivesIslandId) {
    this.maldivesIslandId = maldivesIslandId;
  }

  public Integer getMaldivesRoomId() {
    return maldivesRoomId;
  }

  public void setMaldivesRoomId(Integer maldivesRoomId) {
    this.maldivesRoomId = maldivesRoomId;
  }

}

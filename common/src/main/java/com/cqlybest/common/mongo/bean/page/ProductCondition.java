package com.cqlybest.common.mongo.bean.page;

public class ProductCondition {

  private Condition type; // 产品类型
  private Condition name;// 产品名称
  private BooleanCondition popular;// 热门
  private BooleanCondition recommend;// 推荐
  private BooleanCondition special;// 特价
  private Condition departureCity;// 出发城市
  private Condition recommendedMonth;// 推荐月份

  public Condition getType() {
    return type;
  }

  public void setType(Condition type) {
    this.type = type;
  }

  public Condition getName() {
    return name;
  }

  public void setName(Condition name) {
    this.name = name;
  }

  public BooleanCondition getPopular() {
    return popular;
  }

  public void setPopular(BooleanCondition popular) {
    this.popular = popular;
  }

  public BooleanCondition getRecommend() {
    return recommend;
  }

  public void setRecommend(BooleanCondition recommend) {
    this.recommend = recommend;
  }

  public BooleanCondition getSpecial() {
    return special;
  }

  public void setSpecial(BooleanCondition special) {
    this.special = special;
  }

  public Condition getDepartureCity() {
    return departureCity;
  }

  public void setDepartureCity(Condition departureCity) {
    this.departureCity = departureCity;
  }

  public Condition getRecommendedMonth() {
    return recommendedMonth;
  }

  public void setRecommendedMonth(Condition recommendedMonth) {
    this.recommendedMonth = recommendedMonth;
  }

}

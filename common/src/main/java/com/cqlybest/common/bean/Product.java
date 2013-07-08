package com.cqlybest.common.bean;

import java.util.Date;
import java.util.List;

public class Product {

  public static final int NORMAL = 0;// 普通产品
  public static final int MALDIVES = 1;// 马尔代夫

  private String id;
  private String name;// 产品名称
  private String code;// 产品代码
  private int productType; // 产品类型：0/普通产品; 1/马尔代夫

  private Integer days;// 行程天数
  private String daysUnit;// 行程天数单位 ：天/月/年
  private String departureCities;// 出发城市
  private String destinations;// 目的地
  private String description; // 产品介绍
  private Integer price; // 正常价格
  private Integer childPrice; // 儿童价格
  private Integer specialPrice; // 特价
  private String priceDescription; // 费用说明
  private Date effectiveDate; // 产品生效日期
  private Date expiryDate; // 产品失效日期
  private Date departureDate; // 准确的出发日期
  private String tripCharacteristic;// 行程特色
  private String serviceStandard;// 服务标准
  private String friendlyReminder;// 友情提示
  private String recommendedItem;// 推荐项目
  private Boolean published; // 是否发布
  private Boolean popular;// 是否热门
  private Boolean recommend;// 是否推荐
  private Boolean specialOffer;// 是否特价
  private String recommendedMonths;// 推荐月份
  private String crowds;// 适合人群
  private String traffics;// 交通方式
  private String types;// 产品类型
  private String grades;// 产品等级
  private String keywords;// 关键词

  private Date createdTime;// 产品创建时间
  private Date lastUpdated;// 最后更新时间

  private List<ProductTravel> travels; // 行程
  private List<Image> posters;// 海报图片
  private List<Image> photos;// 相册图片
  private List<ProductComment> comments; // 评论
  private List<ProductCalendar> calendar;// 日历

  private ProductMaldives maldivesIsland;

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

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getProductType() {
    return productType;
  }

  public void setProductType(int productType) {
    this.productType = productType;
  }

  public Integer getDays() {
    return days;
  }

  public void setDays(Integer days) {
    this.days = days;
  }

  public String getDaysUnit() {
    return daysUnit;
  }

  public void setDaysUnit(String daysUnit) {
    this.daysUnit = daysUnit;
  }

  public String getDepartureCities() {
    return departureCities;
  }

  public void setDepartureCities(String departureCities) {
    this.departureCities = departureCities;
  }

  public String getDestinations() {
    return destinations;
  }

  public void setDestinations(String destinations) {
    this.destinations = destinations;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getChildPrice() {
    return childPrice;
  }

  public void setChildPrice(Integer childPrice) {
    this.childPrice = childPrice;
  }

  public Integer getSpecialPrice() {
    return specialPrice;
  }

  public void setSpecialPrice(Integer specialPrice) {
    this.specialPrice = specialPrice;
  }

  public String getPriceDescription() {
    return priceDescription;
  }

  public void setPriceDescription(String priceDescription) {
    this.priceDescription = priceDescription;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public Date getDepartureDate() {
    return departureDate;
  }

  public void setDepartureDate(Date departureDate) {
    this.departureDate = departureDate;
  }

  public String getTripCharacteristic() {
    return tripCharacteristic;
  }

  public void setTripCharacteristic(String tripCharacteristic) {
    this.tripCharacteristic = tripCharacteristic;
  }

  public String getServiceStandard() {
    return serviceStandard;
  }

  public void setServiceStandard(String serviceStandard) {
    this.serviceStandard = serviceStandard;
  }

  public String getFriendlyReminder() {
    return friendlyReminder;
  }

  public void setFriendlyReminder(String friendlyReminder) {
    this.friendlyReminder = friendlyReminder;
  }

  public String getRecommendedItem() {
    return recommendedItem;
  }

  public void setRecommendedItem(String recommendedItem) {
    this.recommendedItem = recommendedItem;
  }

  public Boolean getPublished() {
    return published;
  }

  public void setPublished(Boolean published) {
    this.published = published;
  }

  public Boolean getPopular() {
    return popular;
  }

  public void setPopular(Boolean popular) {
    this.popular = popular;
  }

  public Boolean getRecommend() {
    return recommend;
  }

  public void setRecommend(Boolean recommend) {
    this.recommend = recommend;
  }

  public Boolean getSpecialOffer() {
    return specialOffer;
  }

  public void setSpecialOffer(Boolean specialOffer) {
    this.specialOffer = specialOffer;
  }

  public String getRecommendedMonths() {
    return recommendedMonths;
  }

  public void setRecommendedMonths(String recommendedMonths) {
    this.recommendedMonths = recommendedMonths;
  }

  public String getCrowds() {
    return crowds;
  }

  public void setCrowds(String crowds) {
    this.crowds = crowds;
  }

  public String getTraffics() {
    return traffics;
  }

  public void setTraffics(String traffics) {
    this.traffics = traffics;
  }

  public String getTypes() {
    return types;
  }

  public void setTypes(String types) {
    this.types = types;
  }

  public String getGrades() {
    return grades;
  }

  public void setGrades(String grades) {
    this.grades = grades;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public List<ProductTravel> getTravels() {
    return travels;
  }

  public void setTravels(List<ProductTravel> travels) {
    this.travels = travels;
  }

  public List<Image> getPosters() {
    return posters;
  }

  public void setPosters(List<Image> posters) {
    this.posters = posters;
  }

  public List<Image> getPhotos() {
    return photos;
  }

  public void setPhotos(List<Image> photos) {
    this.photos = photos;
  }

  public List<ProductComment> getComments() {
    return comments;
  }

  public void setComments(List<ProductComment> comments) {
    this.comments = comments;
  }

  public List<ProductCalendar> getCalendar() {
    return calendar;
  }

  public void setCalendar(List<ProductCalendar> calendar) {
    this.calendar = calendar;
  }

  public ProductMaldives getMaldivesIsland() {
    return maldivesIsland;
  }

  public void setMaldivesIsland(ProductMaldives maldivesIsland) {
    this.maldivesIsland = maldivesIsland;
  }

}

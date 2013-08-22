package com.cqlybest.common.mongo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Product {

  public static final String MALDIVES = "maldives";// 马尔代夫
  public static final String MAURITIUS = "mauritius";// 毛里求斯

  private String id;
  private String name;// 产品名称
  private String code;// 产品代码
  private String type; // 产品类型，如maldives

  private Integer marketPrice; // 市场价
  private Integer price; // 参考价格
  private String priceDescription; // 费用说明
  private String priceExclusive; // 费用不包含
  private List<ProductPriceCalendar> priceCalendar = new ArrayList<>();// 价格日历

  private List<ProductBriefTrip> briefTrip; // 简要行程
  private List<ProductTransportation> transportations; // 详细交通

  private List<String> keywords = new ArrayList<>();// 关键词

  private Boolean popular;// 是否热门
  private Boolean recommend;// 是否推荐
  private Boolean special;// 是否特价

  private Date effectiveDate; // 产品生效日期
  private Date expiryDate; // 产品失效日期
  private Date departureDate; // 准确的出发日期

  private List<Integer> recommendedMonths = new ArrayList<>();// 推荐月份

  private String description; // 产品介绍
  private String tripCharacteristic;// 行程特色
  private String serviceStandard;// 服务标准
  private String friendlyReminder;// 友情提示
  private String recommendedItem;// 推荐项目

  private List<String> departureCities = new ArrayList<>();// 出发城市
  private List<String> destinations = new ArrayList<>();// 目的地
  private List<String> crowds = new ArrayList<>();// 适合人群
  private List<String> traffics = new ArrayList<>();// 交通方式
  private List<String> types = new ArrayList<>();// 产品类型
  private List<String> grades = new ArrayList<>();// 产品等级

  private List<ImageMeta> posters = new ArrayList<>();// 海报图片
  private List<ImageMeta> photos = new ArrayList<>();// 相册图片

  private List<ProductMaldives> maldivesDetails; // 马代详细

  private Boolean published; // 是否发布
  private Date createdTime;// 产品创建时间
  private Date lastUpdated;// 最后更新时间

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getMarketPrice() {
    return marketPrice;
  }

  public void setMarketPrice(Integer marketPrice) {
    this.marketPrice = marketPrice;
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public String getPriceDescription() {
    return priceDescription;
  }

  public void setPriceDescription(String priceDescription) {
    this.priceDescription = priceDescription;
  }

  public String getPriceExclusive() {
    return priceExclusive;
  }

  public void setPriceExclusive(String priceExclusive) {
    this.priceExclusive = priceExclusive;
  }

  public List<ProductPriceCalendar> getPriceCalendar() {
    return priceCalendar;
  }

  public void setPriceCalendar(List<ProductPriceCalendar> priceCalendar) {
    this.priceCalendar = priceCalendar;
  }

  public List<ProductBriefTrip> getBriefTrip() {
    return briefTrip;
  }

  public void setBriefTrip(List<ProductBriefTrip> briefTrip) {
    this.briefTrip = briefTrip;
  }

  public List<ProductTransportation> getTransportations() {
    return transportations;
  }

  public void setTransportations(List<ProductTransportation> transportations) {
    this.transportations = transportations;
  }

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
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

  public Boolean getSpecial() {
    return special;
  }

  public void setSpecial(Boolean special) {
    this.special = special;
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

  public List<Integer> getRecommendedMonths() {
    return recommendedMonths;
  }

  public void setRecommendedMonths(List<Integer> recommendedMonths) {
    this.recommendedMonths = recommendedMonths;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
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

  public List<String> getDepartureCities() {
    return departureCities;
  }

  public void setDepartureCities(List<String> departureCities) {
    this.departureCities = departureCities;
  }

  public List<String> getDestinations() {
    return destinations;
  }

  public void setDestinations(List<String> destinations) {
    this.destinations = destinations;
  }

  public List<String> getCrowds() {
    return crowds;
  }

  public void setCrowds(List<String> crowds) {
    this.crowds = crowds;
  }

  public List<String> getTraffics() {
    return traffics;
  }

  public void setTraffics(List<String> traffics) {
    this.traffics = traffics;
  }

  public List<String> getTypes() {
    return types;
  }

  public void setTypes(List<String> types) {
    this.types = types;
  }

  public List<String> getGrades() {
    return grades;
  }

  public void setGrades(List<String> grades) {
    this.grades = grades;
  }

  public List<ImageMeta> getPosters() {
    return posters;
  }

  public void setPosters(List<ImageMeta> posters) {
    this.posters = posters;
  }

  public List<ImageMeta> getPhotos() {
    return photos;
  }

  public void setPhotos(List<ImageMeta> photos) {
    this.photos = photos;
  }

  public List<ProductMaldives> getMaldivesDetails() {
    return maldivesDetails;
  }

  public void setMaldivesDetails(List<ProductMaldives> maldivesDetails) {
    this.maldivesDetails = maldivesDetails;
  }

  public Boolean getPublished() {
    return published;
  }

  public void setPublished(Boolean published) {
    this.published = published;
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

}

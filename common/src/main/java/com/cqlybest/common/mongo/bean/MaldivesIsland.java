package com.cqlybest.common.mongo.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 马尔代夫海岛
 */
public class MaldivesIsland {

  private String id;
  private String zhName; // 中文名称
  private String enName; // 英文名称
  private String byName; // 别名
  private String level; // 岛屿级别
  private String way; // 上岛方式
  private String area; // 岛屿大小
  private String snorkeling; // 浮潜等级
  private String price; // 参考价格
  private String tags; // 标签
  private String ad; // 一句话广告
  private String description;

  private String hotelName;// 酒店集团
  private Integer hotelLevel;// 酒店星级
  private String hotelStart;// 开始营业
  private Integer hotelRoomNum;// 房间总数
  private String hotelSite;// 官方网址
  private String hotelTel;// 电话
  private String hotelFax;// 传真
  private Boolean hotelChinese;// 中文服务
  private String hotelAirport;// 机场柜台号
  private String hotelDescription;// 酒店介绍

  private List<ImageMeta> hotelPictures = new ArrayList<>();// 酒店图片

  private List<ImageMeta> pictures = new ArrayList<>();// 图片
  private List<MaldivesRoom> rooms = new ArrayList<>();// 房型
  private List<MaldivesDining> dinings = new ArrayList<>();// 餐饮设施

  private String plays;// 活动与娱乐设施

  private Date createdTime;// 创建时间
  private Date lastUpdated;// 最后更新时间

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getZhName() {
    return zhName;
  }

  public void setZhName(String zhName) {
    this.zhName = zhName;
  }

  public String getEnName() {
    return enName;
  }

  public void setEnName(String enName) {
    this.enName = enName;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getWay() {
    return way;
  }

  public void setWay(String way) {
    this.way = way;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getSnorkeling() {
    return snorkeling;
  }

  public void setSnorkeling(String snorkeling) {
    this.snorkeling = snorkeling;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getHotelName() {
    return hotelName;
  }

  public void setHotelName(String hotelName) {
    this.hotelName = hotelName;
  }

  public Integer getHotelLevel() {
    return hotelLevel;
  }

  public void setHotelLevel(Integer hotelLevel) {
    this.hotelLevel = hotelLevel;
  }

  public String getHotelStart() {
    return hotelStart;
  }

  public void setHotelStart(String hotelStart) {
    this.hotelStart = hotelStart;
  }

  public Integer getHotelRoomNum() {
    return hotelRoomNum;
  }

  public void setHotelRoomNum(Integer hotelRoomNum) {
    this.hotelRoomNum = hotelRoomNum;
  }

  public String getHotelSite() {
    return hotelSite;
  }

  public void setHotelSite(String hotelSite) {
    this.hotelSite = hotelSite;
  }

  public String getHotelTel() {
    return hotelTel;
  }

  public void setHotelTel(String hotelTel) {
    this.hotelTel = hotelTel;
  }

  public String getHotelFax() {
    return hotelFax;
  }

  public void setHotelFax(String hotelFax) {
    this.hotelFax = hotelFax;
  }

  public Boolean getHotelChinese() {
    return hotelChinese;
  }

  public void setHotelChinese(Boolean hotelChinese) {
    this.hotelChinese = hotelChinese;
  }

  public String getHotelAirport() {
    return hotelAirport;
  }

  public void setHotelAirport(String hotelAirport) {
    this.hotelAirport = hotelAirport;
  }

  public String getHotelDescription() {
    return hotelDescription;
  }

  public void setHotelDescription(String hotelDescription) {
    this.hotelDescription = hotelDescription;
  }

  public List<ImageMeta> getHotelPictures() {
    return hotelPictures;
  }

  public void setHotelPictures(List<ImageMeta> hotelPictures) {
    this.hotelPictures = hotelPictures;
  }

  public List<ImageMeta> getPictures() {
    return pictures;
  }

  public void setPictures(List<ImageMeta> pictures) {
    this.pictures = pictures;
  }

  public List<MaldivesRoom> getRooms() {
    return rooms;
  }

  public void setRooms(List<MaldivesRoom> rooms) {
    this.rooms = rooms;
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

  public List<MaldivesDining> getDinings() {
    return dinings;
  }

  public void setDinings(List<MaldivesDining> dinings) {
    this.dinings = dinings;
  }

  public String getByName() {
    return byName;
  }

  public void setByName(String byName) {
    this.byName = byName;
  }

  public String getPlays() {
    return plays;
  }

  public void setPlays(String plays) {
    this.plays = plays;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public String getAd() {
    return ad;
  }

  public void setAd(String ad) {
    this.ad = ad;
  }

}

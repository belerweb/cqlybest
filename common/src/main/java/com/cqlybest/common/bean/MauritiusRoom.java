package com.cqlybest.common.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 毛里求斯房型
 */
public class MauritiusRoom {

  private String id;
  private String zhName; // 中文名称
  private String enName; // 英文名称
  private String description; // 说明
  private Integer num;// 房间数量
  private String requirements;// 入住要求（人数限制等）
  private String roomSize; // 房间大小
  private Boolean containPool;// 是否包含泳池
  private String roomFacility;// 房间设施
  private Integer displayOrder;

  private List<Image> pictures = new ArrayList<>();;// 房型图片

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

  public String getRequirements() {
    return requirements;
  }

  public void setRequirements(String requirements) {
    this.requirements = requirements;
  }

  public List<Image> getPictures() {
    return pictures;
  }

  public void setPictures(List<Image> pictures) {
    this.pictures = pictures;
  }

  public String getRoomFacility() {
    return roomFacility;
  }

  public void setRoomFacility(String roomFacility) {
    this.roomFacility = roomFacility;
  }

  public Integer getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }

  public String getRoomSize() {
    return roomSize;
  }

  public void setRoomSize(String roomSize) {
    this.roomSize = roomSize;
  }

  public Boolean getContainPool() {
    return containPool;
  }

  public void setContainPool(Boolean containPool) {
    this.containPool = containPool;
  }

}

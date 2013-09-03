package com.cqlybest.common.mongo.bean.page;

public class MaldivesIslandCondition {

  private Condition hotelLevel; // 酒店星级
  private Condition level;// 岛屿级别

  public Condition getHotelLevel() {
    return hotelLevel;
  }

  public void setHotelLevel(Condition hotelLevel) {
    this.hotelLevel = hotelLevel;
  }

  public Condition getLevel() {
    return level;
  }

  public void setLevel(Condition level) {
    this.level = level;
  }

}

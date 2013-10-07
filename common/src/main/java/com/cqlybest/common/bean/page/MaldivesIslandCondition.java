package com.cqlybest.common.bean.page;

public class MaldivesIslandCondition {

  private IntegerCondition hotelLevel; // 酒店星级
  private Condition level;// 岛屿级别

  public IntegerCondition getHotelLevel() {
    return hotelLevel;
  }

  public void setHotelLevel(IntegerCondition hotelLevel) {
    this.hotelLevel = hotelLevel;
  }

  public Condition getLevel() {
    return level;
  }

  public void setLevel(Condition level) {
    this.level = level;
  }

}

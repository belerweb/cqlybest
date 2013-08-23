package com.cqlybest.common.mongo.bean;

import java.util.ArrayList;
import java.util.List;

public class ProductMaldives {

  private String id;
  private String name;
  private String detail;// 详细
  private MaldivesIsland island;// 岛
  private MaldivesRoom room;// 房
  private List<String> meals =new ArrayList<>();// 餐

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

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public MaldivesIsland getIsland() {
    return island;
  }

  public void setIsland(MaldivesIsland island) {
    this.island = island;
  }

  public MaldivesRoom getRoom() {
    return room;
  }

  public void setRoom(MaldivesRoom room) {
    this.room = room;
  }

  public List<String> getMeals() {
    return meals;
  }

  public void setMeals(List<String> meals) {
    this.meals = meals;
  }

}

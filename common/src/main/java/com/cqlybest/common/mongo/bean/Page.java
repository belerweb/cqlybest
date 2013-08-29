package com.cqlybest.common.mongo.bean;

import java.util.ArrayList;
import java.util.List;

import com.cqlybest.common.mongo.bean.page.Section;

/**
 * 页面
 */
public class Page {

  private String id;
  private List<Link> posters = new ArrayList<>();// 海报
  private List<Section> contents = new ArrayList<>(); // 主内容
  private List<Section> sidebars = new ArrayList<>(); // 侧边栏

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<Section> getContents() {
    return contents;
  }

  public void setContents(List<Section> contents) {
    this.contents = contents;
  }

  public List<Section> getSidebars() {
    return sidebars;
  }

  public void setSidebars(List<Section> sidebars) {
    this.sidebars = sidebars;
  }

  public List<Link> getPosters() {
    return posters;
  }

  public void setPosters(List<Link> posters) {
    this.posters = posters;
  }

}

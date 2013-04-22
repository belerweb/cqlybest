package com.cqlybest.common.bean;

import java.util.Set;

/**
 * 软文
 * 
 * @author jun
 * 
 */
public class Advertorial {

  private Integer id;
  private String title;// 标题
  private String summary;// 摘要
  private String content;// 内容

  private Set<AdvertorialHistrory> histories;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Set<AdvertorialHistrory> getHistories() {
    return histories;
  }

  public void setHistories(Set<AdvertorialHistrory> histories) {
    this.histories = histories;
  }


}

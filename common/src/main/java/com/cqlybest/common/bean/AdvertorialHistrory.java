package com.cqlybest.common.bean;

import java.util.Date;

/**
 * 软文发布历史记录
 * 
 * @author jun
 * 
 */
public class AdvertorialHistrory {

  private Integer id;
  private Integer aid;// 软文ID
  private String url;// 发布网址
  private Date publishDate;// 发布时间

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getAid() {
    return aid;
  }

  public void setAid(Integer aid) {
    this.aid = aid;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Date getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(Date publishDate) {
    this.publishDate = publishDate;
  }



}

package com.cqlybest.common.bean;

public class QbProduct {

  private int aid;
  private String title;
  private String keywords;

  private QbProductDetail detail;
  private QbProductContent content;

  public int getAid() {
    return aid;
  }

  public void setAid(int aid) {
    this.aid = aid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public QbProductDetail getDetail() {
    return detail;
  }

  public void setDetail(QbProductDetail detail) {
    this.detail = detail;
  }

  public QbProductContent getContent() {
    return content;
  }

  public void setContent(QbProductContent content) {
    this.content = content;
  }


}

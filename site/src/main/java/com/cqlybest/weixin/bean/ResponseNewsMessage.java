package com.cqlybest.weixin.bean;

import java.util.List;


/**
 * 回复图文消息
 */
public class ResponseNewsMessage extends ResponseMessage {

  private Integer count;// 图文消息个数，限制为10条以内

  private List<Article> articles;

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public ResponseNewsMessage() {
    super();
    setMsgType("news");
  }

  public static class Article {

    private String title;// 图文消息标题

    private String description;// 图文消息描述

    private String picUrl;// 图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。

    private String url;// 点击图文消息跳转链接

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public String getPicUrl() {
      return picUrl;
    }

    public void setPicUrl(String picUrl) {
      this.picUrl = picUrl;
    }

    public String getUrl() {
      return url;
    }

    public void setUrl(String url) {
      this.url = url;
    }

  }

}

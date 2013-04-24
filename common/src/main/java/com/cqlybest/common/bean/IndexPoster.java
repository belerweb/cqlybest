package com.cqlybest.common.bean;

/**
 * 首页海报
 * 
 * @author jun
 */
public class IndexPoster {

  private Integer id;
  private String title;// 标题
  private String description;// 描述
  private String imageUrl;// 图片地址
  private String url;// 链接地址
  private boolean newWindow;// 是否新窗口打开
  private boolean published;// 是否发布

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isNewWindow() {
    return newWindow;
  }

  public void setNewWindow(boolean newWindow) {
    this.newWindow = newWindow;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }


}

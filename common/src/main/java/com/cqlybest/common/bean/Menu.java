package com.cqlybest.common.bean;


/**
 * 菜单
 */
public class Menu {

  private String id;
  private Integer menuType;// 0:聚合菜单, 1:自定义页面, 2:外链
  private String name;// 菜单名称
  private Boolean newWindow;// 是否新窗口打开
  private ProductGroup productGroup;// 聚合菜单
  private String pageContent;// 自定义页面的内容
  private String url;// 外链URL
  private Integer displayOrder;// 菜单显示顺序
  private Boolean published;// 是否发布

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getMenuType() {
    return menuType;
  }

  public void setMenuType(Integer menuType) {
    this.menuType = menuType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Boolean getNewWindow() {
    return newWindow;
  }

  public void setNewWindow(Boolean newWindow) {
    this.newWindow = newWindow;
  }

  public ProductGroup getProductGroup() {
    return productGroup;
  }

  public void setProductGroup(ProductGroup productGroup) {
    this.productGroup = productGroup;
  }

  public String getPageContent() {
    return pageContent;
  }

  public void setPageContent(String pageContent) {
    this.pageContent = pageContent;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Integer getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(Integer displayOrder) {
    this.displayOrder = displayOrder;
  }

  public Boolean getPublished() {
    return published;
  }

  public void setPublished(Boolean published) {
    this.published = published;
  }

}

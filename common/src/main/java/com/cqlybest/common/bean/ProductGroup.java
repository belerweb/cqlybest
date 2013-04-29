package com.cqlybest.common.bean;

import java.util.Set;

/**
 * 产品聚合
 */
public class ProductGroup {

  private String id;
  private String name;// 聚合名称，如出境参团、邮轮旅游度假
  private boolean published;// 是否发布
  private Set<ProductGroupItem> groupItems;

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

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public Set<ProductGroupItem> getGroupItems() {
    return groupItems;
  }

  public void setGroupItems(Set<ProductGroupItem> groupItems) {
    this.groupItems = groupItems;
  }


}

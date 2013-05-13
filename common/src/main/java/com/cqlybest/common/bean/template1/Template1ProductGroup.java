package com.cqlybest.common.bean.template1;

import com.cqlybest.common.bean.ProductGroup;

public class Template1ProductGroup {

  private Integer id;
  private ProductGroup productGroup;
  private int displayOrder;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ProductGroup getProductGroup() {
    return productGroup;
  }

  public void setProductGroup(ProductGroup productGroup) {
    this.productGroup = productGroup;
  }

  public int getDisplayOrder() {
    return displayOrder;
  }

  public void setDisplayOrder(int displayOrder) {
    this.displayOrder = displayOrder;
  }

}

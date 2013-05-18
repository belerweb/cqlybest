package com.cqlybest.common.bean.template1;


/**
 * 针对聚合菜单的二级菜单，如国内游下面有北京、上海...
 */
public class Template1SubMenu {

  private Integer id;
  private String menuId;// 父菜单ID
  private String title;// 菜单标题
  private Integer menuType;// 菜单类型
  private String menuValue;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getMenuId() {
    return menuId;
  }

  public void setMenuId(String menuId) {
    this.menuId = menuId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getMenuType() {
    return menuType;
  }

  public void setMenuType(Integer menuType) {
    this.menuType = menuType;
  }

  public String getMenuValue() {
    return menuValue;
  }

  public void setMenuValue(String menuValue) {
    this.menuValue = menuValue;
  }

}

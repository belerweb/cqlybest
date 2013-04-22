package com.cqlybest.common.bean;

/**
 * 账号
 * 
 * @author jun
 * 
 */
public class Account {

  private Integer id;
  private String name;// 账号名称，如“400电话管理账号”
  private String url;// 网址，通常是账号的登录地址
  private String account;// 账号
  private String password;// 密码
  private String remark;// 备注，其他补充说明

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}

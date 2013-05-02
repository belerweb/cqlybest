package com.cqlybest.common.bean;

import java.util.Date;

/**
 * @author jun
 * 
 *         网站配置，名称...
 */
public class Site {

  private Integer id;
  private String name;// 网站名称
  private String metaKeyword;// 网页 meta keyword
  private String metaDescription;// 网页 meta description
  private String copyright;// 网页版权说明文字
  private String statisticalCode;// 统计代码
  private String icp;// 备案号
  private String imageServer;// 图片服务器
  private Date lastUpdate;

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

  public String getMetaKeyword() {
    return metaKeyword;
  }

  public void setMetaKeyword(String metaKeyword) {
    this.metaKeyword = metaKeyword;
  }

  public String getMetaDescription() {
    return metaDescription;
  }

  public void setMetaDescription(String metaDescription) {
    this.metaDescription = metaDescription;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getStatisticalCode() {
    return statisticalCode;
  }

  public void setStatisticalCode(String statisticalCode) {
    this.statisticalCode = statisticalCode;
  }

  public String getIcp() {
    return icp;
  }

  public void setIcp(String icp) {
    this.icp = icp;
  }

  public String getImageServer() {
    return imageServer;
  }

  public void setImageServer(String imageServer) {
    this.imageServer = imageServer;
  }

  public Date getLastUpdate() {
    return lastUpdate;
  }

  public void setLastUpdate(Date lastUpdate) {
    this.lastUpdate = lastUpdate;
  }

}

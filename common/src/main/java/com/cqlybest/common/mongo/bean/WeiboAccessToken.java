package com.cqlybest.common.mongo.bean;

import java.io.Serializable;
import java.util.Date;

public class WeiboAccessToken implements Serializable {

  private static final long serialVersionUID = -7064065073755298724L;

  private String appKey;

  private String appId; // APP ID
  private String cid;// 企业微博ID
  private String subAppkey;

  private String token;
  private Long expireIn;
  private Date createdTime;
  private Date lastUpdated;

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    this.appKey = appKey;
  }

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getSubAppkey() {
    return subAppkey;
  }

  public void setSubAppkey(String subAppkey) {
    this.subAppkey = subAppkey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Long getExpireIn() {
    return expireIn;
  }

  public void setExpireIn(Long expireIn) {
    this.expireIn = expireIn;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

}

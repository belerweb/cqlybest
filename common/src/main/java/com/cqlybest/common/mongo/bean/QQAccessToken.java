package com.cqlybest.common.mongo.bean;

import java.io.Serializable;
import java.util.Date;

public class QQAccessToken implements Serializable {

  private static final long serialVersionUID = -7064065073755298724L;

  private String appId;

  private String token;
  private Long expireIn;
  private Date createdTime;
  private Date lastUpdated;

  public String getAppId() {
    return appId;
  }

  public void setAppId(String appId) {
    this.appId = appId;
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

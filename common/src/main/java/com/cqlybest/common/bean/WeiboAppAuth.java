package com.cqlybest.common.bean;


public class WeiboAppAuth extends WeiboAuth {

  private static final long serialVersionUID = 5662785456672299867L;

  private Integer id;
  private String appId; // APP ID
  private String cid;// 企业微博ID
  private String subAppkey; // Sub App ID

  public WeiboAppAuth() {}

  public WeiboAppAuth(String appId, String cid, String subAppkey, String uid, String token,
      long expireIn) {
    super(uid, token, expireIn);
    this.appId = appId;
    this.cid = cid;
    this.subAppkey = subAppkey;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

}

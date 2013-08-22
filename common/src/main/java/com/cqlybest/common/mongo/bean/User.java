package com.cqlybest.common.mongo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.cqlybest.common.bean.QQAuth;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.bean.WeiboAppAuth;
import com.cqlybest.common.bean.WeiboAuth;


public class User implements Serializable {

  private static final long serialVersionUID = -5956576275217674723L;

  private String id;
  private String mobile;// 手机号
  private String email;// 电子邮件
  private String username;// 用户名
  private String password;// 密码
  private String fullname;// 姓名
  private String nickname;// 昵称
  private String avatar;// 头像
  private String source;// 注册来源
  private String lastLoginIP;// 最后登录IP
  private Date createdTime;// 注册时间
  private Date lastUpdated;// 最后更新时间

  private QQAuth qqAuth;// QQ 授权
  private WeiboAuth weiboAuth;// 新浪微博授权
  private WeiboAppAuth weiboAppAuth;// 新浪微博APP授权

  private Set<Role> roles = new HashSet<>();// 角色

  public User() {}

  public User(String mobile, String password) {
    this.mobile = mobile;
    this.password = password;
    this.id = UUID.randomUUID().toString();
  }

  public User(QQAuth auth) {
    this.qqAuth = auth;
    this.id = UUID.randomUUID().toString();
  }

  public User(WeiboAuth auth) {
    if (auth instanceof WeiboAppAuth) {
      this.weiboAppAuth = (WeiboAppAuth) auth;
      this.weiboAuth = new WeiboAuth(auth.getUid(), null, 0);
    } else {
      this.weiboAuth = auth;
    }
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getLastLoginIP() {
    return lastLoginIP;
  }

  public void setLastLoginIP(String lastLoginIP) {
    this.lastLoginIP = lastLoginIP;
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

  public QQAuth getQqAuth() {
    return qqAuth;
  }

  public void setQqAuth(QQAuth qqAuth) {
    this.qqAuth = qqAuth;
  }

  public WeiboAuth getWeiboAuth() {
    return weiboAuth;
  }

  public void setWeiboAuth(WeiboAuth weiboAuth) {
    this.weiboAuth = weiboAuth;
  }

  public WeiboAppAuth getWeiboAppAuth() {
    return weiboAppAuth;
  }

  public void setWeiboAppAuth(WeiboAppAuth weiboAppAuth) {
    this.weiboAppAuth = weiboAppAuth;
  }

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

  public static class UserWrapper extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = -2466927911565414318L;
    private User user;

    public UserWrapper(String username, User user) {
      super(username, user.getPassword(), user.getRoles());
      this.user = user;
    }

    public User getDetail() {
      return user;
    }
  }

}

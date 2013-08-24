package com.cqlybest.common.mongo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;


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

  private WeiboUser weibo;// 新浪微博用户
  private QQUser qzone;// QQ登录用户

  private List<String> roles = new ArrayList<>();// 角色

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

  public List<SimpleGrantedAuthority> getWrapperRoles() {
    List<SimpleGrantedAuthority> roles = new ArrayList<>();
    for (String role : this.roles) {
      roles.add(new SimpleGrantedAuthority(role));
    }
    return roles;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public WeiboUser getWeibo() {
    return weibo;
  }

  public void setWeibo(WeiboUser weibo) {
    this.weibo = weibo;
  }

  public QQUser getQzone() {
    return qzone;
  }

  public void setQzone(QQUser qzone) {
    this.qzone = qzone;
  }



  public static class UserWrapper extends org.springframework.security.core.userdetails.User {
    private static final long serialVersionUID = -2466927911565414318L;
    private User user;

    public UserWrapper(String username, User user) {
      super(username, user.getPassword(), user.getWrapperRoles());
      this.user = user;
    }

    public User getDetail() {
      return user;
    }
  }

}

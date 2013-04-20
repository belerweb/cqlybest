package com.cqlybest.common.bean;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class LoginUser implements UserDetails {

  private static final long serialVersionUID = 4843380217443944009L;

  private String username;// SpringSecurity
  private String password;// 密码

  private String id;// 用户编号
  private String cellPhone;// 手机号
  private String email;// 电子邮件
  private String loginUsername;// 登陆用户名
  private String fullname;// 姓名
  private String nickname;// 昵称
  private String avatar;// 头像

  private String qqOpenid;// QQ openid
  private QQAuth qqAuth;// QQ 授权
  private SinaAuth sinaAuth;// 新浪授权

  public LoginUser() {}

  public LoginUser(QQAuth qqAuth) {
    this.qqAuth = qqAuth;
    this.qqOpenid = qqAuth.getOpenid();
    this.id = UUID.randomUUID().toString();
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

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCellPhone() {
    return cellPhone;
  }

  public void setCellPhone(String cellPhone) {
    this.cellPhone = cellPhone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getLoginUsername() {
    return loginUsername;
  }

  public void setLoginUsername(String loginUsername) {
    this.loginUsername = loginUsername;
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

  public String getQqOpenid() {
    return qqOpenid;
  }

  public void setQqOpenid(String qqOpenid) {
    this.qqOpenid = qqOpenid;
  }

  public QQAuth getQqAuth() {
    return qqAuth;
  }

  public void setQqAuth(QQAuth qqAuth) {
    this.qqAuth = qqAuth;
  }

  public SinaAuth getSinaAuth() {
    return sinaAuth;
  }

  public void setSinaAuth(SinaAuth sinaAuth) {
    this.sinaAuth = sinaAuth;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}

package com.cqlybest.common.mongo.bean;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class WeiboAuthToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = 7201859537467572288L;

  private UserWrapper userWrapper;

  public WeiboAuthToken(User user) {
    this(user.getWrapperRoles());
    this.userWrapper = new UserWrapper(user);
  }

  private WeiboAuthToken(Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getPrincipal() {
    return userWrapper;
  }

  public class UserWrapper {
    private User detail;

    public UserWrapper(User detail) {
      this.detail = detail;
    }

    public User getDetail() {
      return detail;
    }

    public void setDetail(User detail) {
      this.detail = detail;
    }
  }

}

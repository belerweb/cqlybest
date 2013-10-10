package com.cqlybest.common.bean;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class QQAuthToken extends AbstractAuthenticationToken {

  private static final long serialVersionUID = -7795121296695826713L;

  private UserWrapper userWrapper;

  public QQAuthToken(User user) {
    this(user.getWrapperRoles());
    this.userWrapper = new UserWrapper(user);
  }

  private QQAuthToken(Collection<? extends GrantedAuthority> authorities) {
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

  public class UserWrapper implements Serializable {
    private static final long serialVersionUID = 3168630662836285091L;
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

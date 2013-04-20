package com.cqlybest.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.QQAuth;
import com.cqlybest.common.service.QQConnectInitService;
import com.cqlybest.common.service.UserService;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.qq.connect.utils.QQConnectConfig;

@Controller
public class Oauth2Controller {

  private static final Oauth QQ_OAUTH = new Oauth();

  @Autowired
  private UserService userService;

  @RequestMapping("/connector/qq_login.do")
  public String qqLogin(HttpServletRequest request) {
    String redirect = "redirect:/index.html";
    try {
      String redirectURI =
          request.getScheme() + "://" + request.getServerName() + request.getContextPath()
              + "/connector/qq.do";
      QQConnectConfig.updateProperties(QQConnectInitService.REDIRECT_URI, redirectURI);
      redirect = "redirect:" + QQ_OAUTH.getAuthorizeURL(request);
    } catch (QQConnectException e) {
      e.printStackTrace();
    }

    return redirect;
  }

  @RequestMapping("/connector/qq.do")
  public String auth(HttpServletRequest request) {
    String redirect = "redirect:/index.html";
    try {
      AccessToken accessToken = QQ_OAUTH.getAccessTokenByRequest(request);
      // TODO validate accessToken
      QQAuth qqAuth =
          new QQAuth(new OpenID(accessToken.getAccessToken()).getUserOpenID(), accessToken
              .getAccessToken(), accessToken.getExpireIn());
      LoginUser user = userService.register(qqAuth);
      SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword() == null ? user
              .getId() : user.getPassword()));
    } catch (QQConnectException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return redirect;
  }
}

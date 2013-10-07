package com.cqlybest.weibo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.User;
import com.cqlybest.common.bean.WeiboAccessToken;
import com.cqlybest.common.bean.WeiboAuthToken;
import com.cqlybest.common.bean.WeiboUser;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.UserService;

@Controller
public class WeiboSecurityController {

  private static final Oauth WEIBO_OAUTH = new Oauth();

  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private UserService mongoUserService;

  @RequestMapping("/weibo/security/proxy")
  public String securityProxy(HttpServletRequest request) {
    try {
      String redirectURI =
          request.getScheme() + "://" + request.getServerName() + request.getContextPath()
              + "/weibo/security/auth";
      Constant.checkWeiboConfig(Constant.WEIBO_PRO_APP_KEY, Constant.WEIBO_PRO_APP_SECRET);
      WeiboConfig.updateProperties(Constant.REDIRECT_URI, redirectURI);
      return "redirect:" + WEIBO_OAUTH.authorize(Constant.RESPONSE_TYPE_CODE, Constant.SCOPE_ALL);
    } catch (WeiboException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/weibo/security/auth")
  public String securityAuth(@RequestParam(required = false) String state,
      @RequestParam String code, HttpServletRequest request) {
    HttpSession session = request.getSession();
    try {
      AccessToken accessToken = WEIBO_OAUTH.getAccessTokenByCode(code);
      WeiboUser weiboUser = new WeiboUser();
      weiboUser.setId(accessToken.getUid());
      WeiboAccessToken token = new WeiboAccessToken();
      // token.setAppKey(appKey);
      token.setAppId((String) session.getAttribute("appId"));
      token.setCid((String) session.getAttribute("cid"));
      token.setSubAppkey((String) session.getAttribute("sub_appkey"));
      token.setToken(accessToken.getAccessToken());
      token.setExpireIn(Long.valueOf(accessToken.getExpireIn()));
      Date current = new Date();
      token.setCreatedTime(current);
      token.setLastUpdated(current);
      weiboUser.getTokens().add(token);

      // 读取用户详细信息
      Users api = new Users();
      api.setToken(accessToken.getAccessToken());


      User user = mongoUserService.register(weiboUser, api.showUserById(accessToken.getUid()));

      SecurityContextHolder.getContext().setAuthentication(new WeiboAuthToken(user));
    } catch (WeiboException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "redirect:/index.html";
  }

}

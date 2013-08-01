package com.cqlybest.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weibo4j.Friendships;
import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import com.cqlybest.common.Constant;
import com.cqlybest.common.auth.WeiboAuthToken;
import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.WeiboAuth;
import com.cqlybest.common.service.OptionService;
import com.cqlybest.common.service.UserService;

@Controller
public class WeiboLoginController {

  private static final Logger LOGGER = LoggerFactory.getLogger(WeiboLoginController.class);
  private static final Oauth WEIBO_OAUTH = new Oauth();

  @Autowired
  private UserService userService;
  @Autowired
  private OptionService optionService;

  @RequestMapping("/connector/weibo_login")
  public String weiboLogin(HttpServletRequest request) {
    String redirect = "redirect:/index.html";
    try {
      String redirectURI =
          request.getScheme() + "://" + request.getServerName() + request.getContextPath()
              + "/connector/weibo";
      WeiboConfig.updateProperties(Constant.REDIRECT_URI, redirectURI);
      redirect =
          "redirect:" + WEIBO_OAUTH.authorize(Constant.RESPONSE_TYPE_CODE, Constant.SCOPE_ALL);
    } catch (WeiboException e) {
      e.printStackTrace();
    }

    return redirect;
  }

  @RequestMapping("/connector/weibo")
  public String weibo(@RequestParam String state, @RequestParam String code,
      HttpServletRequest request) {
    String redirect = "redirect:/index.html";
    try {
      AccessToken accessToken = WEIBO_OAUTH.getAccessTokenByCode(code);
      String token = accessToken.getAccessToken();
      WeiboAuth auth =
          new WeiboAuth(accessToken.getUid(), token, Long.parseLong(accessToken.getExpireIn()));

      // 读取用户详细信息
      Users api = new Users();
      api.setToken(token);
      LoginUser user = userService.register(auth, api.showUserById(accessToken.getUid()));
      SecurityContextHolder.getContext().setAuthentication(new WeiboAuthToken(user));

      // 关注官方微博
      String official = optionService.getOptions().get(Constant.OPTION_WEIBO_NICKNAME);
      try {
        if (StringUtils.isNotBlank(official)) {
          Friendships friendships = new Friendships();
          friendships.setToken(token);
          friendships.createFriendshipsByName(official);
          LOGGER.info("{} followed {}.", user.getNickname(), official);
        }
      } catch (WeiboException e) {
        LOGGER.warn("{} follow {} failed: {}", user.getNickname(), official, e.getMessage());
        e.printStackTrace();
      }
    } catch (WeiboException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return redirect;
  }

  static {
    Constant.checkWeiboConfig(Constant.WEIBO_APP_KEY, Constant.WEIBO_APP_SECRET);
  }

}

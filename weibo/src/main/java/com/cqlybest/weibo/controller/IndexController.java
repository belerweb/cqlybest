package com.cqlybest.weibo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import weibo4j.Oauth;
import weibo4j.Users;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

import com.cqlybest.common.Constant;
import com.cqlybest.common.auth.WeiboAuthToken;
import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.WeiboAppAuth;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.UserService;

@Controller
public class IndexController extends ControllerHelper {

  private static final Oauth WEIBO_OAUTH = new Oauth();

  @Autowired
  private ProductService productService;
  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private DictService dictService;
  @Autowired
  private UserService userService;

  @RequestMapping("/security/proxy")
  public String securityProxy(HttpServletRequest request) {
    try {
      String redirectURI =
          request.getScheme() + "://" + request.getServerName() + request.getContextPath()
              + "/security/auth";
      Constant.checkWeiboConfig(Constant.WEIBO_PRO_APP_KEY, Constant.WEIBO_PRO_APP_SECRET);
      WeiboConfig.updateProperties(Constant.REDIRECT_URI, redirectURI);
      return "redirect:" + WEIBO_OAUTH.authorize(Constant.RESPONSE_TYPE_CODE, Constant.SCOPE_ALL);
    } catch (WeiboException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @RequestMapping("/security/auth")
  public String securityAuth(@RequestParam(required = false) String state,
      @RequestParam String code, HttpServletRequest request) {
    try {
      AccessToken accessToken = WEIBO_OAUTH.getAccessTokenByCode(code);
      String token = accessToken.getAccessToken();

      String appId = WeiboConfig.getValue(Constant.CLIENT_ID);
      HttpSession session = request.getSession();
      String cid = (String) session.getAttribute("cid");
      String sub_appkey = (String) session.getAttribute("sub_appkey");
      WeiboAppAuth auth =
          new WeiboAppAuth(appId, cid, sub_appkey, accessToken.getUid(), token, Long
              .parseLong(accessToken.getExpireIn()));

      // 读取用户详细信息
      Users api = new Users();
      api.setToken(token);
      LoginUser user = userService.register(auth, api.showUserById(accessToken.getUid()));

      SecurityContextHolder.getContext().setAuthentication(new WeiboAuthToken(user));
    } catch (WeiboException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return "redirect:/index.html";
  }

  /**
   * 首页
   */
  @RequestMapping("/index.html")
  public String index(@RequestParam(required = false) String cid,
      @RequestParam(required = false) String sub_appkey, HttpServletRequest request, Model model) {
    HttpSession session = request.getSession();
    session.setAttribute("cid", StringUtils.isEmpty(cid) ? null : cid);
    session.setAttribute("sub_appkey", StringUtils.isEmpty(sub_appkey) ? null : sub_appkey);
    return "/v1/index";
  }

  static {
    Constant.checkWeiboConfig(Constant.WEIBO_PRO_APP_KEY, Constant.WEIBO_PRO_APP_SECRET);
  }
}

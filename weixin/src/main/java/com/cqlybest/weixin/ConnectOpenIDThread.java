package com.cqlybest.weixin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belerweb.weixin.mp.WeixinMP;
import com.belerweb.weixin.mp.WeixinMessage;
import com.belerweb.weixin.mp.WeixinUser;
import com.cqlybest.common.service.WeixinUserService;
import com.cqlybest.weixin.bean.RequestMessage;

public class ConnectOpenIDThread extends Thread {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConnectOpenIDThread.class);

  private WeixinUserService service;
  private String openid;
  private RequestMessage message;

  private WeixinMP mp;

  public ConnectOpenIDThread(WeixinUserService service, String openid) {
    this.service = service;
    this.openid = openid;
    init();
  }

  public ConnectOpenIDThread(WeixinUserService service, RequestMessage message) {
    this.service = service;
    this.message = message;
    init();
  }

  private void init() {
    String username =
        System.getProperty(WeixinMP.CONFIG_USERNAME, System.getenv(WeixinMP.CONFIG_USERNAME));
    String password =
        System.getProperty(WeixinMP.CONFIG_PASSWORD, System.getenv(WeixinMP.CONFIG_PASSWORD));
    this.mp = WeixinMP.init(username, password);
  }

  @Override
  public void run() {
    if (openid != null) {
      connect();
    }
    if (message != null) {
      connectWithMessage();
    }
  }

  private void connect() {
    List<WeixinUser> users = mp.getUsers(2); // 考虑并发两个关注
    int size = users.size();
    if (size == 0) { // 微信公众平台上查不到用户
      LOGGER.warn("微信公众平台上查不到用户(OpenID):{}", openid);
      return;
    }
    if (size == 2 && service.get(users.get(1).getFakeid()) == null) {
      // 两个或以上用户信息未保存到数据库
      for (WeixinUser user : mp.getUsers()) {
        service.save(user);
      }
      return;
    }
    WeixinUser user = users.get(0);
    WeixinUser userInDb = service.get(user.getFakeid());
    if (userInDb == null) {// 只有一个用户信息未保存到数据库，那么OpenID和FakeID可关联
      LOGGER.info("OpenID <--> FakeID: {} <--> {}", openid, user.getFakeid());
      user.setOpenid(openid);
      service.save(user);
      return;
    }
    if (openid.equals(userInDb.getOpenid())) {// FakeID已在库中且OpenID相同（二次关注），更新信息
      LOGGER.info("Update {}/{}.", openid, user.getFakeid());
      userInDb.setCity(user.getCity());
      userInDb.setCountry(user.getCountry());
      userInDb.setNickname(user.getNickname());
      userInDb.setProvince(user.getProvince());
      userInDb.setReMarkName(user.getReMarkName());
      userInDb.setSex(user.getSex());
      userInDb.setSignature(user.getSignature());
      userInDb.setUsername(user.getUsername());
      service.save(userInDb);
    }
  }

  private void connectWithMessage() {
    List<WeixinMessage> messages = mp.getMessage(0, 20, 0);// 当天的最新20条消息
    for (WeixinMessage message : messages) {
      if (this.message.getCreateTime().getTime() == message.getDateTime().getTime()
          && this.message.getContent().equals(message.getContent())) {
        // 时间和消息内容相同，暂不考虑其他因素，假定OpenID和FakeID可以关联
        WeixinUser user = mp.getUser(message.getFakeId());
        user.setOpenid(this.message.getFromUserName());
        service.save(user);
        break;
      }
    }
  }
}

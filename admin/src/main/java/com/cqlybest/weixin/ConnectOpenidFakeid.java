package com.cqlybest.weixin;

import java.util.List;

import com.belerweb.weixin.mp.WeixinMP;
import com.belerweb.weixin.mp.WeixinMessage;
import com.belerweb.weixin.mp.WeixinUser;
import com.cqlybest.weixin.bean.RequestMessage;

public class ConnectOpenidFakeid {

  private static WeixinMP mp;

  public static void connect(String openid) {
  // TODO
  }

  public static void connect(RequestMessage message) {
    List<WeixinMessage> messages = mp.getMessage(0, 20, 0);// 当天的最新20条消息
    for (WeixinMessage m : messages) {
      if (message.getCreateTime().getTime() == m.getDateTime().getTime()
          && message.getContent().equals(m.getContent())) {
        // 时间和消息内容相同，暂不考虑其他因素，假定OpenID和FakeID可以关联
        WeixinUser user = mp.getUser(m.getFakeId());
        user.setOpenid(message.getFromUserName());
        break;
      }
    }
  }

  static {
    String username =
        System.getProperty(WeixinMP.CONFIG_USERNAME, System.getenv(WeixinMP.CONFIG_USERNAME));
    String password =
        System.getProperty(WeixinMP.CONFIG_PASSWORD, System.getenv(WeixinMP.CONFIG_PASSWORD));
    mp = WeixinMP.init(username, password);
  }

}

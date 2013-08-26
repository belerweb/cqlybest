package com.cqlybest.weixin.smart;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.bean.ResponseTextMessage;

@Component
public class AnyHandler implements Handler {

  @Autowired
  private SettingsService settingsService;

  @Override
  public boolean support(RequestMessage request) {
    return true;
  }

  @Override
  public ResponseMessage handle(RequestMessage request) {
    String content =
        (String) ((Map<?, ?>) ((Map<?, ?>) settingsService.getSettings().get("mp")).get("message"))
            .get("unknow");
    if (StringUtils.isBlank(content)) {
      content = "系统暂时不能理解您的请求，请等候人工应答。";
    }
    ResponseTextMessage response = new ResponseTextMessage();
    response.setFromUserName(request.getToUserName());
    response.setToUserName(request.getFromUserName());
    response.setCreateTime(System.currentTimeMillis());
    response.setContent(content);
    return response;
  }

  @Override
  public int priority() {
    return 99;
  }

}

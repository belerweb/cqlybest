package com.cqlybest.weixin.smart;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.bean.ResponseTextMessage;

@Component
public class HelpHandler implements Handler {

  @Autowired
  private ImageService imageService;
  @Autowired
  private SettingsService settingsService;

  @Override
  public boolean support(RequestMessage request) {
    String content = request.getContent();
    return content != null && "text".equals(request.getMsgType())
        && content.matches("(?i)(.*帮助.*)|(.*help.*)|(.*幫助.*)");
  }

  @Override
  public ResponseMessage handle(RequestMessage request) {
    String content =
        (String) ((Map<?, ?>) ((Map<?, ?>) settingsService.getSettings().get("mp")).get("message"))
            .get("help");
    if (StringUtils.isBlank(content)) {
      return null;
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
    return 0;
  }

}

package com.cqlybest.weixin.smart;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.Constant;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.OptionService;
import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.bean.ResponseTextMessage;

@Component
public class HelpHandler implements Handler {

  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private OptionService optionService;

  @Override
  public boolean support(RequestMessage request) {
    String content = request.getContent();
    return content != null && "text".equals(request.getMsgType())
        && content.matches("(?i)(.*帮助.*)|(.*help.*)|(.*幫助.*)");
  }

  @Override
  public ResponseMessage handle(RequestMessage request) {
    String content = optionService.getOptions().get(Constant.OPTION_WEIXIN_HELP);
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

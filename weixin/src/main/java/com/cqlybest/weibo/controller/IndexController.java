package com.cqlybest.weibo.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.weibo.bean.RequestMessage;
import com.cqlybest.weibo.bean.ResponseTextMessage;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Controller
public class IndexController extends ControllerHelper {

  private static final String TOKEN_CONFIG = "weixin.token";
  private static final XmlMapper XML = new XmlMapper();


  @RequestMapping(method = RequestMethod.GET, value = "/")
  public Object root(@RequestParam String signature, @RequestParam String timestamp,
      @RequestParam String nonce, @RequestParam String echostr) {
    try {
      String token = System.getProperty(TOKEN_CONFIG, System.getenv(TOKEN_CONFIG));
      String[] chars = new String[] {token, timestamp, nonce,};
      Arrays.sort(chars);
      String sha1 = DigestUtils.shaHex(StringUtils.join(chars));
      if (sha1.equals(signature)) {
        return new ResponseEntity<byte[]>(echostr.getBytes(), HttpStatus.OK);
      }

      return ok();
    } catch (Exception e) {
      e.printStackTrace();
      return ok();
    }
  }

  @RequestMapping(method = RequestMethod.POST, value = "/")
  public Object root(@RequestBody String data, HttpServletRequest request, Model model) {
    String encoding = request.getCharacterEncoding();
    System.out.println("Encoding:" + encoding);
    // TODO auth
    try {
      RequestMessage message =
          XML.readValue(new String(data.getBytes(encoding == null ? "ISO-8859-1" : encoding)),
              RequestMessage.class);
      System.out.println(XML.writeValueAsString(message));
      String type = message.getMsgType();
      if ("text".equals(type)) {// 文本消息
        return text(model, message);
      }
      if ("image".equals(type)) {// 图片消息
        return image(model, message);
      }
      if ("location".equals(type)) {// 地理位置消息
        return location(model, message);
      }
      if ("link".equals(type)) {// 链接消息
        return link(model, message);
      }
      if ("event".equals(type)) {// 事件推送
        return event(model, message);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return ok();
  }

  private Object text(Model model, RequestMessage message) throws Exception {
    ResponseTextMessage response = new ResponseTextMessage();
    response.setFromUserName(message.getToUserName());
    response.setToUserName(message.getFromUserName());
    response.setContent(message.getContent().toUpperCase());
    response.setCreateTime(System.currentTimeMillis());
    model.addAttribute("message", response);
    return "/text";
  }

  private Object image(Model model, RequestMessage message) throws Exception {
    return ok();
  }

  private Object location(Model model, RequestMessage message) throws Exception {
    return ok();
  }

  private Object link(Model model, RequestMessage message) throws Exception {
    return ok();
  }

  private Object event(Model model, RequestMessage message) throws Exception {
    String type = message.getEvent();
    if ("subscribe".equals(type)) {// 订阅
      ResponseTextMessage response = new ResponseTextMessage();
      response.setFromUserName(message.getToUserName());
      response.setToUserName(message.getFromUserName());
      response.setContent("同乔旅游感谢您关注。");
      response.setCreateTime(System.currentTimeMillis());
      model.addAttribute("message", response);
      return "/text";
    }
    if ("unsubscribe".equals(type)) {// 取消
      ResponseTextMessage response = new ResponseTextMessage();
      response.setFromUserName(message.getToUserName());
      response.setToUserName(message.getFromUserName());
      response.setContent("再见。");
      response.setCreateTime(System.currentTimeMillis());
      model.addAttribute("message", response);
      return "/text";
    }
    if ("CLICK".equals(type)) {// 自定义菜单点击事件

    }
    return ok();
  }

  static {
    XML.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
}

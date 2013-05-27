package com.cqlybest.www.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.PhoneValidationCode;
import com.cqlybest.common.service.PhoneValidationService;
import com.cqlybest.common.service.SmsService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class IndexController {

  @Autowired
  private TemplateService templateService;
  @Autowired
  private PhoneValidationService phoneValidationService;
  @Autowired
  private SmsService smsService;

  @RequestMapping( {"/index.html", // 首页
      "/register.html",// 注册
      "/login.html",// 登陆
      "/group/{id}.html",// 产品聚合
      "/group/{id}/{f0}-{f1}-{f2}-{f3}-{f4}-{f5}-{f6}-{f7}-{page}.html",// 产品聚合
      "/page/{id}.html",// 自定义页面
      "/product/{id}.html"// 自定义页面
  })
  public String forward(HttpServletRequest request) {
    return templateService.forward(request.getRequestURI());
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register.do")
  @ResponseBody
  public void register(@RequestParam String cellPhone, @RequestParam String password,
      @RequestParam String validationCode) {
    System.out.println(cellPhone);
    System.out.println(password);
    System.out.println(validationCode);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register_phone_validation.do")
  @ResponseBody
  public String registerPhoneValidation(@RequestParam String cellPhone, HttpSession session) {
    if (!cellPhone.matches("^1[3458]\\d{9}$")) {
      return "手机号码不正确";
    }
    if (phoneValidationService.checkSendAvailable(cellPhone)) {
      String code = RandomStringUtils.randomNumeric(4);
      phoneValidationService.save(new PhoneValidationCode(cellPhone, code));
      smsService.send(cellPhone, "您正在重庆易游天下网站注册帐户，您的手机验证码是" + code);
      session.setAttribute("PHONE_VALIDATION_CODE", code);
      return null;
    }
    return "一分钟只能发送一次验证码，请稍后再试";
  }

}

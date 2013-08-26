package com.cqlybest.www.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.PhoneValidationCode;
import com.cqlybest.common.mongo.bean.User;
import com.cqlybest.common.service.PhoneValidationService;
import com.cqlybest.common.service.SmsService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class UserController extends ControllerHelper {

  @Autowired
  private TemplateService templateService;
  @Autowired
  private PhoneValidationService phoneValidationService;
  @Autowired
  private SmsService smsService;

  @RequestMapping(method = RequestMethod.POST, value = "/user/phone_validation")
  @ResponseBody
  public String registerPhoneValidation(@RequestParam String cellPhone, HttpSession session) {
    if (!cellPhone.matches("^1[3458]\\d{9}$")) {
      return "手机号码不正确";
    }
    if (phoneValidationService.checkSendAvailable(cellPhone)) {
      String code = RandomStringUtils.randomNumeric(4);
      PhoneValidationCode validationCode = new PhoneValidationCode(cellPhone, code);
      phoneValidationService.save(validationCode);
      smsService.send(null, "系统", cellPhone, null, "您正在重庆易游天下网站注册帐户，您的手机验证码是" + code + "，使用一次后失效。");
      session.setAttribute("PHONE_VALIDATION_CODE", validationCode);
      return null;
    }
    return "一分钟只能发送一次验证码，请稍后再试";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/user/register")
  @ResponseBody
  public String register(@RequestParam String cellPhone, @RequestParam String password,
      @RequestParam String validationCode, HttpSession session) {
    PhoneValidationCode code = (PhoneValidationCode) session.getAttribute("PHONE_VALIDATION_CODE");
    if (code == null || !validationCode.equalsIgnoreCase(code.getCode())) {
      return "验证码不正确";
    }
    // TODO
    session.removeAttribute("PHONE_VALIDATION_CODE");
    return null;
  }

  @RequestMapping("/user/home")
  public String home(Model model) {
    setCommonData(model);
    return templateService.getTemplate() + "/user/home";
  }

  @RequestMapping("/user/info")
  public String info(Model model) {
    model.addAttribute("user", getUser());
    return templateService.getTemplate() + "/user/info";
  }

  @RequestMapping("/user/update")
  public ResponseEntity<Object> update(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    User user = getUser();
    if (!user.getId().equals(pk)) {
      return illegal();
    }
    if (name.equals("username")) {
      if (StringUtils.isNotEmpty(user.getUsername())) {
        return illegal();
      }
    }
    if (name.equals("mobile")) {

    }
    if (name.equals("email")) {

    }
    if (name.equals("fullname")) {

    }
    if (name.equals("nickname")) {

    }
    // userService.update(pk, name, value);
    return ok();
  }

}

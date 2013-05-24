package com.cqlybest.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.service.TemplateService;

@Controller
public class IndexController {

  @Autowired
  private TemplateService templateService;

  /**
   * 首页
   */
  @RequestMapping("/index.html")
  public String index() {
    return templateService.forward("/index.html");
  }

  /**
   * 注册
   */
  @RequestMapping("/register.html")
  public String register() {
    return templateService.forward("/register.html");
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register.do")
  @ResponseBody
  public void register(@RequestParam String cellPhone, @RequestParam String password,
      @RequestParam String validationCode) {
    System.out.println(cellPhone);
    System.out.println(password);
    System.out.println(validationCode);
  }

  /**
   * 登录
   */
  @RequestMapping("/login.html")
  public String login() {
    return templateService.forward("/login.html");
  }

  /**
   * 聚合产品页
   */
  @RequestMapping("/group/{id}.html")
  public String group(@PathVariable String id) {
    return templateService.forward("/group/" + id + ".html");
  }

  @RequestMapping("/group/{id}/{f0}-{f1}-{f2}-{f3}-{f4}-{f5}-{f6}-{f7}-{page}.html")
  public String group(HttpServletRequest request) {
    return templateService.forward(request.getRequestURI());
  }

  /**
   * 自定义内容页
   */
  @RequestMapping("/page/{id}.html")
  public String page(@PathVariable String id) {
    return templateService.forward("/page/" + id + ".html");
  }

}

package com.cqlybest.www.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.service.TemplateService;

@Controller
public class IndexController {

  @Autowired
  private TemplateService templateService;

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

}

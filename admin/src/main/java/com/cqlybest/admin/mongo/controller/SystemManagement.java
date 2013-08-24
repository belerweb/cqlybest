package com.cqlybest.admin.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemManagement {

  @RequestMapping("/system.do")
  public String system() {
    return "/v1/system";
  }

  /**
   * 系统信息：环境变量、系统属性
   */
  @RequestMapping("/system/info.do")
  public String info(Model model) {
    model.addAttribute("env", System.getenv());
    model.addAttribute("property", System.getProperties());
    return "/v1/system/info";
  }

}

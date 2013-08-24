package com.cqlybest.admin.mongo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemManagement {

  @RequestMapping("/system.do")
  public String system() {
    return "/v1/system";
  }

}

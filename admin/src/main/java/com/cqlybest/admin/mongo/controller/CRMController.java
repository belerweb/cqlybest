package com.cqlybest.admin.mongo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.common.mongo.service.UserService;

@Controller
public class CRMController {

  @Autowired
  private UserService mongoUserService;

  @RequestMapping("/crm.do")
  public String crm() {
    return "/v1/crm";
  }

  /**
   * 用户登录帐号列表
   */
  @RequestMapping(value = "/crm/login/list.do", method = RequestMethod.GET)
  public String list(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", mongoUserService.queryUser(page, pageSize));
    return "/v1/crm/login/list";
  }

}

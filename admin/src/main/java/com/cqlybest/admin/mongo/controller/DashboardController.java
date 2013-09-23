package com.cqlybest.admin.mongo.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.service.CustomerService;

@Controller
public class DashboardController extends ControllerHelper {

  @Autowired
  private CustomerService customerService;

  /**
   * 生日提醒
   */
  @RequestMapping(value = "/dashboard/birth.do", method = RequestMethod.GET)
  public String birth(Model model) {
    generatePageId(model);
    model.addAttribute("events", customerService.getTodayCustomerEvent());
    return "/v1/dashboard/birth";
  }

  /**
   * 客户资料查询
   */
  @RequestMapping("/dashboard/customer.do")
  public String customer(@RequestParam(required = false) String keyword, Model model) {
    generatePageId(model);
    if (StringUtils.isNotEmpty(keyword)) {
      keyword = keyword.trim();
      if (keyword.contains(":")) {
        String[] kv = keyword.split(":");
        model.addAttribute("result", customerService.getCustomer(kv[0], kv[1], 5));
      } else {
        model.addAttribute("result", customerService.getCustomer(keyword, 5));
      }
    }
    model.addAttribute("keyword", keyword);
    return "/v1/dashboard/customer";
  }
}

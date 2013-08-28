package com.cqlybest.admin.mongo.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping("/home.do")
  public String home(Model model) {
    return "/v1/home";
  }

  @RequestMapping("/error")
  public String error(HttpServletRequest request, Model model) {
    String xRequestedWith = request.getHeader("X-Requested-With");
    model.addAttribute("ajax", "XMLHttpRequest".equals(xRequestedWith));

    Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    model.addAttribute("statusCode", statusCode);
    if (statusCode == 403) {
      return "/error/403";
    }
    if (statusCode == 404) {
      return "/error/404";
    }
    if (statusCode == 400) {
      return "/error/400";
    }

    return "/error/default";
  }

}

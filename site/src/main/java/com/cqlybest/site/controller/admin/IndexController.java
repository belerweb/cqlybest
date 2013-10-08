package com.cqlybest.site.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

  @RequestMapping("/home.do")
  public String home(Model model) {
    return "/v1/home";
  }

}

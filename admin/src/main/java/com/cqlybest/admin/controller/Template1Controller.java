package com.cqlybest.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Template1Controller {

  @RequestMapping(value = "/template1/template.html", method = RequestMethod.GET)
  public void template(Model model) {}

}

package com.cqlybest.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

  @RequestMapping(value = "/site/products.html", method = RequestMethod.GET)
  public void products(Model model) {}


}

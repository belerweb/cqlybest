package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.service.ProductService;

@Controller
public class ProductController {

  @Autowired
  private ProductService productService;

  @RequestMapping(value = "/product/list.html", method = RequestMethod.GET)
  public void products(Model model) {
    model.addAttribute("products", productService.queryProduct());
  }

  @RequestMapping(value = "/product/add.html", method = RequestMethod.GET)
  public void addProduct() {}

  @RequestMapping(value = "/product/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void addProduct(Product product) {
    productService.addProduct(product);
  }


}

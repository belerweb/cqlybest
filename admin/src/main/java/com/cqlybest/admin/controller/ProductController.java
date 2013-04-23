package com.cqlybest.admin.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.JsonService;
import com.cqlybest.common.service.ProductService;

@Controller
public class ProductController {

  @Autowired
  private JsonService jsonService;

  @Autowired
  private ProductService productService;

  @Autowired
  private DestinationService destinationService;

  @RequestMapping(value = "/product/list.html", method = RequestMethod.GET)
  public void products(Model model) {
    model.addAttribute("products", productService.queryProduct());
  }

  @RequestMapping(value = "/product/add.html", method = RequestMethod.GET)
  public void addProduct(Model model) throws JsonGenerationException, JsonMappingException,
      IOException {
    model.addAttribute("dests", jsonService.writeValueAsString(destinationService.getTree()));
  }

  @RequestMapping(value = "/product/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void addProduct(Product product) {
    productService.addProduct(product);
  }


}

package com.cqlybest.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.mongo.bean.Product;
import com.cqlybest.common.mongo.service.ProductService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class ProductController extends ControllerHelper {

  @Autowired
  private TemplateService templateService;
  @Autowired
  private ProductService mongoProductService;

  /**
   * 聚合产品页
   */
  @RequestMapping("/group/{id}.html")
  public String group(@PathVariable String id) {
    return "forward:/group/" + id + "/0-0-0-0-0-0-0-0-0.html";
  }

  /**
   * 聚合产品页
   */
  @RequestMapping("/group/{id}/{f0}-{f1}-{f2}-{f3}-{f4}-{f5}-{f6}-{f7}-{page}.html")
  public Object group(@PathVariable String id, @PathVariable Integer f0, @PathVariable Integer f1,
      @PathVariable Integer f2, @PathVariable Integer f3, @PathVariable Integer f4,
      @PathVariable Integer f5, @PathVariable Integer f6, @PathVariable Integer f7,
      @PathVariable Integer page, Model model) {
    // 菜单不存在或者不是产品聚合菜单
    return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
  }

  @RequestMapping("/product/{id}.html")
  public Object product(@PathVariable String id, Model model) {
    Product product = mongoProductService.getProduct(id);
    model.addAttribute("product", product);
    setCommonData(model);
    return templateService.getTemplate() + "/product_" + product.getType();
  }

}

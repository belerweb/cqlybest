package com.cqlybest.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.Product;
import com.cqlybest.common.mongo.bean.User;
import com.cqlybest.common.mongo.service.FriendlyLinkService;
import com.cqlybest.common.mongo.service.ProductService;
import com.cqlybest.common.mongo.service.SettingsService;

@Controller("siteProductController")
public class ProductController extends ControllerHelper {

  @Autowired
  private ProductService mongoProductService;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  @RequestMapping("/product/{id}.html")
  public Object product(@PathVariable String id, Model model) {
    Product product = mongoProductService.getProduct(id);
    if (product == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    User user = getUser();
    if (Boolean.FALSE.equals(product.getPublished())
        && (user == null || !user.getRoles().contains("ROLE_ADMIN"))) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    model.addAttribute("product", product);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v2/product_" + product.getType();
  }

}

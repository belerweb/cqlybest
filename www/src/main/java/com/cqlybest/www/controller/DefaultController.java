package com.cqlybest.www.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.bean.Menu;
import com.cqlybest.common.service.MenuService;
import com.cqlybest.common.service.SiteService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class DefaultController {

  @Autowired
  private TemplateService templateService;

  @Autowired
  private MenuService menuService;

  @Autowired
  private SiteService siteService;

  @RequestMapping("/default/index.html")
  public void index(Model model) {
    setCommonData(model);
  }

  @RequestMapping("/default/register.html")
  public void register(Model model) {
    setCommonData(model);
  }

  @RequestMapping("/default/pg/{id}.html")
  public Object pg(@PathVariable String id, Model model) {
    Menu menu = menuService.get(id);
    if (menu == null || menu.getMenuType() != 0) {
      // 菜单不存在或者不是产品聚合菜单
      return new ResponseEntity<String>(StringUtils.EMPTY, HttpStatus.NOT_FOUND);
    }

    setCommonData(model);
    return "/default/product_group";
  }

  @RequestMapping("/default/page/{id}.html")
  public Object page(@PathVariable String id, Model model) {
    Menu menu = menuService.get(id);
    if (menu == null || menu.getMenuType() != 1) {
      // 菜单不存在或者不是产品聚合菜单
      return new ResponseEntity<String>(StringUtils.EMPTY, HttpStatus.NOT_FOUND);
    }

    model.addAttribute("page", menu);
    setCommonData(model);
    return "/default/page";
  }

  public void setCommonData(Model model) {
    model.addAttribute("Site", siteService.getSite());
    model.addAttribute("Menu", menuService.getPublishedMenus());
  }

}

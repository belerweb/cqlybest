package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Menu;
import com.cqlybest.common.service.MenuService;
import com.cqlybest.common.service.ProductGroupService;

@Controller
public class MenuController {

  @Autowired
  private MenuService menuService;

  @Autowired
  private ProductGroupService productGroupService;

  @RequestMapping(value = "/menu/list.html", method = RequestMethod.GET)
  public void list(Model model) {
    model.addAttribute("menus", menuService.getAllMenus());
  }

  @RequestMapping(value = "/menu/add.html", method = RequestMethod.GET)
  public void add(Model model) {
    model.addAttribute("productGroups", productGroupService.getAllProductGroup());
  }

  @RequestMapping(value = "/menu/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void add(Menu menu) {
    menuService.add(menu);
  }

  @RequestMapping("/menu/delete.html")
  @ResponseBody
  public void delete(String id) {
    menuService.delete(id);
  }

  @RequestMapping(value = "/menu/modify.html", method = RequestMethod.GET)
  public void modify(@RequestParam String id, Model model) {
    model.addAttribute("productGroups", productGroupService.getAllProductGroup());
    model.addAttribute("menu", menuService.get(id));
  }

  @RequestMapping(value = "/menu/modify.html", method = RequestMethod.POST)
  @ResponseBody
  public void modify(Menu menu) {
    menuService.modify(menu);
  }

  @RequestMapping("/menu/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam String id, @RequestParam boolean published) {
    menuService.togglePublished(id, published);
  }

  @RequestMapping("/menu/up.html")
  @ResponseBody
  public void up(@RequestParam String id) {
    menuService.moveUp(id);
  }

  @RequestMapping("/menu/down.html")
  @ResponseBody
  public void down(@RequestParam String id) {
    menuService.moveDown(id);
  }

}

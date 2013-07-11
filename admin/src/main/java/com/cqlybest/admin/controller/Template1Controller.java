package com.cqlybest.admin.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.template1.Template1IndexPoster;
import com.cqlybest.common.bean.template1.Template1Menu;
import com.cqlybest.common.bean.template1.Template1ProductGroup;
import com.cqlybest.common.bean.template1.Template1SubMenu;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ProductGroupService;
import com.cqlybest.common.service.Template1Service;

@Controller
public class Template1Controller {

  @Autowired
  private Template1Service template1Service;

  @Autowired
  private ProductGroupService productGroupService;

  @Autowired
  private DictService dictService;

  @RequestMapping(value = "/template1/template.html", method = RequestMethod.GET)
  public void template(Model model) {
    model.addAttribute("posters", template1Service.getPosters());
    model.addAttribute("menus", template1Service.getAllMenus());
    model.addAttribute("productGroups", productGroupService.getAllProductGroup());
    model.addAttribute("template1ProductGroups", template1Service.getAllIndexProductGroups());
  }

  @RequestMapping("/template1/poster.html")
  public void list(Model model) {
    model.addAttribute("posters", template1Service.getPosters());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/template1/poster/add.html")
  public void add() {}

  @RequestMapping(method = RequestMethod.POST, value = "/template1/poster/add.html")
  @ResponseBody
  public void add(Template1IndexPoster poster) {
    template1Service.add(poster);
  }

  @RequestMapping("/template1/poster/delete.html")
  @ResponseBody
  public void delete(@RequestParam Integer id) {
    template1Service.delete(id);
  }

  @RequestMapping("/template1/poster/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam Integer id, @RequestParam boolean published) {
    template1Service.togglePublished(id, published);
  }

  @RequestMapping(value = "/template1/menu.html", method = RequestMethod.GET)
  public void menu(Model model) {
    model.addAttribute("menus", template1Service.getAllMenus());
  }

  @RequestMapping(value = "/template1/menu/add.html", method = RequestMethod.GET)
  public void add(Model model) {
    model.addAttribute("productGroups", productGroupService.getAllProductGroup());
  }

  @RequestMapping(value = "/template1/menu/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void add(Template1Menu menu, @RequestParam(required = false) List<Integer> _menuTypes,
      @RequestParam(required = false) List<String> _menuValues,
      @RequestParam(required = false) List<String> _titles) {
    menu.setId(UUID.randomUUID().toString());
    if (menu.getMenuType() == 0 && _menuTypes != null) {// 聚合菜单
      Set<Template1SubMenu> subMenus = new HashSet<>();
      for (int i = 0; i < _menuTypes.size(); i++) {
        Template1SubMenu _menu = new Template1SubMenu();
        _menu.setMenuId(menu.getId());
        _menu.setTitle(_titles.get(i));
        _menu.setMenuType(_menuTypes.get(i));
        _menu.setMenuValue(_menuValues.get(i));
        subMenus.add(_menu);
      }
      menu.setSubMenus(subMenus);
    }
    template1Service.add(menu);
  }

  @RequestMapping("/template1/menu/delete.html")
  @ResponseBody
  public void delete(String id) {
    template1Service.delete(id);
  }

  @RequestMapping(value = "/template1/menu/modify.html", method = RequestMethod.GET)
  public void modify(@RequestParam String id, Model model) {
    model.addAttribute("productGroups", productGroupService.getAllProductGroup());
    model.addAttribute("menu", template1Service.get(id));

    model.addAttribute("traffics", dictService.getDict(Constant.DICT_TRAFFIC));
    model.addAttribute("types", dictService.getDict(Constant.DICT_PRODUCT_TYPE));
    model.addAttribute("grades", dictService.getDict(Constant.DICT_PRODUCT_GRADE));
    model.addAttribute("keywords", dictService.getDict(Constant.DICT_TAG));
    model.addAttribute("departureCities", dictService.getDict(Constant.DICT_DEPARTURE_CITY));
    model.addAttribute("destinations", dictService.getDict(Constant.DICT_DESTINATION));
  }

  @RequestMapping(value = "/template1/menu/modify.html", method = RequestMethod.POST)
  @ResponseBody
  public void modify(Template1Menu menu, @RequestParam(required = false) List<Integer> _menuTypes,
      @RequestParam(required = false) List<String> _menuValues,
      @RequestParam(required = false) List<String> _titles,
      @RequestParam(required = false) List<Integer> _ids) {
    if (menu.getMenuType() == 0 && _menuTypes != null) {// 聚合菜单
      Set<Template1SubMenu> subMenus = new HashSet<>();
      for (int i = 0; i < _menuTypes.size(); i++) {
        Template1SubMenu _menu = new Template1SubMenu();
        _menu.setId(_ids.get(i));
        _menu.setMenuId(menu.getId());;
        _menu.setTitle(_titles.get(i));
        _menu.setMenuType(_menuTypes.get(i));
        _menu.setMenuValue(_menuValues.get(i));
        subMenus.add(_menu);
      }
      menu.setSubMenus(subMenus);
    }
    template1Service.modify(menu);
  }

  @RequestMapping("/template1/menu/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam String id, @RequestParam boolean published) {
    template1Service.togglePublished(id, published);
  }

  @RequestMapping("/template1/menu/up.html")
  @ResponseBody
  public void up(@RequestParam String id) {
    template1Service.moveUp(id);
  }

  @RequestMapping("/template1/menu/down.html")
  @ResponseBody
  public void down(@RequestParam String id) {
    template1Service.moveDown(id);
  }

  @RequestMapping(value = "/template1/product_group.html", method = RequestMethod.GET)
  public void productGroup(Model model) {
    model.addAttribute("productGroups", productGroupService.getAllProductGroup());
    model.addAttribute("template1ProductGroups", template1Service.getAllIndexProductGroups());
  }

  @RequestMapping(value = "/template1/product_group/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void addProductGroup(Template1ProductGroup group) {
    template1Service.add(group);
  }

  @RequestMapping(value = "/template1/product_group/delete.html", method = RequestMethod.GET)
  @ResponseBody
  public void deleteProductGroup(Template1ProductGroup group) {
    template1Service.delete(group);
  }

  @RequestMapping(value = "/template1/product_group/order.html", method = RequestMethod.POST)
  @ResponseBody
  public void orderProductGroup(@RequestParam(value = "ids[]") Integer[] ids,
      @RequestParam(value = "orders[]") Integer[] orders) {
    template1Service.orderProductGroup(ids, orders);
  }

}

package com.cqlybest.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.ProductGroup;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ProductGroupService;

@Controller
public class ProductGroupController {

  @Autowired
  private ProductGroupService productGroupService;
  @Autowired
  private DictService dictService;
  @Autowired
  private DestinationService destinationService;

  /**
   * 添加产品聚合
   */
  @RequestMapping(value = "/product_group/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String name) {
    ProductGroup group = new ProductGroup();
    group.setName(name);
    productGroupService.add(group);
    return group.getId();
  }

  /**
   * 修改产品聚合
   */
  @RequestMapping(value = "/product_group/update.do", method = RequestMethod.GET)
  public void update(@RequestParam String id, Model model) {
    model.addAttribute("group", productGroupService.getProductGroup(id));
  }

  /**
   * 修改产品聚合
   */
  @RequestMapping(value = "/product_group/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void update(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) throws Exception {
    Object _value = value == null ? StringUtils.join(values, ",") : value;
    productGroupService.update(pk, name, _value);
  }

  @RequestMapping(value = "/product_group/list.html", method = RequestMethod.GET)
  public void list(Model model) {
    model.addAttribute("groups", productGroupService.getAllProductGroup());
  }

  @RequestMapping("/product_group/delete.html")
  @ResponseBody
  public void delete(@RequestParam String id) {
    productGroupService.delete(id);
  }

  @RequestMapping("/product_group/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam String id, @RequestParam boolean published) {
    productGroupService.update(id, "published", published);
  }

}

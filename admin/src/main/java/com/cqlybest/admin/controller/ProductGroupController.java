package com.cqlybest.admin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.ProductGroup;
import com.cqlybest.common.service.ProductGroupService;

@Controller
public class ProductGroupController {

  @Autowired
  private ProductGroupService productGroupService;

  @RequestMapping(value = "/product_group/list.html", method = RequestMethod.GET)
  public void list(Model model) {
    model.addAttribute("groups", productGroupService.getAllProductGroup());
  }

  @RequestMapping(value = "/product_group/add.html", method = RequestMethod.GET)
  public void add(Model model) {}

  @RequestMapping(value = "/product_group/modify.html", method = RequestMethod.GET)
  public void modify(@RequestParam String id, Model model) {
    ProductGroup group = productGroupService.getProductGroup(id);
    List<Integer> groupTypes = new ArrayList<>();
    List<String> groupValues = new ArrayList<>();
    if (group.getGroupType1() != null) {
      groupTypes.add(group.getGroupType1());
      groupValues.add(group.getGroupValue1());
    }
    if (group.getGroupType2() != null) {
      groupTypes.add(group.getGroupType2());
      groupValues.add(group.getGroupValue2());
    }
    if (group.getGroupType3() != null) {
      groupTypes.add(group.getGroupType3());
      groupValues.add(group.getGroupValue3());
    }
    if (group.getGroupType4() != null) {
      groupTypes.add(group.getGroupType4());
      groupValues.add(group.getGroupValue4());
    }
    if (group.getGroupType5() != null) {
      groupTypes.add(group.getGroupType5());
      groupValues.add(group.getGroupValue5());
    }
    model.addAttribute("groupTypes", groupTypes);
    model.addAttribute("groupValues", groupValues);
    model.addAttribute("group", group);
  }

  @RequestMapping(value = {"/product_group/add.html", "/product_group/modify.html"}, method = RequestMethod.POST)
  @ResponseBody
  public void edit(@RequestParam(required = false) String id, @RequestParam String name,
      @RequestParam(required = false) List<Integer> groupTypes,
      @RequestParam(required = false) List<String> groupValues) {
    ProductGroup group = new ProductGroup();
    group.setId(id == null ? UUID.randomUUID().toString() : id);
    group.setName(name);
    if (groupTypes != null) {
      int size = groupTypes.size();
      if (size >= 1) {
        group.setGroupType1(groupTypes.get(0));
        group.setGroupValue1(groupValues.get(0));
      }
      if (size >= 2) {
        group.setGroupType2(groupTypes.get(1));
        group.setGroupValue2(groupValues.get(1));
      }
      if (size >= 3) {
        group.setGroupType3(groupTypes.get(2));
        group.setGroupValue3(groupValues.get(2));
      }
      if (size >= 4) {
        group.setGroupType4(groupTypes.get(3));
        group.setGroupValue4(groupValues.get(3));
      }
      if (size >= 5) {
        group.setGroupType5(groupTypes.get(4));
        group.setGroupValue5(groupValues.get(4));
      }
    }
    group.setPublished(false);
    productGroupService.edit(group);
  }

  @RequestMapping("/product_group/delete.html")
  @ResponseBody
  public void delete(@RequestParam String id) {
    productGroupService.delete(id);
  }

  @RequestMapping("/product_group/toggle.html")
  @ResponseBody
  public void delete(@RequestParam String id, @RequestParam boolean published) {
    productGroupService.togglePublished(id, published);
  }

}

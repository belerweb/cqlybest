package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cqlybest.common.service.UserService;

@Controller
public class AdministratorController {

  @Autowired
  private UserService userservice;

  @RequestMapping(value = "/administrator/add.html", method = RequestMethod.GET)
  public void add() {}

  @RequestMapping(value = "/administrator/add.html", method = RequestMethod.POST)
  public void add(Model model) {}

  public void delete() {

  }

  @RequestMapping(value = "/administrator/list.html", method = RequestMethod.GET)
  public void list(Model model) {
    model.addAttribute("admins", null);

  }

  @RequestMapping(value = "/administrator/modify.html", method = RequestMethod.GET)
  public void modify() {}

  @RequestMapping(value = "/administrator/modify.html", method = RequestMethod.POST)
  public void modify(Model model) {}


}

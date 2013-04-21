package com.cqlybest.admin.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.service.UserService;

@Controller
public class AdministratorController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/administrator/add.html", method = RequestMethod.GET)
  public void add() {}

  @RequestMapping(value = "/administrator/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void add(LoginUser admin) {
    admin.setId(UUID.randomUUID().toString());
    Set<Role> roles = new HashSet<>();
    roles.add(Role.ADMIN);
    roles.add(Role.CUSTOMER);
    admin.setRoles(roles);
    userService.addUser(admin);
  }

  public void delete() {

  }

  @RequestMapping(value = "/administrator/list.html", method = RequestMethod.GET)
  public void list(Model model) {
    model.addAttribute("admins", userService.getUserList(Role.ADMIN, 0, 10));
  }

  @RequestMapping(value = "/administrator/modify.html", method = RequestMethod.GET)
  public void modify() {}

  @RequestMapping(value = "/administrator/modify.html", method = RequestMethod.POST)
  public void modify(Model model) {}


}

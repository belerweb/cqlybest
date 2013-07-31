package com.cqlybest.admin.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.service.UserService;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/user/list.do", method = RequestMethod.GET)
  public void list(@RequestParam(defaultValue = "1") Integer page, Model model) {
    int _page = Math.max(page, 1);
    Integer pageSize = 10;
    model.addAttribute("page", _page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", userService.getUserListTotal());
    model.addAttribute("users", userService.getUserList(page, pageSize));
  }

  @RequestMapping("/user/toggleadmin.do")
  @ResponseBody
  public void toggleadmin(@RequestParam String id) {
    userService.toggleadmin(id);
  }

  /**
   * 增加用户
   */
  @RequestMapping(value = "/user/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String role, @RequestParam String name) {
    Set<Role> roles = new HashSet<>();
    if (Role.GROUP.getRole().equals(role)) {
      roles.add(Role.GROUP);
    } else if (Role.AGENCY.getRole().equals(role)) {
      roles.add(Role.AGENCY);
    }

    LoginUser user = new LoginUser();
    user.setFullname(name);
    user.setRoles(roles);
    userService.addUser(user);
    return user.getId();
  }

  @RequestMapping(value = "/user/update.do", method = RequestMethod.GET)
  public void fit(@RequestParam String id, Model model) {
    LoginUser user = userService.getUser(id);
    model.addAttribute("user", user);
  }

  @RequestMapping(value = "/user/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void fit(@RequestParam String pk, @RequestParam String name, @RequestParam String value,
      Model model) {
    Object _value = value;
    if ("password".equals(name)) {
      _value = new ShaPasswordEncoder(256).encodePassword(value, null);
    }
    userService.update(pk, name, _value);
  }

  /**
   * 删除
   */
  @RequestMapping("/user/delete.do")
  @ResponseBody
  public void delete(@RequestParam String id) {
    LoginUser user = userService.getUser(id);
    userService.delete(user);
    // TODO 判断用户角色并删除相关信息
  }

}

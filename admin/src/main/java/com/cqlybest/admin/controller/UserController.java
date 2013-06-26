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

  /**
   * 散客列表
   */
  @RequestMapping(value = "/user/fit.html", method = RequestMethod.GET)
  public void fit(@RequestParam(defaultValue = "1") Integer page, Model model) {
    Integer pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", userService.getUserListTotal(Role.FIT));
    model.addAttribute("users", userService.getUserList(Role.FIT, page, pageSize));
  }

  /**
   * 团体列表
   */
  @RequestMapping(value = "/user/group.html", method = RequestMethod.GET)
  public void group(@RequestParam(defaultValue = "1") Integer page, Model model) {
    Integer pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", userService.getUserListTotal(Role.GROUP));
    model.addAttribute("users", userService.getUserList(Role.GROUP, page, pageSize));
  }

  /**
   * 旅行社列表
   */
  @RequestMapping(value = "/user/agency.html", method = RequestMethod.GET)
  public void agency(@RequestParam(defaultValue = "1") Integer page, Model model) {
    Integer pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", userService.getUserListTotal(Role.AGENCY));
    model.addAttribute("users", userService.getUserList(Role.AGENCY, page, pageSize));
  }

  /**
   * 管理员列表
   */
  @RequestMapping(value = "/user/admin.html", method = RequestMethod.GET)
  public void admin(@RequestParam(defaultValue = "1") Integer page, Model model) {
    Integer pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", userService.getUserListTotal(Role.ADMIN));
    model.addAttribute("users", userService.getUserList(Role.ADMIN, page, pageSize));
  }

  /**
   * 增加用户
   */
  @RequestMapping(value = "/user/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String role, @RequestParam String name) {
    Set<Role> roles = new HashSet<>();
    if ("fit".equals(role)) {
      roles.add(Role.FIT);
    } else if ("group".equals(role)) {
      roles.add(Role.GROUP);
    } else if ("agency".equals(role)) {
      roles.add(Role.AGENCY);
    } else if ("admin".equals(role)) {
      roles.add(Role.ADMIN);
    }
    LoginUser user = new LoginUser();
    user.setFullname(name);
    user.setRoles(roles);
    userService.addUser(user);
    return user.getId();
  }

  /**
   * 修改散客资料
   */
  @RequestMapping(value = "/user/fit/update.do", method = RequestMethod.GET)
  public void fit(@RequestParam String id, Model model) {
    LoginUser user = userService.getUser(id);
    model.addAttribute("user", user);
  }

  /**
   * 修改散客资料
   */
  @RequestMapping(value = "/user/fit/update.do", method = RequestMethod.POST)
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
   * 修改团体资料
   */
  @RequestMapping(value = "/user/group/update.do", method = RequestMethod.GET)
  public void group(@RequestParam String id, Model model) {
    LoginUser user = userService.getUser(id);
    model.addAttribute("user", user);
  }

  /**
   * 修改团体资料
   */
  @RequestMapping(value = "/user/group/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void group(@RequestParam String pk, @RequestParam String name, @RequestParam String value,
      Model model) {
    Object _value = value;
    if ("password".equals(name)) {
      _value = new ShaPasswordEncoder(256).encodePassword(value, null);
    }
    userService.update(pk, name, _value);
  }

  /**
   * 修改旅行社资料
   */
  @RequestMapping(value = "/user/agency/update.do", method = RequestMethod.GET)
  public void agency(@RequestParam String id, Model model) {
    LoginUser user = userService.getUser(id);
    model.addAttribute("user", user);
  }

  /**
   * 修改旅行社资料
   */
  @RequestMapping(value = "/user/agency/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void agency(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value, Model model) {
    Object _value = value;
    if ("password".equals(name)) {
      _value = new ShaPasswordEncoder(256).encodePassword(value, null);
    }
    userService.update(pk, name, _value);
  }

  /**
   * 修改管理员资料
   */
  @RequestMapping(value = "/user/admin/update.do", method = RequestMethod.GET)
  public void admin(@RequestParam String id, Model model) {
    LoginUser user = userService.getUser(id);
    model.addAttribute("user", user);
  }

  /**
   * 修改管理员资料
   */
  @RequestMapping(value = "/user/admin/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void admin(@RequestParam String pk, @RequestParam String name, @RequestParam String value,
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

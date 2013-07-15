package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Account;
import com.cqlybest.common.service.AccountService;

@Controller
public class AccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping("/account/list.do")
  public void list(Model model) {
    model.addAttribute("accounts", accountService.getAccounts());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/account/add.do")
  public void add() {}

  @RequestMapping(method = RequestMethod.POST, value = "/account/add.do")
  @ResponseBody
  public void add(Account account) {
    accountService.add(account);
  }

  @RequestMapping("/account/delete.do")
  @ResponseBody
  public void delete(@RequestParam Integer id) {
    accountService.delete(id);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/account/modify.do")
  public void modify(@RequestParam Integer id, Model model) {
    model.addAttribute("account", accountService.getAccount(id));
  }

  @RequestMapping(method = RequestMethod.POST, value = "/account/modify.do")
  @ResponseBody
  public void modify(Account account) {
    accountService.modify(account);
  }

}

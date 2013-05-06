package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.common.service.SmsService;

@Controller
public class SmsController {

  @Autowired
  private SmsService smsService;

  @RequestMapping(value = "/sms/list.html", method = RequestMethod.GET)
  public void list(@RequestParam Integer page, Model model) {
    model.addAttribute("list", null);
  }

  @RequestMapping(value = "/sms/send.html", method = RequestMethod.POST)
  public void send(@RequestParam String phone, @RequestParam String content, Model model) {
    smsService.send(phone, content);
  }


}

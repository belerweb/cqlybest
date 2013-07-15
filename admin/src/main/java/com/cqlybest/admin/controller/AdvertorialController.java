package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Advertorial;
import com.cqlybest.common.service.AdvertorialService;

@Controller
public class AdvertorialController {

  @Autowired
  private AdvertorialService advertorialService;

  @RequestMapping("/advertorial/list.do")
  public void list(Model model) {
    model.addAttribute("advertorials", advertorialService.getAdvertorials());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/advertorial/add.do")
  public void add() {}

  @RequestMapping(method = RequestMethod.POST, value = "/advertorial/add.do")
  @ResponseBody
  public void add(Advertorial advertorial) {
    advertorialService.add(advertorial);
  }

  @RequestMapping("/advertorial/delete.do")
  @ResponseBody
  public void delete(@RequestParam Integer id) {
    advertorialService.delete(id);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/advertorial/modify.do")
  public void modify(@RequestParam Integer id, Model model) {
    model.addAttribute("advertorial", advertorialService.getAdvertorial(id));
  }

  @RequestMapping(method = RequestMethod.POST, value = "/advertorial/modify.do")
  @ResponseBody
  public void modify(Advertorial advertorial) {
    advertorialService.modify(advertorial);
  }

  @RequestMapping("/advertorial/view.do")
  public void view(@RequestParam Integer id, Model model) {
    model.addAttribute("advertorial", advertorialService.getAdvertorial(id));
  }

}

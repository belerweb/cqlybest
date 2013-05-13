package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.template1.Template1IndexPoster;
import com.cqlybest.common.service.Template1Service;

@Controller
public class Template1Controller {

  @Autowired
  private Template1Service template1Service;

  @RequestMapping(value = "/template1/template.html", method = RequestMethod.GET)
  public void template(Model model) {
    model.addAttribute("posters", template1Service.getPosters());
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

}

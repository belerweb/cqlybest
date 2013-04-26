package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.IndexPoster;
import com.cqlybest.common.service.IndexPosterService;

@Controller
public class IndexPosterController {

  @Autowired
  private IndexPosterService indexPosterService;

  @RequestMapping("/poster/list.html")
  public void list(Model model) {
    model.addAttribute("posters", indexPosterService.getPosters());
  }

  @RequestMapping(method = RequestMethod.GET, value = "/poster/add.html")
  public void add() {}

  @RequestMapping(method = RequestMethod.POST, value = "/poster/add.html")
  @ResponseBody
  public void add(IndexPoster poster) {
    indexPosterService.add(poster);
  }

  @RequestMapping("/poster/delete.html")
  @ResponseBody
  public void delete(@RequestParam Integer id) {
    indexPosterService.delete(id);
  }

  @RequestMapping("/poster/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam Integer id, @RequestParam boolean published) {
    indexPosterService.togglePublished(id, published);
  }

}

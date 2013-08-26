package com.cqlybest.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.service.ImageService;

@Controller
public class IndexController extends ControllerHelper {


  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private ImageService imageService;

  /**
   * 首页
   */
  @RequestMapping("/index.html")
  public String index(Model model) {
    model.addAttribute("posters", template1Service.getPublishedPosters());// 海报
    model.addAttribute("result", mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE));
    setCommonData(model);
    return "/v1/index";
  }


  @RequestMapping("/error.html")
  public void error(HttpServletRequest request, Model model) {}

}

package com.cqlybest.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.maldives.MaldivesSeaIsland;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.ProductService;

@Controller
public class IndexController extends ControllerHelper {


  @Autowired
  private ProductService productService;
  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private DictService dictService;
  @Autowired
  private ImageService imageService;

  /**
   * 首页
   */
  @RequestMapping("/index.html")
  public String index(Model model) {
    model.addAttribute("posters", template1Service.getPublishedPosters());// 海报
    model.addAttribute("specials", productService.getSpecialProduct(4));// 特价
    model.addAttribute("recommendeds", productService.getRecommendedProduct(2));// 推荐
    model.addAttribute("hots", productService.getHotProduct(10));// 热门

    List<MaldivesSeaIsland> maldivesIslands = maldivesService.list(1, 5);
    for (MaldivesSeaIsland island : maldivesIslands) {
      island.setHotelPictures(imageService.getImages(Constant.IMAGE_MALDIVES_HOTEL_PICTURE, island
          .getId()));
    }
    model.addAttribute("maldivesIslands", maldivesIslands);

    setCommonData(model);
    return "/v1/index";
  }


  @RequestMapping("/error.html")
  public void error(HttpServletRequest request, Model model) {}

}

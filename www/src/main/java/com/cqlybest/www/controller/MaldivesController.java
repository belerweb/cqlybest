package com.cqlybest.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.MaldivesSeaIsland;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class MaldivesController extends ControllerHelper {

  @Autowired
  private TemplateService templateService;
  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ImageService imageService;

  @RequestMapping("/maldives.html")
  public Object maldives(Model model) {
    List<MaldivesSeaIsland> islands = maldivesService.list(1, Integer.MAX_VALUE);
    for (MaldivesSeaIsland island : islands) {
      island.setHotelPictures(imageService.getImages(Constant.IMAGE_MALDIVES_HOTEL_PICTURE, island
          .getId()));
    }
    model.addAttribute("islands", islands);
    setCommonData(model);
    return templateService.getTemplate() + "/maldives";
  }

  @RequestMapping("/maldives/{id}.html")
  public Object maldives(@PathVariable String id, Model model) {
    MaldivesSeaIsland island = maldivesService.get(id);
    if (island == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    model.addAttribute("island", island);
    model.addAttribute("islandProducts", productService.getMaldivesProductByIsland(id, 5));

    setCommonData(model);
    return templateService.getTemplate() + "/maldives_island";
  }

}

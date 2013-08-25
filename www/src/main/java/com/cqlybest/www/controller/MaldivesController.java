package com.cqlybest.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class MaldivesController extends ControllerHelper {

  @Autowired
  private TemplateService templateService;
  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ImageService imageService;

  @RequestMapping("/maldives.html")
  public Object maldives(Model model) {
    model.addAttribute("result", mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE));
    setCommonData(model);
    return templateService.getTemplate() + "/maldives";
  }

  @RequestMapping("/maldives/{id}.html")
  public Object maldives(@PathVariable String id, Model model) {
    MaldivesIsland island = mongoMaldivesService.getIsland(id);
    if (island == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    model.addAttribute("island", island);
    setCommonData(model);
    return templateService.getTemplate() + "/maldives_island";
  }

}

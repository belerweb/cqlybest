package com.cqlybest.www.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.bean.MaldivesSeaIsland;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.OptionService;
import com.cqlybest.common.service.Template1Service;
import com.cqlybest.common.service.TemplateService;

@Controller
public class MaldivesController {

  @Autowired
  private Template1Service template1Service;
  @Autowired
  private TemplateService templateService;
  @Autowired
  private OptionService optionService;
  @Autowired
  private MaldivesService maldivesService;

  @RequestMapping("/maldives/{id}.html")
  public Object maldives(@PathVariable String id, Model model) {
    MaldivesSeaIsland island = maldivesService.get(id);
    if (island == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("island", island);
    setCommonData(model);
    return templateService.getTemplate() + "/maldives";
  }

  private void setCommonData(Model model) {
    model.addAttribute("Options", optionService.getOptions());
    model.addAttribute("Menu", template1Service.getPublishedMenus());
  }

}

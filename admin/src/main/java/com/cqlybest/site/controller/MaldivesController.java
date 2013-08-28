package com.cqlybest.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.common.service.FriendlyLinkService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.Template1Service;

@Controller("siteMaldivesController")
public class MaldivesController extends ControllerHelper {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private ImageService imageService;
  @Autowired
  protected Template1Service template1Service;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  @RequestMapping("/maldives.html")
  public Object maldives(Model model) {
    model.addAttribute("result", mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE));
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Menu", template1Service.getPublishedMenus());
    model.addAttribute("Links", friendlyLinkService.list(1, Integer.MAX_VALUE));
    return "/v2/maldives";
  }

  @RequestMapping("/maldives/{id}.html")
  public Object maldives(@PathVariable String id, Model model) {
    MaldivesIsland island = mongoMaldivesService.getIsland(id);
    if (island == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    model.addAttribute("island", island);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Menu", template1Service.getPublishedMenus());
    model.addAttribute("Links", friendlyLinkService.list(1, Integer.MAX_VALUE));
    return "/v2/maldives_island";
  }

}

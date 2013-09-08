package com.cqlybest.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.service.FriendlyLinkService;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.SettingsService;

@Controller
public class MaldivesV5Controller extends ControllerHelper {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  @RequestMapping("/maldives5.html")
  public Object maldives(HttpServletRequest request, Model model) {
    model.addAttribute("result", mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE));
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/maldives/main";
  }

  @RequestMapping("/maldives5/{id}.html")
  public Object maldives(@PathVariable String id, HttpServletRequest request, Model model) {
    MaldivesIsland island = mongoMaldivesService.getIsland(id);
    if (island == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("island", island);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/maldives/island";
  }

}

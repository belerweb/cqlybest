package com.cqlybest.weibo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.cqlybest.common.service.FriendlyLinkService;
import com.cqlybest.common.service.OptionService;
import com.cqlybest.common.service.Template1Service;


public abstract class ControllerHelper extends com.cqlybest.common.controller.ControllerHelper {

  @Autowired
  protected Template1Service template1Service;
  @Autowired
  protected OptionService optionService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  protected void setCommonData(Model model) {
    model.addAttribute("Options", optionService.getOptions());
    model.addAttribute("Menu", template1Service.getPublishedMenus());
    model.addAttribute("Links", friendlyLinkService.list(1, Integer.MAX_VALUE));
  }

}

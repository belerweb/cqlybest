package com.cqlybest.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.common.service.FriendlyLinkService;
import com.cqlybest.common.service.Template1Service;

@Controller("siteIndexController")
public class IndexController {

  @Autowired
  protected Template1Service template1Service;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  @RequestMapping( {"/", "/index.html"})
  public Object index(HttpServletRequest request, Model model) {
    String host = request.getServerName();
    if (host.startsWith("admin.")) {

    }
    if (host.startsWith("m.")) {

    }
    if (host.startsWith("weibo.")) {

    }
    if (host.startsWith("weixin.")) {

    }

    model.addAttribute("posters", template1Service.getPublishedPosters());// 海报
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Menu", template1Service.getPublishedMenus());
    model.addAttribute("Links", friendlyLinkService.list(1, Integer.MAX_VALUE));
    return "/v2/index";
  }

}

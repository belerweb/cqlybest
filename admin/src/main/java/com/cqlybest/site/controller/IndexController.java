package com.cqlybest.site.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.bean.Page;
import com.cqlybest.common.mongo.bean.page.Section;
import com.cqlybest.common.mongo.service.FriendlyLinkService;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.PageService;
import com.cqlybest.common.mongo.service.ProductService;
import com.cqlybest.common.mongo.service.SettingsService;

@Controller("siteIndexController")
public class IndexController extends ControllerHelper {

  @Autowired
  private SettingsService settingsService;
  @Autowired
  private PageService pageService;
  @Autowired
  private FriendlyLinkService friendlyLinkService;
  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private ProductService mongoProductService;

  @RequestMapping("/robots.txt")
  public Object robots(HttpServletRequest request) {
    String host = request.getServerName();
    if (host.startsWith("admin.")) {
      return notFound();
    }
    return "/robots";
  }

  @RequestMapping({"/", "/index.html"})
  public Object index(@RequestParam(required = false) String cid,
      @RequestParam(required = false) String sub_appkey, HttpServletRequest request, Model model) {
    String host = request.getServerName();
    if (host.startsWith("admin.")) {
      return "/v1/login";
    }
    if (host.startsWith("m.")) {
      Page page = pageService.getPage("www.index");
      model.addAttribute("posters", page.getPosters());// 海报
      model.addAttribute("result", mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE));
      model.addAttribute("Settings", settingsService.getSettings());
      model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
      return "/v3/index";
    }

    if (host.startsWith("weibo.")) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        if (StringUtils.isBlank(cid) || StringUtils.isBlank(sub_appkey)) {
          return illegal();
        }

        HttpSession session = request.getSession();
        session.setAttribute("cid", StringUtils.isEmpty(cid) ? null : cid);
        session.setAttribute("sub_appkey", StringUtils.isEmpty(sub_appkey) ? null : sub_appkey);
        return "redirect:/weibo/security/proxy";
      }

      List<MaldivesIsland> islands =
          mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE).getItems();
      List<MaldivesIsland> result = new ArrayList<>();
      for (MaldivesIsland island : islands) {
        if (!island.getHotelPictures().isEmpty()) {
          result.add(island);
        }
      }
      model.addAttribute("islands", result);
      model.addAttribute("Settings", settingsService.getSettings());
      return "/v4/index";
    }

    Page page = pageService.getPage("www.index");
    for (Section section : page.getContents()) {
      int pageSize = Integer.MAX_VALUE;
      if (section.getNumber() != null && section.getNumber() > 0) {
        pageSize = section.getNumber();
      }
      if (Section.TYPE_PRODUCT.equals(section.getType())) {
        section.setQueryResult(mongoProductService.queryProduct(section.getPc(), 0, pageSize));
      }
      if (Section.TYPE_MALDIVES.equals(section.getType())) {
        section.setQueryResult(mongoMaldivesService.queryIsland(section.getMdc(), 0, pageSize));
      }
    }
    for (Section section : page.getSidebars()) {
      int pageSize = Integer.MAX_VALUE;
      if (section.getNumber() != null && section.getNumber() > 0) {
        pageSize = section.getNumber();
      }
      if (Section.TYPE_PRODUCT.equals(section.getType())) {
        section.setQueryResult(mongoProductService.queryProduct(section.getPc(), 0, pageSize));
      }
      if (Section.TYPE_MALDIVES.equals(section.getType())) {
        section.setQueryResult(mongoMaldivesService.queryIsland(section.getMdc(), 0, pageSize));
      }
    }
    model.addAttribute("Page", page);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/index/index";
  }

  @RequestMapping("/login.html")
  public Object login(HttpServletRequest request, Model model) {
    String host = request.getServerName();
    if (host.startsWith("admin.")) {
      return "/v1/login";
    }

    model.addAttribute("Settings", settingsService.getSettings());
    return "/v2/login";
  }

  @RequestMapping("/redirect")
  public Object redirect(HttpServletRequest request, Model model) {
    String host = request.getServerName();
    if (host.startsWith("admin.")) {
      return "redirect:/home.do";
    }

    return "redirect:/index.html";
  }

  @RequestMapping("/soon.html")
  public Object comingSoon() {
    return "/v5/soon";
  }
}

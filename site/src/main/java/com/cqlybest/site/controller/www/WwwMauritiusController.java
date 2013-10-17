package com.cqlybest.site.controller.www;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.bean.MauritiusHotel;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.service.FriendlyLinkService;
import com.cqlybest.common.service.MauritiusService;
import com.cqlybest.common.service.SettingsService;
import com.googlecode.mjorm.query.Query;
import com.googlecode.mjorm.query.QueryGroup;
import com.googlecode.mjorm.query.criteria.DocumentCriterion;
import com.googlecode.mjorm.query.criteria.GroupedQueryCriterion;
import com.googlecode.mjorm.query.criteria.GroupedQueryCriterion.Group;

@Controller
public class WwwMauritiusController extends ControllerHelper {

  @Autowired
  private MauritiusService mauritiusService;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  @RequestMapping("/mauritius.html")
  public Object mauritius(HttpServletRequest request, Model model) {
    model.addAttribute("result", mauritiusService.queryHotel(0, Integer.MAX_VALUE));
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    // return "/v2/mauritius";
    return "/v5/mauritius/main";
  }

  @RequestMapping("/mauritius/{id}.html")
  public Object mauritius(@PathVariable String id, HttpServletRequest request, Model model) {
    String host = request.getServerName();
    if (host.startsWith("m.")) {
      // List<DocumentCriterion> conditions = new ArrayList<>();
      // QueryGroup queryGroup =
      // new QueryGroup(Arrays.asList(new Query[] {Query.start().eq("_id", id),
      // Query.start().eq("zhName", id), Query.start().eq("enName", id)}));
      // conditions.add(new GroupedQueryCriterion(Group.OR, queryGroup));
      // MauritiusHotel hotel = mauritiusService.queryHotel(conditions);
      // if (hotel == null) {
      // return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      // }
      // model.addAttribute("hotel", hotel);
      // model.addAttribute("Settings", settingsService.getSettings());
      // model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
      // return "/v3/mauritius_hotel";
    }

    List<DocumentCriterion> conditions = new ArrayList<>();
    QueryGroup queryGroup =
        new QueryGroup(Arrays.asList(new Query[] {Query.start().eq("_id", id),
            Query.start().eq("zhName", id), Query.start().eq("enName", id)}));
    conditions.add(new GroupedQueryCriterion(Group.OR, queryGroup));
    MauritiusHotel hotel = mauritiusService.queryHotel(conditions);
    if (hotel == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("hotel", hotel);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    // return "/v2/mauritius_hotel";
    return "/v5/mauritius/hotel";
  }
}

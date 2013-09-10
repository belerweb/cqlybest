package com.cqlybest.site.controller;

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

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.service.FriendlyLinkService;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.SettingsService;
import com.googlecode.mjorm.query.Query;
import com.googlecode.mjorm.query.QueryGroup;
import com.googlecode.mjorm.query.criteria.DocumentCriterion;
import com.googlecode.mjorm.query.criteria.GroupedQueryCriterion;
import com.googlecode.mjorm.query.criteria.GroupedQueryCriterion.Group;

@Controller("siteMaldivesController")
public class MaldivesController extends ControllerHelper {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  @RequestMapping("/maldives.html")
  public Object maldives(HttpServletRequest request, Model model) {
    model.addAttribute("result", mongoMaldivesService.queryIsland(0, Integer.MAX_VALUE));
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    // return "/v2/maldives";
    return "/v5/maldives/main";
  }

  @RequestMapping("/maldives/{id}.html")
  public Object maldives(@PathVariable String id, HttpServletRequest request, Model model) {
    String host = request.getServerName();
    if (host.startsWith("m.")) {
      List<DocumentCriterion> conditions = new ArrayList<>();
      QueryGroup queryGroup =
          new QueryGroup(Arrays.asList(new Query[] {Query.start().eq("_id", id),
              Query.start().eq("zhName", id), Query.start().eq("enName", id)}));
      conditions.add(new GroupedQueryCriterion(Group.OR, queryGroup));
      MaldivesIsland island = mongoMaldivesService.queryIsland(conditions);
      if (island == null) {
        return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
      }
      model.addAttribute("island", island);
      model.addAttribute("Settings", settingsService.getSettings());
      model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
      return "/v3/maldives_island";
    }

    List<DocumentCriterion> conditions = new ArrayList<>();
    QueryGroup queryGroup =
        new QueryGroup(Arrays.asList(new Query[] {Query.start().eq("_id", id),
            Query.start().eq("zhName", id), Query.start().eq("enName", id)}));
    conditions.add(new GroupedQueryCriterion(Group.OR, queryGroup));
    MaldivesIsland island = mongoMaldivesService.queryIsland(conditions);
    if (island == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("island", island);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    // return "/v2/maldives_island";
    return "/v5/maldives/island";
  }
}

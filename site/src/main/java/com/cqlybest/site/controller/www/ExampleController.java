package com.cqlybest.site.controller.www;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.bean.example.Case;
import com.cqlybest.common.bean.example.Company;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.service.example.ExampleService;

@Controller
public class ExampleController extends ControllerHelper {

  @Autowired
  private ExampleService exampleService;

  @RequestMapping("/enterprise.html")
  public Object enterprise(Model model) {
    model.addAttribute("cases", exampleService.queryCase(0, 4));
    model.addAttribute("companys", exampleService.queryCompany(0, 12));

    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/enterprise/index";
  }

  @RequestMapping("/case.html")
  public Object exampleCase(Model model) {
    model.addAttribute("result", exampleService.queryCase(0, Integer.MAX_VALUE));
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/enterprise/cases";
  }

  @RequestMapping("/case/{id}.html")
  public Object exampleCase(@PathVariable String id, Model model) {
    Case c = exampleService.getCase(id);
    if (c == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("case", c);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/enterprise/case";
  }

  @RequestMapping("/enterprise/partner.html")
  public Object partner(Model model) {
    model.addAttribute("result", exampleService.queryCompany(0, Integer.MAX_VALUE));
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/enterprise/partners";
  }

  @RequestMapping("/enterprise/partner/{id}.html")
  public Object partner(@PathVariable String id, Model model) {
    Company c = exampleService.getCompany(id);
    if (c == null) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    model.addAttribute("company", c);
    model.addAttribute("Settings", settingsService.getSettings());
    model.addAttribute("Links", friendlyLinkService.queryLink(0, Integer.MAX_VALUE));
    return "/v5/enterprise/partner";
  }

}

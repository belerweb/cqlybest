package com.cqlybest.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.service.OptionService;

@Controller
public class SiteController {

  @Autowired
  private OptionService optionService;

  @RequestMapping(value = "/site/config.html", method = RequestMethod.GET)
  public void config(Model model) {
    model.addAttribute("options", optionService.getOptions());
  }

  @RequestMapping(value = "/site/config.html", method = RequestMethod.POST)
  @ResponseBody
  public void config(@RequestParam String name, @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) {
    optionService.setOption(name, value == null ? StringUtils.join(values, ",") : value);
  }

}

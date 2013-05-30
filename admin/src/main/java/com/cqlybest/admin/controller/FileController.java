package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.service.OptionService;

@Controller
public class FileController {

  @Autowired
  private OptionService optionService;

  @RequestMapping("/file.html")
  public void file(Model model) {
    model.addAttribute("Options", optionService.getOptions());
  }

}

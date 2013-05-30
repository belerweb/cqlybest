package com.cqlybest.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.service.OptionService;

@Controller
public class IndexController {

  @Autowired
  private OptionService optionService;

  @RequestMapping("/index.html")
  public String index(HttpServletRequest request, Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
      return "login";
    }

    model.addAttribute("Options", optionService.getOptions());
    return "index";
  }

}

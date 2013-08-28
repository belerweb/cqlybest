package com.cqlybest.site.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("siteIndexController")
public class IndexController {

  @RequestMapping( {"/", "/index.html"})
  public Object index(HttpServletRequest request) {
    String host = request.getServerName();
    if (host.equals("admin")) {

    }
    if (host.equals("mobile")) {

    }
    if (host.equals("weibo")) {

    }

    System.out.println(host);
    return null;
  }

}

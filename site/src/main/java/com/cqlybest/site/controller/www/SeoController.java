package com.cqlybest.site.controller.www;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.controller.ControllerHelper;

@Controller
public class SeoController extends ControllerHelper {

  @RequestMapping("/robots.txt")
  public Object robots(HttpServletRequest request) {
    String host = request.getServerName();
    if (host.startsWith("admin.")) {
      notFound();
    }

    return "/robots";
  }

}

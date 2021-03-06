package com.cqlybest.site.controller.www;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.exception.ForbiddenException;
import com.cqlybest.common.exception.NotFoundException;
import com.cqlybest.common.service.SettingsService;

@Controller
public class ErrorController extends ControllerHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

  @Autowired
  private SettingsService settingsService;

  @RequestMapping("/error")
  public Object error(HttpServletRequest request, HttpServletResponse response, Model model) {
    String xRequestedWith = request.getHeader("X-Requested-With");
    boolean ajax = "XMLHttpRequest".equals(xRequestedWith);
    model.addAttribute("ajax", ajax);

    Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
    Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
    Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
    Object uri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
    Object servlet = request.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);

    LOGGER.error("Request Error: {}", errorMessage);
    LOGGER.error("Request Error servlet: {}", servlet);
    LOGGER.error("Request Error uri: {}", uri);

    model.addAttribute("uri", uri);
    model.addAttribute("message", errorMessage);

    if (new Integer(403).equals(statusCode)) {
      // TODO return "v1/error/403";
    }

    if (exception != null) {
      if (exception instanceof ForbiddenException) {
        response.setStatus(HttpStatus.SC_FORBIDDEN);
      }
      if (exception instanceof NotFoundException) {
        response.setStatus(HttpStatus.SC_NOT_FOUND);
      }
    }

    model.addAttribute("Settings", settingsService.getSettings());
    return "v5/error/default";
  }

}

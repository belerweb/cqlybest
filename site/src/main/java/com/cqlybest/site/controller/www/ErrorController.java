package com.cqlybest.site.controller.www;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.controller.ControllerHelper;

@Controller
public class ErrorController extends ControllerHelper {

  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorController.class);

  @RequestMapping("/error")
  public Object error(HttpServletRequest request, Model model) {
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

    if ((exception != null && exception instanceof Exception)
        || new Integer(403).equals(statusCode)) {
      // TODO return "v1/error/403";
    }

    return "v5/error/default";
  }

}

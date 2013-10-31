package com.cqlybest.common.controller;

import java.lang.reflect.Method;
import java.util.UUID;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import com.cqlybest.common.bean.User;
import com.cqlybest.common.exception.NotFoundException;
import com.cqlybest.common.service.CentralConfig;
import com.cqlybest.common.service.FriendlyLinkService;
import com.cqlybest.common.service.SettingsService;

public abstract class ControllerHelper {

  @Autowired
  protected CentralConfig centralConfig;
  @Autowired
  protected SettingsService settingsService;
  @Autowired
  protected FriendlyLinkService friendlyLinkService;

  protected String getQiniuBucket() {
    return centralConfig.get(CentralConfig.QINIU_BK);
  }

  protected ResponseEntity<Object> ok() {
    return new ResponseEntity<Object>(HttpStatus.OK);
  }

  protected void notFound() {
    throw new NotFoundException();
  }

  protected ResponseEntity<Object> error(String message) {
    return new ResponseEntity<Object>(message, HttpStatus.BAD_REQUEST);
  }

  protected ResponseEntity<Object> illegal() {
    return error("非法请求");
  }

  protected ResponseEntity<Object> json(Object object) {
    try {
      MultiValueMap<String, String> headers = new HttpHeaders();
      headers.add("Content-Type", "application/json; charset=utf-8");
      return new ResponseEntity<Object>(new ObjectMapper().writeValueAsString(object), headers,
          HttpStatus.OK);
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  protected void generatePageId(Model model) {
    model.addAttribute("pageId", UUID.randomUUID().toString().replaceAll("-", ""));
  }

  protected User getUser() {
    try {
      Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      Method getDetail = principal.getClass().getMethod("getDetail");
      return (User) getDetail.invoke(principal);
    } catch (Exception e) {
      return null;
    }
  }

}

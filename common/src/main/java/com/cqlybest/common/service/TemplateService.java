package com.cqlybest.common.service;

import org.springframework.stereotype.Service;

@Service
public class TemplateService {

  public String getTemplate() {
    return "/template1";
  }

}

package com.cqlybest.admin.mongo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.mongo.service.SettingsService;

@Controller
public class SystemManagement {

  @Autowired
  private SettingsService settingsService;

  @RequestMapping("/system.do")
  public String system() {
    return "/v1/system";
  }

  /**
   * 系统设置
   */
  @RequestMapping("/system/settings.do")
  public String settings(Model model) {
    model.addAttribute("settings", settingsService.getSettings());
    return "/v1/system/settings";
  }

  /**
   * 更改系统设置
   */
  @RequestMapping("/system/settings/update.do")
  @ResponseBody
  public void updateSettings(@RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(value = "value[]", required = false) List<String> values) {
    Object _value = value == null ? values : value;
    if ("watermark.img".equals(name)) {
      Map<String, String> img = new HashMap<>();
      img.put("id", values.get(0));
      img.put("extension", values.get(1));
      _value = img;
    }
    settingsService.updateSettings(name, _value);
  }

  /**
   * 系统信息：环境变量、系统属性
   */
  @RequestMapping("/system/info.do")
  public String info(Model model) {
    model.addAttribute("env", System.getenv());
    model.addAttribute("property", System.getProperties());
    return "/v1/system/info";
  }

}

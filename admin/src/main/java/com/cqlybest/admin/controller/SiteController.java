package com.cqlybest.admin.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.Constant;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.OptionService;

public class SiteController {

  @Autowired
  private OptionService optionService;
  @Autowired
  private ImageService imageService;

  @RequestMapping(value = "/site/config.do", method = RequestMethod.GET)
  public void config(Model model) {
    Map<String, String> options = optionService.getOptions();
    model.addAttribute("options", options);
    String watermark = options.get(Constant.IMAGE_WATERMARK_IMAGE_ID);
    if (watermark != null) {
      model.addAttribute("watermark", imageService.get(watermark));
    }
  }

  @RequestMapping(value = "/site/config.do", method = RequestMethod.POST)
  @ResponseBody
  public void config(@RequestParam String name, @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) {
    optionService.setOption(name, value == null ? StringUtils.join(values, ",") : value);
  }

}

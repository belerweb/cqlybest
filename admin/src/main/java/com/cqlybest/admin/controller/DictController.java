package com.cqlybest.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Dict;
import com.cqlybest.common.service.DictService;

@Controller
public class DictController {

  @Autowired
  private DictService dictService;

  @RequestMapping("/dict/list.html")
  public void dict(@RequestParam String type, Model model) {
    model.addAttribute("type", type);
    model.addAttribute("dicts", dictService.getDict(type));
  }

  @RequestMapping("/dict/search.do")
  @ResponseBody
  public Map<String, Object> dict(@RequestParam String type,
      @RequestParam(required = false) String q) {
    Map<String, Object> result = new HashMap<>();
    result.put("tags", dictService.getDict(type, q));
    return result;
  }

  @RequestMapping(value = "/dict/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void add(@RequestParam String type, @RequestParam String name) {
    Dict dict = new Dict();
    dict.setType(type);
    dict.setName(name);
    dictService.addDict(dict);
  }

  @RequestMapping(value = "/dict/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void delete(@RequestParam Integer id) {
    dictService.delete(id);
  }

  @RequestMapping(value = "/dict/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void update(@RequestParam Integer pk, @RequestParam String name, @RequestParam String value) {
    dictService.update(pk, name, value);
  }

}

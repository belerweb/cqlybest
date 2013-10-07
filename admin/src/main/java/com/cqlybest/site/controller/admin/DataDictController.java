package com.cqlybest.site.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.DataDict;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.service.DataDictService;

@Controller
public class DataDictController extends ControllerHelper {

  @Autowired
  private DataDictService dataDictService;

  @RequestMapping("/datadict/list.do")
  public String dict(@RequestParam String type, @RequestParam(required = false) String q,
      @RequestParam(defaultValue = "0") int page, Model model) {
    model.addAttribute("type", type);
    model.addAttribute("result", dataDictService.queryDict(type, q, page, 10));
    return "/v1/datadict/" + type;
  }

  @RequestMapping("/datadict/ajax.do")
  @ResponseBody
  public QueryResult<DataDict> dict(@RequestParam String type,
      @RequestParam(required = false) String q) {
    return dataDictService.queryDict(type, q, 0, 10);
  }

  @RequestMapping(value = "/datadict/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void add(@RequestParam String type, @RequestParam String name) {
    dataDictService.addDict(type, name);
  }

  @RequestMapping(value = "/datadict/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void delete(@RequestParam String id) {
    dataDictService.deleteDict(id);
  }

  @RequestMapping(value = "/datadict/update.do", method = RequestMethod.POST)
  public Object update(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    if (!("pinyin".equals(name) || "py".equals(name))) {
      return illegal();
    }
    dataDictService.updateDict(pk, name, value);
    return ok();
  }

}

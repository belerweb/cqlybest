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

import com.cqlybest.common.bean.DepartureCity;
import com.cqlybest.common.bean.Dict;
import com.cqlybest.common.bean.DictProductGrade;
import com.cqlybest.common.bean.DictProductType;
import com.cqlybest.common.bean.DictTraffic;
import com.cqlybest.common.bean.Keyword;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;

@Controller
public class DictController {

  @Autowired
  private DictService dictService;

  @Autowired
  private DestinationService destinationService;


  @RequestMapping("/data/dict.html")
  public void dict() {}

  @RequestMapping(value = "/data/dict.html", params = "action=dict")
  @ResponseBody
  public Map<String, Object> dict(@RequestParam String type,
      @RequestParam(required = false) String typeahead, @RequestParam(required = false) String q) {
    Map<String, Object> result = new HashMap<>();
    if ("keyword".equals(type)) {
      result.put("tags", dictService.getDict(Keyword.class, q));
    }
    if ("departure-city".equals(type)) {
      result.put("tags", dictService.getDict(DepartureCity.class, q));
    }
    if ("destination".equals(type)) {
      result.put("tags", destinationService.getTree());
    }
    if ("traffic".equals(type)) {
      result.put("tags", dictService.getDict(DictTraffic.class, q));
    }
    if ("product-type".equals(type)) {
      result.put("tags", dictService.getDict(DictProductType.class, q));
    }
    if ("product-grade".equals(type)) {
      result.put("tags", dictService.getDict(DictProductGrade.class, q));
    }
    return result;
  }

  @RequestMapping(value = "/data/dict/add.do", method = RequestMethod.POST)
  @ResponseBody
  public Integer add(@RequestParam String type, @RequestParam String value) {
    Dict dict = null;
    if ("keyword".equals(type)) {
      dict = new Keyword();
    }
    if ("departure-city".equals(type)) {
      dict = new DepartureCity();
    }
    if ("destination".equals(type)) {
      // TODO dict = new DepartureCity();
    }
    if ("traffic".equals(type)) {
      dict = new DictTraffic();
    }
    if ("product-type".equals(type)) {
      dict = new DictProductType();
    }
    if ("product-grade".equals(type)) {
      dict = new DictProductGrade();
    }
    if (dict != null) {
      dict.setName(value);
      dictService.addDict(dict);
      return dict.getId();
    }
    return null;
  }

  @RequestMapping(value = "/data/dict/add_departure_city.html", method = RequestMethod.POST)
  @ResponseBody
  public DepartureCity addDepartureCity(DepartureCity dict) {
    dictService.addDict(dict);
    return dict;
  }

  @RequestMapping(value = "/data/dict/add_keyword.html", method = RequestMethod.POST)
  @ResponseBody
  public Keyword addKeyword(Keyword dict) {
    dictService.addDict(dict);
    return dict;
  }

  @RequestMapping(value = "/data/dict/add_traffic.html", method = RequestMethod.POST)
  @ResponseBody
  public void addTraffic(DictTraffic dict) {
    dictService.addDict(dict);
  }

  @RequestMapping(value = "/data/dict/add_product_type.html", method = RequestMethod.POST)
  @ResponseBody
  public void addProductType(DictProductType dict) {
    dictService.addDict(dict);
  }

  @RequestMapping(value = "/data/dict/add_product_grade.html", method = RequestMethod.POST)
  @ResponseBody
  public void DepartureCity(DictProductGrade dict) {
    dictService.addDict(dict);
  }

  @RequestMapping("/data/dict/delete_departure_city.html")
  @ResponseBody
  public void deleteDepartureCity(DepartureCity dict) {
    dictService.deleteDict(dict);
  }

  @RequestMapping("/data/dict/delete_keyword.html")
  @ResponseBody
  public void deleteKeyword(Keyword dict) {
    dictService.deleteDict(dict);
  }

  @RequestMapping("/data/dict/delete_traffic.html")
  @ResponseBody
  public void deleteTraffic(DictTraffic dict) {
    dictService.deleteDict(dict);
  }

  @RequestMapping("/data/dict/delete_product_type.html")
  @ResponseBody
  public void deleteProductType(DictProductType dict) {
    dictService.deleteDict(dict);
  }

  @RequestMapping("/data/dict/delete_product_grade.html")
  @ResponseBody
  public void deleteProductGrade(DictProductGrade dict) {
    dictService.deleteDict(dict);
  }

  @RequestMapping(value = "/data/dict/departure_city.html", method = RequestMethod.GET)
  public void departureCity(Model model) {
    model.addAttribute("dicts", dictService.getDict(DepartureCity.class));
  }

  @RequestMapping(value = "/data/dict/keyword.html", method = RequestMethod.GET)
  public void keyword(Model model) {
    model.addAttribute("dicts", dictService.getDict(Keyword.class));
  }

  @RequestMapping(value = "/data/dict/traffic.html", method = RequestMethod.GET)
  public void traffic(Model model) {
    model.addAttribute("dicts", dictService.getDict(DictTraffic.class));
  }

  @RequestMapping(value = "/data/dict/product_type.html", method = RequestMethod.GET)
  public void productType(Model model) {
    model.addAttribute("dicts", dictService.getDict(DictProductType.class));
  }

  @RequestMapping(value = "/data/dict/product_grade.html", method = RequestMethod.GET)
  public void productGrade(Model model) {
    model.addAttribute("dicts", dictService.getDict(DictProductGrade.class));
  }


}

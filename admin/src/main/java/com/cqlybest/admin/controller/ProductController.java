package com.cqlybest.admin.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.DictProductGrade;
import com.cqlybest.common.bean.DictProductType;
import com.cqlybest.common.bean.DictTraffic;
import com.cqlybest.common.bean.Keyword;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.JsonService;
import com.cqlybest.common.service.ProductService;

@Controller
public class ProductController {

  @Autowired
  private JsonService jsonService;

  @Autowired
  private ProductService productService;

  @Autowired
  private DestinationService destinationService;

  @Autowired
  private DictService dictService;

  @RequestMapping(value = "/product/list.html", method = RequestMethod.GET)
  public void products(@RequestParam(defaultValue = "0") int page, Model model) {
    page = Math.max(1, page);
    int pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", productService.queryProductTotal());
    model.addAttribute("products", productService.queryProduct(page, pageSize));
  }

  @RequestMapping(value = "/product/add.html", method = RequestMethod.GET)
  public void add(Model model) throws JsonGenerationException, JsonMappingException, IOException {
    model.addAttribute("dests", jsonService.writeValueAsString(destinationService.getTree()));
    model.addAttribute("traffics", dictService.getDict(DictTraffic.class));
    model.addAttribute("types", dictService.getDict(DictProductType.class));
    model.addAttribute("grades", dictService.getDict(DictProductGrade.class));
  }

  @RequestMapping(value = "/product/add.html", method = RequestMethod.POST)
  @ResponseBody
  public void add(Product product, @RequestParam(required = false) List<Integer> trafficIds,
      @RequestParam(required = false) List<Integer> productTypeIds,
      @RequestParam(required = false) List<Integer> productGradeIds,
      @RequestParam(required = false) String keywordIds) {
    if (trafficIds != null && !trafficIds.isEmpty()) {
      Set<DictTraffic> items = new HashSet<>();
      for (Integer id : trafficIds) {
        DictTraffic item = new DictTraffic();
        item.setId(id);
        items.add(item);
      }
      product.setTraffics(items);
    }
    if (productTypeIds != null && !productTypeIds.isEmpty()) {
      Set<DictProductType> items = new HashSet<>();
      for (Integer id : productTypeIds) {
        DictProductType item = new DictProductType();
        item.setId(id);
        items.add(item);
      }
      product.setTypes(items);
    }
    if (productGradeIds != null && !productGradeIds.isEmpty()) {
      Set<DictProductGrade> items = new HashSet<>();
      for (Integer id : productGradeIds) {
        DictProductGrade item = new DictProductGrade();
        item.setId(id);
        items.add(item);
      }
      product.setGrades(items);
    }
    if (!StringUtils.isEmpty(keywordIds)) {
      Set<Keyword> keywords = new HashSet<>();
      for (String id : keywordIds.split(",")) {
        Keyword keyword = new Keyword();
        keyword.setId(Integer.parseInt(id));
        keywords.add(keyword);
      }
      product.setKeywords(keywords);
    }
    productService.add(product);
  }

  @RequestMapping("/product/delete.html")
  @ResponseBody
  public void delete(Product product) {
    productService.delete(product);
  }


}

package com.cqlybest.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Product2;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.JsonService;
import com.cqlybest.common.service.Product2Service;

@Controller
public class ProductController {

  @Autowired
  private JsonService jsonService;
  @Autowired
  private Product2Service product2Service;
  @Autowired
  private DestinationService destinationService;
  @Autowired
  private DictService dictService;
  @Autowired
  private ImageService imageService;

  /**
   * 添加产品
   */
  @RequestMapping(value = "/product/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String name) {
    Product2 product = new Product2();
    product.setName(name);
    product2Service.add(product);
    return product.getId();
  }

  /**
   * 修改产品
   */
  @RequestMapping(value = "/product/update.do", method = RequestMethod.GET)
  public void update(@RequestParam String id, Model model) {
    model.addAttribute("product", product2Service.get(id));
  }

  /**
   * 修改产品
   */
  @RequestMapping(value = "/product/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void update(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) throws Exception {
    Object _value = value == null ? StringUtils.join(values, ",") : value;
    if ("days".equals(name)) {
      _value = Integer.parseInt(value);
    }
    if ("price".equals(name) || "childPrice".equals(name) || "specialPrice".equals(name)) {
      _value = (int) (Double.parseDouble(value) * 100);
    }
    if ("effectiveDate".equals(name) || "expiryDate".equals(name) || "departureDate".equals(name)) {
      _value = DateUtils.parseDate(value, new String[] {"yyyy-MM-dd"});
    }
    product2Service.update(pk, name, _value);
  }

  @RequestMapping(value = "/product/list.html", method = RequestMethod.GET)
  public void products(@RequestParam(defaultValue = "0") int page, Model model) {
    page = Math.max(1, page);
    int pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", product2Service.queryProductTotal());
    model.addAttribute("products", product2Service.queryProduct(page, pageSize));
  }

  @RequestMapping("/product/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam String id, @RequestParam boolean published) {
    product2Service.update(id, "published", published);
  }

  @RequestMapping("/product/hot.html")
  @ResponseBody
  public void hot(@RequestParam(value = "ids[]") String[] ids, @RequestParam boolean hot) {
    product2Service.update(ids, "popular", hot);
  }

  @RequestMapping("/product/recommend.html")
  @ResponseBody
  public void recommend(@RequestParam(value = "ids[]") String[] ids, @RequestParam boolean red) {
    product2Service.update(ids, "recommend", red);
  }

  @RequestMapping("/product/special.html")
  @ResponseBody
  public void special(@RequestParam(value = "ids[]") String[] ids, @RequestParam boolean special) {
    product2Service.update(ids, "specialOffer", special);
  }

  @RequestMapping("/product/pub.html")
  @ResponseBody
  public void pub(@RequestParam(value = "ids[]") String[] ids, @RequestParam boolean pub) {
    product2Service.update(ids, "published", pub);
  }

  @RequestMapping("/product/delete.html")
  @ResponseBody
  public void del(@RequestParam(value = "ids[]") String[] ids) {
    product2Service.delete(ids);
  }

}

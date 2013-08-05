package com.cqlybest.www.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductFilterItem;
import com.cqlybest.common.bean.ProductGroup;
import com.cqlybest.common.bean.ProductMaldives;
import com.cqlybest.common.bean.maldives.MaldivesRoom;
import com.cqlybest.common.bean.maldives.MaldivesSeaIsland;
import com.cqlybest.common.bean.template1.Template1Menu;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.TemplateService;

@Controller
public class ProductController extends ControllerHelper {

  @Autowired
  private TemplateService templateService;
  @Autowired
  private ProductService productService;
  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private DictService dictService;

  /**
   * 聚合产品页
   */
  @RequestMapping("/group/{id}.html")
  public String group(@PathVariable String id) {
    return "forward:/group/" + id + "/0-0-0-0-0-0-0-0-0.html";
  }

  /**
   * 聚合产品页
   */
  @RequestMapping("/group/{id}/{f0}-{f1}-{f2}-{f3}-{f4}-{f5}-{f6}-{f7}-{page}.html")
  public Object group(@PathVariable String id, @PathVariable Integer f0, @PathVariable Integer f1,
      @PathVariable Integer f2, @PathVariable Integer f3, @PathVariable Integer f4,
      @PathVariable Integer f5, @PathVariable Integer f6, @PathVariable Integer f7,
      @PathVariable Integer page, Model model) {
    Template1Menu menu = template1Service.get(id);
    if (menu == null || menu.getMenuType() != 0) {
      // 菜单不存在或者不是产品聚合菜单
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }

    ProductGroup group = menu.getProductGroup();
    Set<ProductFilterItem> filterItems = new HashSet<>();
    addToFilterSet(filterItems, 0, f0);
    addToFilterSet(filterItems, 1, f1);
    addToFilterSet(filterItems, 2, f2);
    addToFilterSet(filterItems, 3, f3);
    addToFilterSet(filterItems, 4, f4);
    addToFilterSet(filterItems, 5, f5);
    addToFilterSet(filterItems, 6, f6);
    addToFilterSet(filterItems, 7, f7);
    List<Product> products = productService.queryProducts(group, filterItems, 0, 0);
    model.addAttribute("total", products.size());
    model.addAttribute("products", products);
    model.addAttribute("menu", menu);

    // 过滤条件
    model.addAttribute("f0", f0);
    model.addAttribute("f1", f1);
    model.addAttribute("f2", f2);
    model.addAttribute("f3", f3);
    model.addAttribute("f4", f4);
    model.addAttribute("f5", f5);
    model.addAttribute("f6", f6);
    model.addAttribute("f7", f7);
    model.addAttribute("page", page);

    // 数据字典
    model.addAttribute("traffics", dictService.getDict(Constant.DICT_TRAFFIC));
    model.addAttribute("types", dictService.getDict(Constant.DICT_PRODUCT_TYPE));
    model.addAttribute("grades", dictService.getDict(Constant.DICT_PRODUCT_GRADE));
    model.addAttribute("keywords", dictService.getDict(Constant.DICT_TAG));
    model.addAttribute("departureCities", dictService.getDict(Constant.DICT_DEPARTURE_CITY));
    model.addAttribute("destinations", dictService.getDict(Constant.DICT_DESTINATION));
    setCommonData(model);
    return templateService.getTemplate() + "/product_group";
  }

  @RequestMapping("/product/{id}.html")
  public Object product(@PathVariable String id, Model model) {
    Product product = productService.get(id);
    if (product == null || !product.getPublished()) {
      return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
    }
    product.setCalendar(productService.getCalendar(id));

    if (product.getProductType() == Product.MALDIVES) {
      List<MaldivesRoom> rooms = new ArrayList<>();
      List<MaldivesRoom> distinctRooms = new ArrayList<>();
      List<MaldivesSeaIsland> islands = new ArrayList<>();
      List<MaldivesSeaIsland> distinctIslands = new ArrayList<>();
      for (ProductMaldives maldives : product.getMaldives()) {
        if (StringUtils.isEmpty(maldives.getIslandId()) || maldives.getRoomId() == null) {
          continue;
        }
        MaldivesRoom room = maldivesService.getRoom(maldives.getRoomId());
        rooms.add(room);
        if (!distinctRooms.contains(room)) {
          distinctRooms.add(room);
        }
        MaldivesSeaIsland island = maldivesService.getIslandWithoutRoom(maldives.getIslandId());
        islands.add(island);
        if (!distinctIslands.contains(island)) {
          distinctIslands.add(island);
        }
      }
      model.addAttribute("rooms", rooms);
      model.addAttribute("distinctRooms", distinctRooms);
      model.addAttribute("islands", islands);
      model.addAttribute("distinctIslands", distinctIslands);
    }

    model.addAttribute("product", product);
    setCommonData(model);
    return templateService.getTemplate() + "/product_" + product.getProductType();
  }

  private void addToFilterSet(Set<ProductFilterItem> set, Integer type, Integer id) {
    if (id > 0) {
      set.add(new ProductFilterItem(type, id));
    }
  }

}

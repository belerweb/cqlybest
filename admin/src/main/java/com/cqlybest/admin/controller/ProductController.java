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
import org.springframework.web.multipart.MultipartFile;

import com.cqlybest.common.bean.DepartureCity;
import com.cqlybest.common.bean.Destination;
import com.cqlybest.common.bean.DictProductGrade;
import com.cqlybest.common.bean.DictProductType;
import com.cqlybest.common.bean.DictTraffic;
import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.Keyword;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ImageService;
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
  @Autowired
  private ImageService imageService;

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
    // model.addAttribute("dests", jsonService.writeValueAsString(destinationService.getTree()));
    model.addAttribute("traffics", dictService.getDict(DictTraffic.class));
    model.addAttribute("types", dictService.getDict(DictProductType.class));
    model.addAttribute("grades", dictService.getDict(DictProductGrade.class));
  }

  @RequestMapping(value = "/product/modify.html", method = RequestMethod.GET)
  public void modify(@RequestParam Integer id, Model model) throws JsonGenerationException,
      JsonMappingException, IOException {
    // model.addAttribute("dests", jsonService.writeValueAsString(destinationService.getTree()));
    model.addAttribute("traffics", dictService.getDict(DictTraffic.class));
    model.addAttribute("types", dictService.getDict(DictProductType.class));
    model.addAttribute("grades", dictService.getDict(DictProductGrade.class));
    model.addAttribute("product", productService.getProduct(id));
  }

  @RequestMapping(value = {"/product/add.html", "/product/modify.html"}, method = RequestMethod.POST)
  @ResponseBody
  public void edit(Product product, @RequestParam(required = false) List<Integer> trafficIds,
      @RequestParam(required = false) List<Integer> productTypeIds,
      @RequestParam(required = false) List<Integer> productGradeIds,
      @RequestParam(required = false) String keywordIds,
      @RequestParam(required = false) String destIds,
      @RequestParam(required = false) String departureCityIds,
      @RequestParam(required = false) List<String> posterIds,
      @RequestParam(required = false) List<String> photoIds,
      @RequestParam(required = false) MultipartFile[] _posters,
      @RequestParam(required = false) MultipartFile[] _photos) throws IOException {
    // 交通方式
    if (trafficIds != null) {
      Set<DictTraffic> items = new HashSet<>();
      for (Integer id : trafficIds) {
        DictTraffic item = new DictTraffic();
        item.setId(id);
        items.add(item);
      }
      product.setTraffics(items);
    }
    // 产品类型
    if (productTypeIds != null) {
      Set<DictProductType> items = new HashSet<>();
      for (Integer id : productTypeIds) {
        DictProductType item = new DictProductType();
        item.setId(id);
        items.add(item);
      }
      product.setTypes(items);
    }
    // 产品等级
    if (productGradeIds != null) {
      Set<DictProductGrade> items = new HashSet<>();
      for (Integer id : productGradeIds) {
        DictProductGrade item = new DictProductGrade();
        item.setId(id);
        items.add(item);
      }
      product.setGrades(items);
    }
    // 产品关键词
    if (!StringUtils.isEmpty(keywordIds)) {
      Set<Keyword> keywords = new HashSet<>();
      for (String id : keywordIds.split(",")) {
        Keyword keyword = new Keyword();
        keyword.setId(Integer.parseInt(id));
        keywords.add(keyword);
      }
      product.setKeywords(keywords);
    }
    // 目的地
    if (!StringUtils.isEmpty(destIds)) {
      Set<Destination> destinations = new HashSet<>();
      for (String id : destIds.split(",")) {
        Destination destination = new Destination();
        destination.setId(Integer.parseInt(id));
        destinations.add(destination);
      }
      product.setDestinations(destinations);
    }
    // 出发城市
    if (!StringUtils.isEmpty(departureCityIds)) {
      Set<DepartureCity> departureCities = new HashSet<>();
      for (String id : departureCityIds.split(",")) {
        DepartureCity city = new DepartureCity();
        city.setId(Integer.parseInt(id));
        departureCities.add(city);
      }
      product.setDepartureCities(departureCities);
    }
    // 海报
    Set<Image> posters = new HashSet<>();
    if (posterIds != null) {
      for (String id : posterIds) {
        Image image = new Image();
        image.setId(id);
        posters.add(image);
      }
    }
    // 新上传海报
    if (_posters != null) {
      for (MultipartFile multipartFile : _posters) {
        Image image = imageService.multipartFileToImage(multipartFile);
        imageService.add(image);
        posters.add(image);
      }
    }
    product.setPosters(posters);
    // 照片
    Set<Image> photos = new HashSet<>();
    if (photoIds != null) {
      for (String id : photoIds) {
        Image image = new Image();
        image.setId(id);
        photos.add(image);
      }
    }
    // 新上传照片
    if (_photos != null) {
      for (MultipartFile multipartFile : _photos) {
        Image image = imageService.multipartFileToImage(multipartFile);
        imageService.add(image);
        photos.add(image);
      }
    }
    product.setPhotos(photos);
    productService.edit(product);
  }

  @RequestMapping("/product/delete.html")
  @ResponseBody
  public void delete(@RequestParam Integer id) {
    productService.delete(id);
  }

  @RequestMapping("/product/toggle.html")
  @ResponseBody
  public void toggle(@RequestParam Integer id, @RequestParam boolean published) {
    productService.togglePublished(id, published);
  }

}

package com.cqlybest.admin.mongo.controller;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.Constant;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.bean.Product;
import com.cqlybest.common.mongo.bean.Transportation;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.ProductService;
import com.cqlybest.common.mongo.service.TransportationService;
import com.cqlybest.common.service.OptionService;

@Controller("mongoMaldivesController")
public class MaldivesController {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private TransportationService transportationService;
  @Autowired
  private ProductService mongoProductService;
  @Autowired
  private OptionService optionService;

  /**
   * 马尔代夫管理页面
   */
  @RequestMapping("/maldives.do")
  public String maldives() {
    return "/v1/maldives";
  }

  /**
   * 添加海岛
   */
  @RequestMapping(value = "/maldives/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String zhName, @RequestParam String enName) {
    // TODO validate zhName & enName
    MaldivesIsland island = mongoMaldivesService.addIsland(zhName, enName);
    return island.getId();
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/maldives/update.do", method = RequestMethod.GET)
  public String update(@RequestParam String id, Model model) {
    model.addAttribute("island", mongoMaldivesService.getIsland(id));
    return "/v1/maldives/update";
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/maldives/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void update(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) throws Exception {
    Object _value = value == null ? StringUtils.join(values, ",") : value;
    if (name.equals("hotelLevel") || name.equals("hotelRoomNum")) {
      _value = StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }
    if (name.equals("hotelChinese")) {
      _value = StringUtils.isEmpty(value) ? null : Boolean.valueOf(value);
    }
    mongoMaldivesService.updateIsland(pk, name, _value);
  }

  /**
   * 海岛列表
   */
  @RequestMapping(value = "/maldives/list.do", method = RequestMethod.GET)
  public String list(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", mongoMaldivesService.queryIsland(page, pageSize));
    return "/v1/maldives/list";
  }

  /**
   * Ajax 获取海岛列表
   */
  @RequestMapping(value = "/maldives/island/ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public Object list(@RequestParam String q, Model model) {
    return mongoMaldivesService.queryIsland(q, 10);
  }

  /**
   * 添加房型
   */
  @RequestMapping(value = "/maldives/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoom(@RequestParam String islandId, @RequestParam String zhName,
      @RequestParam String enName) {
    mongoMaldivesService.addRoom(islandId, zhName, enName);
  }

  /**
   * 修改房型
   */
  @RequestMapping(value = "/maldives/room/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateRoom(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    Object _value = value;
    if ("num".equals(name) || "displayOrder".equals(name)) {
      _value = StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }
    if ("containPool".equals(name)) {
      _value = StringUtils.isEmpty(value) ? null : Boolean.valueOf(value);
    }
    mongoMaldivesService.updateRoom(pk, name, _value);
  }

  /**
   * Ajax 获取房型列表
   */
  @RequestMapping(value = "/maldives/room/ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public Object room(@RequestParam String islandId, @RequestParam String q, Model model) {
    return mongoMaldivesService.queryRoom(islandId, q, 10);
  }

  /**
   * 添加餐饮设施
   */
  @RequestMapping(value = "/maldives/dining/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDining(@RequestParam String islandId, @RequestParam String zhName,
      @RequestParam String enName) {
    mongoMaldivesService.addDining(islandId, zhName, enName);
  }

  /**
   * 修改餐饮设施
   */
  @RequestMapping(value = "/maldives/dining/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateDining(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    Object _value = value;
    if ("reservation".equals(name)) {
      _value = StringUtils.isEmpty(value) ? null : Boolean.valueOf(value);
    }
    if ("displayOrder".equals(name)) {
      _value = StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }
    mongoMaldivesService.updateDining(pk, name, _value);
  }

  /**
   * 增加海岛海报
   */
  @RequestMapping(value = "/maldives/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addPicture(@RequestParam String islandId,
      @RequestParam("images[]") List<String> filenames) {
    mongoMaldivesService.addPicture(islandId, filenames);
  }

  /**
   * 修改海岛海报
   */
  @RequestMapping(value = "/maldives/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updatePicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mongoMaldivesService.updatePicture(pk, name, value);
  }

  /**
   * 删除海岛海报
   */
  @RequestMapping(value = "/maldives/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deletePicture(@RequestParam String imageId) {
    mongoMaldivesService.deletePicture(imageId);
  }

  /**
   * 增加酒店图片
   */
  @RequestMapping(value = "/maldives/hotel/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addHotelPicture(@RequestParam String islandId,
      @RequestParam("images[]") List<String> filenames) {
    mongoMaldivesService.addHotelPicture(islandId, filenames);
  }

  /**
   * 修改酒店图片
   */
  @RequestMapping(value = "/maldives/hotel/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateHotelPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mongoMaldivesService.updateHotelPicture(pk, name, value);
  }

  /**
   * 删除酒店图片
   */
  @RequestMapping(value = "/maldives/hotel/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteHotelPicture(@RequestParam String imageId) {
    mongoMaldivesService.deleteHotelPicture(imageId);
  }

  /**
   * 增加房型图片
   */
  @RequestMapping(value = "/maldives/room/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoomPicture(@RequestParam String roomId,
      @RequestParam("images[]") List<String> filenames) {
    mongoMaldivesService.addRoomPicture(roomId, filenames);
  }

  /**
   * 修改房型图片
   */
  @RequestMapping(value = "/maldives/room/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateRoomPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String[] tmp = pk.split("\\|");
    mongoMaldivesService.updateRoomPicture(tmp[0], tmp[1], name, value);
  }

  /**
   * 删除房型图片
   */
  @RequestMapping(value = "/maldives/room/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteRoomPicture(@RequestParam String imageId) {
    mongoMaldivesService.deleteRoomPicture(imageId);
  }

  /**
   * 增加餐厅图片
   */
  @RequestMapping(value = "/maldives/dining/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDiningPicture(@RequestParam String diningId,
      @RequestParam("images[]") List<String> filenames) {
    mongoMaldivesService.addDiningPicture(diningId, filenames);
  }

  /**
   * 修改餐厅图片
   */
  @RequestMapping(value = "/maldives/dining/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateDiningPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String[] tmp = pk.split("\\|");
    mongoMaldivesService.updateDiningPicture(tmp[0], tmp[1], name, value);
  }

  /**
   * 删除餐厅图片
   */
  @RequestMapping(value = "/maldives/dining/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteDiningPicture(@RequestParam String imageId) {
    mongoMaldivesService.deleteDiningPicture(imageId);
  }

  /**
   * 航班信息
   */
  @RequestMapping(value = "/maldives/flight/update.do", method = RequestMethod.GET)
  public String updateFlight(@RequestParam(required = false) String flightId, Model model) {
    if (flightId != null) {
      model.addAttribute("flight", transportationService.getTransportation(flightId));
    }
    return "/v1/maldives/flight/update";
  }

  @RequestMapping(value = "/maldives/flight/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateFlight(@RequestParam(required = false) String id, @RequestParam String number,
      @RequestParam String airline, @RequestParam String airlineCode, @RequestParam String from,
      @RequestParam String to, @RequestParam String departuresAirportCode,
      @RequestParam String arrivalsAirportCode, @RequestParam long departuresTime,
      @RequestParam long arrivalsTime, @RequestParam String extra) {
    Transportation flight = new Transportation();
    flight.setId(id);
    flight.setNumber(number);
    flight.setAirline(airline);
    flight.setAirlineCode(airlineCode);
    flight.setFrom(from);
    flight.setTo(to);
    flight.setDeparturesAirportCode(departuresAirportCode);
    flight.setArrivalsAirportCode(arrivalsAirportCode);
    flight.setDeparturesTime(new Date(departuresTime));
    flight.setArrivalsTime(new Date(arrivalsTime));
    flight.setType(Transportation.TYPE_FLIGHT);
    flight.setExtra(extra);
    flight.setLineType(Transportation.LINE_TYPE_MALDIVES);
    transportationService.updateTransportation(flight);
  }

  /**
   * 航班列表
   */
  @RequestMapping(value = "/maldives/flight.do", method = RequestMethod.GET)
  public String flight(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", transportationService.queryTransportation(
        Transportation.LINE_TYPE_MALDIVES, page, pageSize));
    return "/v1/maldives/flight";
  }

  /**
   * Ajax 获取航班列表
   */
  @RequestMapping("/maldives/flight/ajax.do")
  @ResponseBody
  public List<Transportation> flight(@RequestParam String q) {
    return transportationService.queryTransportation(Transportation.LINE_TYPE_MALDIVES, q, 10);
  }

  /**
   * 添加产品
   */
  @RequestMapping(value = "/maldives/product/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String addProduct(@RequestParam String name) {
    // TODO validate name
    Product product = mongoProductService.addProduct(Product.TYPE_MALDIVES, name);
    return product.getId();
  }

  /**
   * 修改产品
   */
  @RequestMapping(value = "/maldives/product/update.do", method = RequestMethod.GET)
  public String updateProduct(@RequestParam String id, Model model) {
    model.addAttribute("product", mongoProductService.getProduct(id));
    return "/v1/maldives/product/update";
  }

  /**
   * 修改产品
   */
  @RequestMapping(value = "/maldives/product/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProduct(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) throws Exception {
    Object _value = value == null ? StringUtils.join(values, ",") : value;
    if (name.equals("hotelLevel") || name.equals("hotelRoomNum")) {
      _value = StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }
    if (name.equals("hotelChinese")) {
      _value = StringUtils.isEmpty(value) ? null : Boolean.valueOf(value);
    }
    mongoMaldivesService.updateIsland(pk, name, _value);
  }

  /**
   * 产品列表
   */
  @RequestMapping(value = "/maldives/product.do", method = RequestMethod.GET)
  public String product(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", mongoProductService.queryProduct(page, pageSize));
    return "/v1/maldives/product";
  }

  /**
   * 添加产品交通
   */
  @RequestMapping(value = "/maldives/product/transportation/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductTransportation(@RequestParam String productId, @RequestParam String name) {
    mongoProductService.addTransportation(productId, name);
  }

  /**
   * 修改产品交通
   */
  @RequestMapping(value = "/maldives/product/transportation/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductTransportation(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mongoProductService.updateTransportation(pk, name, value);
  }

  /**
   * 删除产品交通
   */
  @RequestMapping(value = "/maldives/product/transportation/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductTransportation(@RequestParam String id) {
    mongoProductService.deleteTransportation(id);
  }

  /**
   * 添加马代住宿（行程）
   */
  @RequestMapping(value = "/maldives/product/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductRoom(@RequestParam String productId, @RequestParam String name) {
    mongoProductService.addMaldivesDetail(productId, name);
  }

  /**
   * 修改马代住宿（行程）
   */
  @RequestMapping(value = "/maldives/product/room/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductRoom(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(value = "value[]", required = false) List<String> values) {
    mongoProductService.updateMaldivesDetail(pk, name, value == null ? values : value);
  }

  /**
   * 删除马代住宿（行程）
   */
  @RequestMapping(value = "/maldives/product/room/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductRoom(@RequestParam String id) {
    mongoProductService.deleteMaldivesDetail(id);
  }

  /**
   * 价格日历
   */
  @RequestMapping(value = "/maldives/product/price.do", method = RequestMethod.POST)
  @ResponseBody
  public void price(@RequestParam String productId, @RequestParam String start,
      @RequestParam(required = false) String end, @RequestParam(required = false) String price,
      @RequestParam(required = false) String childPrice,
      @RequestParam(defaultValue = "false") boolean special,
      @RequestParam(defaultValue = "false") boolean delete) throws ParseException {
    Date startDate = Constant.YYYYMMDD_FORMAT.parse(start);
    Date endDate = StringUtils.isNotBlank(end) ? Constant.YYYYMMDD_FORMAT.parse(end) : startDate;
    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    Integer _price =
        StringUtils.isNotBlank(price)
            ? (int) (numberFormat.parse(price).doubleValue() * 100)
            : null;
    Integer _childPrice =
        StringUtils.isNotBlank(childPrice)
            ? (int) (numberFormat.parse(childPrice).doubleValue() * 100)
            : null;
    if (!delete && _price == null) {
      throw new RuntimeException("必须设置价格");
    }
    mongoProductService.updatePrice(productId, startDate, endDate, _price, _childPrice, special,
        delete);
  }


  /**
   * 增加产品海报
   */
  @RequestMapping(value = "/maldives/product/poster/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductPoster(@RequestParam String productId,
      @RequestParam("images[]") List<String> filenames) {
    mongoProductService.addPoster(productId, filenames);
  }

  /**
   * 修改产品海报
   */
  @RequestMapping(value = "/maldives/product/poster/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductPoster(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mongoProductService.updatePoster(pk, name, value);
  }

  /**
   * 删除产品海报
   */
  @RequestMapping(value = "/maldives/product/poster/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductPoster(@RequestParam String imageId) {
    mongoProductService.deletePoster(imageId);
  }

  /**
   * 增加产品图片
   */
  @RequestMapping(value = "/maldives/product/photo/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductPhoto(@RequestParam String productId,
      @RequestParam("images[]") List<String> filenames) {
    mongoProductService.addPhoto(productId, filenames);
  }

  /**
   * 修改产品图片
   */
  @RequestMapping(value = "/maldives/product/photo/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductPhoto(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mongoProductService.updatePhoto(pk, name, value);
  }

  /**
   * 删除产品图片
   */
  @RequestMapping(value = "/maldives/product/photo/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductPhoto(@RequestParam String imageId) {
    mongoProductService.deletePhoto(imageId);
  }

}

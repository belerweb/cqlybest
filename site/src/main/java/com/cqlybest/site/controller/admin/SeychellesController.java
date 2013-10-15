package com.cqlybest.site.controller.admin;

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
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.TransferComb;
import com.cqlybest.common.bean.Transportation;
import com.cqlybest.common.bean.seychelles.SeychellesIsland;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.SettingsService;
import com.cqlybest.common.service.SeychellesService;
import com.cqlybest.common.service.TransportationService;

@Controller
public class SeychellesController extends ControllerHelper {

  @Autowired
  private SeychellesService seychellesService;
  @Autowired
  private TransportationService transportationService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SettingsService settingsService;

  /**
   * 马尔代夫管理页面
   */
  @RequestMapping("/seychelles.do")
  public String seychelles() {
    return "/v1/seychelles";
  }

  /**
   * 添加海岛
   */
  @RequestMapping(value = "/seychelles/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String zhName, @RequestParam String enName) {
    // TODO validate zhName & enName
    SeychellesIsland island = seychellesService.addIsland(zhName, enName);
    return island.getId();
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/seychelles/update.do", method = RequestMethod.GET)
  public String update(@RequestParam String id, Model model) {
    model.addAttribute("island", seychellesService.getIsland(id));
    return "/v1/seychelles/update";
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/seychelles/update.do", method = RequestMethod.POST)
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
    seychellesService.updateIsland(pk, name, _value);
  }

  /**
   * 海岛列表
   */
  @RequestMapping(value = "/seychelles/list.do", method = RequestMethod.GET)
  public String list(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", seychellesService.queryIsland(page, pageSize));
    model.addAttribute("settings", settingsService.getSettings());
    return "/v1/seychelles/list";
  }

  /**
   * Ajax 获取海岛列表
   */
  @RequestMapping(value = "/seychelles/island/ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public Object list(@RequestParam String q, Model model) {
    return seychellesService.queryIsland(q, 10);
  }

  /**
   * 添加房型
   */
  @RequestMapping(value = "/seychelles/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoom(@RequestParam String islandId, @RequestParam String zhName,
      @RequestParam String enName) {
    seychellesService.addRoom(islandId, zhName, enName);
  }

  /**
   * 修改房型
   */
  @RequestMapping(value = "/seychelles/room/update.do", method = RequestMethod.POST)
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
    seychellesService.updateRoom(pk, name, _value);
  }

  /**
   * Ajax 获取房型列表
   */
  @RequestMapping(value = "/seychelles/room/ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public Object room(@RequestParam String islandId, @RequestParam String q, Model model) {
    return seychellesService.queryRoom(islandId, q, 10);
  }

  /**
   * 添加餐饮设施
   */
  @RequestMapping(value = "/seychelles/dining/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDining(@RequestParam String islandId, @RequestParam String zhName,
      @RequestParam String enName) {
    seychellesService.addDining(islandId, zhName, enName);
  }

  /**
   * 修改餐饮设施
   */
  @RequestMapping(value = "/seychelles/dining/update.do", method = RequestMethod.POST)
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
    seychellesService.updateDining(pk, name, _value);
  }

  /**
   * 增加海岛海报
   */
  @RequestMapping(value = "/seychelles/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addPicture(@RequestParam String islandId,
      @RequestParam("images[]") List<String> imageIds) {
    seychellesService.addPicture(islandId, imageIds);
  }

  /**
   * 修改海岛海报
   */
  @RequestMapping(value = "/seychelles/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updatePicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    seychellesService.updatePicture(pk, name, value);
  }

  /**
   * 删除海岛海报
   */
  @RequestMapping(value = "/seychelles/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deletePicture(@RequestParam String imageId) {
    seychellesService.deletePicture(imageId);
  }

  /**
   * 增加酒店图片
   */
  @RequestMapping(value = "/seychelles/hotel/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addHotelPicture(@RequestParam String islandId,
      @RequestParam("images[]") List<String> imageIds) {
    seychellesService.addHotelPicture(islandId, imageIds);
  }

  /**
   * 修改酒店图片
   */
  @RequestMapping(value = "/seychelles/hotel/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateHotelPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    seychellesService.updateHotelPicture(pk, name, value);
  }

  /**
   * 删除酒店图片
   */
  @RequestMapping(value = "/seychelles/hotel/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteHotelPicture(@RequestParam String imageId) {
    seychellesService.deleteHotelPicture(imageId);
  }

  /**
   * 增加房型图片
   */
  @RequestMapping(value = "/seychelles/room/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoomPicture(@RequestParam String roomId,
      @RequestParam("images[]") List<String> imageIds) {
    seychellesService.addRoomPicture(roomId, imageIds);
  }

  /**
   * 修改房型图片
   */
  @RequestMapping(value = "/seychelles/room/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateRoomPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String[] tmp = pk.split("\\|");
    seychellesService.updateRoomPicture(tmp[0], tmp[1], name, value);
  }

  /**
   * 删除房型图片
   */
  @RequestMapping(value = "/seychelles/room/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteRoomPicture(@RequestParam String imageId) {
    seychellesService.deleteRoomPicture(imageId);
  }

  /**
   * 增加餐厅图片
   */
  @RequestMapping(value = "/seychelles/dining/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDiningPicture(@RequestParam String diningId,
      @RequestParam("images[]") List<String> imageIds) {
    seychellesService.addDiningPicture(diningId, imageIds);
  }

  /**
   * 修改餐厅图片
   */
  @RequestMapping(value = "/seychelles/dining/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateDiningPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String[] tmp = pk.split("\\|");
    seychellesService.updateDiningPicture(tmp[0], tmp[1], name, value);
  }

  /**
   * 删除餐厅图片
   */
  @RequestMapping(value = "/seychelles/dining/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteDiningPicture(@RequestParam String imageId) {
    seychellesService.deleteDiningPicture(imageId);
  }

  /**
   * 航班信息
   */
  @RequestMapping(value = "/seychelles/flight/update.do", method = RequestMethod.GET)
  public String updateFlight(@RequestParam(required = false) String flightId, Model model) {
    if (flightId != null) {
      model.addAttribute("flight", transportationService.getTransportation(flightId));
    }
    return "/v1/seychelles/flight/update";
  }

  @RequestMapping(value = "/seychelles/flight/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateFlight(@RequestParam(required = false) String id, @RequestParam String number,
      @RequestParam String airline, @RequestParam String airlineCode, @RequestParam String from,
      @RequestParam String to, @RequestParam String departuresAirportCode,
      @RequestParam String arrivalsAirportCode, @RequestParam long departuresTime,
      @RequestParam long arrivalsTime, @RequestParam String extra,
      @RequestParam(required = false) String nonStop,
      @RequestParam(required = false) List<Integer> schedule) {
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
    if (nonStop != null) {
      flight.setNonStop(Boolean.valueOf(nonStop));
    }
    if (schedule != null) {
      flight.setSchedule(schedule);
    }
    flight.setLineType(Transportation.LINE_TYPE_SEYCHELLES);
    transportationService.updateTransportation(flight);
  }

  /**
   * 航班列表
   */
  @RequestMapping(value = "/seychelles/flight.do", method = RequestMethod.GET)
  public String flight(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", transportationService.queryTransportation(
        Transportation.LINE_TYPE_SEYCHELLES, page, pageSize));
    return "/v1/seychelles/flight";
  }

  /**
   * Ajax 获取航班列表
   */
  @RequestMapping("/seychelles/flight/ajax.do")
  @ResponseBody
  public List<Transportation> flight(@RequestParam String q) {
    return transportationService.queryTransportation(Transportation.LINE_TYPE_SEYCHELLES, q, 10);
  }

  /**
   * 航班组合信息
   */
  @RequestMapping(value = "/seychelles/flight/comb_update.do", method = RequestMethod.GET)
  public String updateFlightComb(@RequestParam(required = false) String combId, Model model) {
    // TODO
    return "/v1/seychelles/flight/comb_update";
  }

  /**
   * 航班组合信息
   */
  @RequestMapping(value = "/seychelles/flight/comb_update.do", method = RequestMethod.POST)
  public Object updateFlightComb(@RequestParam(required = false) String id,
      @RequestParam List<String> transfers, @RequestParam List<Integer> day, Model model) {
    if (transfers.size() < 2 || day.size() < 2 || transfers.size() != day.size()) {
      return illegal();
    }
    TransferComb comb = new TransferComb();
    if (StringUtils.isNotBlank(id)) {
      comb.setId(id);
    }
    comb.setType(Transportation.LINE_TYPE_SEYCHELLES);
    for (int i = 0; i < transfers.size(); i++) {
      Transportation transportation = transportationService.getTransportation(transfers.get(i));
      transportation.setDay(day.get(i));
      comb.getTransfers().add(transportation);
    }
    transportationService.updateTransferComb(comb);
    return ok();
  }

  /**
   * 航班组合列表
   */
  @RequestMapping(value = "/seychelles/flight/comb.do", method = RequestMethod.GET)
  public String flightComb(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", transportationService.queryTransferComb(
        Transportation.LINE_TYPE_SEYCHELLES, page, pageSize));
    return "/v1/seychelles/flight/comb";
  }

  /**
   * 航班组合信息
   */
  @RequestMapping(value = "/seychelles/flight/comb_delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteFlightComb(@RequestParam String id) {
    transportationService.deleteTransferComb(id);
  }

  /**
   * 添加产品
   */
  @RequestMapping(value = "/seychelles/product/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String addProduct(@RequestParam String name) {
    // TODO validate name
    Product product = productService.addProduct(Product.TYPE_SEYCHELLES, name);
    return product.getId();
  }

  /**
   * 修改产品
   */
  @RequestMapping(value = "/seychelles/product/update.do", method = RequestMethod.GET)
  public String updateProduct(@RequestParam String id, Model model) {
    model.addAttribute("product", productService.getProduct(id));
    return "/v1/seychelles/product/update";
  }

  /**
   * 修改产品
   */
  @RequestMapping(value = "/seychelles/product/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProduct(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(required = false, value = "value[]") List<String> values) throws Exception {
    Object _value = value == null ? values : value;
    if ("price".equals(name) || "marketPrice".equals(name)) {
      _value = (int) (Double.parseDouble(value) * 100);
    }
    if ("popular".equals(name) || "recommend".equals(name) || "special".equals(name)
        || "published".equals(name)) {
      _value = Boolean.valueOf(value);
    }
    if ("effectiveDate".equals(name) || "expiryDate".equals(name) || "departureDate".equals(name)) {
      _value = Constant.YYYYMMDD_FORMAT.parse(value);
    }
    productService.updateProduct(pk, name, _value);
  }

  /**
   * 产品列表
   */
  @RequestMapping(value = "/seychelles/product.do", method = RequestMethod.GET)
  public String product(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("settings", settingsService.getSettings());
    model.addAttribute("result", productService.queryProduct(page, pageSize));
    return "/v1/seychelles/product";
  }

  /**
   * 添加产品交通
   */
  @RequestMapping(value = "/seychelles/product/transportation/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductTransportation(@RequestParam String productId, @RequestParam String name) {
    productService.addTransportation(productId, name);
  }

  /**
   * 修改产品交通
   */
  @RequestMapping(value = "/seychelles/product/transportation/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductTransportation(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    productService.updateTransportation(pk, name, value);
  }

  /**
   * 删除产品交通
   */
  @RequestMapping(value = "/seychelles/product/transportation/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductTransportation(@RequestParam String id) {
    productService.deleteTransportation(id);
  }

  /**
   * 添加塞舌尔住宿（行程）
   */
  @RequestMapping(value = "/seychelles/product/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductRoom(@RequestParam String productId, @RequestParam String name) {
  // TODO productService.addSeychellesDetail(productId, name);
  }

  /**
   * 修改塞舌尔住宿（行程）
   */
  @RequestMapping(value = "/seychelles/product/room/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductRoom(@RequestParam String pk, @RequestParam String name,
      @RequestParam(required = false) String value,
      @RequestParam(value = "value[]", required = false) List<String> values) {
  // TODO productService.updateSeychellesDetail(pk, name, value == null ? values : value);
  }

  /**
   * 删除塞舌尔住宿（行程）
   */
  @RequestMapping(value = "/seychelles/product/room/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductRoom(@RequestParam String id) {
  // TODO productService.deleteSeychellesDetail(id);
  }

  /**
   * 价格日历
   */
  @RequestMapping(value = "/seychelles/product/price.do", method = RequestMethod.POST)
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
    productService.updatePrice(productId, startDate, endDate, _price, _childPrice, special, delete);
  }


  /**
   * 增加产品海报
   */
  @RequestMapping(value = "/seychelles/product/poster/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductPoster(@RequestParam String productId,
      @RequestParam("images[]") List<String> imageIds) {
    productService.addPoster(productId, imageIds);
  }

  /**
   * 修改产品海报
   */
  @RequestMapping(value = "/seychelles/product/poster/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductPoster(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    productService.updatePoster(pk, name, value);
  }

  /**
   * 删除产品海报
   */
  @RequestMapping(value = "/seychelles/product/poster/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductPoster(@RequestParam String imageId) {
    productService.deletePoster(imageId);
  }

  /**
   * 增加产品图片
   */
  @RequestMapping(value = "/seychelles/product/photo/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addProductPhoto(@RequestParam String productId,
      @RequestParam("images[]") List<String> imageIds) {
    productService.addPhoto(productId, imageIds);
  }

  /**
   * 修改产品图片
   */
  @RequestMapping(value = "/seychelles/product/photo/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateProductPhoto(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    productService.updatePhoto(pk, name, value);
  }

  /**
   * 删除产品图片
   */
  @RequestMapping(value = "/seychelles/product/photo/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteProductPhoto(@RequestParam String imageId) {
    productService.deletePhoto(imageId);
  }

}

package com.cqlybest.site.controller.admin;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.MauritiusHotel;
import com.cqlybest.common.bean.Transportation;
import com.cqlybest.common.service.MauritiusService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.SettingsService;
import com.cqlybest.common.service.TransportationService;

@Controller
public class MauritiusController {

  @Autowired
  private MauritiusService mauritiusService;
  @Autowired
  private TransportationService transportationService;
  @Autowired
  private ProductService productService;
  @Autowired
  private SettingsService settingsService;

  /**
   * 毛里求斯管理页面
   */
  @RequestMapping("/mauritius.do")
  public String mauritius() {
    return "/v1/mauritius";
  }

  /**
   * 添加海岛
   */
  @RequestMapping(value = "/mauritius/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String zhName, @RequestParam String enName) {
    // TODO validate zhName & enName
    MauritiusHotel hotel = mauritiusService.addHotel(zhName, enName);
    return hotel.getId();
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/mauritius/update.do", method = RequestMethod.GET)
  public String update(@RequestParam String id, Model model) {
    model.addAttribute("hotel", mauritiusService.getHotel(id));
    return "/v1/mauritius/update";
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/mauritius/update.do", method = RequestMethod.POST)
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
    mauritiusService.updateHotel(pk, name, _value);
  }

  /**
   * 海岛列表
   */
  @RequestMapping(value = "/mauritius/list.do", method = RequestMethod.GET)
  public String list(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", mauritiusService.queryHotel(page, pageSize));
    model.addAttribute("settings", settingsService.getSettings());
    return "/v1/mauritius/list";
  }

  /**
   * Ajax 获取海岛列表
   */
  @RequestMapping(value = "/mauritius/hotel/ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public Object list(@RequestParam String q, Model model) {
    return mauritiusService.queryHotel(q, 10);
  }

  /**
   * 添加房型
   */
  @RequestMapping(value = "/mauritius/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoom(@RequestParam String hotelId, @RequestParam String zhName,
      @RequestParam String enName) {
    mauritiusService.addRoom(hotelId, zhName, enName);
  }

  /**
   * 修改房型
   */
  @RequestMapping(value = "/mauritius/room/update.do", method = RequestMethod.POST)
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
    mauritiusService.updateRoom(pk, name, _value);
  }

  /**
   * Ajax 获取房型列表
   */
  @RequestMapping(value = "/mauritius/room/ajax.do", method = RequestMethod.GET)
  @ResponseBody
  public Object room(@RequestParam String hotelId, @RequestParam String q, Model model) {
    return mauritiusService.queryRoom(hotelId, q, 10);
  }

  /**
   * 添加餐饮设施
   */
  @RequestMapping(value = "/mauritius/dining/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDining(@RequestParam String hotelId, @RequestParam String zhName,
      @RequestParam String enName) {
    mauritiusService.addDining(hotelId, zhName, enName);
  }

  /**
   * 修改餐饮设施
   */
  @RequestMapping(value = "/mauritius/dining/update.do", method = RequestMethod.POST)
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
    mauritiusService.updateDining(pk, name, _value);
  }

  /**
   * 增加海岛海报
   */
  @RequestMapping(value = "/mauritius/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addPicture(@RequestParam String hotelId,
      @RequestParam("images[]") List<String> imageIds) {
    mauritiusService.addPicture(hotelId, imageIds);
  }

  /**
   * 修改海岛海报
   */
  @RequestMapping(value = "/mauritius/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updatePicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mauritiusService.updatePicture(pk, name, value);
  }

  /**
   * 删除海岛海报
   */
  @RequestMapping(value = "/mauritius/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deletePicture(@RequestParam String imageId) {
    mauritiusService.deletePicture(imageId);
  }

  /**
   * 增加酒店图片
   */
  @RequestMapping(value = "/mauritius/hotel/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addHotelPicture(@RequestParam String hotelId,
      @RequestParam("images[]") List<String> imageIds) {
    mauritiusService.addHotelPicture(hotelId, imageIds);
  }

  /**
   * 修改酒店图片
   */
  @RequestMapping(value = "/mauritius/hotel/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateHotelPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    mauritiusService.updateHotelPicture(pk, name, value);
  }

  /**
   * 删除酒店图片
   */
  @RequestMapping(value = "/mauritius/hotel/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteHotelPicture(@RequestParam String imageId) {
    mauritiusService.deleteHotelPicture(imageId);
  }

  /**
   * 增加房型图片
   */
  @RequestMapping(value = "/mauritius/room/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoomPicture(@RequestParam String roomId,
      @RequestParam("images[]") List<String> imageIds) {
    mauritiusService.addRoomPicture(roomId, imageIds);
  }

  /**
   * 修改房型图片
   */
  @RequestMapping(value = "/mauritius/room/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateRoomPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String[] tmp = pk.split("\\|");
    mauritiusService.updateRoomPicture(tmp[0], tmp[1], name, value);
  }

  /**
   * 删除房型图片
   */
  @RequestMapping(value = "/mauritius/room/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteRoomPicture(@RequestParam String imageId) {
    mauritiusService.deleteRoomPicture(imageId);
  }

  /**
   * 增加餐厅图片
   */
  @RequestMapping(value = "/mauritius/dining/picture/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDiningPicture(@RequestParam String diningId,
      @RequestParam("images[]") List<String> imageIds) {
    mauritiusService.addDiningPicture(diningId, imageIds);
  }

  /**
   * 修改餐厅图片
   */
  @RequestMapping(value = "/mauritius/dining/picture/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateDiningPicture(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String[] tmp = pk.split("\\|");
    mauritiusService.updateDiningPicture(tmp[0], tmp[1], name, value);
  }

  /**
   * 删除餐厅图片
   */
  @RequestMapping(value = "/mauritius/dining/picture/delete.do", method = RequestMethod.POST)
  @ResponseBody
  public void deleteDiningPicture(@RequestParam String imageId) {
    mauritiusService.deleteDiningPicture(imageId);
  }

  /**
   * 航班信息
   */
  @RequestMapping(value = "/mauritius/flight/update.do", method = RequestMethod.GET)
  public String updateFlight(@RequestParam(required = false) String flightId, Model model) {
    if (flightId != null) {
      model.addAttribute("flight", transportationService.getTransportation(flightId));
    }
    return "/v1/mauritius/flight/update";
  }

  @RequestMapping(value = "/mauritius/flight/update.do", method = RequestMethod.POST)
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
    flight.setLineType(Transportation.LINE_TYPE_MAURITIUS);
    transportationService.updateTransportation(flight);
  }

  /**
   * 航班列表
   */
  @RequestMapping(value = "/mauritius/flight.do", method = RequestMethod.GET)
  public String flight(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", transportationService.queryTransportation(
        Transportation.LINE_TYPE_MAURITIUS, page, pageSize));
    return "/v1/mauritius/flight";
  }

  /**
   * Ajax 获取航班列表
   */
  @RequestMapping("/mauritius/flight/ajax.do")
  @ResponseBody
  public List<Transportation> flight(@RequestParam String q) {
    return transportationService.queryTransportation(Transportation.LINE_TYPE_MAURITIUS, q, 10);
  }

}

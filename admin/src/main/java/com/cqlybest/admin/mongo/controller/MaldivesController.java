package com.cqlybest.admin.mongo.controller;

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

import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.bean.Transportation;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.TransportationService;
import com.cqlybest.common.service.OptionService;

@Controller("mongoMaldivesController")
public class MaldivesController {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private TransportationService transportationService;
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
    model.addAttribute("options", optionService.getOptions());
    return "/v1/maldives/list";
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
}

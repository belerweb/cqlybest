package com.cqlybest.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.mauritius.MauritiusDining;
import com.cqlybest.common.bean.mauritius.MauritiusHotel;
import com.cqlybest.common.bean.mauritius.MauritiusRoom;
import com.cqlybest.common.service.MauritiusService;

public class MauritiusController {

  @Autowired
  private MauritiusService mauritiusService;

  /**
   * 添加酒店
   */
  @RequestMapping(value = "/mauritius/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String zhName, @RequestParam String enName) {
    MauritiusHotel hotel = new MauritiusHotel();
    hotel.setZhName(zhName);
    hotel.setEnName(enName);
    mauritiusService.add(hotel);
    return hotel.getId();
  }

  /**
   * 修改酒店
   */
  @RequestMapping(value = "/mauritius/update.do", method = RequestMethod.GET)
  public void update(@RequestParam String id, Model model) {
    model.addAttribute("hotel", mauritiusService.get(id));
  }

  /**
   * 修改酒店
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
    mauritiusService.update(pk, name, _value);
  }

  @RequestMapping(value = "/mauritius/list.do", method = RequestMethod.GET)
  public void list(@RequestParam(defaultValue = "0") int page, Model model) {
    page = Math.max(1, page);
    int pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", mauritiusService.total());
    model.addAttribute("hotels", mauritiusService.list(page, pageSize));
  }

  @RequestMapping("/mauritius/delete.do")
  @ResponseBody
  public void del(@RequestParam(value = "ids[]") String[] ids) {
    mauritiusService.delete(ids);
  }

  /**
   * 添加房型
   */
  @RequestMapping(value = "/mauritius/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoom(@RequestParam String hotelId, @RequestParam String zhName,
      @RequestParam String enName) {
    MauritiusRoom room = new MauritiusRoom();
    room.setHotelId(hotelId);
    room.setZhName(zhName);
    room.setEnName(enName);
    mauritiusService.add(room);
  }

  /**
   * 修改房型
   */
  @RequestMapping(value = "/mauritius/room/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateRoom(@RequestParam Integer pk, @RequestParam String name,
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

  @RequestMapping("/mauritius/room/delete.do")
  @ResponseBody
  public void deleteRoom(@RequestParam Integer id) {
    mauritiusService.deleteRoom(id);
  }

  /**
   * 获取房型
   */
  @RequestMapping(value = "/mauritius/room.do", method = RequestMethod.GET)
  public String getRooms(@RequestParam String hotelId, Model model) {
    model.addAttribute("rooms", mauritiusService.getRooms(hotelId));
    return "/mauritius/update_room_accordion";
  }

  @RequestMapping(value = "/mauritius/dining/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDining(@RequestParam String hotelId, @RequestParam String zhName,
      @RequestParam String enName) {
    MauritiusDining dining = new MauritiusDining();
    dining.setHotelId(hotelId);
    dining.setZhName(zhName);
    dining.setEnName(enName);
    mauritiusService.add(dining);
  }

  @RequestMapping(value = "/mauritius/dining/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateDining(@RequestParam Integer pk, @RequestParam String name,
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

  @RequestMapping("/mauritius/dining/delete.do")
  @ResponseBody
  public void deleteDining(@RequestParam Integer id) {
    mauritiusService.deleteDining(id);
  }

  @RequestMapping(value = "/mauritius/dining.do", method = RequestMethod.GET)
  public String getDinings(@RequestParam String hotelId, Model model) {
    model.addAttribute("dinings", mauritiusService.getDinings(hotelId));
    return "/mauritius/update_dining_accordion";
  }

}

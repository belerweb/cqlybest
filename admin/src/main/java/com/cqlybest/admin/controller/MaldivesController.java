package com.cqlybest.admin.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.MaldivesDining;
import com.cqlybest.common.bean.MaldivesRoom;
import com.cqlybest.common.bean.MaldivesSeaIsland;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.OptionService;

@Controller
public class MaldivesController {

  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private OptionService optionService;

  /**
   * 添加海岛
   */
  @RequestMapping(value = "/maldives/add.do", method = RequestMethod.POST)
  @ResponseBody
  public String add(@RequestParam String zhName, @RequestParam String enName) {
    MaldivesSeaIsland island = new MaldivesSeaIsland();
    island.setZhName(zhName);
    island.setEnName(enName);
    maldivesService.add(island);
    return island.getId();
  }

  /**
   * 修改海岛
   */
  @RequestMapping(value = "/maldives/update.do", method = RequestMethod.GET)
  public void update(@RequestParam String id, Model model) {
    model.addAttribute("island", maldivesService.get(id));
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
    maldivesService.update(pk, name, _value);
  }

  @RequestMapping(value = "/maldives/list.do", method = RequestMethod.GET)
  public void list(@RequestParam(defaultValue = "0") int page, Model model) {
    page = Math.max(1, page);
    int pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", maldivesService.total());
    model.addAttribute("islands", maldivesService.list(page, pageSize));
    model.addAttribute("options", optionService.getOptions());
  }

  @RequestMapping("/maldives/delete.do")
  @ResponseBody
  public void del(@RequestParam(value = "ids[]") String[] ids) {
    maldivesService.delete(ids);
  }

  /**
   * 添加房型
   */
  @RequestMapping(value = "/maldives/room/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addRoom(@RequestParam String islandId, @RequestParam String zhName,
      @RequestParam String enName) {
    MaldivesRoom room = new MaldivesRoom();
    room.setIslandId(islandId);
    room.setZhName(zhName);
    room.setEnName(enName);
    maldivesService.add(room);
  }

  /**
   * 修改房型
   */
  @RequestMapping(value = "/maldives/room/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateRoom(@RequestParam Integer pk, @RequestParam String name,
      @RequestParam String value) {
    Object _value = value;
    if ("num".equals(name) || "displayOrder".equals(name)) {
      _value = StringUtils.isEmpty(value) ? null : Integer.valueOf(value);
    }
    maldivesService.updateRoom(pk, name, _value);
  }

  @RequestMapping("/maldives/room/delete.do")
  @ResponseBody
  public void deleteRoom(@RequestParam Integer id) {
    maldivesService.deleteRoom(id);
  }

  /**
   * 获取房型
   */
  @RequestMapping(value = "/maldives/room.do", method = RequestMethod.GET)
  public String getRooms(@RequestParam String islandId, Model model) {
    model.addAttribute("rooms", maldivesService.getRooms(islandId));
    return "/maldives/update_room_accordion";
  }

  @RequestMapping(value = "/maldives/dining/add.do", method = RequestMethod.POST)
  @ResponseBody
  public void addDining(@RequestParam String islandId, @RequestParam String zhName,
      @RequestParam String enName) {
    MaldivesDining dining = new MaldivesDining();
    dining.setIslandId(islandId);
    dining.setZhName(zhName);
    dining.setEnName(enName);
    maldivesService.add(dining);
  }

  @RequestMapping(value = "/maldives/dining/update.do", method = RequestMethod.POST)
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
    maldivesService.updateDining(pk, name, _value);
  }

  @RequestMapping("/maldives/dining/delete.do")
  @ResponseBody
  public void deleteDining(@RequestParam Integer id) {
    maldivesService.deleteDining(id);
  }

  @RequestMapping(value = "/maldives/dining.do", method = RequestMethod.GET)
  public String getDinings(@RequestParam String islandId, Model model) {
    model.addAttribute("dinings", maldivesService.getDinings(islandId));
    return "/maldives/update_dining_accordion";
  }

}

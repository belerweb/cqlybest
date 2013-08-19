package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.OptionService;

@Controller
public class MaldivesController {

  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private OptionService optionService;

  @RequestMapping("/maldives/delete.do")
  @ResponseBody
  public void del(@RequestParam(value = "ids[]") String[] ids) {
    maldivesService.delete(ids);
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

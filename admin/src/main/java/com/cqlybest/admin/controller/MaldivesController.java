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

import com.cqlybest.common.bean.MaldivesSeaIsland;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.JsonService;
import com.cqlybest.common.service.MaldivesService;

@Controller
public class MaldivesController {

  @Autowired
  private JsonService jsonService;
  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private DestinationService destinationService;
  @Autowired
  private DictService dictService;
  @Autowired
  private ImageService imageService;

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
    maldivesService.update(pk, name, _value);
  }

  @RequestMapping(value = "/maldives/list.do", method = RequestMethod.GET)
  public void products(@RequestParam(defaultValue = "0") int page, Model model) {
    page = Math.max(1, page);
    int pageSize = 10;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", maldivesService.total());
    model.addAttribute("islands", maldivesService.list(page, pageSize));
  }

  @RequestMapping("/maldives/delete.do")
  @ResponseBody
  public void del(@RequestParam(value = "ids[]") String[] ids) {
    maldivesService.delete(ids);
  }

}

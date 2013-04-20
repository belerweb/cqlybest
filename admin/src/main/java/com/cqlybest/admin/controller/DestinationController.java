package com.cqlybest.admin.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.Destination;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.JsonService;

@Controller
public class DestinationController {

  @Autowired
  private JsonService jsonService;

  @Autowired
  private DestinationService destinationService;

  @RequestMapping("/data/dict/destination.html")
  public void destination(Model model) throws JsonGenerationException, JsonMappingException,
      IOException {
    model.addAttribute("tree", jsonService.writeValueAsString(destinationService.getTree()));
  }

  @RequestMapping(value = "/data/dict/add_dest.html", method = RequestMethod.POST)
  @ResponseBody
  public void addDict(@RequestParam Integer pid, Destination node) {
    if (pid == 0) {
      destinationService.add(node);
    } else {
      destinationService.add(pid, node);
    }
  }

  @RequestMapping(value = "/data/dict/del_dest.html", method = RequestMethod.POST)
  @ResponseBody
  public void delDict(@RequestParam Integer id) {
    destinationService.delete(id);
  }

}

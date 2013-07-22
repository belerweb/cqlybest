package com.cqlybest.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.bean.FriendlyLink;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.service.FriendlyLinkService;

@Controller
public class FriendlyLinkController extends ControllerHelper {

  @Autowired
  private FriendlyLinkService friendlyLinkService;

  @RequestMapping(value = "/link/add.do", method = RequestMethod.POST)
  @ResponseBody
  public Integer add(@RequestParam String name) {
    FriendlyLink link = new FriendlyLink();
    link.setName(name);
    friendlyLinkService.add(link);
    return link.getId();
  }

  @RequestMapping(value = "/link/update.do", method = RequestMethod.GET)
  public void update(@RequestParam Integer id, Model model) {
    model.addAttribute("link", friendlyLinkService.get(id));
  }

  @RequestMapping(value = "/link/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void update(@RequestParam Integer pk, @RequestParam String name, @RequestParam String value) {
    Object _value = value;
    if (name.equals("displayOrder")) {
      _value = Integer.valueOf(value);
    }
    friendlyLinkService.update(pk, name, _value);
  }

  @RequestMapping(value = "/link/list.do", method = RequestMethod.GET)
  public void products(@RequestParam(defaultValue = "0") int page, Model model) {
    page = Math.max(1, page);
    int pageSize = Integer.MAX_VALUE;
    model.addAttribute("page", page);
    model.addAttribute("pageSize", pageSize);
    model.addAttribute("total", friendlyLinkService.total());
    model.addAttribute("links", friendlyLinkService.list(page, pageSize));
  }

  @RequestMapping("/link/delete.do")
  @ResponseBody
  public void del(@RequestParam(value = "ids[]") Integer[] ids) {
    friendlyLinkService.delete(ids);
  }

}

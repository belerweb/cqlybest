package com.cqlybest.site.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.common.bean.example.Company;
import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.example.ExampleService;

@Controller
public class AdminExampleController extends ControllerHelper {

  @Autowired
  private ExampleService exampleService;
  @Autowired
  private ImageService imageService;

  @RequestMapping("/admin/example.do")
  public String example(Model model) {
    return "/v1/example";
  }

  /**
   * 标杆企业
   */
  @RequestMapping("/admin/example/company.do")
  public String company(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", exampleService.queryCompany(page, pageSize));
    return "/v1/example/company";
  }

  @RequestMapping(method = RequestMethod.GET, value = "/admin/example/company/add.do")
  public String addCompany(Model model) {
    return "/v1/example/company/update";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/admin/example/company/add.do")
  public Object addCompany(@RequestParam String name, @RequestParam String area,
      @RequestParam String description, @RequestParam String logo, Model model) {
    Company company = new Company();
    company.setName(name);
    company.setArea(area);
    company.setDescription(description);
    if (StringUtils.isNotBlank(logo)) {
      company.setLogo(imageService.getImage(logo));
    }
    exampleService.addCompany(company);
    return ok();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/admin/example/company/update.do")
  public String updateCompany(@RequestParam String id, Model model) {
    model.addAttribute("company", exampleService.getCompany(id));
    return "/v1/example/company/update";
  }

  @RequestMapping(method = RequestMethod.POST, value = "/admin/example/company/update.do")
  public Object updateCompany(@RequestParam String id, @RequestParam String name,
      @RequestParam String area, @RequestParam String description, @RequestParam String logo,
      Model model) {
    Company company = exampleService.getCompany(id);
    if (company == null) {
      return illegal();
    }

    company.setName(name);
    company.setArea(area);
    company.setDescription(description);
    if (StringUtils.isNotBlank(logo)) {
      company.setLogo(imageService.getImage(logo));
    } else {
      company.setLogo(null);
    }
    exampleService.updateCompany(company);
    return ok();
  }

  @RequestMapping(method = RequestMethod.POST, value = "/admin/example/company/delete.do")
  public Object deleteCompany(@RequestParam String id, Model model) {
    exampleService.deleteCompany(id);
    return ok();
  }
}

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
    return "/v1/example/company/add";
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

}

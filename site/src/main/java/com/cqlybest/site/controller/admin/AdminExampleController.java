package com.cqlybest.site.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cqlybest.common.service.example.ExampleService;

@Controller
public class AdminExampleController {

  @Autowired
  private ExampleService exampleService;

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

}

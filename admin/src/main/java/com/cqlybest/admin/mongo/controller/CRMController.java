package com.cqlybest.admin.mongo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.service.CustomerService;
import com.cqlybest.common.mongo.service.UserService;

@Controller
public class CRMController extends ControllerHelper {

  @Autowired
  private UserService mongoUserService;
  @Autowired
  private CustomerService customerService;

  @RequestMapping("/crm.do")
  public String crm() {
    return "/v1/crm";
  }

  /**
   * 用户登录帐号列表
   */
  @RequestMapping(value = "/crm/login/list.do", method = RequestMethod.GET)
  public String list(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", mongoUserService.queryUser(page, pageSize));
    return "/v1/crm/login/list";
  }

  /**
   * 修改用户登录帐号
   */
  @RequestMapping(method = RequestMethod.POST, value = "/crm/login/update.do")
  @ResponseBody
  public void updateLogin(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    if (name.equals("admin")) {
      mongoUserService.toggleRole(pk, "ROLE_ADMIN", Boolean.valueOf(value));
    }
  }

  /**
   * 导入客户资料
   */
  @RequestMapping(method = RequestMethod.GET, value = "/crm/import/customer.do")
  public String importCustomer() {
    return "/v1/crm/import/customer";
  }

  /**
   * 导入客户资料：上传文件
   */
  @RequestMapping(method = RequestMethod.POST, value = "/crm/import/customer.do", params = "step=1")
  public Object importCustomer(@RequestParam MultipartFile file, Model model) {
    Workbook workbook;
    try {
      workbook = WorkbookFactory.create(file.getInputStream());
    } catch (Exception e) {
      e.printStackTrace();
      return error("请上传有效的 xls 或 xlsx 文件。");
    }

    List<List<Object>> data = new ArrayList<>();
    int sheets = workbook.getNumberOfSheets();
    for (int i = 0; i < sheets; i++) {
      Sheet sheet = workbook.getSheetAt(i);
      int rows = sheet.getLastRowNum();
      for (int j = 0; j < rows; j++) {
        data.add(new ArrayList<>());
        Row row = sheet.getRow(j);
        if (row == null) {
          continue;
        }
        short cells = row.getLastCellNum();
        data.get(j).addAll(Collections.nCopies(cells, null));
        for (short k = 0; k < cells; k++) {
          Cell cell = row.getCell(k);
          if (cell == null) {
            continue;
          }
          int type = cell.getCellType();
          if (type == Cell.CELL_TYPE_NUMERIC) {
            data.get(j).set(k, cell.getNumericCellValue());
          }
          if (type == Cell.CELL_TYPE_STRING) {
            data.get(j).set(k, cell.getStringCellValue());
          }
          if (type == Cell.CELL_TYPE_FORMULA) {
            // TODO
          }
          if (type == Cell.CELL_TYPE_BLANK) {
            // TODO
          }
          if (type == Cell.CELL_TYPE_BOOLEAN) {
            data.get(j).set(k, cell.getBooleanCellValue());
          }
          if (type == Cell.CELL_TYPE_ERROR) {
            // TODO
          }
        }
      }
    }

    model.addAttribute("data", data);
    return "/v1/crm/import/customer_step2";
  }

  /**
   * 导入客户资料：保存数据
   */
  @RequestMapping(method = RequestMethod.POST, value = "/crm/import/customer.do", params = "step=2")
  public Object importCustomer(@RequestParam String data) throws Exception {
    @SuppressWarnings("unchecked")
    List<Map<String, String>> customers = new ObjectMapper().readValue(data, List.class);
    customerService.importCustomer(customers);
    return ok();
  }
}

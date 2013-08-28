package com.cqlybest.admin.mongo.controller;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.Page;
import com.cqlybest.common.mongo.bean.page.Image;
import com.cqlybest.common.mongo.bean.page.Section;
import com.cqlybest.common.mongo.service.PageService;

/**
 * PC 版网站内容管理
 * 
 */
@Controller
public class WwwController extends ControllerHelper {

  @Autowired
  private PageService pageService;

  @RequestMapping("/www.do")
  public String www() {
    return "/v1/www";
  }

  @RequestMapping("/www/index.do")
  public String index(Model model) {
    Page page = pageService.getPage("www.index");
    if (page == null) {
      page = pageService.addPage("www.index");
    }

    model.addAttribute("page", page);
    return "/v1/www/index";
  }

  @RequestMapping("/www/index/section/add.do")
  public Object addIndexSection(@RequestParam String in, @RequestParam String name,
      @RequestParam String type, @RequestParam(required = false) String code,
      @RequestParam(required = false) String img,
      @RequestParam(value = "img.title", required = false) String imgTitle,
      @RequestParam(value = "img.description", required = false) String imgDescription,
      @RequestParam(value = "img.url", required = false) String imgUrl,
      @RequestParam(value = "img.target", required = false) String imgTarget) {
    if (StringUtils.isBlank(type)) {
      return error("请选择类型");
    }

    Section section = new Section();
    section.setId(UUID.randomUUID().toString());
    section.setName(name);
    section.setType(type);
    if (Section.TYPE_CODE.equals(type)) {
      section.setCode(code);
    }
    if (Section.TYPE_IMG.equals(type)) {
      if (StringUtils.isBlank(img)) {
        return error("请上传图片");
      }

      String[] imgStrs = img.split("\\.");
      Image image = new Image();
      image.setId(imgStrs[0]);
      image.setExtension(imgStrs[1]);
      image.setTitle(imgTitle);
      image.setDescription(imgDescription);
      image.setUrl(imgUrl);
      image.setTarget(imgTarget);
      section.setImg(image);
    }

    pageService.addSection("www.index", in, section);
    return ok();
  }

  @RequestMapping("/www/index/section/code/update.do")
  @ResponseBody
  public void updateIndexCodeSection(@RequestParam String in, @RequestParam String sectionId,
      @RequestParam(required = false) String name, @RequestParam(required = false) String code) {
    pageService.updateCodeSection(in, sectionId, name, code);
  }

  @RequestMapping("/www/index/section/img/update.do")
  public Object updateIndexImgSection(@RequestParam String in, @RequestParam String sectionId,
      @RequestParam(required = false) String name, @RequestParam(required = false) String img,
      @RequestParam(value = "img.title", required = false) String imgTitle,
      @RequestParam(value = "img.description", required = false) String imgDescription,
      @RequestParam(value = "img.url", required = false) String imgUrl,
      @RequestParam(value = "img.target", required = false) String imgTarget) {
    if (StringUtils.isBlank(img)) {
      return error("请上传图片");
    }
    pageService.updateImageSection(in, sectionId, name, img, imgTitle, imgDescription, imgUrl,
        imgTarget);
    return ok();
  }

  @RequestMapping("/www/index/section/delete.do")
  @ResponseBody
  public void deleteIndexSection(@RequestParam String in, @RequestParam String sectionId) {
    pageService.deleteSection(in, sectionId);
  }

}

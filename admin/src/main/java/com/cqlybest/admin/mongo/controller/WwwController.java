package com.cqlybest.admin.mongo.controller;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cqlybest.common.controller.ControllerHelper;
import com.cqlybest.common.mongo.bean.FriendlyLink;
import com.cqlybest.common.mongo.bean.ImageMeta;
import com.cqlybest.common.mongo.bean.Page;
import com.cqlybest.common.mongo.bean.page.Image;
import com.cqlybest.common.mongo.bean.page.Section;
import com.cqlybest.common.mongo.service.FriendlyLinkService;
import com.cqlybest.common.mongo.service.PageService;

/**
 * PC 版网站内容管理
 * 
 */
@Controller
public class WwwController extends ControllerHelper {

  @Autowired
  private PageService pageService;
  @Autowired
  private FriendlyLinkService friendlyLinkService;

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


  /**
   * 友情链接
   */
  @RequestMapping(value = "/www/friendlylink/add.do", method = RequestMethod.GET)
  public String addFriendlyLink(@RequestParam(required = false) String linkId, Model model) {
    if (linkId != null) {
      model.addAttribute("flight", friendlyLinkService.getLink(linkId));
    }
    return "/v1/www/friendlylink/add";
  }

  @RequestMapping(value = "/www/friendlylink/add.do", method = RequestMethod.POST)
  public Object addFriendlyLink(@RequestParam(required = false) String name,
      @RequestParam(required = false) String title, @RequestParam(required = false) String link,
      @RequestParam(required = false) String target, @RequestParam(required = false) String image,
      @RequestParam(required = false) Integer displayOrder) {
    if (StringUtils.isBlank(name)) {
      return error("请填写名称");
    }
    if (StringUtils.isBlank(link)) {
      return error("请填写链接地址");
    }

    FriendlyLink friendlyLink = new FriendlyLink();
    friendlyLink.setId(link);
    friendlyLink.setName(name);
    friendlyLink.setTitle(title);
    friendlyLink.setLink(link);
    if (StringUtils.isNotBlank(target)) {
      friendlyLink.setTarget(target);
    }
    if (StringUtils.isNotBlank(image)) {
      String[] imgStr = image.split("\\.");
      ImageMeta img = new ImageMeta();
      img.setId(imgStr[0]);
      img.setExtension(imgStr[1]);
      friendlyLink.setImage(img);
    }
    friendlyLink.setDisplayOrder(displayOrder);
    friendlyLinkService.addLink(friendlyLink);
    return ok();
  }

  @RequestMapping(value = "/www/friendlylink/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateFriendlyLink(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    friendlyLinkService.updateLink(pk, name, value);
  }

  /**
   * 友情链接列表
   */
  @RequestMapping(value = "/www/friendlylink.do", method = RequestMethod.GET)
  public String friendlylink(@RequestParam(defaultValue = "0") int page, Model model) {
    int pageSize = 10;
    model.addAttribute("result", friendlyLinkService.queryLink(page, pageSize));
    return "/v1/www/friendlylink";
  }

  @RequestMapping("/www/friendlylink/delete.do")
  @ResponseBody
  public void deleteFriendlyLink(@RequestParam String linkId) {
    friendlyLinkService.deleteLink(linkId);
  }

}

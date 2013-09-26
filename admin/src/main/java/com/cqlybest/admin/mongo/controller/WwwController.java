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
import com.cqlybest.common.mongo.bean.Link;
import com.cqlybest.common.mongo.bean.Page;
import com.cqlybest.common.mongo.bean.page.BooleanCondition;
import com.cqlybest.common.mongo.bean.page.Condition;
import com.cqlybest.common.mongo.bean.page.Image;
import com.cqlybest.common.mongo.bean.page.IntegerCondition;
import com.cqlybest.common.mongo.bean.page.MaldivesIslandCondition;
import com.cqlybest.common.mongo.bean.page.ProductCondition;
import com.cqlybest.common.mongo.bean.page.Section;
import com.cqlybest.common.mongo.service.FriendlyLinkService;
import com.cqlybest.common.mongo.service.ImageService;
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
  private ImageService mongoImageService;
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


  @RequestMapping(value = "/www/index/poster/add.do", method = RequestMethod.POST)
  public Object addIndexPoster(@RequestParam(required = false) String name,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String description,
      @RequestParam(required = false) String url, @RequestParam(required = false) String target,
      @RequestParam(required = false) String image,
      @RequestParam(required = false) Integer displayOrder) {
    if (StringUtils.isBlank(name)) {
      return error("请填写名称");
    }
    if (StringUtils.isBlank(image)) {
      return error("请上传图片");
    }

    Link link = new Link();
    link.setName(name);
    link.setTitle(title);
    link.setDescription(description);;
    link.setLink(url);
    if (StringUtils.isNotBlank(target)) {
      link.setTarget(target);
    }
    link.setImage(mongoImageService.getImage(image));
    link.setDisplayOrder(displayOrder);
    pageService.addPoster("www.index", link);
    return ok();
  }

  @RequestMapping(value = "/www/index/poster/update.do", method = RequestMethod.POST)
  @ResponseBody
  public void updateIndexPoster(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    pageService.updatePoster(pk, name, value);
  }

  @RequestMapping("/www/index/poster/delete.do")
  @ResponseBody
  public void deleteIndexPoster(@RequestParam String posterId) {
    pageService.deletePoster(posterId);
  }

  @RequestMapping("/www/index/section/add.do")
  public Object addIndexSection(@RequestParam String in, @RequestParam String name,
      @RequestParam String type, @RequestParam(required = false) String code,
      @RequestParam(required = false) String img,
      @RequestParam(value = "img.title", required = false) String imgTitle,
      @RequestParam(value = "img.description", required = false) String imgDescription,
      @RequestParam(value = "img.url", required = false) String imgUrl,
      @RequestParam(value = "img.target", required = false) String imgTarget,
      @RequestParam(value = "mdc.level.type", required = false) String mdcLevelType,
      @RequestParam(value = "mdc.level.value", required = false) String mdcLevelValue,
      @RequestParam(value = "mdc.hotelLevel.type", required = false) String mdcHotelLevelType,
      @RequestParam(value = "mdc.hotelLevel.value", required = false) String mdcHotelLevelValue,
      @RequestParam(value = "pc.type.value", required = false) String pcTypeValue,
      @RequestParam(value = "pc.name.type", required = false) String pcNameType,
      @RequestParam(value = "pc.name.value", required = false) String pcNameValue,
      @RequestParam(value = "pc.popular.value", required = false) String pcPopularValue,
      @RequestParam(value = "pc.recommend.value", required = false) String pcRecommendValue,
      @RequestParam(value = "pc.special.value", required = false) String pcSpecialValue,
      @RequestParam(value = "pc.dcity.value", required = false) String pcDcityValue,
      @RequestParam(value = "pc.rmonth.value", required = false) String pcRmonthValue,
      @RequestParam(required = false) Integer number, @RequestParam(required = false) Boolean more) {
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
    if (Section.TYPE_PRODUCT.equals(type)) {
      section.setPc(makeProductCondition(pcTypeValue, pcNameType, pcNameValue, pcPopularValue,
          pcRecommendValue, pcSpecialValue, pcDcityValue, pcRmonthValue));
    }
    if (Section.TYPE_MALDIVES.equals(type)) {
      MaldivesIslandCondition mdc = new MaldivesIslandCondition();
      if (StringUtils.isNotBlank(mdcLevelValue)) {
        mdc.setLevel(new Condition(Integer.valueOf(mdcLevelType), mdcLevelValue));
      }
      if (StringUtils.isNotBlank(mdcHotelLevelValue)) {
        mdc.setHotelLevel(new IntegerCondition(Integer.valueOf(mdcHotelLevelType), Integer
            .valueOf(mdcHotelLevelValue)));
      }
      section.setMdc(mdc);
    }

    section.setNumber(number);
    section.setMore(more);
    pageService.addSection("www.index", in, section);
    return ok();
  }

  private ProductCondition makeProductCondition(String typeValue, String nameType,
      String nameValue, String popularValue, String recommendValue, String specialValue,
      String dcityValue, String rmonthValue) {
    ProductCondition pc = new ProductCondition();
    if (StringUtils.isNotBlank(typeValue)) {
      pc.setType(new Condition(Condition.CONDITION_TYPE_EQ, typeValue));
    }
    if (StringUtils.isNotBlank(nameValue)) {
      pc.setName(new Condition(Integer.valueOf(nameType), nameValue));
    }
    if (StringUtils.isNotBlank(popularValue)) {
      pc
          .setPopular(new BooleanCondition(Condition.CONDITION_TYPE_EQ, Boolean
              .valueOf(popularValue)));
    }
    if (StringUtils.isNotBlank(recommendValue)) {
      pc.setRecommend(new BooleanCondition(Condition.CONDITION_TYPE_EQ, Boolean
          .valueOf(recommendValue)));
    }
    if (StringUtils.isNotBlank(specialValue)) {
      pc
          .setSpecial(new BooleanCondition(Condition.CONDITION_TYPE_EQ, Boolean
              .valueOf(specialValue)));
    }
    if (StringUtils.isNotBlank(dcityValue)) {
      pc.setDepartureCity(new Condition(Condition.CONDITION_TYPE_IN, dcityValue));
    }
    if (StringUtils.isNotBlank(rmonthValue)) {
      pc.setRecommendedMonth(new Condition(Condition.CONDITION_TYPE_IN, rmonthValue));
    }
    return pc;
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

  @RequestMapping("/www/index/section/maldives/update.do")
  public Object updateIndexMdSection(@RequestParam String in, @RequestParam String sectionId,
      @RequestParam(required = false) String name,
      @RequestParam(value = "mdc.level.type", required = false) String mdcLevelType,
      @RequestParam(value = "mdc.level.value", required = false) String mdcLevelValue,
      @RequestParam(value = "mdc.hotelLevel.type", required = false) String mdcHotelLevelType,
      @RequestParam(value = "mdc.hotelLevel.value", required = false) String mdcHotelLevelValue,
      @RequestParam(required = false) Integer number, @RequestParam(required = false) Boolean more) {
    Section section = new Section();
    section.setId(sectionId);
    section.setName(name);
    section.setType(Section.TYPE_MALDIVES);
    section.setNumber(number);
    section.setMore(more);

    MaldivesIslandCondition mdc = new MaldivesIslandCondition();
    if (StringUtils.isNotBlank(mdcLevelValue)) {
      mdc.setLevel(new Condition(Integer.valueOf(mdcLevelType), mdcLevelValue));
    }
    if (StringUtils.isNotBlank(mdcHotelLevelValue)) {
      mdc.setHotelLevel(new IntegerCondition(Integer.valueOf(mdcHotelLevelType), Integer
          .valueOf(mdcHotelLevelValue)));
    }
    section.setMdc(mdc);
    pageService.updateSection(in, sectionId, section);
    return ok();
  }

  @RequestMapping("/www/index/section/product/update.do")
  public Object updateIndexProductSection(@RequestParam String in, @RequestParam String sectionId,
      @RequestParam(required = false) String name,
      @RequestParam(value = "pc.type.value", required = false) String pcTypeValue,
      @RequestParam(value = "pc.name.type", required = false) String pcNameType,
      @RequestParam(value = "pc.name.value", required = false) String pcNameValue,
      @RequestParam(value = "pc.popular.value", required = false) String pcPopularValue,
      @RequestParam(value = "pc.recommend.value", required = false) String pcRecommendValue,
      @RequestParam(value = "pc.special.value", required = false) String pcSpecialValue,
      @RequestParam(value = "pc.dcity.value", required = false) String pcDcityValue,
      @RequestParam(value = "pc.rmonth.value", required = false) String pcRmonthValue,
      @RequestParam(required = false) Integer number, @RequestParam(required = false) Boolean more) {
    Section section = new Section();
    section.setId(sectionId);
    section.setName(name);
    section.setType(Section.TYPE_PRODUCT);
    section.setNumber(number);
    section.setMore(more);

    section.setPc(makeProductCondition(pcTypeValue, pcNameType, pcNameValue, pcPopularValue,
        pcRecommendValue, pcSpecialValue, pcDcityValue, pcRmonthValue));
    pageService.updateSection(in, sectionId, section);
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
    friendlyLink.setName(name);
    friendlyLink.setTitle(title);
    friendlyLink.setLink(link);
    if (StringUtils.isNotBlank(target)) {
      friendlyLink.setTarget(target);
    }
    if (StringUtils.isNotBlank(image)) {
      friendlyLink.setImage(mongoImageService.getImage(image));
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

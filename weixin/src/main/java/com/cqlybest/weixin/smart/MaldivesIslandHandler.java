package com.cqlybest.weixin.smart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.maldives.MaldivesSeaIsland;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.bean.ResponseNewsMessage;
import com.cqlybest.weixin.bean.ResponseNewsMessage.Article;

@Component
public class MaldivesIslandHandler implements Handler {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private SettingsService settingsService;

  private Map<RequestMessage, MaldivesSeaIsland> cached =
      new HashMap<RequestMessage, MaldivesSeaIsland>();

  @Override
  public boolean support(RequestMessage request) {
    String content = request.getContent();
    if (StringUtils.isBlank(content)) {
      return false;
    }

    content = content.replaceAll("岛", "");
    if (StringUtils.isBlank(content)) {
      return false;
    }

    // TODO
    return false;
  }

  @Override
  public ResponseMessage handle(RequestMessage request) {
    String siteUrl =
        (String) ((Map<?, ?>) settingsService.getSettings().get("basic")).get("mobileSiteUrl");
    ResponseNewsMessage response = new ResponseNewsMessage();
    response.setFromUserName(request.getToUserName());
    response.setToUserName(request.getFromUserName());
    response.setCreateTime(System.currentTimeMillis());

    List<Article> articles = new ArrayList<>();
    MaldivesSeaIsland island = cached.remove(request); // 海岛
    List<Image> images =
        imageService.getImages(Constant.IMAGE_MALDIVES_HOTEL_PICTURE, island.getId());
    if (!images.isEmpty()) {
      Article article = new Article();
      article.setTitle(island.getZhName() + "|" + island.getEnName());
      article.setDescription(island.getAd());
      if (siteUrl != null) {
        article.setPicUrl(siteUrl + "/image/" + images.get(0).getId() + "."
            + images.get(0).getImageType());
        article.setUrl(siteUrl + "/maldives/" + island.getId() + ".html");
      }
      articles.add(article);
    }

    // TODO 返回相关产品

    response.setArticles(articles);
    response.setCount(articles.size());
    return response.getCount() == 0 ? null : response;
  }

  @Override
  public int priority() {
    return 0;
  }

}

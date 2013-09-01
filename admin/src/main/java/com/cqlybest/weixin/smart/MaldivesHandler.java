package com.cqlybest.weixin.smart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.mongo.bean.ImageMeta;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.service.MaldivesService;
import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.bean.ResponseNewsMessage;
import com.cqlybest.weixin.bean.ResponseNewsMessage.Article;

@Component
public class MaldivesHandler implements Handler {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private SettingsService settingsService;

  @Override
  public boolean support(RequestMessage request) {
    String content = request.getContent();
    return content != null && "text".equals(request.getMsgType())
        && content.matches("(.*马尔代夫.*)|(.*馬爾代夫.*)|(.*马代.*)|(.*馬代.*)|(.*maldives.*)|(md)");
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
    List<MaldivesIsland> islands = mongoMaldivesService.queryIsland(0, 10).getItems();
    boolean first = true;
    for (MaldivesIsland island : islands) {
      List<ImageMeta> images = island.getHotelPictures();
      if (!images.isEmpty()) {
        Article article = new Article();
        article.setTitle(island.getZhName() + "|" + island.getEnName());
        article.setDescription(island.getAd());
        if (siteUrl != null) {
          article.setPicUrl(siteUrl + (first ? "/image/640/320/" : "/image/80/80/")
              + images.get(0).getId() + "." + images.get(0).getExtension());
          article.setUrl(siteUrl + "/maldives/" + island.getId() + ".html");
        }
        articles.add(article);
        first = false;
      }
    }
    response.setArticles(articles);
    response.setCount(articles.size());
    return response.getCount() == 0 ? null : response;
  }

  @Override
  public int priority() {
    return 0;
  }

}

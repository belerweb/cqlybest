package com.cqlybest.weixin.smart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.MaldivesIsland;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.SettingsService;
import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.bean.ResponseNewsMessage;
import com.cqlybest.weixin.bean.ResponseNewsMessage.Article;
import com.googlecode.mjorm.query.Query;
import com.googlecode.mjorm.query.QueryGroup;
import com.googlecode.mjorm.query.criteria.DocumentCriterion;
import com.googlecode.mjorm.query.criteria.EqualsCriterion;
import com.googlecode.mjorm.query.criteria.FieldCriterion;
import com.googlecode.mjorm.query.criteria.GroupedQueryCriterion;
import com.googlecode.mjorm.query.criteria.GroupedQueryCriterion.Group;

@Component
public class MaldivesIslandHandler implements Handler {

  @Autowired
  private MaldivesService mongoMaldivesService;
  @Autowired
  private ProductService mongoProductService;
  @Autowired
  private SettingsService settingsService;

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

    return true;
  }

  @Override
  public ResponseMessage handle(RequestMessage request) {
    String siteUrl =
        (String) ((Map<?, ?>) settingsService.getSettings().get("basic")).get("mobileSiteUrl");
    ResponseNewsMessage response = new ResponseNewsMessage();
    response.setFromUserName(request.getToUserName());
    response.setToUserName(request.getFromUserName());
    response.setCreateTime(System.currentTimeMillis());

    String query = ".*" + request.getContent().replaceAll("岛", StringUtils.EMPTY) + ".*";
    List<DocumentCriterion> conditions = new ArrayList<>();
    QueryGroup queryGroup =
        new QueryGroup(Arrays.asList(new Query[] {Query.start().regex("zhName", query),
            Query.start().regex("enName", query), Query.start().regex("byName", query)}));
    conditions.add(new GroupedQueryCriterion(Group.OR, queryGroup));

    List<Article> articles = new ArrayList<>();

    MaldivesIsland island = mongoMaldivesService.queryIsland(conditions);
    if (island != null && !island.getHotelPictures().isEmpty()) {
      Image image = island.getHotelPictures().get(0);
      Article article = new Article();
      article.setTitle(island.getZhName() + "|" + island.getEnName());
      article.setDescription(island.getAd());
      if (siteUrl != null) {
        article.setPicUrl(siteUrl + "/image/640/320/" + image.getId() + "." + image.getExtension());
        article.setUrl(siteUrl + "/maldives/" + island.getId() + ".html");
      }
      articles.add(article);

      conditions.clear();
      conditions.add(new FieldCriterion("maldivesDetails.island.id", new EqualsCriterion(island
          .getId())));
      for (Product product : mongoProductService.queryProduct(conditions, 0, 5).getItems()) {
        if (!product.getPosters().isEmpty()) {
          image = island.getHotelPictures().get(0);
          article = new Article();
          article.setTitle(product.getName()
              + StringUtils.join(product.getBriefTrip(), StringUtils.EMPTY));
          article.setDescription(product.getDescription());
          if (siteUrl != null) {
            article.setPicUrl(siteUrl + "/image/80/80/" + image.getId() + "." + image.getExtension());
            article.setUrl(siteUrl + "/product/" + product.getId() + ".html");
          }
          articles.add(article);
        }
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

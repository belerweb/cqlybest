package com.cqlybest.common.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.Link;
import com.cqlybest.common.bean.Page;
import com.cqlybest.common.bean.page.Section;
import com.cqlybest.common.dao.MongoDao;

@Service
public class PageService {

  @Autowired
  private MongoDao mongoDao;

  public Page addPage(String id) {
    Page page = new Page();
    page.setId(id);
    return mongoDao.createObject("Page", page);
  }

  public Page getPage(String id) {
    return mongoDao.findById("Page", Page.class, id);
  }

  public void addSection(String pageId, String in, Section section) {
    mongoDao.createQuery("Page").eq("_id", pageId).modify().push(in, mongoDao.unmap(section))
        .update();
  }

  public void updateCodeSection(String in, String sectionId, String name, String code) {
    mongoDao.createQuery("Page").eq(in + ".id", sectionId).modify().set(in + ".$.name", name).set(
        in + ".$.code", code).update();
  }

  public void updateSection(String in, String sectionId, Section section) {
    mongoDao.createQuery("Page").eq(in + ".id", sectionId).modify().set(in + ".$",
        mongoDao.unmap(section)).update();
  }

  public void updateImageSection(String in, String sectionId, String name, String img,
      String imgTitle, String imgDescription, String imgUrl, String imgTarget) {
    String[] imgStr = img.split("\\.");
    mongoDao.createQuery("Page").eq(in + ".id", sectionId).modify().set(in + ".$.name", name).set(
        in + ".$.img.id", imgStr[0]).set(in + ".$.img.extension", imgStr[1]).set(
        in + ".$.img.title", imgTitle).set(in + ".$.img.description", imgDescription).set(
        in + ".$.img.url", imgUrl).set(in + ".$.img.target", imgTarget).update();
  }

  public void deleteSection(String in, String sectionId) {
    Map<String, String> delete = new HashMap<>();
    delete.put("id", sectionId);
    mongoDao.createQuery("Page").eq(in + ".id", sectionId).modify().pull(in, delete).update();
  }

  public void addPoster(String pageId, Link poster) {
    poster.setId(UUID.randomUUID().toString());
    mongoDao.createQuery("Page").eq("_id", pageId).modify().push("posters", mongoDao.unmap(poster))
        .update();
  }

  public void updatePoster(String posterId, String property, String value) {
    Object _value = value;
    if ("displayOrder".equals(property)) {
      _value = StringUtils.isBlank(value) ? null : Integer.valueOf(value);
    }
    if ("published".equals(property)) {
      _value = Boolean.valueOf(value);
    }

    mongoDao.createQuery("Page").eq("posters.id", posterId).modify().set("posters.$." + property,
        _value).update();
  }

  public void deletePoster(String posterId) {
    Map<String, String> delete = new HashMap<>();
    delete.put("id", posterId);
    mongoDao.createQuery("Page").eq("posters.id", posterId).modify().pull("posters", delete)
        .update();
  }
}

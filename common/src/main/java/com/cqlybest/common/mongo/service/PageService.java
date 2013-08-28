package com.cqlybest.common.mongo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.mongo.bean.Page;
import com.cqlybest.common.mongo.bean.page.Section;
import com.cqlybest.common.mongo.dao.MongoDb;

@Service
public class PageService {

  @Autowired
  private MongoDb mongoDb;

  public Page addPage(String id) {
    Page page = new Page();
    page.setId(id);
    return mongoDb.createObject("Page", page);
  }

  public Page getPage(String id) {
    return mongoDb.findById("Page", Page.class, id);
  }

  public void addSection(String pageId, String in, Section section) {
    mongoDb.createQuery("Page").eq("_id", pageId).modify().push(in, mongoDb.unmap(section))
        .update();
  }

  public void updateCodeSection(String in, String sectionId, String name, String code) {
    mongoDb.createQuery("Page").eq(in + ".id", sectionId).modify().set(in + ".$.name", name).set(
        in + ".$.code", code).update();
  }

  public void updateImageSection(String in, String sectionId, String name, String img,
      String imgTitle, String imgDescription, String imgUrl, String imgTarget) {
    String[] imgStr = img.split("\\.");
    mongoDb.createQuery("Page").eq(in + ".id", sectionId).modify().set(in + ".$.name", name).set(
        in + ".$.img.id", imgStr[0]).set(in + ".$.img.extension", imgStr[1]).set(
        in + ".$.img.title", imgTitle).set(in + ".$.img.description", imgDescription).set(
        in + ".$.img.url", imgUrl).set(in + ".$.img.target", imgTarget).update();
  }

  public void deleteSection(String in, String sectionId) {
    Map<String, String> delete = new HashMap<>();
    delete.put("id", sectionId);
    mongoDb.createQuery("Page").eq(in + ".id", sectionId).modify().pull(in, delete).update();
  }
}

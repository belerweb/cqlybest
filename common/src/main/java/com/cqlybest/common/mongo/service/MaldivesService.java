package com.cqlybest.common.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.Constant;
import com.cqlybest.common.mongo.bean.ImageMeta;
import com.cqlybest.common.mongo.bean.MaldivesDining;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.bean.MaldivesRoom;
import com.cqlybest.common.mongo.bean.QueryResult;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.googlecode.mjorm.query.DaoQuery;
import com.mongodb.WriteResult;

@Service("mongoMaldivesService")
public class MaldivesService {

  @Autowired
  private MongoDb mongoDb;

  public MaldivesIsland addIsland(String zhName, String enName) {
    MaldivesIsland island = new MaldivesIsland();
    island.setId(UUID.randomUUID().toString());
    island.setZhName(zhName);
    island.setEnName(enName);

    Date now = new Date();
    island.setCreatedTime(now);
    island.setLastUpdated(now);
    return mongoDb.getMongoDao().createObject("MaldivesIsland", island);
  }

  public int updateIsland(String islandId, String property, Object value) {
    WriteResult result =
        mongoDb.createQuery("MaldivesIsland").eq("id", islandId).modify().set(property, value).set(
            "lastUpdated", new Date()).update();
    return result.getN();
  }

  public QueryResult<MaldivesIsland> queryIsland(int page, int pageSize) {
    QueryResult<MaldivesIsland> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDb.createQuery("MaldivesIsland");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(MaldivesIsland.class).readAll());

    return result;
  }

  public MaldivesIsland getIsland(String id) {
    return mongoDb.createQuery("MaldivesIsland").eq("id", id).findObject(MaldivesIsland.class);
  }

  public void addRoom(String islandId, String zhName, String enName) {
    MaldivesRoom room = new MaldivesRoom();
    room.setId(UUID.randomUUID().toString());
    room.setZhName(zhName);
    room.setEnName(enName);
    mongoDb.createQuery("MaldivesIsland").eq("id", islandId).modify().addToSet("rooms",
        mongoDb.unmap(room)).update();
  }

  public void updateRoom(String roomId, String property, Object value) {
    mongoDb.createQuery("MaldivesIsland").eq("rooms.id", roomId).modify().set(
        "rooms.$." + property, value).update();
  }

  public void addDining(String islandId, String zhName, String enName) {
    MaldivesDining dining = new MaldivesDining();
    dining.setId(UUID.randomUUID().toString());
    dining.setZhName(zhName);
    dining.setEnName(enName);
    mongoDb.createQuery("MaldivesIsland").eq("id", islandId).modify().addToSet("dinings",
        mongoDb.unmap(dining)).update();
  }

  public void updateDining(String diningId, String property, Object value) {
    mongoDb.createQuery("MaldivesIsland").eq("dinings.id", diningId).modify().set(
        "dinings.$." + property, value).update();
  }

  public void addPicture(String islandId, List<String> filenames) {
    List<Object> images = new ArrayList<>();
    List<String> imageIds = new ArrayList<>();
    for (String fileName : filenames) {
      String[] tmp = fileName.split("\\.");
      imageIds.add(tmp[0]);
      ImageMeta image = new ImageMeta();
      image.setId(tmp[0]);
      image.setExtension(tmp[1]);
      images.add(mongoDb.unmap(image));
    }
    // 保存图片
    mongoDb.createQuery("MaldivesIsland").eq("id", islandId).modify()
        .addToSetEach("pictures", images).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").in("id", imageIds).modify()
        .set("extra", Constant.IMAGE_MALDIVES_ISLAND_POSTER).set("extraKey", islandId).update();
  }
}

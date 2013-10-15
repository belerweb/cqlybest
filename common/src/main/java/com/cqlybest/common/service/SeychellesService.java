package com.cqlybest.common.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.bean.seychelles.SeychellesDining;
import com.cqlybest.common.bean.seychelles.SeychellesIsland;
import com.cqlybest.common.bean.seychelles.SeychellesRoom;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.query.Query;
import com.googlecode.mjorm.query.criteria.DocumentCriterion;
import com.mongodb.WriteResult;

@Service
public class SeychellesService {

  @Autowired
  private MongoDao mongoDao;

  public SeychellesIsland addIsland(String zhName, String enName) {
    SeychellesIsland island = new SeychellesIsland();
    island.setId(UUID.randomUUID().toString());
    island.setZhName(zhName);
    island.setEnName(enName);

    Date now = new Date();
    island.setCreatedTime(now);
    island.setLastUpdated(now);
    return mongoDao.createObject("SeychellesIsland", island);
  }

  public int updateIsland(String islandId, String property, Object value) {
    WriteResult result =
        mongoDao.createQuery("SeychellesIsland").eq("_id", islandId).modify().set(property, value)
            .set("lastUpdated", new Date()).update();
    return result.getN();
  }

  public QueryResult<SeychellesIsland> queryIsland(int page, int pageSize) {
    QueryResult<SeychellesIsland> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("SeychellesIsland");
    result.setTotal(query.countObjects());

    query.addSort("createdTime", Constant.SORT_ASC);
    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(SeychellesIsland.class).readAll());

    return result;
  }

  public List<Properties> queryIsland(String q, int pageSize) {
    DaoQuery query = mongoDao.createQuery("SeychellesIsland");
    Properties fields = new Properties();
    fields.put("zhName", Boolean.TRUE);
    String pattern = ".*" + q + ".*";
    query.or(Query.start().regex("zhName", pattern), Query.start().regex("enName", pattern), Query
        .start().regex("byName", pattern));
    query.setMaxDocuments(pageSize);
    return mongoDao.map(query.findObjects(mongoDao.unmap(fields)), Properties.class);
  }

  public SeychellesIsland queryIsland(List<DocumentCriterion> conditions) {
    DaoQuery query = mongoDao.createQuery("SeychellesIsland");
    for (DocumentCriterion cnd : conditions) {
      query.add(cnd);
    }
    return query.findObject(SeychellesIsland.class);
  }

  public QueryResult<SeychellesIsland> queryIsland(List<DocumentCriterion> conditions, int page,
      int pageSize) {
    QueryResult<SeychellesIsland> result = new QueryResult<>(page, pageSize);

    DaoQuery query = mongoDao.createQuery("SeychellesIsland");
    for (DocumentCriterion cnd : conditions) {
      query.add(cnd);
    }
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(SeychellesIsland.class).readAll());

    return result;
  }

  public SeychellesIsland getIsland(String id) {
    return mongoDao.findById("SeychellesIsland", SeychellesIsland.class, id);
  }

  public void addRoom(String islandId, String zhName, String enName) {
    SeychellesRoom room = new SeychellesRoom();
    room.setId(UUID.randomUUID().toString());
    room.setZhName(zhName);
    room.setEnName(enName);
    mongoDao.createQuery("SeychellesIsland").eq("_id", islandId).modify().push("rooms",
        mongoDao.unmap(room)).update();
  }

  public void updateRoom(String roomId, String property, Object value) {
    mongoDao.createQuery("SeychellesIsland").eq("rooms.id", roomId).modify().set(
        "rooms.$." + property, value).update();
  }

  public List<Properties> queryRoom(String islandId, String q, int pageSize) {
    DaoQuery query = mongoDao.createQuery("SeychellesIsland");
    Properties fields = new Properties();
    fields.put("_id", Boolean.FALSE);
    fields.put("rooms.id", Boolean.TRUE);
    fields.put("rooms.zhName", Boolean.TRUE);
    if (StringUtils.isNotBlank(islandId)) {
      query.eq("_id", islandId);
    }
    // String pattern = ".*" + q + ".*";
    // query.or(Query.start().regex("rooms.zhName", pattern), Query.start().regex("rooms.enName",
    // pattern));
    query.setMaxDocuments(pageSize);
    return mongoDao.map(query.findObjects(mongoDao.unmap(fields)), Properties.class);
  }

  public void addDining(String islandId, String zhName, String enName) {
    SeychellesDining dining = new SeychellesDining();
    dining.setId(UUID.randomUUID().toString());
    dining.setZhName(zhName);
    dining.setEnName(enName);
    mongoDao.createQuery("SeychellesIsland").eq("_id", islandId).modify().push("dinings",
        mongoDao.unmap(dining)).update();
  }

  public void updateDining(String diningId, String property, Object value) {
    mongoDao.createQuery("SeychellesIsland").eq("dinings.id", diningId).modify().set(
        "dinings.$." + property, value).update();
  }

  public void addPicture(String islandId, List<String> imageIds) {
    // 保存图片
    mongoDao.createQuery("SeychellesIsland").eq("_id", islandId).modify().pushAll("pictures",
        mongoDao.createQuery("Image").in("id", imageIds).findObjects().toArray()).update();
  }

  public void updatePicture(String imageId, String property, String value) {
    mongoDao.createQuery("SeychellesIsland").eq("pictures.id", imageId).modify().set(
        "pictures.$." + property, value).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").eq("id", imageId).modify().set(property, value).update();
  }

  public void deletePicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDao.createQuery("SeychellesIsland").eq("pictures.id", imageId).modify()
        .pull("pictures", image).update();
    mongoDao.createQuery("Image").eq("id", imageId).modify().delete();
  }

  public void addHotelPicture(String islandId, List<String> imageIds) {
    // 保存图片
    mongoDao.createQuery("SeychellesIsland").eq("_id", islandId).modify().pushAll("hotelPictures",
        mongoDao.createQuery("Image").in("id", imageIds).findObjects().toArray()).update();
  }

  public void updateHotelPicture(String imageId, String property, String value) {
    mongoDao.createQuery("SeychellesIsland").eq("hotelPictures.id", imageId).modify().set(
        "hotelPictures.$." + property, value).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").eq("id", imageId).modify().set(property, value).update();
  }

  public void deleteHotelPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDao.createQuery("SeychellesIsland").eq("hotelPictures.id", imageId).modify()
        .pull("hotelPictures", image).update();
    mongoDao.createQuery("Image").eq("id", imageId).modify().delete();
  }

  public void addRoomPicture(String roomId, List<String> imageIds) {
    // 保存图片
    mongoDao.createQuery("SeychellesIsland").eq("rooms.id", roomId).modify().pushAll(
        "rooms.$.pictures",
        mongoDao.createQuery("Image").in("id", imageIds).findObjects().toArray()).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").in("id", imageIds).modify().set("extra",
        Constant.IMAGE_MALDIVES_ROOM_PICTURE).set("extraKey", roomId).updateMulti();
  }

  public void updateRoomPicture(String index, String imageId, String property, String value) {
    mongoDao.createQuery("SeychellesIsland").eq("rooms.pictures.id", imageId).modify().set(
        "rooms.$.pictures." + index + "." + property, value).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").eq("id", imageId).modify().set(property, value).update();
  }

  public void deleteRoomPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDao.createQuery("SeychellesIsland").eq("rooms.pictures.id", imageId).modify()
        .pull("rooms.$.pictures", image).update();
    mongoDao.createQuery("Image").eq("id", imageId).modify().delete();
  }

  public void addDiningPicture(String diningId, List<String> imageIds) {
    // 保存图片
    mongoDao.createQuery("SeychellesIsland").eq("dinings.id", diningId).modify().pushAll(
        "dinings.$.pictures",
        mongoDao.createQuery("Image").in("id", imageIds).findObjects().toArray()).update();
  }

  public void updateDiningPicture(String index, String imageId, String property, String value) {
    mongoDao.createQuery("SeychellesIsland").eq("dinings.pictures.id", imageId).modify().set(
        "dinings.$.pictures." + index + "." + property, value).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").eq("id", imageId).modify().set(property, value).update();
  }

  public void deleteDiningPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDao.createQuery("SeychellesIsland").eq("dinings.pictures.id", imageId).modify()
        .pull("dinings.$.pictures", image).update();
    mongoDao.createQuery("Image").eq("id", imageId).modify().delete();
  }
}

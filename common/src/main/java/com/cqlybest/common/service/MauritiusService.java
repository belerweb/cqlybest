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

import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.MauritiusDining;
import com.cqlybest.common.bean.MauritiusHotel;
import com.cqlybest.common.bean.MauritiusRoom;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.dao.MongoDb;
import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.query.Query;
import com.googlecode.mjorm.query.criteria.DocumentCriterion;
import com.mongodb.WriteResult;

@Service
public class MauritiusService {

  @Autowired
  private MongoDb mongoDb;

  public MauritiusHotel addHotel(String zhName, String enName) {
    MauritiusHotel hotel = new MauritiusHotel();
    hotel.setId(UUID.randomUUID().toString());
    hotel.setZhName(zhName);
    hotel.setEnName(enName);

    Date now = new Date();
    hotel.setCreatedTime(now);
    hotel.setLastUpdated(now);
    return mongoDb.createObject("MauritiusHotel", hotel);
  }

  public int updateHotel(String hotelId, String property, Object value) {
    WriteResult result =
        mongoDb.createQuery("MauritiusHotel").eq("_id", hotelId).modify().set(property, value).set(
            "lastUpdated", new Date()).update();
    return result.getN();
  }

  public QueryResult<MauritiusHotel> queryHotel(int page, int pageSize) {
    QueryResult<MauritiusHotel> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDb.createQuery("MauritiusHotel");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(MauritiusHotel.class).readAll());

    return result;
  }

  public List<Properties> queryHotel(String q, int pageSize) {
    DaoQuery query = mongoDb.createQuery("MauritiusHotel");
    Properties fields = new Properties();
    fields.put("zhName", Boolean.TRUE);
    String pattern = ".*" + q + ".*";
    query.or(Query.start().regex("zhName", pattern), Query.start().regex("enName", pattern), Query
        .start().regex("byName", pattern));
    query.setMaxDocuments(pageSize);
    return mongoDb.map(query.findObjects(mongoDb.unmap(fields)), Properties.class);
  }

  public MauritiusHotel queryHotel(List<DocumentCriterion> conditions) {
    DaoQuery query = mongoDb.createQuery("MauritiusHotel");
    for (DocumentCriterion cnd : conditions) {
      query.add(cnd);
    }
    return query.findObject(MauritiusHotel.class);
  }

  public QueryResult<MauritiusHotel> queryHotel(List<DocumentCriterion> conditions, int page,
      int pageSize) {
    QueryResult<MauritiusHotel> result = new QueryResult<>(page, pageSize);

    DaoQuery query = mongoDb.createQuery("MauritiusHotel");
    for (DocumentCriterion cnd : conditions) {
      query.add(cnd);
    }
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(MauritiusHotel.class).readAll());

    return result;
  }

  public MauritiusHotel getHotel(String id) {
    return mongoDb.findById("MauritiusHotel", MauritiusHotel.class, id);
  }

  public void addRoom(String hotelId, String zhName, String enName) {
    MauritiusRoom room = new MauritiusRoom();
    room.setId(UUID.randomUUID().toString());
    room.setZhName(zhName);
    room.setEnName(enName);
    mongoDb.createQuery("MauritiusHotel").eq("_id", hotelId).modify().push("rooms",
        mongoDb.unmap(room)).update();
  }

  public void updateRoom(String roomId, String property, Object value) {
    mongoDb.createQuery("MauritiusHotel").eq("rooms.id", roomId).modify().set(
        "rooms.$." + property, value).update();
  }

  public List<Properties> queryRoom(String hotelId, String q, int pageSize) {
    DaoQuery query = mongoDb.createQuery("MauritiusHotel");
    Properties fields = new Properties();
    fields.put("_id", Boolean.FALSE);
    fields.put("rooms.id", Boolean.TRUE);
    fields.put("rooms.zhName", Boolean.TRUE);
    if (StringUtils.isNotBlank(hotelId)) {
      query.eq("_id", hotelId);
    }
    // String pattern = ".*" + q + ".*";
    // query.or(Query.start().regex("rooms.zhName", pattern), Query.start().regex("rooms.enName",
    // pattern));
    query.setMaxDocuments(pageSize);
    return mongoDb.map(query.findObjects(mongoDb.unmap(fields)), Properties.class);
  }

  public void addDining(String hotelId, String zhName, String enName) {
    MauritiusDining dining = new MauritiusDining();
    dining.setId(UUID.randomUUID().toString());
    dining.setZhName(zhName);
    dining.setEnName(enName);
    mongoDb.createQuery("MauritiusHotel").eq("_id", hotelId).modify().push("dinings",
        mongoDb.unmap(dining)).update();
  }

  public void updateDining(String diningId, String property, Object value) {
    mongoDb.createQuery("MauritiusHotel").eq("dinings.id", diningId).modify().set(
        "dinings.$." + property, value).update();
  }

  public void addPicture(String hotelId, List<String> imageIds) {
    // 保存图片
    mongoDb.createQuery("MauritiusHotel").eq("_id", hotelId).modify().pushAll("pictures",
        mongoDb.createQuery("Image").in("_id", imageIds).findObjects(Image.class).readAll());
  }

  public void updatePicture(String imageId, String property, String value) {
    mongoDb.createQuery("MauritiusHotel").eq("pictures.id", imageId).modify().set(
        "pictures.$." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deletePicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MauritiusHotel").eq("pictures.id", imageId).modify()
        .pull("pictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void addHotelPicture(String hotelId, List<String> imageIds) {
    // 保存图片
    mongoDb.createQuery("MauritiusHotel").eq("_id", hotelId).modify().pushAll("hotelPictures",
        mongoDb.createQuery("Image").in("_id", imageIds).findObjects(Image.class).readAll())
        .update();
  }

  public void updateHotelPicture(String imageId, String property, String value) {
    mongoDb.createQuery("MauritiusHotel").eq("hotelPictures.id", imageId).modify().set(
        "hotelPictures.$." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deleteHotelPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MauritiusHotel").eq("hotelPictures.id", imageId).modify()
        .pull("hotelPictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void addRoomPicture(String roomId, List<String> imageIds) {
    // 保存图片
    mongoDb.createQuery("MauritiusHotel").eq("rooms.id", roomId).modify().pushAll(
        "rooms.$.pictures",
        mongoDb.createQuery("Image").in("_id", imageIds).findObjects(Image.class).readAll())
        .update();
  }

  public void updateRoomPicture(String index, String imageId, String property, String value) {
    mongoDb.createQuery("MauritiusHotel").eq("rooms.pictures.id", imageId).modify().set(
        "rooms.$.pictures." + index + "." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deleteRoomPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MauritiusHotel").eq("rooms.pictures.id", imageId).modify()
        .pull("rooms.$.pictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void addDiningPicture(String diningId, List<String> imageIds) {
    // 保存图片
    mongoDb.createQuery("MauritiusHotel").eq("dinings.id", diningId).modify().pushAll(
        "dinings.$.pictures",
        mongoDb.createQuery("Image").in("_id", imageIds).findObjects(Image.class).readAll())
        .update();
  }

  public void updateDiningPicture(String index, String imageId, String property, String value) {
    mongoDb.createQuery("MauritiusHotel").eq("dinings.pictures.id", imageId).modify().set(
        "dinings.$.pictures." + index + "." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deleteDiningPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MauritiusHotel").eq("dinings.pictures.id", imageId).modify()
        .pull("dinings.$.pictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }
}

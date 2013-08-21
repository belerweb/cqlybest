package com.cqlybest.common.mongo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.Constant;
import com.cqlybest.common.mongo.bean.ImageMeta;
import com.cqlybest.common.mongo.bean.MaldivesDining;
import com.cqlybest.common.mongo.bean.MaldivesFlight;
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
    return mongoDb.createObject("MaldivesIsland", island);
  }

  public int updateIsland(String islandId, String property, Object value) {
    WriteResult result =
        mongoDb.createQuery("MaldivesIsland").eq("_id", islandId).modify().set(property, value)
            .set("lastUpdated", new Date()).update();
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
    return mongoDb.findById("MaldivesIsland", MaldivesIsland.class, id);
  }

  public void addRoom(String islandId, String zhName, String enName) {
    MaldivesRoom room = new MaldivesRoom();
    room.setId(UUID.randomUUID().toString());
    room.setZhName(zhName);
    room.setEnName(enName);
    mongoDb.createQuery("MaldivesIsland").eq("_id", islandId).modify().push("rooms",
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
    mongoDb.createQuery("MaldivesIsland").eq("_id", islandId).modify().push("dinings",
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
    mongoDb.createQuery("MaldivesIsland").eq("_id", islandId).modify().pushAll("pictures", images)
        .update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").in("_id", imageIds).modify()
        .set("extra", Constant.IMAGE_MALDIVES_ISLAND_POSTER).set("extraKey", islandId)
        .updateMulti();
  }

  public void updatePicture(String imageId, String property, String value) {
    mongoDb.createQuery("MaldivesIsland").eq("pictures.id", imageId).modify().set(
        "pictures.$." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deletePicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MaldivesIsland").eq("pictures.id", imageId).modify()
        .pull("pictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void addHotelPicture(String islandId, List<String> filenames) {
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
    mongoDb.createQuery("MaldivesIsland").eq("_id", islandId).modify()
        .pushAll("hotelPictures", images).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").in("_id", imageIds).modify()
        .set("extra", Constant.IMAGE_MALDIVES_HOTEL_PICTURE).set("extraKey", islandId)
        .updateMulti();
  }

  public void updateHotelPicture(String imageId, String property, String value) {
    mongoDb.createQuery("MaldivesIsland").eq("hotelPictures.id", imageId).modify().set(
        "hotelPictures.$." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deleteHotelPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MaldivesIsland").eq("hotelPictures.id", imageId).modify()
        .pull("hotelPictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void addRoomPicture(String roomId, List<String> filenames) {
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
    mongoDb.createQuery("MaldivesIsland").eq("rooms.id", roomId).modify()
        .pushAll("rooms.$.pictures", images).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").in("_id", imageIds).modify()
        .set("extra", Constant.IMAGE_MALDIVES_ROOM_PICTURE).set("extraKey", roomId).updateMulti();
  }

  public void updateRoomPicture(String index, String imageId, String property, String value) {
    mongoDb.createQuery("MaldivesIsland").eq("rooms.pictures.id", imageId).modify().set(
        "rooms.$.pictures." + index + "." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deleteRoomPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MaldivesIsland").eq("rooms.pictures.id", imageId).modify()
        .pull("rooms.$.pictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void addDiningPicture(String diningId, List<String> filenames) {
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
    mongoDb.createQuery("MaldivesIsland").eq("dinings.id", diningId).modify()
        .pushAll("dinings.$.pictures", images).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").in("_id", imageIds).modify()
        .set("extra", Constant.IMAGE_MALDIVES_DINING_PICTURE).set("extraKey", diningId)
        .updateMulti();
  }

  public void updateDiningPicture(String index, String imageId, String property, String value) {
    mongoDb.createQuery("MaldivesIsland").eq("dinings.pictures.id", imageId).modify().set(
        "dinings.$.pictures." + index + "." + property, value).update();
    // 更新原始图片的信息
    mongoDb.createQuery("Image").eq("_id", imageId).modify().set(property, value).update();
  }

  public void deleteDiningPicture(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDb.createQuery("MaldivesIsland").eq("dinings.pictures.id", imageId).modify()
        .pull("dinings.$.pictures", image).update();
    mongoDb.createQuery("Image").eq("_id", imageId).modify().delete();
  }

  public void updateFlight(MaldivesFlight flight) {
    String flightId = flight.getId();
    if (StringUtils.isBlank(flightId)) {
      flight.setId(UUID.randomUUID().toString());
      mongoDb.createObject("MaldivesFlight", flight);
    } else {
      mongoDb.getMongoDao().updateObject("MaldivesFlight", flightId, flight);
    }
  }

  public MaldivesFlight getFlight(String flightId) {
    return mongoDb.findById("MaldivesFlight", MaldivesFlight.class, flightId);
  }

  public QueryResult<MaldivesFlight> queryFlight(int lineType, int page, int pageSize) {
    QueryResult<MaldivesFlight> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDb.createQuery("MaldivesFlight");
    query.eq("lineType", lineType);
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(MaldivesFlight.class).readAll());

    return result;
  }
}

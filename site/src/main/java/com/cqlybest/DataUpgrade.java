package com.cqlybest;

import java.util.UUID;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.cqlybest.common.bean.DataDict;
import com.cqlybest.common.bean.MaldivesIsland;
import com.cqlybest.common.bean.MaldivesRoom;
import com.cqlybest.common.bean.Version;
import com.cqlybest.common.dao.MongoDao;
import com.cqlybest.common.service.DataDictService;

@Component
public class DataUpgrade implements ServletContextAware {

  @Autowired
  private MongoDao mongoDao;
  @Autowired
  private Version version;
  @Autowired
  private DataDictService dataDictService;
  private ServletContext servletContext;

  private void upgrade() throws Exception {
    Version version = mongoDao.createQuery("Version").findObject(Version.class);
    version = v2ToV3(version);
    version = v3ToV4(version);
    mongoDao.createQuery("Version").modify().delete();
    mongoDao.createObject("Version", this.version);
  }

  private Version v2ToV3(Version version) throws Exception {
    if (version.getId() == 2) {
      String jsonString =
          IOUtils.toString(servletContext.getResourceAsStream("/WEB-INF/data/maldives_island.js"));
      JSONObject json = new JSONObject(jsonString);
      JSONArray data = json.getJSONArray("data");
      for (int i = 0; i < data.length(); i++) {
        JSONObject src = data.getJSONObject(i);
        String zhName = src.getString("zhName");
        MaldivesIsland island =
            mongoDao.createQuery("MaldivesIsland").eq("zhName", zhName).findObject(
                MaldivesIsland.class);
        if (island != null) {
          if (StringUtils.isBlank(island.getHotelTel())) {
            island.setHotelTel(src.optString("tel"));
          }
          if (island.getHotelLevel() == null) {
            island.setHotelLevel(src.getInt("hotel_level"));
          }
          if (StringUtils.isBlank(island.getWay())) {
            island.setWay(src.optString("way"));
          }
          if (island.getHotelChinese() == null) {
            String chinese = src.optString("chinese");
            island.setHotelChinese(chinese == null ? null : chinese.equals("有"));
          }
          if (StringUtils.isBlank(island.getArea())) {
            island.setArea(src.optString("island_size"));
          }
          if (StringUtils.isBlank(island.getHotelStart())) {
            island.setHotelStart(src.optString("open"));
          }
          if (StringUtils.isBlank(island.getDescription())) {
            island.setDescription(src.optString("description"));
          }
          if (island.getHotelRoomNum() == null) {
            int rooms = src.optInt("rooms", 0);
            if (rooms > 0) {
              island.setHotelRoomNum(rooms);
            }
          }
          if (StringUtils.isBlank(island.getTags())) {
            String tags = src.optString("tags");
            if (StringUtils.isNotBlank(tags)) {
              for (String tag : tags.split(",")) {
                if (mongoDao.createQuery("DataDict").eq("name", tag).findObject(DataDict.class) == null) {
                  dataDictService.addDict("tag", tag);
                }
              }
              island.setTags(tags);
            }
          }
          if (StringUtils.isBlank(island.getPrice())) {
            island.setPrice(src.optString("m_price"));
          }
          if (StringUtils.isBlank(island.getHotelFax())) {
            island.setHotelFax(src.optString("fax"));
          }
          if (StringUtils.isBlank(island.getLevel())) {
            island.setLevel(src.optString("island_level"));
          }
          if (StringUtils.isBlank(island.getHotelDescription())) {
            island.setHotelDescription(src.getString("hotel_description"));
          }
          if (StringUtils.isBlank(island.getAd())) {
            island.setAd(src.optString("ad"));
          }
          if (StringUtils.isBlank(island.getHotelName())) {
            island.setHotelName(src.optString("hotel"));
          }
          if (StringUtils.isBlank(island.getByName())) {
            island.setByName(src.optString("cname"));
          }

          mongoDao.updateObject("MaldivesIsland", island.getId(), island);
        }
      }
    }
    version.setId(3);
    return version;
  }

  private Version v3ToV4(Version version) throws Exception {
    if (version.getId() == 3) {
      String jsonString =
          IOUtils.toString(servletContext
              .getResourceAsStream("/WEB-INF/data/maldives_island_room.js"));
      JSONObject json = new JSONObject(jsonString);
      JSONArray data = json.getJSONArray("data");
      MaldivesIsland island = null;
      for (int i = 0; i < data.length(); i++) {
        JSONObject src = data.getJSONObject(i);
        String islandName = src.getString("island_name");

        if (island != null && !islandName.equals(island.getZhName())) {
          mongoDao.updateObject("MaldivesIsland", island.getId(), island);
          island = null;
        }

        if (island == null) {
          island =
              mongoDao.createQuery("MaldivesIsland").eq("zhName", islandName).findObject(
                  MaldivesIsland.class);
          if (island == null || !island.getRooms().isEmpty()) {
            island = null;
            continue;
          }
        }

        MaldivesRoom room = new MaldivesRoom();
        room.setId(UUID.randomUUID().toString());
        room.setZhName(src.optString("zhName"));
        room.setEnName(src.optString("enName"));
        room.setTags(src.optString("tags"));
        room.setDescription(src.optString("description"));
        room.setAd(src.optString("ad"));
        room.setRequirements(src.optString("required"));
        room.setRoomFacility("共有\n" + src.optString("public_facility") + "\n\n\n\n私有\n"
            + src.optString("private_facility"));
        room.setRoomSize(src.optString("size"));
        int rooms = src.optInt("rooms");
        room.setNum(rooms == 0 ? null : rooms);
        island.getRooms().add(room);

        if (i == data.length() - 1) {
          mongoDao.updateObject("MaldivesIsland", island.getId(), island);
        }
      }
    }
    version.setId(4);
    return version;
  }

  @Override
  public void setServletContext(ServletContext servletContext) {
    this.servletContext = servletContext;
    try {
      upgrade();
    } catch (Exception e) {
      throw new RuntimeException("Update exception", e);
    }
  }
}

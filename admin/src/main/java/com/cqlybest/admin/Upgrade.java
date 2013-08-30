package com.cqlybest.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.cqlybest.common.Version;
import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.mauritius.MauritiusDining;
import com.cqlybest.common.bean.mauritius.MauritiusHotel;
import com.cqlybest.common.bean.mauritius.MauritiusRoom;
import com.cqlybest.common.mongo.bean.ImageMeta;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.common.service.MauritiusService;

@Component
public class Upgrade implements InitializingBean {

  @Autowired
  Version version;
  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private SettingsService settingsService;
  @Autowired
  private MongoDb mongoDb;
  @Autowired
  private MauritiusService mauritiusService;

  @Override
  public void afterPropertiesSet() throws Exception {
    Session session = sessionFactory.openSession();
    SessionHolder sessionHolder = new SessionHolder(session);
    TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
    List<MauritiusHotel> list = mauritiusService.list(null, null);
    for (MauritiusHotel mauritiusHotel : list) {
      mauritiusHotel = mauritiusService.get(mauritiusHotel.getId());
      com.cqlybest.common.mongo.bean.MauritiusHotel hotel =
          new com.cqlybest.common.mongo.bean.MauritiusHotel();
      hotel.setId(mauritiusHotel.getId());
      hotel.setZhName(mauritiusHotel.getZhName());
      hotel.setEnName(mauritiusHotel.getEnName());
      hotel.setByName(mauritiusHotel.getByName());
      hotel.setPrice(mauritiusHotel.getPrice());
      hotel.setTags(mauritiusHotel.getTags());
      hotel.setAd(mauritiusHotel.getAd());
      hotel.setDescription(mauritiusHotel.getDescription());
      hotel.setHotelName(mauritiusHotel.getHotelName());
      hotel.setHotelLevel(mauritiusHotel.getHotelLevel());
      hotel.setHotelStart(mauritiusHotel.getHotelStart());
      hotel.setHotelRoomNum(mauritiusHotel.getHotelRoomNum());
      hotel.setHotelSite(mauritiusHotel.getHotelSite());
      hotel.setHotelTel(mauritiusHotel.getHotelTel());
      hotel.setHotelFax(mauritiusHotel.getHotelFax());
      hotel.setHotelChinese(mauritiusHotel.getHotelChinese());
      hotel.setHotelAirport(mauritiusHotel.getHotelAirport());
      hotel.setHotelDescription(mauritiusHotel.getHotelDescription());
      hotel.setPlays(mauritiusHotel.getPlays());
      hotel.setCreatedTime(new Date(mauritiusHotel.getCreatedTime().getTime()));
      hotel.setLastUpdated(new Date(mauritiusHotel.getLastUpdated().getTime()));

      List<ImageMeta> images = new ArrayList<>();
      for (Image image : mauritiusHotel.getHotelPictures()) {
        ImageMeta img = new ImageMeta();
        img.setId(image.getId());
        img.setExtension(image.getImageType());
        img.setTitle(image.getTitle());
        img.setDescription(image.getDescription());
        images.add(img);
      }
      hotel.setHotelPictures(images);

      images = new ArrayList<>();
      for (Image image : mauritiusHotel.getPictures()) {
        ImageMeta img = new ImageMeta();
        img.setId(image.getId());
        img.setExtension(image.getImageType());
        img.setTitle(image.getTitle());
        img.setDescription(image.getDescription());
        images.add(img);
      }
      hotel.setPictures(images);

      List<com.cqlybest.common.mongo.bean.MauritiusRoom> rooms = new ArrayList<>();
      for (MauritiusRoom room : mauritiusHotel.getRooms()) {
        com.cqlybest.common.mongo.bean.MauritiusRoom r =
            new com.cqlybest.common.mongo.bean.MauritiusRoom();
        r.setId(room.getId().toString());
        r.setZhName(room.getZhName());
        r.setEnName(room.getEnName());
        r.setDescription(room.getDescription());
        r.setNum(room.getNum());
        r.setRequirements(room.getRequirements());
        r.setRoomSize(room.getRoomSize());
        r.setContainPool(room.getContainPool());
        r.setRoomFacility(room.getRoomFacility());
        r.setDisplayOrder(room.getDisplayOrder());
        images = new ArrayList<>();
        for (Image image : room.getPictures()) {
          ImageMeta img = new ImageMeta();
          img.setId(image.getId());
          img.setExtension(image.getImageType());
          img.setTitle(image.getTitle());
          img.setDescription(image.getDescription());
          images.add(img);
        }
        r.setPictures(images);
        rooms.add(r);
      }
      hotel.setRooms(rooms);

      List<com.cqlybest.common.mongo.bean.MauritiusDining> dinings = new ArrayList<>();
      for (MauritiusDining dining : mauritiusHotel.getDinings()) {
        com.cqlybest.common.mongo.bean.MauritiusDining d =
            new com.cqlybest.common.mongo.bean.MauritiusDining();
        d.setId(dining.getId().toString());
        d.setZhName(dining.getZhName());
        d.setZhName(dining.getZhName());
        d.setDescription(dining.getDescription());
        d.setFood(dining.getFood());
        d.setStyle(dining.getStyle());
        d.setOpenTime(dining.getOpenTime());
        d.setLocation(dining.getLocation());
        d.setReservation(dining.getReservation());
        d.setDisplayOrder(dining.getDisplayOrder());
        images = new ArrayList<>();
        for (Image image : dining.getPictures()) {
          ImageMeta img = new ImageMeta();
          img.setId(image.getId());
          img.setExtension(image.getImageType());
          img.setTitle(image.getTitle());
          img.setDescription(image.getDescription());
          images.add(img);
        }
        d.setPictures(images);
        dinings.add(d);
      }
      hotel.setDinings(dinings);
      mongoDb.createObject("MauritiusHotel", hotel);
    }
    upgradeVersion();
    session.close();
  }

  private void upgradeVersion() {
    settingsService.updateSettings("version.name", version.getName());
    settingsService.updateSettings("version.buildTime", version.getBuildTime());
  }

}

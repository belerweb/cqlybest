package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.mauritius.MauritiusDining;
import com.cqlybest.common.bean.mauritius.MauritiusHotel;
import com.cqlybest.common.bean.mauritius.MauritiusRoom;
import com.cqlybest.common.dao.ImageDao;
import com.cqlybest.common.dao.MauritiusDao;

@Service
public class MauritiusService {

  @Autowired
  private MauritiusDao MauritiusDao;
  @Autowired
  private ImageDao imageDao;

  @Transactional
  public void add(MauritiusHotel hotel) {
    hotel.setId(UUID.randomUUID().toString());
    Date now = new Date();
    hotel.setCreatedTime(now);
    hotel.setLastUpdated(now);
    MauritiusDao.saveOrUpdate(hotel);
  }

  @Transactional
  public void update(String id, String name, Object value) {
    MauritiusDao.update(id, name, value);
  }

  @Transactional
  public void update(String[] ids, String name, Object value) {
    MauritiusDao.update(ids, name, value);
  }

  public MauritiusHotel get(String id) {
    MauritiusHotel hotel = MauritiusDao.findById(id);

    List<MauritiusRoom> rooms = MauritiusDao.getRooms(id);
    for (MauritiusRoom room : rooms) {
      room.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_ROOM_PICTURE, room.getId()
          .toString()));
    }
    hotel.setRooms(rooms);

    List<MauritiusDining> dinings = MauritiusDao.getDinings(id);
    for (MauritiusDining dining : dinings) {
      dining.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_DINING_PICTURE, dining
          .getId().toString()));
    }
    hotel.setDinings(dinings);

    hotel.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_HOTEL_POSTER, id));
    hotel.setHotelPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_HOTEL_PICTURE, id));
    return hotel;
  }

  public MauritiusHotel getHotelWithoutRoom(String id) {
    MauritiusHotel hotel = MauritiusDao.findById(id);
    if (hotel != null) {
      hotel.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_HOTEL_POSTER, id));
    }
    return hotel;
  }

  public Long total() {
    return MauritiusDao.total();
  }

  public List<MauritiusHotel> list(Integer page, Integer pageSize) {
    if (page != null && pageSize != null) {
      return MauritiusDao.find(page, pageSize);
    }
    return MauritiusDao.findAll();
  }

  @Transactional
  public void delete(String[] ids) {
    MauritiusDao.delete(ids);
  }

  @Transactional
  public void add(MauritiusRoom room) {
    MauritiusDao.saveOrUpdate(room);
  }

  @Transactional
  public void updateRoom(Integer id, String name, Object value) {
    MauritiusDao.updateRoom(id, name, value);
  }

  @Transactional
  public void deleteRoom(Integer id) {
    MauritiusDao.deleteRoom(id);
    imageDao.deleteByExtra(Constant.IMAGE_MAURITIUS_ROOM_PICTURE, id.toString());
  }

  public MauritiusRoom getRoom(Integer id) {
    MauritiusRoom room = MauritiusDao.findById(MauritiusRoom.class, id);
    room.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_ROOM_PICTURE, room.getId()
        .toString()));
    return room;
  }

  public List<MauritiusRoom> getRooms(String hotelId) {
    List<MauritiusRoom> rooms = MauritiusDao.getRooms(hotelId);
    for (MauritiusRoom room : rooms) {
      room.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_ROOM_PICTURE, room.getId()
          .toString()));
    }
    return rooms;
  }

  public List<MauritiusRoom> getSimpleRooms(String hotelId) {
    return MauritiusDao.find(MauritiusRoom.class, Restrictions.eq("hotelId", hotelId));
  }

  @Transactional
  public void add(MauritiusDining dining) {
    MauritiusDao.saveOrUpdate(dining);
  }

  @Transactional
  public void updateDining(Integer id, String name, Object value) {
    MauritiusDao.updateDining(id, name, value);
  }

  @Transactional
  public void deleteDining(Integer id) {
    MauritiusDao.deleteDining(id);
    imageDao.deleteByExtra(Constant.IMAGE_MAURITIUS_DINING_PICTURE, id.toString());
  }

  public MauritiusDining getDining(Integer id) {
    MauritiusDining dining = MauritiusDao.findById(MauritiusDining.class, id);
    dining.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_DINING_PICTURE, dining.getId()
        .toString()));
    return dining;
  }

  public List<MauritiusDining> getDinings(String hotelId) {
    List<MauritiusDining> dinings = MauritiusDao.getDinings(hotelId);
    for (MauritiusDining dining : dinings) {
      dining.setPictures(imageDao.queryImages(Constant.IMAGE_MAURITIUS_DINING_PICTURE, dining
          .getId().toString()));
    }
    return dinings;
  }

  public List<MauritiusDining> getSimpleDinings(String hotelId) {
    return MauritiusDao.find(MauritiusDining.class, Restrictions.eq("hotelId", hotelId));
  }

}

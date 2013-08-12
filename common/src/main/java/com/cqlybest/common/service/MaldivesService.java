package com.cqlybest.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.maldives.MaldivesDining;
import com.cqlybest.common.bean.maldives.MaldivesRoom;
import com.cqlybest.common.bean.maldives.MaldivesSeaIsland;
import com.cqlybest.common.dao.ImageDao;
import com.cqlybest.common.dao.MaldivesDao;

@Service
public class MaldivesService {

  @Autowired
  private MaldivesDao maldivesDao;
  @Autowired
  private ImageDao imageDao;

  @Transactional
  public void add(MaldivesSeaIsland island) {
    island.setId(UUID.randomUUID().toString());
    Date now = new Date();
    island.setCreatedTime(now);
    island.setLastUpdated(now);
    maldivesDao.saveOrUpdate(island);
  }

  @Transactional
  public void update(String id, String name, Object value) {
    maldivesDao.update(id, name, value);
  }

  @Transactional
  public void update(String[] ids, String name, Object value) {
    maldivesDao.update(ids, name, value);
  }

  public MaldivesSeaIsland get(String id) {
    MaldivesSeaIsland island = maldivesDao.findById(id);

    List<MaldivesRoom> rooms = maldivesDao.getRooms(id);
    for (MaldivesRoom room : rooms) {
      room.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_ROOM_PICTURE, room.getId()
          .toString()));
    }
    island.setRooms(rooms);

    List<MaldivesDining> dinings = maldivesDao.getDinings(id);
    for (MaldivesDining dining : dinings) {
      dining.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_DINING_PICTURE, dining
          .getId().toString()));
    }
    island.setDinings(dinings);

    island.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_ISLAND_POSTER, id));
    island.setHotelPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_HOTEL_PICTURE, id));
    return island;
  }

  public MaldivesSeaIsland getIslandWithoutRoom(String id) {
    MaldivesSeaIsland island = maldivesDao.findById(id);
    if (island != null) {
      island.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_ISLAND_POSTER, id));
    }
    return island;
  }

  public Long total() {
    return maldivesDao.total();
  }

  public List<MaldivesSeaIsland> list(Integer page, Integer pageSize) {
    if (page != null && pageSize != null) {
      return maldivesDao.find(page, pageSize);
    }
    return maldivesDao.findAll();
  }

  public List<MaldivesSeaIsland> searchIslandByKeyword(String keyword, int page, int pageSize) {
    List<Criterion> propertyConditions = new ArrayList<>();
    propertyConditions.add(Restrictions.disjunction()
        .add(Restrictions.like("zhName", keyword, MatchMode.ANYWHERE))
        .add(Restrictions.like("enName", keyword, MatchMode.ANYWHERE))
        .add(Restrictions.like("byName", keyword, MatchMode.ANYWHERE)));
    List<MaldivesSeaIsland> islands = maldivesDao.findIslands(propertyConditions, page, pageSize);
    return islands;
  }

  @Transactional
  public void delete(String[] ids) {
    maldivesDao.delete(ids);
  }

  @Transactional
  public void add(MaldivesRoom room) {
    maldivesDao.saveOrUpdate(room);
  }

  @Transactional
  public void updateRoom(Integer id, String name, Object value) {
    maldivesDao.updateRoom(id, name, value);
  }

  @Transactional
  public void deleteRoom(Integer id) {
    maldivesDao.deleteRoom(id);
    imageDao.deleteByExtra(Constant.IMAGE_MALDIVES_ROOM_PICTURE, id.toString());
  }

  public MaldivesRoom getRoom(Integer id) {
    MaldivesRoom room = maldivesDao.findById(MaldivesRoom.class, id);
    room.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_ROOM_PICTURE, room.getId()
        .toString()));
    return room;
  }

  public List<MaldivesRoom> getRooms(String islandId) {
    List<MaldivesRoom> rooms = maldivesDao.getRooms(islandId);
    for (MaldivesRoom room : rooms) {
      room.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_ROOM_PICTURE, room.getId()
          .toString()));
    }
    return rooms;
  }

  public List<MaldivesRoom> getSimpleRooms(String islandId) {
    return maldivesDao.find(MaldivesRoom.class, Restrictions.eq("islandId", islandId));
  }

  @Transactional
  public void add(MaldivesDining dining) {
    maldivesDao.saveOrUpdate(dining);
  }

  @Transactional
  public void updateDining(Integer id, String name, Object value) {
    maldivesDao.updateDining(id, name, value);
  }

  @Transactional
  public void deleteDining(Integer id) {
    maldivesDao.deleteDining(id);
    imageDao.deleteByExtra(Constant.IMAGE_MALDIVES_DINING_PICTURE, id.toString());
  }

  public MaldivesDining getDining(Integer id) {
    MaldivesDining dining = maldivesDao.findById(MaldivesDining.class, id);
    dining.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_DINING_PICTURE, dining.getId()
        .toString()));
    return dining;
  }

  public List<MaldivesDining> getDinings(String islandId) {
    List<MaldivesDining> dinings = maldivesDao.getDinings(islandId);
    for (MaldivesDining dining : dinings) {
      dining.setPictures(imageDao.queryImages(Constant.IMAGE_MALDIVES_DINING_PICTURE, dining
          .getId().toString()));
    }
    return dinings;
  }

  public List<MaldivesDining> getSimpleDinings(String islandId) {
    return maldivesDao.find(MaldivesDining.class, Restrictions.eq("islandId", islandId));
  }

}

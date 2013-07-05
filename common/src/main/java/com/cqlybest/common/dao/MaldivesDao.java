package com.cqlybest.common.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.MaldivesRoom;
import com.cqlybest.common.bean.MaldivesSeaIsland;

@Repository
@SuppressWarnings("unchecked")
public class MaldivesDao extends AbstractDao<MaldivesSeaIsland, String> {

  protected MaldivesDao() {
    super(MaldivesSeaIsland.class);
  }

  public int update(String id, String name, Object value) {
    String hql = "UPDATE MaldivesSeaIsland SET " + name + " = ?, lastUpdated = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, new Date());
    query.setParameter(2, id);
    return query.executeUpdate();
  }

  public int update(String[] ids, String name, Object value) {
    String hql =
        "UPDATE MaldivesSeaIsland SET " + name + " = ?, lastUpdated = ? WHERE id IN (:ids)";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, new Date());
    query.setParameterList("ids", ids);
    return query.executeUpdate();
  }

  public int updateRoom(Integer id, String name, Object value) {
    String hql = "UPDATE MaldivesRoom SET " + name + " = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

  public void delete(String[] ids) {
    getCurrentSession().createQuery("DELETE FROM MaldivesSeaIsland WHERE id IN (:ids)")
        .setParameterList("ids", ids).executeUpdate();
  }

  public List<MaldivesRoom> getRooms(String islandId) {
    Criteria criteria = getCurrentSession().createCriteria(MaldivesRoom.class);
    criteria.add(Restrictions.eq("islandId", islandId));
    return criteria.list();
  }
}
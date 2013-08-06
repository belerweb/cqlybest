package com.cqlybest.common.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.mauritius.MauritiusDining;
import com.cqlybest.common.bean.mauritius.MauritiusHotel;
import com.cqlybest.common.bean.mauritius.MauritiusRoom;

@Repository
@SuppressWarnings("unchecked")
public class MauritiusDao extends AbstractDao<MauritiusHotel, String> {

  protected MauritiusDao() {
    super(MauritiusHotel.class);
  }

  public int update(String id, String name, Object value) {
    String hql = "UPDATE MauritiusHotel SET " + name + " = ?, lastUpdated = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, new Date());
    query.setParameter(2, id);
    return query.executeUpdate();
  }

  public int update(String[] ids, String name, Object value) {
    String hql = "UPDATE MauritiusHotel SET " + name + " = ?, lastUpdated = ? WHERE id IN (:ids)";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, new Date());
    query.setParameterList("ids", ids);
    return query.executeUpdate();
  }

  public void delete(String[] ids) {
    getCurrentSession().createQuery("DELETE FROM MauritiusHotel WHERE id IN (:ids)")
        .setParameterList("ids", ids).executeUpdate();
  }

  public int updateRoom(Integer id, String name, Object value) {
    String hql = "UPDATE MauritiusRoom SET " + name + " = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

  public void deleteRoom(Integer id) {
    getCurrentSession().createQuery("DELETE FROM MauritiusRoom WHERE id = ?").setParameter(0, id)
        .executeUpdate();
  }

  public List<MauritiusRoom> getRooms(String hotelId) {
    Criteria criteria = getCurrentSession().createCriteria(MauritiusRoom.class);
    criteria.add(Restrictions.eq("hotelId", hotelId));
    criteria.addOrder(Order.asc("displayOrder"));
    return criteria.list();
  }

  public int updateDining(Integer id, String name, Object value) {
    String hql = "UPDATE MauritiusDining SET " + name + " = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, id);
    return query.executeUpdate();
  }

  public void deleteDining(Integer id) {
    getCurrentSession().createQuery("DELETE FROM MauritiusDining WHERE id = ?").setParameter(0, id)
        .executeUpdate();
  }

  public List<MauritiusDining> getDinings(String hotelId) {
    Criteria criteria = getCurrentSession().createCriteria(MauritiusDining.class);
    criteria.add(Restrictions.eq("hotelId", hotelId));
    criteria.addOrder(Order.asc("displayOrder"));
    return criteria.list();
  }
}

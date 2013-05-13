package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.template1.Template1IndexPoster;
import com.cqlybest.common.dao.Template1Dao;

@Service
public class Template1Service {

  @Autowired
  private Template1Dao template1Dao;

  /**
   * 增加海报
   * 
   * @param poster
   */
  @Transactional
  public void add(Template1IndexPoster poster) {
    template1Dao.saveOrUpdate(poster);
  }

  /**
   * 删除海报
   */
  @Transactional
  public void delete(Integer id) {
    Template1IndexPoster poster = template1Dao.find(Template1IndexPoster.class, id);
    if (poster != null) {
      template1Dao.del(poster);
    }
  }

  /**
   * 获取所有海报
   * 
   * @return
   */
  public List<Template1IndexPoster> getPosters() {
    return template1Dao.findAll(Template1IndexPoster.class);
  }

  /**
   * 获取已发布的海报
   * 
   * @return
   */
  public List<Template1IndexPoster> getPublishedPosters() {
    return template1Dao.find(Template1IndexPoster.class, Restrictions.eq("published", true));
  }

  /**
   * 发布/取消发布海报
   * 
   * @param id
   * @param published
   */
  @Transactional
  public void togglePublished(Integer id, boolean published) {
    Template1IndexPoster poster = template1Dao.find(Template1IndexPoster.class, id);
    poster.setPublished(published);
    template1Dao.saveOrUpdate(poster);
  }


}

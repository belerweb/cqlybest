package com.cqlybest.common.service;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.IndexPoster;
import com.cqlybest.common.dao.IndexPosterDao;

@Service
public class IndexPosterService {

  @Autowired
  private IndexPosterDao indexPosterDao;

  /**
   * 增加海报
   * 
   * @param poster
   */
  @Transactional
  public void add(IndexPoster poster) {
    indexPosterDao.saveOrUpdate(poster);
  }

  /**
   * 删除海报
   */
  @Transactional
  public void delete(Integer id) {
    IndexPoster poster = indexPosterDao.findById(id);
    if (poster != null) {
      indexPosterDao.delete(poster);
    }
  }

  /**
   * 获取所有海报
   * 
   * @return
   */
  public List<IndexPoster> getPosters() {
    return indexPosterDao.findAll();
  }

  /**
   * 获取已发布的海报
   * 
   * @return
   */
  public List<IndexPoster> getPublishedPosters() {
    return indexPosterDao.find(Restrictions.eq("published", true));
  }

  /**
   * 发布/取消发布海报
   * 
   * @param id
   * @param published
   */
  @Transactional
  public void togglePublished(Integer id, boolean published) {
    IndexPoster poster = indexPosterDao.findById(id);
    poster.setPublished(published);
    indexPosterDao.saveOrUpdate(poster);
  }


}

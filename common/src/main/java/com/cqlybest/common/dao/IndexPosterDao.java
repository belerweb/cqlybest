package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.IndexPoster;

@Repository
public class IndexPosterDao extends AbstractDao<IndexPoster, Integer> {

  protected IndexPosterDao() {
    super(IndexPoster.class);
  }

}

package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Destination;

@Repository
public class DestinationDao extends TreeNodeDao<Destination, Integer> {

  protected DestinationDao() {
    super(Destination.class, "DESTINATION");
  }

}

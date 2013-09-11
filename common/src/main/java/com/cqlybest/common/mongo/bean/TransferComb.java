package com.cqlybest.common.mongo.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 交通
 */
public class TransferComb {

  private String id;
  private String type;
  private List<Transportation> transfers = new ArrayList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public List<Transportation> getTransfers() {
    return transfers;
  }

  public void setTransfers(List<Transportation> transfers) {
    this.transfers = transfers;
  }
}

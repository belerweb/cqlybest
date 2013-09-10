package com.cqlybest.common.mongo.bean;

import java.util.List;

/**
 * 交通
 */
public class TransferComb {

  private String id;
  private String type;
  private List<Transfer> transfers;

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

  public List<Transfer> getTransfers() {
    return transfers;
  }

  public void setTransfers(List<Transfer> transfers) {
    this.transfers = transfers;
  }
}

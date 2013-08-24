package com.cqlybest.common.mongo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QQUser implements Serializable {

  private static final long serialVersionUID = -4184061616229208059L;

  private String id;
  private List<QQAccessToken> tokens = new ArrayList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<QQAccessToken> getTokens() {
    return tokens;
  }

  public void setTokens(List<QQAccessToken> tokens) {
    this.tokens = tokens;
  }

}

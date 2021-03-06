package com.cqlybest.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WeiboUser implements Serializable {

  private static final long serialVersionUID = 1067314097347229412L;

  private String id;
  private List<WeiboAccessToken> tokens = new ArrayList<>();

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<WeiboAccessToken> getTokens() {
    return tokens;
  }

  public void setTokens(List<WeiboAccessToken> tokens) {
    this.tokens = tokens;
  }

}

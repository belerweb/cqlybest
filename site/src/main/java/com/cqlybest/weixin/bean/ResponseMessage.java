package com.cqlybest.weixin.bean;



public class ResponseMessage {

  private String msgType;// text/image...

  private String toUserName;// 接收方帐号（收到的OpenID）

  private String fromUserName;// 开发者微信号

  private Long createTime;// 消息创建时间 （整型）

  public String getMsgType() {
    return msgType;
  }

  public void setMsgType(String msgType) {
    this.msgType = msgType;
  }

  public String getToUserName() {
    return toUserName;
  }

  public void setToUserName(String toUserName) {
    this.toUserName = toUserName;
  }

  public String getFromUserName() {
    return fromUserName;
  }

  public void setFromUserName(String fromUserName) {
    this.fromUserName = fromUserName;
  }

  public Long getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Long createTime) {
    this.createTime = createTime;
  }

}

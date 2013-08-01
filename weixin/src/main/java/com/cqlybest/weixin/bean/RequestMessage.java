package com.cqlybest.weixin.bean;

import java.util.Date;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;


/**
 * 请求消息：文本/图片
 */
@JacksonXmlRootElement(localName = "xml")
public class RequestMessage {

  @JacksonXmlProperty(localName = "MsgId")
  private Long msgId;// 消息id，64位整型

  @JacksonXmlProperty(localName = "MsgType")
  private String msgType;// text/image...

  @JacksonXmlProperty(localName = "ToUserName")
  private String toUserName;// 开发者微信号

  @JacksonXmlProperty(localName = "FromUserName")
  private String fromUserName;// 发送方帐号（一个OpenID）

  @JacksonXmlProperty(localName = "Content")
  private String content;// 文本消息内容

  @JacksonXmlProperty(localName = "PicUrl")
  private String picUrl;// 图片链接

  @JacksonXmlProperty(localName = "CreateTime")
  private Date createTime;// 消息创建时间 （整型）

  @JacksonXmlProperty(localName = "Event")
  private String event;// 事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)

  @JacksonXmlProperty(localName = "EventKey")
  private String eventKey;// 事件KEY值，与自定义菜单接口中KEY值对应

  @JacksonXmlProperty(localName = "Location_X")
  private Double lat;// 地理位置纬度

  @JacksonXmlProperty(localName = "Location_Y")
  private Double lon;// 地理位置经度

  @JacksonXmlProperty(localName = "Scale")
  private Integer scale;// 地图缩放大小

  @JacksonXmlProperty(localName = "Label")
  private String label;// 地理位置信息

  @JacksonXmlProperty(localName = "Title")
  private String title;// 消息标题

  @JacksonXmlProperty(localName = "Description")
  private String description;// 消息描述

  @JacksonXmlProperty(localName = "Url")
  private String url;// 消息链接

  public Long getMsgId() {
    return msgId;
  }

  public void setMsgId(Long msgId) {
    this.msgId = msgId;
  }

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

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPicUrl() {
    return picUrl;
  }

  public void setPicUrl(String picUrl) {
    this.picUrl = picUrl;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public String getEvent() {
    return event;
  }

  public void setEvent(String event) {
    this.event = event;
  }

  public String getEventKey() {
    return eventKey;
  }

  public void setEventKey(String eventKey) {
    this.eventKey = eventKey;
  }

  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }

  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}

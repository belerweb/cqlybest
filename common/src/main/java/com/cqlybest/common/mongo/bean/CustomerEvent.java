package com.cqlybest.common.mongo.bean;

import java.util.Date;

/**
 * 客户事件（如生日、结婚纪念日）提醒
 */
public class CustomerEvent {

  private String id;
  private String name;// 事件名称，如生日
  private String description;// 如：9月10日是张三的生日。
  private Date eventDate;// 事件日期
  private Date created;// 此条提醒创建时间
  private Date expire;// 此条提醒过期时间

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEventDate() {
    return eventDate;
  }

  public void setEventDate(Date eventDate) {
    this.eventDate = eventDate;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getExpire() {
    return expire;
  }

  public void setExpire(Date expire) {
    this.expire = expire;
  }

}

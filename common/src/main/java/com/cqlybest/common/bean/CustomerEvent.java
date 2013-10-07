package com.cqlybest.common.bean;


/**
 * 客户事件（如生日、结婚纪念日）提醒
 */
public class CustomerEvent {

  private String id;
  private String customerId;// 客户ID
  private String name;// 事件名称，如生日
  private String description;// 如：9月10日是张三的生日。
  private DateBean eventDate;// 事件日期
  private String created;// 此条提醒创建时间

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

  public DateBean getEventDate() {
    return eventDate;
  }

  public void setEventDate(DateBean eventDate) {
    this.eventDate = eventDate;
  }

  public String getCreated() {
    return created;
  }

  public void setCreated(String created) {
    this.created = created;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

}

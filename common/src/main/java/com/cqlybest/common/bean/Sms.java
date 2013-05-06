package com.cqlybest.common.bean;

import java.util.Date;


/**
 * @author jun
 * 
 *         短信
 */
public class Sms {

  private Integer id;
  private String uid;// 发信人ID
  private String from;// 发信人姓名
  private String phone;// 收信人手机号码
  private String to;// 收信人姓名
  private String content;// 短信内容
  private Date sentDate;// 发送时间
  private Boolean success;// 发送是否成功

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUid() {
    return uid;
  }

  public void setUid(String uid) {
    this.uid = uid;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Date getSentDate() {
    return sentDate;
  }

  public void setSentDate(Date sentDate) {
    this.sentDate = sentDate;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

}

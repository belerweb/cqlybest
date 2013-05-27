package com.cqlybest.common.bean;

import java.util.Date;

/**
 * @author jun
 * 
 */
public class PhoneValidationCode {

  private Integer id;
  private String phone;
  private String code;
  private Date createdTime;

  public PhoneValidationCode() {}

  public PhoneValidationCode(String phone, String code) {
    this.phone = phone;
    this.code = code;
    this.createdTime = new Date();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

}

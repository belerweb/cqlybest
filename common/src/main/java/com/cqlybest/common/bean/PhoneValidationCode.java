package com.cqlybest.common.bean;

import java.util.Date;

/**
 * @author jun
 * 
 */
public class PhoneValidationCode {

  private String phone;
  private String code;
  private Date createdTime;

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

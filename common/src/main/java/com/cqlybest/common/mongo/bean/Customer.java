package com.cqlybest.common.mongo.bean;

import java.util.Date;

/**
 * 客户
 */
public class Customer {

  private String id;
  private String fullname;// 姓名
  private Integer sex;// 性别
  private DateBean birth;// 出生日期
  private Boolean birthLunar;// 阴历生日
  private String idCard;// 身份证号
  private String idCardImg;// 身份证图片
  private String passport;// 护照号码
  private String passportImg;// 护照图片
  private String mobile;// 手机号码
  private String email;// 电子邮件
  private String qq;// QQ号
  private String ancestralLocation;// 祖籍，如北京
  private Date created;// 创建时间
  private Date modified;// 最后更新时间

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }

  public DateBean getBirth() {
    return birth;
  }

  public void setBirth(DateBean birth) {
    this.birth = birth;
  }

  public Boolean getBirthLunar() {
    return birthLunar;
  }

  public void setBirthLunar(Boolean birthLunar) {
    this.birthLunar = birthLunar;
  }

  public String getIdCard() {
    return idCard;
  }

  public void setIdCard(String idCard) {
    this.idCard = idCard;
  }

  public String getIdCardImg() {
    return idCardImg;
  }

  public void setIdCardImg(String idCardImg) {
    this.idCardImg = idCardImg;
  }

  public String getPassport() {
    return passport;
  }

  public void setPassport(String passport) {
    this.passport = passport;
  }

  public String getPassportImg() {
    return passportImg;
  }

  public void setPassportImg(String passportImg) {
    this.passportImg = passportImg;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getAncestralLocation() {
    return ancestralLocation;
  }

  public void setAncestralLocation(String ancestralLocation) {
    this.ancestralLocation = ancestralLocation;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getQq() {
    return qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

}

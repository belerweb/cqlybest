package com.cqlybest.common.bean;

import java.util.Date;


public class Image {

  private String _id;
  private String id;
  private String extension;
  private String contentType;
  private String title;
  private String description;
  private byte[] data;
  private String extra;
  private String extraKey;
  private Date createdTime;
  private Date lastUpdated;

  private String name;// 原始文件名
  private String token;// 上传校验码
  private String qiniuKey;// 七牛文件Key
  private String userId;// 上传者ID
  private Long size;
  private Integer width;
  private Integer height;
  private Boolean uploaded = Boolean.FALSE;

  public String get_id() {
    return _id;
  }

  public void set_id(String _id) {
    this._id = _id;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

  public Boolean getUploaded() {
    return uploaded;
  }

  public void setUploaded(Boolean uploaded) {
    this.uploaded = uploaded;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getQiniuKey() {
    return qiniuKey;
  }

  public void setQiniuKey(String qiniuKey) {
    this.qiniuKey = qiniuKey;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
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

  public byte[] getData() {
    return data;
  }

  public void setData(byte[] data) {
    this.data = data;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public String getExtraKey() {
    return extraKey;
  }

  public void setExtraKey(String extraKey) {
    this.extraKey = extraKey;
  }

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(Date lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

}

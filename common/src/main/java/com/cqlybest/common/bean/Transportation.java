package com.cqlybest.common.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transportation {

  public static final String TYPE_FLIGHT = "flight";// 航班
  public static final String TYPE_TRAIN = "train";// 火车

  public static final String LINE_TYPE_MALDIVES = "maldives";// 马代线路
  public static final String LINE_TYPE_MAURITIUS = "mauritius";// 毛求线路

  private String id;
  private String type;// 类型：航班，火车
  private String number;// 航班号
  private String airline;// 航空公司
  private String airlineCode;// 航空公司代码
  private String from;// 起点城市
  private String to;// 前往城市
  private String departuresAirportCode;// 出发机场三字码
  private String arrivalsAirportCode;// 到达机场三字码
  private Date departuresTime;// 起飞时间（当地）
  private Date arrivalsTime;// 到达时间（当地）
  private String lineType; // 线路类型，如马代
  private String extra;// 补充信息
  private Boolean nonStop = false;// 是否直飞
  private Integer day;// 具体出发班期
  private List<Integer> schedule = new ArrayList<>();// 航班班期

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public String getAirline() {
    return airline;
  }

  public void setAirline(String airline) {
    this.airline = airline;
  }

  public String getAirlineCode() {
    return airlineCode;
  }

  public void setAirlineCode(String airlineCode) {
    this.airlineCode = airlineCode;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getDeparturesAirportCode() {
    return departuresAirportCode;
  }

  public void setDeparturesAirportCode(String departuresAirportCode) {
    this.departuresAirportCode = departuresAirportCode;
  }

  public String getArrivalsAirportCode() {
    return arrivalsAirportCode;
  }

  public void setArrivalsAirportCode(String arrivalsAirportCode) {
    this.arrivalsAirportCode = arrivalsAirportCode;
  }

  public Date getDeparturesTime() {
    return departuresTime;
  }

  public void setDeparturesTime(Date departuresTime) {
    this.departuresTime = departuresTime;
  }

  public Date getArrivalsTime() {
    return arrivalsTime;
  }

  public void setArrivalsTime(Date arrivalsTime) {
    this.arrivalsTime = arrivalsTime;
  }

  public String getLineType() {
    return lineType;
  }

  public void setLineType(String lineType) {
    this.lineType = lineType;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public Boolean getNonStop() {
    return nonStop;
  }

  public void setNonStop(Boolean nonStop) {
    this.nonStop = nonStop;
  }

  public Integer getDay() {
    return day;
  }

  public void setDay(Integer day) {
    this.day = day;
  }

  public List<Integer> getSchedule() {
    return schedule;
  }

  public void setSchedule(List<Integer> schedule) {
    this.schedule = schedule;
  }

}

package com.cqlybest.common.bean;

import java.util.Date;

import org.joda.time.DateTime;

public class DateBean {

  private Integer dayOfYear;// a date to a number between 1 and 366.
  private Integer dayOfMonth;// a date to a number between 1 and 31.
  private Integer dayOfWeek;// a date to a number between 1 and 7.
  private Integer year;// a date to the full year.
  private Integer month;// a date into a number between 1 and 12.
  private Integer week;// a date into a number between 0 and 53
  private Integer hour;// a date into a number between 0 and 23.
  private Integer minute;// a date into a number between 0 and 59.
  private Integer second;// a date into a number between 0 and 59. May be 60 to account for leap
  // seconds.
  private Long millisecond;

  public DateBean() {}

  public DateBean(Date date) {
    DateTime dateTime = new DateTime(date);
    dayOfYear = dateTime.getDayOfYear();
    dayOfMonth = dateTime.getDayOfMonth();
    dayOfWeek = dateTime.getDayOfWeek();
    year = dateTime.getYear();
    month = dateTime.getMonthOfYear();
    week = dateTime.getWeekOfWeekyear();
    hour = dateTime.getHourOfDay();
    minute = dateTime.getMinuteOfHour();
    second = dateTime.getSecondOfMinute();
    millisecond = dateTime.getMillis();
  }

  public Integer getDayOfYear() {
    return dayOfYear;
  }

  public void setDayOfYear(Integer dayOfYear) {
    this.dayOfYear = dayOfYear;
  }

  public Integer getDayOfMonth() {
    return dayOfMonth;
  }

  public void setDayOfMonth(Integer dayOfMonth) {
    this.dayOfMonth = dayOfMonth;
  }

  public Integer getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(Integer dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public Integer getYear() {
    return year;
  }

  public void setYear(Integer year) {
    this.year = year;
  }

  public Integer getMonth() {
    return month;
  }

  public void setMonth(Integer month) {
    this.month = month;
  }

  public Integer getWeek() {
    return week;
  }

  public void setWeek(Integer week) {
    this.week = week;
  }

  public Integer getHour() {
    return hour;
  }

  public void setHour(Integer hour) {
    this.hour = hour;
  }

  public Integer getMinute() {
    return minute;
  }

  public void setMinute(Integer minute) {
    this.minute = minute;
  }

  public Integer getSecond() {
    return second;
  }

  public void setSecond(Integer second) {
    this.second = second;
  }

  public Long getMillisecond() {
    return millisecond;
  }

  public void setMillisecond(Long millisecond) {
    this.millisecond = millisecond;
  }

}

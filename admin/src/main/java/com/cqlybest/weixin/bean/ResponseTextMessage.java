package com.cqlybest.weixin.bean;



/**
 * 回复文本消息
 */
public class ResponseTextMessage extends ResponseMessage {

  private String content;// 回复的消息内容,长度不超过2048字节

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public ResponseTextMessage() {
    super();
    setMsgType("text");
  }


}

package com.cqlybest.weixin.smart;

import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;

/**
 * 智能应该处理器
 */
public interface Handler {

  /**
   * 判断处理器是否能处理消息
   * 
   * @param request 请求消息
   * @return 是否处理此请求消息
   */
  boolean support(RequestMessage request);

  /**
   * 根据请求响应智能应答
   * 
   * @param request 请求消息
   * @return 智能应答响应结果
   */
  ResponseMessage handle(RequestMessage request);

  /**
   * 优先级
   */
  int priority();

}

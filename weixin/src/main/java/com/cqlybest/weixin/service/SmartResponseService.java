package com.cqlybest.weixin.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.cqlybest.weixin.bean.RequestMessage;
import com.cqlybest.weixin.bean.ResponseMessage;
import com.cqlybest.weixin.smart.Handler;

@Service
public class SmartResponseService implements ApplicationContextAware {

  private List<Handler> handlers = new ArrayList<Handler>();

  public ResponseMessage smartResponse(RequestMessage request) {
    for (Handler handler : handlers) {
      if (handler.support(request)) {
        return handler.handle(request);
      }
    }

    return null;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    Map<String, Handler> beans = applicationContext.getBeansOfType(Handler.class);
    for (String key : beans.keySet()) {
      handlers.add(beans.get(key));
    }
    Collections.sort(handlers, new Comparator<Handler>() {
      @Override
      public int compare(Handler o1, Handler o2) {
        return o1.priority() - o2.priority();
      }
    });
  }

}

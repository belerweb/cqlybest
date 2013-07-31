package com.cqlybest.weibo.controller;

import java.util.Arrays;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController extends ControllerHelper {

  private static final String TOKEN_CONFIG = "weixin.token";

  @RequestMapping("/")
  public Object root(@RequestParam String signature, @RequestParam String timestamp,
      @RequestParam String nonce, @RequestParam String echostr) {
    try {
      String token = System.getProperty(TOKEN_CONFIG, System.getenv(TOKEN_CONFIG));
      String[] chars = new String[] {token, timestamp, nonce,};
      Arrays.sort(chars);
      String sha1 = DigestUtils.shaHex(StringUtils.join(chars));
      if (sha1.equals(signature)) {
        return new ResponseEntity<byte[]>(echostr.getBytes(), HttpStatus.OK);
      }

      return ok();
    } catch (Exception e) {
      e.printStackTrace();
      return ok();
    }
  }

}

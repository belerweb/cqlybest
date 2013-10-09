package com.cqlybest.common.service;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;

public class CentralConfig implements InitializingBean {

  public static final String DB_HOST = "db.host";
  public static final String DB_NAME = "db.name";
  public static final String DB_USERNAME = "db.username";
  public static final String DB_PASSWORD = "db.password";
  public static final String NNUO_USERNAME = "9nuo.username";
  public static final String QQ_APP_ID = "qq.app_id";
  public static final String QQ_APP_KEY = "qq.app_key";
  public static final String WEIBO_APP_KEY = "weibo.app_key";
  public static final String WEIBO_APP_SECRET = "weibo.app_secret";
  public static final String WEIXIN_TOKEN = "weixin.token";
  public static final String WEIXIN_MP_USERNAME = "weixin.mp.username";
  public static final String WEIXIN_MP_PASSWORD = "weixin.mp.password";
  public static final String QINIU_BK = "qiniu.bk";
  public static final String QINIU_AK = "qiniu.ak";
  public static final String QINIU_SK = "qiniu.sk";
  public static final String QINIU_CALLBACK = "qiniu.callback";
  public static final String IMAGE_SERVER = "image.server";
  public static final String CACHE_DIR = "cache.dir";

  private Map<String, String> properties;

  private String app;
  private String key;
  private String secret;
  private String profile;

  public String getApp() {
    return app;
  }

  public void setApp(String app) {
    this.app = app;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public String get(String config) {
    return properties.get(config);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void afterPropertiesSet() throws Exception {
    SSLSocketFactory sf = new SSLSocketFactory(new TrustStrategy() {
      @Override
      public boolean isTrusted(X509Certificate[] chain, String authType)
          throws CertificateException {
        return true;
      }
    }, new AllowAllHostnameVerifier());
    SchemeRegistry registry = new SchemeRegistry();
    registry.register(new Scheme("https", 443, sf));
    HttpClient client = new DefaultHttpClient(new PoolingClientConnectionManager(registry));
    HttpPost post = new HttpPost("https://central.net.in");
    post.setHeader("App", app);
    post.setHeader("App-Key", key);
    post.setHeader("App-Secret", secret);
    post.setHeader("App-Profile", profile);
    HttpResponse response = client.execute(post);
    if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
      throw new RuntimeException(IOUtils.toString(response.getEntity().getContent()));
    }

    properties = new ObjectMapper().readValue(response.getEntity().getContent(), Map.class);
  }
}

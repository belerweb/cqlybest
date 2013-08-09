package com.cqlybest.common;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.util.WeiboConfig;

import com.cqlybest.common.controller.WeiboLoginController;

public class Constant {

  private static final Logger LOGGER = LoggerFactory.getLogger(WeiboLoginController.class);

  public static final String OPTION_SITE_NAME = "site_name";// 网站名称
  public static final String OPTION_SITE_META_KEYWORD = "site_meta_keyword";// 网页 meta keyword
  public static final String OPTION_SITE_META_DESC = "site_meta_description";// meta description
  public static final String OPTION_SITE_COPYRIGHT = "site_copyright";// 网页版权说明文字
  public static final String OPTION_SITE_STATISTICAL_CODE = "site_statistical_code";// 统计代码
  public static final String OPTION_SITE_ICP = "site_icp";// 备案号
  public static final String OPTION_WATERMARK_POSITION = "watermark_position";// 水印位置
  public static final String OPTION_WEIBO_NICKNAME = "weibo_nickname";// 官方微博昵称
  public static final String OPTION_WEIXIN_WELCOME_MESSAGE = "weixin_welcome_message";// 微信关注欢迎消息
  public static final String OPTION_WEIXIN_DO_NOT_UNDERSTAND = "weixin_do_not_understand";// 微信不可理解的消息
  public static final String OPTION_WWW_URL = "site_url";// www网站地址
  public static final String OPTION_MOBILE_URL = "site_mobile_url";// mobile网站地址

  public static final String IMAGE_MALDIVES_ISLAND_POSTER = "maldives-island-poster";// 马尔代夫海岛海报
  public static final String IMAGE_MALDIVES_ROOM_PICTURE = "maldives-room-picture";// 马尔代夫房间图片
  public static final String IMAGE_MALDIVES_HOTEL_PICTURE = "maldives-hotel-picture";// 马尔代夫酒店图片
  public static final String IMAGE_MALDIVES_DINING_PICTURE = "maldives-dining-picture";// 马尔代夫房间设施

  public static final String IMAGE_MAURITIUS_HOTEL_POSTER = "mauritius-hotel-poster";// 毛里求斯酒店海报
  public static final String IMAGE_MAURITIUS_ROOM_PICTURE = "mauritius-room-picture";// 毛里求斯房间图片
  public static final String IMAGE_MAURITIUS_HOTEL_PICTURE = "mauritius-hotel-picture";// 毛里求斯酒店图片
  public static final String IMAGE_MAURITIUS_DINING_PICTURE = "mauritius-dining-picture";// 毛里求斯房间设施

  public static final String IMAGE_PRODUCT_POSTER = "product-poster";// 产品海报
  public static final String IMAGE_PRODUCT_PHOTO = "product-photo";// 产品图片

  public static final String IMAGE_WATERMARK_IMAGE_ID = "watermark-image-id";// 水印图片ID

  public static final String DICT_DEPARTURE_CITY = "departure-city";// 出发城市
  public static final String DICT_DESTINATION = "destination";// 目的地
  public static final String DICT_PRODUCT_TYPE = "product-type";// 产品类型
  public static final String DICT_PRODUCT_GRADE = "product-grade";// 产品等级
  public static final String DICT_TRAFFIC = "traffic";// 交通方式
  public static final String DICT_TAG = "tag";// 标签

  public static final String WEIBO_APP_KEY = "weibo.app_key";
  public static final String WEIBO_APP_SECRET = "weibo.app_secret";

  public static final String WEIBO_PRO_APP_KEY = "weibo.pro.app_key";
  public static final String WEIBO_PRO_APP_SECRET = "weibo.pro.app_secret";

  public static final String CLIENT_ID = "client_ID";
  public static final String CLIENT_SECRET = "client_SERCRET";
  public static final String REDIRECT_URI = "redirect_URI";
  public static final String SCOPE = "scope";
  public static final String SCOPE_ALL = "all";
  public static final String SCOPE_EMAIL = "email";// 用户的联系邮箱
  public static final String SCOPE_DIRECT_MESSAGES_WRITE = "direct_messages_write";// 私信发送接口
  public static final String SCOPE_DIRECT_MESSAGES_READ = "direct_messages_read";// 私信读取接口
  public static final String SCOPE_FRIENDSHIPS_GROUPS_READ = "friendships_groups_read";// 好友分组读取接口组
  public static final String SCOPE_FRIENDSHIPS_GROUPS_WRITE = "friendships_groups_write";// 好友分组写入接口组
  public static final String SCOPE_STATUSES_TO_ME_READ = "statuses_to_me_read";// 定向微博读取接口组
  public static final String SCOPE_FOLLOW_APP_OFFICIAL_MICROBLOG = "follow_app_official_microblog";// 关注应用官方微博

  public static final String RESPONSE_TYPE_CODE = "code";


  public static void checkWeiboConfig(String appKeyConfig, String appSecretConfig) {
    String appKey = System.getProperty(appKeyConfig, System.getenv(appKeyConfig));
    String appSecret = System.getProperty(appSecretConfig, System.getenv(appSecretConfig));
    if (StringUtils.isEmpty(appKey) || StringUtils.isEmpty(appSecret)) {
      LOGGER.warn("Weibo app key & secret not exixt, weibo login will be disabled.");
      return;
    }
    WeiboConfig.updateProperties(Constant.CLIENT_ID, appKey);
    WeiboConfig.updateProperties(Constant.CLIENT_SECRET, appSecret);
  }

}

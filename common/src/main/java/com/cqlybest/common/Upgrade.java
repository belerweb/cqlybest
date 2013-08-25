package com.cqlybest.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.cqlybest.common.bean.Dict;
import com.cqlybest.common.bean.Image;
import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductCalendar;
import com.cqlybest.common.bean.ProductDetailMaldives;
import com.cqlybest.common.bean.QQAuth;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.bean.WeiboAppAuth;
import com.cqlybest.common.bean.WeiboAuth;
import com.cqlybest.common.bean.maldives.MaldivesSeaIsland;
import com.cqlybest.common.mongo.bean.DataDict;
import com.cqlybest.common.mongo.bean.ImageMeta;
import com.cqlybest.common.mongo.bean.MaldivesDining;
import com.cqlybest.common.mongo.bean.MaldivesIsland;
import com.cqlybest.common.mongo.bean.MaldivesRoom;
import com.cqlybest.common.mongo.bean.ProductBriefTrip;
import com.cqlybest.common.mongo.bean.ProductMaldives;
import com.cqlybest.common.mongo.bean.ProductPriceCalendar;
import com.cqlybest.common.mongo.bean.ProductTransportation;
import com.cqlybest.common.mongo.bean.QQAccessToken;
import com.cqlybest.common.mongo.bean.QQUser;
import com.cqlybest.common.mongo.bean.User;
import com.cqlybest.common.mongo.bean.WeiboAccessToken;
import com.cqlybest.common.mongo.bean.WeiboUser;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.cqlybest.common.mongo.service.SettingsService;
import com.cqlybest.common.service.DictService;
import com.cqlybest.common.service.ImageService;
import com.cqlybest.common.service.MaldivesService;
import com.cqlybest.common.service.OptionService;
import com.cqlybest.common.service.ProductService;
import com.cqlybest.common.service.UserService;
import com.googlecode.mjorm.query.DaoModifier;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

@Component
public class Upgrade implements InitializingBean {

  @Autowired
  private SessionFactory sessionFactory;
  @Autowired
  private SettingsService settingsService;
  @Autowired
  private UserService userService;
  @Autowired
  private DictService dictService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private OptionService optionService;
  @Autowired
  private MaldivesService maldivesService;
  @Autowired
  private ProductService productService;
  @Autowired
  private MongoDb mongoDb;

  @Override
  public void afterPropertiesSet() throws Exception {
    Session session = sessionFactory.openSession();
    SessionHolder sessionHolder = new SessionHolder(session);
    TransactionSynchronizationManager.bindResource(sessionFactory, sessionHolder);
    upgradeUser();
    updateDataDict();
    updateImage();
    updateSettings();
    updateMaldives();
    updateProduct();
    session.close();
  }

  private void updateProduct() {
    List<Product> products = productService.all();
    com.cqlybest.common.mongo.bean.Product[] newProducts =
        new com.cqlybest.common.mongo.bean.Product[products.size()];
    for (int i = 0; i < products.size(); i++) {
      Product product = products.get(i);
      com.cqlybest.common.mongo.bean.Product newProduct =
          new com.cqlybest.common.mongo.bean.Product();
      newProduct.setId(product.getId());
      newProduct.setName(product.getName());
      newProduct.setCode(product.getCode());
      newProduct.setType(product.getProductType() == 1
          ? com.cqlybest.common.mongo.bean.Product.TYPE_MALDIVES
          : null);
      newProduct.setMarketPrice(product.getMarketPrice());
      newProduct.setPrice(product.getPrice());
      newProduct.setPriceDescription(product.getPriceDescription());
      newProduct.setPriceExclusive(product.getPriceExclusive());
      newProduct.setPopular(product.getPopular());
      newProduct.setRecommend(product.getRecommend());
      newProduct.setSpecial(product.getSpecialOffer());
      newProduct.setEffectiveDate(product.getEffectiveDate() == null ? null : new Date(product
          .getEffectiveDate().getTime()));
      newProduct.setExpiryDate(product.getExpiryDate() == null ? null : new Date(product
          .getExpiryDate().getTime()));
      newProduct.setDepartureDate(product.getDepartureDate() == null ? null : new Date(product
          .getDepartureDate().getTime()));
      newProduct.setDescription(product.getDescription());
      newProduct.setTripCharacteristic(product.getTripCharacteristic());
      newProduct.setServiceStandard(product.getServiceStandard());
      newProduct.setFriendlyReminder(product.getFriendlyReminder());
      newProduct.setRecommendedItem(product.getRecommendedItem());
      newProduct.setPublished(product.getPublished());
      newProduct.setCreatedTime(new Date(product.getCreatedTime().getTime()));
      newProduct.setLastUpdated(new Date(product.getLastUpdated().getTime()));

      newProduct.setKeywords(convertString(product.getKeywords()));
      newProduct.setRecommendedMonths(convertString(product.getRecommendedMonths()));
      newProduct.setDepartureCities(convertString(product.getDepartureCities()));
      newProduct.setDestinations(convertString(product.getDestinations()));
      newProduct.setCrowds(convertString(product.getCrowds()));
      newProduct.setTraffics(convertString(product.getTraffics()));
      newProduct.setTypes(convertString(product.getTypes()));
      newProduct.setGrades(convertString(product.getGrades()));

      product = productService.get(product.getId());

      newProduct.setPhotos(convertImages(product.getPhotos()));
      newProduct.setPosters(convertImages(product.getPosters()));

      List<ProductPriceCalendar> priceCalendar = new ArrayList<>();
      List<ProductCalendar> calendarList = productService.getCalendar(product.getId());
      for (ProductCalendar productCalendar : calendarList) {
        ProductPriceCalendar price = new ProductPriceCalendar();
        price.setDate(Constant.YYYYMMDD_FORMAT.format(productCalendar.getDate()));
        price.setPrice(productCalendar.getPrice());
        price.setChildPrice(productCalendar.getChildPrice());
        price.setSpecial(productCalendar.isSpecial());
        priceCalendar.add(price);
      }
      newProduct.setPriceCalendar(priceCalendar);

      List<ProductTransportation> transportations = new ArrayList<>();
      newProduct.setTransportations(transportations);

      if (product.getProductType() == 1) {
        ProductDetailMaldives detail = (ProductDetailMaldives) product.getDetail();
        List<ProductBriefTrip> briefTrip = new ArrayList<>();
        if (detail.getRoom1() != null && detail.getRoom1Unit() != null) {
          ProductBriefTrip b = new ProductBriefTrip();
          b.setNum(detail.getRoom1());
          b.setUnit(detail.getRoom1Unit());
          briefTrip.add(b);
        }
        if (detail.getRoom2() != null && detail.getRoom2Unit() != null) {
          ProductBriefTrip b = new ProductBriefTrip();
          b.setNum(detail.getRoom2());
          b.setUnit(detail.getRoom2Unit());
          briefTrip.add(b);
        }
        if (detail.getRoom3() != null && detail.getRoom3Unit() != null) {
          ProductBriefTrip b = new ProductBriefTrip();
          b.setNum(detail.getRoom3());
          b.setUnit(detail.getRoom3Unit());
          briefTrip.add(b);
        }
        newProduct.setBriefTrip(briefTrip);

        List<ProductMaldives> maldivesDetails = new ArrayList<>();
        List<com.cqlybest.common.bean.ProductMaldives> maldives = product.getMaldives();
        for (com.cqlybest.common.bean.ProductMaldives productMaldives : maldives) {
          ProductMaldives md = new ProductMaldives();
          md.setId(productMaldives.getId().toString());
          md.setName(productMaldives.getName());
          md.setDetail(productMaldives.getExtra());
          md.setMeals(convertString(productMaldives.getMeal()));

          if (productMaldives.getIslandId() != null) {
            Properties fields = new Properties();
            fields.put("rooms", Boolean.FALSE); // 不包含房型
            fields.put("dinings", Boolean.FALSE); // 不包含餐饮设施
            md.setIsland(mongoDb.map(
                mongoDb.findById("MaldivesIsland", productMaldives.getIslandId(), fields),
                MaldivesIsland.class));

            if (productMaldives.getRoomId() != null) {
              fields = new Properties();
              fields.put("rooms.$", Boolean.TRUE); // 只查询房型
              DBObject result =
                  mongoDb.createQuery("MaldivesIsland")
                      .eq("rooms.id", productMaldives.getRoomId().toString())
                      .findObject(mongoDb.unmap(fields));
              Object room = ((BasicDBList) result.get("rooms")).get(0);
              md.setRoom(mongoDb.map((DBObject) room, MaldivesRoom.class));
            }
          }

          maldivesDetails.add(md);
        }
        newProduct.setMaldivesDetails(maldivesDetails);
      }

      newProducts[i] = newProduct;
    }
    mongoDb.getMongoDao().createObjects("Product", newProducts);
  }

  private List<String> convertString(String string) {
    if (StringUtils.isBlank(string)) {
      return Collections.emptyList();
    }

    return Arrays.asList(string.split(","));
  }

  private void updateMaldives() {
    List<MaldivesSeaIsland> islands = maldivesService.list(null, null);
    MaldivesIsland[] newIslands = new MaldivesIsland[islands.size()];
    for (int i = 0; i < islands.size(); i++) {
      MaldivesSeaIsland island = islands.get(i);
      MaldivesIsland newIsland = new MaldivesIsland();
      newIsland.setId(island.getId());
      newIsland.setZhName(island.getZhName());
      newIsland.setEnName(island.getEnName());
      newIsland.setByName(island.getByName());
      newIsland.setLevel(island.getLevel());
      newIsland.setWay(island.getWay());
      newIsland.setArea(island.getArea());
      newIsland.setSnorkeling(island.getSnorkeling());
      newIsland.setPrice(island.getPrice());
      newIsland.setTags(island.getTags());
      newIsland.setAd(island.getAd());
      newIsland.setDescription(island.getDescription());
      newIsland.setHotelName(island.getHotelName());
      newIsland.setHotelLevel(island.getHotelLevel());
      newIsland.setHotelStart(island.getHotelStart());
      newIsland.setHotelRoomNum(island.getHotelRoomNum());
      newIsland.setHotelSite(island.getHotelSite());
      newIsland.setHotelTel(island.getHotelTel());
      newIsland.setHotelFax(island.getHotelFax());
      newIsland.setHotelChinese(island.getHotelChinese());
      newIsland.setHotelAirport(island.getHotelAirport());
      newIsland.setHotelDescription(island.getHotelDescription());
      newIsland.setPlays(island.getPlays());
      newIsland.setCreatedTime(new Date(island.getCreatedTime().getTime()));
      newIsland.setLastUpdated(new Date(island.getLastUpdated().getTime()));

      island = maldivesService.get(island.getId());
      newIsland.setHotelPictures(convertImages(island.getHotelPictures()));
      newIsland.setPictures(convertImages(island.getPictures()));

      List<MaldivesRoom> rooms = new ArrayList<>();
      for (com.cqlybest.common.bean.maldives.MaldivesRoom room : island.getRooms()) {
        MaldivesRoom newRoom = new MaldivesRoom();
        newRoom.setId(room.getId().toString());
        newRoom.setZhName(room.getZhName());
        newRoom.setEnName(room.getEnName());
        newRoom.setDescription(room.getDescription());
        newRoom.setNum(room.getNum());
        newRoom.setRequirements(room.getRequirements());
        newRoom.setRoomSize(room.getRoomSize());
        newRoom.setContainPool(room.getContainPool());
        newRoom.setRoomFacility(room.getRoomFacility());
        newRoom.setDisplayOrder(room.getDisplayOrder());
        newRoom.setPictures(convertImages(room.getPictures()));
        rooms.add(newRoom);
      }
      newIsland.setRooms(rooms);

      List<MaldivesDining> dinings = new ArrayList<>();
      for (com.cqlybest.common.bean.maldives.MaldivesDining dining : island.getDinings()) {
        MaldivesDining newDining = new MaldivesDining();
        newDining.setId(dining.getId().toString());
        newDining.setZhName(dining.getZhName());
        newDining.setEnName(dining.getEnName());
        newDining.setDescription(dining.getDescription());
        newDining.setStyle(dining.getStyle());
        newDining.setFood(dining.getFood());
        newDining.setLocation(dining.getLocation());
        newDining.setReservation(dining.getReservation());
        newDining.setOpenTime(dining.getOpenTime());
        newDining.setDisplayOrder(newDining.getDisplayOrder());
        newDining.setPictures(convertImages(dining.getPictures()));
        dinings.add(newDining);
      }
      newIsland.setDinings(dinings);

      newIslands[i] = newIsland;
    }
    mongoDb.getMongoDao().createObjects("MaldivesIsland", newIslands);
  }

  private List<ImageMeta> convertImages(List<Image> images) {
    List<ImageMeta> result = new ArrayList<>();
    for (Image image : images) {
      ImageMeta meta = new ImageMeta();
      meta.setId(image.getId());
      meta.setExtension(image.getImageType());
      meta.setTitle(image.getTitle());
      meta.setDescription(image.getDescription());
      result.add(meta);
    }
    return result;
  }

  private void updateSettings() {
    Map<String, String> options = optionService.getOptions();
    mongoDb.createObject("Settings", new HashMap<>());
    DaoModifier modifier = mongoDb.createQuery("Settings").modify();
    modifier.set("basic.siteName", options.get("site_name"));
    modifier.set("basic.siteUrl", options.get("site_url"));
    modifier.set("basic.logo", options.get("template1.logo"));
    modifier.set("basic.mobileSiteUrl", options.get("site_mobile_url"));
    modifier.set("basic.adminSiteUrl", options.get("site_admin_url"));
    modifier.set("basic.hotline", options.get("site_400"));
    modifier.set("basic.meta", options.get("site_meta"));
    modifier.set("basic.keywords", options.get("site_meta_keyword").split(","));
    modifier.set("basic.description", options.get("site_meta_description"));
    modifier.set("basic.icp", options.get("site_icp"));
    modifier.set("basic.icpLicense", options.get("site_icp_license"));
    modifier.set("basic.copyright", options.get("site_copyright"));
    modifier.set("basic.weibo.id", options.get("weibo_url"));
    modifier.set("basic.weibo.nickname", options.get("weibo_nickname"));
    modifier.set("basic.statistical", options.get("site_statistical_code"));
    modifier.set("watermark.img.id", options.get("watermark-image-id"));
    modifier.set("watermark.img.extension", "png");
    modifier.set("watermark.position", options.get("watermark_position"));
    modifier.set("mp.message.welcome", options.get("weixin_welcome_message"));
    modifier.set("mp.message.help", options.get("weixin_help"));
    modifier.set("mp.message.unknown", options.get("weixin_do_not_understand"));
    modifier.update();
  }

  private void updateImage() {
    List<Image> images = imageService.all();
    com.cqlybest.common.mongo.bean.Image[] mongoImages =
        new com.cqlybest.common.mongo.bean.Image[images.size()];
    for (int i = 0; i < images.size(); i++) {
      Image image = images.get(i);
      com.cqlybest.common.mongo.bean.Image newImage = new com.cqlybest.common.mongo.bean.Image();
      newImage.setId(image.getId());
      String type = image.getImageType();
      newImage.setExtension(type);
      newImage.setContentType(type.equals("jpg") ? "image/jpeg" : "image/" + type);
      newImage.setTitle(image.getTitle());
      newImage.setDescription(image.getDescription());
      newImage.setData(image.getImageData());
      newImage.setExtra(image.getExtra());
      newImage.setExtraKey(image.getExtraKey());
      newImage.setCreatedTime(new Date(image.getCreatedTime().getTime()));
      newImage.setLastUpdated(new Date(image.getLastUpdated().getTime()));
      mongoImages[i] = newImage;
    }
    mongoDb.getMongoDao().createObjects("Image", mongoImages);
  }

  private void updateDataDict() {
    List<Dict> dicts = dictService.getDicts();
    DataDict[] dataDicts = new DataDict[dicts.size()];
    for (int i = 0; i < dicts.size(); i++) {
      Dict dict = dicts.get(i);
      DataDict dataDict = new DataDict();
      dataDict.setId(dict.getId().toString());
      dataDict.setName(dict.getName());
      dataDict.setPinyin(dict.getPinyin());
      dataDict.setPy(dict.getPy());
      dataDict.setType(dict.getType());
      dataDicts[i] = dataDict;
    }
    mongoDb.getMongoDao().createObjects("DataDict", dataDicts);
  }

  private void upgradeUser() {
    List<LoginUser> loginUsers = userService.getUserList(1, Integer.MAX_VALUE);
    for (LoginUser loginUser : loginUsers) {
      User user = new User();
      user.setId(loginUser.getId());
      user.setMobile(loginUser.getCellPhone());
      user.setEmail(loginUser.getEmail());
      user.setUsername(loginUser.getLoginUsername());
      user.setPassword(loginUser.getPassword());
      user.setFullname(loginUser.getFullname());
      user.setNickname(loginUser.getNickname());
      user.setAvatar(loginUser.getAvatar());
      user.setSource(null);
      user.setLastLoginIP(null);
      user.setCreatedTime(new Date());
      user.setLastUpdated(new Date());
      WeiboAuth weiboAuth = loginUser.getWeiboAuth();
      WeiboAppAuth weiboAppAuth = loginUser.getWeiboAppAuth();
      if (weiboAuth != null || weiboAppAuth != null) {
        WeiboUser weibo = new WeiboUser();
        weibo.setId(weiboAuth != null ? weiboAuth.getUid() : weiboAppAuth.getUid());
        if (weiboAuth != null) {
          WeiboAccessToken token = new WeiboAccessToken();
          token.setAppKey(System.getProperty("weibo.app_key", System.getenv("weibo.app_key")));
          token.setToken(weiboAuth.getToken());
          token.setExpireIn(weiboAuth.getExpireIn());
          token.setCreatedTime(new Date(weiboAuth.getCreatedTime().getTime()));
          token.setLastUpdated(new Date(weiboAuth.getLastUpdate().getTime()));
          weibo.getTokens().add(token);
        }
        if (weiboAppAuth != null) {
          WeiboAccessToken token = new WeiboAccessToken();
          token.setAppKey(System.getProperty(Constant.WEIBO_PRO_APP_KEY, System
              .getenv(Constant.WEIBO_PRO_APP_KEY)));
          token.setAppId(weiboAppAuth.getAppId());
          token.setCid(weiboAppAuth.getCid());
          token.setSubAppkey(weiboAppAuth.getSubAppkey());
          token.setToken(weiboAppAuth.getToken());
          token.setExpireIn(weiboAppAuth.getExpireIn());
          token.setCreatedTime(new Date(weiboAppAuth.getCreatedTime().getTime()));
          token.setLastUpdated(new Date(weiboAppAuth.getLastUpdate().getTime()));
          weibo.getTokens().add(token);
        }
        user.setWeibo(weibo);
      }

      QQAuth qqAuth = loginUser.getQqAuth();
      if (qqAuth != null) {
        QQUser qzone = new QQUser();
        qzone.setId(qqAuth.getOpenid());
        QQAccessToken token = new QQAccessToken();
        token.setAppId(System.getProperty("qq.app_id", System.getenv("qq.app_id")));
        token.setToken(qqAuth.getToken());
        token.setExpireIn(qqAuth.getExpireIn());
        token.setCreatedTime(new Date(qqAuth.getCreatedTime().getTime()));
        token.setLastUpdated(new Date(qqAuth.getLastUpdate().getTime()));
        qzone.getTokens().add(token);
        user.setQzone(qzone);
      }

      for (Role role : loginUser.getRoles()) {
        user.getRoles().add(role.getRole());
      }
      mongoDb.createObject("User", user);
    }
  }
}

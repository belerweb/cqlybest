package com.cqlybest.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductBriefTrip;
import com.cqlybest.common.bean.ProductMaldives;
import com.cqlybest.common.bean.ProductPriceCalendar;
import com.cqlybest.common.bean.ProductTransportation;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.bean.page.BooleanCondition;
import com.cqlybest.common.bean.page.Condition;
import com.cqlybest.common.bean.page.ProductCondition;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoModifier;
import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.query.criteria.DocumentCriterion;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

@Service
public class ProductService {

  @Autowired
  private MongoDao mongoDao;

  public Product addProduct(String type, String name) {
    Product product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setType(type);
    product.setName(name);

    Date now = new Date();
    product.setCreatedTime(now);
    product.setLastUpdated(now);
    return mongoDao.createObject("Product", product);
  }

  public int updateProduct(String productId, String property, Object value) {
    Object _value = value;
    if ("briefTrip".equals(property)) {
      if (StringUtils.isNotBlank(value.toString())) {
        List<Object> trips = new ArrayList<>();
        Matcher matcher = Pattern.compile("(\\d+)([^\\d]+)").matcher(value.toString());
        while (matcher.find()) {
          ProductBriefTrip trip = new ProductBriefTrip();
          trip.setNum(Integer.valueOf(matcher.group(1)));
          trip.setUnit(matcher.group(2));
          trips.add(mongoDao.unmap(trip));
        }
        _value = trips;
      }
    }
    DaoModifier modify = mongoDao.createQuery("Product").eq("_id", productId).modify();
    modify.set(property, _value);
    modify.set("lastUpdated", new Date());
    if (!("popular".equals(property) || "recommend".equals(property) || "special".equals(property) || "published"
        .equals(property))) {
      modify.set("published", Boolean.FALSE);
    }
    return modify.update().getN();
  }

  public Product getProduct(String id) {
    return mongoDao.findById("Product", Product.class, id);
  }

  public QueryResult<Product> queryProduct(int page, int pageSize) {
    QueryResult<Product> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("Product");
    result.setTotal(query.countObjects());

    query.addSort("createdTime", Constant.SORT_DESC);
    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Product.class).readAll());

    return result;
  }

  public Product queryProduct(List<DocumentCriterion> conditions) {
    DaoQuery query = mongoDao.createQuery("Product");
    for (DocumentCriterion cnd : conditions) {
      query.add(cnd);
    }
    return query.findObject(Product.class);
  }

  public QueryResult<Product> queryProduct(List<DocumentCriterion> conditions, int page,
      int pageSize) {
    QueryResult<Product> result = new QueryResult<>(page, pageSize);

    DaoQuery query = mongoDao.createQuery("Product");
    for (DocumentCriterion cnd : conditions) {
      query.add(cnd);
    }
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Product.class).readAll());

    return result;
  }

  public void addTransportation(String productId, String name) {
    ProductTransportation transportation = new ProductTransportation();
    transportation.setId(UUID.randomUUID().toString());
    transportation.setName(name);
    mongoDao.createQuery("Product").eq("_id", productId).modify().push("transportations",
        mongoDao.unmap(transportation)).update();
  }

  public void updateTransportation(String id, String property, Object value) {
    Object _value = value;
    if ("detail".equals(property)) {
      _value = mongoDao.findById("Transportation", (String) value);
    }
    mongoDao.createQuery("Product").eq("transportations.id", id).modify().set(
        "transportations.$." + property, _value).update();
  }

  public void deleteTransportation(String id) {
    Map<String, String> transportation = new HashMap<>();
    transportation.put("id", id);
    mongoDao.createQuery("Product").eq("transportations.id", id).modify()
        .pull("transportations", transportation).update();
  }

  public void addMaldivesDetail(String productId, String name) {
    ProductMaldives detail = new ProductMaldives();
    detail.setId(UUID.randomUUID().toString());
    detail.setName(name);
    mongoDao.createQuery("Product").eq("_id", productId).modify().push("maldivesDetails",
        mongoDao.unmap(detail)).update();
  }

  public void updateMaldivesDetail(String id, String property, Object value) {
    Object _value = value;
    if ("island".equals(property)) {
      Properties fields = new Properties();
      fields.put("rooms", Boolean.FALSE); // 不包含房型
      fields.put("dinings", Boolean.FALSE); // 不包含餐饮设施
      _value = mongoDao.findById("MaldivesIsland", (String) value, fields);
      mongoDao.createQuery("Product").eq("maldivesDetails.id", id).modify().set(
          "maldivesDetails.$." + property, _value).set("maldivesDetails.$.room", null).update();
    } else if ("room".equals(property)) {
      Properties fields = new Properties();
      fields.put("rooms.$", Boolean.TRUE); // 只查询房型
      DBObject result =
          mongoDao.createQuery("MaldivesIsland").eq("rooms.id", value).findObject(
              mongoDao.unmap(fields));
      _value = ((BasicDBList) result.get("rooms")).get(0);
      mongoDao.createQuery("Product").eq("maldivesDetails.id", id).modify().set(
          "maldivesDetails.$." + property, _value).update();
    } else {
      mongoDao.createQuery("Product").eq("maldivesDetails.id", id).modify().set(
          "maldivesDetails.$." + property, _value).update();
    }
  }

  public void deleteMaldivesDetail(String id) {
    Map<String, String> detail = new HashMap<>();
    detail.put("id", id);
    mongoDao.createQuery("Product").eq("maldivesDetails.id", id).modify()
        .pull("maldivesDetails", detail).update();
  }

  public void updatePrice(String productId, Date startDate, Date endDate, Integer price,
      Integer childPrice, boolean special, boolean delete) {
    Date date = startDate;
    List<String> toDeleteds = new ArrayList<>();
    List<DBObject> toAdds = new ArrayList<>();
    while (date.getTime() <= endDate.getTime()) {
      String dateString = Constant.YYYYMMDD_FORMAT.format(date);
      Map<String, String> toDeleted = new HashMap<>();
      toDeleted.put("date", dateString);
      toDeleteds.add(dateString);

      if (!delete) {
        ProductPriceCalendar toAdd = new ProductPriceCalendar();
        toAdd.setDate(dateString);
        toAdd.setPrice(price);
        toAdd.setChildPrice(childPrice);
        toAdd.setSpecial(special);
        toAdds.add(mongoDao.unmap(toAdd));
      }

      date = DateUtils.addDays(date, 1);
    }

    Map<String, Object> in = new HashMap<>();
    in.put("$in", toDeleteds);
    Map<String, Object> subQuery = new HashMap<>();
    subQuery.put("date", in);
    mongoDao.createQuery("Product").eq("_id", productId).modify().pull("priceCalendar", subQuery)
        .update();
    if (!delete) {
      mongoDao.createQuery("Product").eq("_id", productId).modify()
          .pushAll("priceCalendar", toAdds).update();
    }
  }

  public void addPoster(String productId, List<String> imageIds) {
    // 保存图片
    mongoDao.createQuery("Product").eq("_id", productId).modify().pushAll("posters",
        mongoDao.createQuery("Image").in("id", imageIds).findObjects().toArray()).update();
    // 更新原始图片的信息
  }

  public void updatePoster(String imageId, String property, String value) {
    mongoDao.createQuery("Product").eq("posters.id", imageId).modify().set("posters.$." + property,
        value).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").eq("id", imageId).modify().set(property, value).update();
  }

  public void deletePoster(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDao.createQuery("Product").eq("posters.id", imageId).modify().pull("posters", image)
        .update();
    mongoDao.createQuery("Image").eq("id", imageId).modify().delete();
  }

  public void addPhoto(String productId, List<String> imageIds) {
    // 保存图片
    mongoDao.createQuery("Product").eq("_id", productId).modify().pushAll("photos",
        mongoDao.createQuery("Image").in("id", imageIds).findObjects().toArray()).update();
  }

  public void updatePhoto(String imageId, String property, String value) {
    mongoDao.createQuery("Product").eq("photos.id", imageId).modify().set("photos.$." + property,
        value).update();
    // 更新原始图片的信息
    mongoDao.createQuery("Image").eq("id", imageId).modify().set(property, value).update();
  }

  public void deletePhoto(String imageId) {
    Map<String, String> image = new HashMap<>();
    image.put("id", imageId);
    mongoDao.createQuery("Product").eq("photos.id", imageId).modify().pull("photos", image)
        .update();
    mongoDao.createQuery("Image").eq("id", imageId).modify().delete();
  }

  public QueryResult<Product> queryProduct(ProductCondition pc, int page, int pageSize) {
    QueryResult<Product> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("Product");
    Condition cnd = pc.getType();
    if (cnd != null) {// 类型
      query.eq("type", cnd.getValue());
    }
    cnd = pc.getName();
    if (cnd != null) {// 名称
      if (cnd.getType() == Condition.CONDITION_TYPE_EQ) {
        query.eq("name", cnd.getValue());
      } else if (cnd.getType() == Condition.CONDITION_TYPE_IN) {
        query.regex("name", ".*" + cnd.getValue() + ".*");
      }
    }
    BooleanCondition bcnd = pc.getPopular();
    if (bcnd != null) {// 热门
      if (bcnd.getValue()) {
        query.eq("popular", true);
      } else {
        query.ne("popular", true);
      }
    }
    bcnd = pc.getRecommend();
    if (bcnd != null) {// 推荐
      if (bcnd.getValue()) {
        query.eq("recommend", true);
      } else {
        query.ne("recommend", true);
      }
    }
    bcnd = pc.getSpecial();
    if (bcnd != null) {// 特价
      if (bcnd.getValue()) {
        query.eq("special", true);
      } else {
        query.ne("special", true);
      }
    }
    cnd = pc.getDepartureCity();
    if (cnd != null) {// 出发城市
      query.in("departureCities", cnd.getValue());
    }
    cnd = pc.getRecommendedMonth();
    if (cnd != null) {// 推荐月份
      query.in("recommendedMonth", cnd.getValue());
    }

    result.setTotal(query.countObjects());
    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Product.class).readAll());
    return result;
  }
}

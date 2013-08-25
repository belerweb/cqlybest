package com.cqlybest.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.Constant;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductCalendar;
import com.cqlybest.common.bean.ProductComment;
import com.cqlybest.common.bean.ProductDetailMaldives;
import com.cqlybest.common.bean.ProductFilterItem;
import com.cqlybest.common.bean.ProductGroup;
import com.cqlybest.common.bean.ProductMaldives;
import com.cqlybest.common.bean.ProductTraffic;
import com.cqlybest.common.bean.ProductTravel;
import com.cqlybest.common.dao.ImageDao;
import com.cqlybest.common.dao.ProductDao;

@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;
  @Autowired
  private ImageDao imageDao;

  @Transactional
  public void add(Product product) {
    product.setId(UUID.randomUUID().toString());
    Date now = new Date();
    product.setCreatedTime(now);
    product.setLastUpdated(now);
    productDao.saveOrUpdate(product);
  }

  @Transactional
  public void add(ProductTravel travel) {
    productDao.saveOrUpdate(travel);
  }

  @Transactional
  public void add(ProductTraffic travel) {
    productDao.saveOrUpdate(travel);
  }

  @Transactional
  public void add(ProductMaldives maldives) {
    productDao.saveOrUpdate(maldives);
  }

  @Transactional
  public void add(ProductComment comment) {
    productDao.saveOrUpdate(comment);
  }

  @Transactional
  public void addCalendar(String productId, Date start, Date end, Integer price,
      Integer childPrice, boolean special) {
    productDao.deleteCalendar(productId, start, end);
    Date date = start;
    while (date.getTime() <= end.getTime()) {
      ProductCalendar calendar = new ProductCalendar();
      calendar.setProductId(productId);
      calendar.setDate(date);
      calendar.setPrice(price);
      calendar.setChildPrice(childPrice);
      calendar.setSpecial(special);
      productDao.saveOrUpdate(calendar);
      date = new Date(date.getTime() + 86400000);
    }
  }

  public Product get(String id) {
    Product product = productDao.findById(id);
    product.setPosters(imageDao.queryImages(Constant.IMAGE_PRODUCT_POSTER, id));
    product.setPhotos(imageDao.queryImages(Constant.IMAGE_PRODUCT_PHOTO, id));
    product.setComments(productDao.getComments(id));
    product.setTrafficList(productDao.getTraffic(id));
    if (product.getProductType() == Product.MALDIVES) {
      product.setMaldives(productDao.getMaldives(id));
      product.setDetail(productDao.findById(ProductDetailMaldives.class, id));
    } else {
      product.setTravels(productDao.getTravels(id));
    }
    return product;
  }

  public List<ProductCalendar> getCalendar(String id) {
    return productDao.getCalendar(id);
  }

  @Transactional
  public void update(String id, String name, Object value) {
    productDao.update(id, name, value);
  }

  @Transactional
  public void update(String[] ids, String name, Object value) {
    productDao.update(ids, name, value);
  }

  @Transactional
  public void updateTravel(Integer id, String name, String value) {
    productDao.updateTravel(id, name, value);
  }

  @Transactional
  public void updateTraffic(Integer id, String name, Object value) {
    productDao.updateTraffic(id, name, value);
  }

  @Transactional
  public void updateMaldives(Integer id, String name, Object value) {
    productDao.updateMaldives(id, name, value);
    if (name.equals("islandId")) {
      // 修改海岛时需要将房间置空
      productDao.updateMaldives(id, "roomId", null);
    }
  }

  @Transactional
  public void updateMaldivesDetail(String id, String name, Object value) {
    ProductDetailMaldives detail = productDao.findById(ProductDetailMaldives.class, id);
    if (detail == null) {
      detail = new ProductDetailMaldives();
      detail.setId(id);
      productDao.saveOrUpdate(detail);
    }
    productDao.updateMaldivesDetail(id, name, value);
  }

  @Transactional
  public void delete(String[] ids) {
    productDao.delete(ids); // 删除产品
    productDao.deleteTravelByProduct(ids);
    productDao.deleteTrafficByProduct(ids);
    productDao.deleteMaldivesByProduct(ids);
    productDao.deleteCommentByProduct(ids);
    productDao.deleteCalendarByProduct(ids);
    for (String id : ids) {
      imageDao.deleteByExtra(Constant.IMAGE_PRODUCT_PHOTO, id);
      imageDao.deleteByExtra(Constant.IMAGE_PRODUCT_POSTER, id);
    }
  }

  @Transactional
  public void deleteTravel(Integer id) {
    productDao.deleteTravel(id);
  }

  @Transactional
  public void deleteTraffic(Integer id) {
    productDao.deleteTraffic(id);
  }

  @Transactional
  public void deleteMaldives(Integer id) {
    productDao.deleteMaldives(id);
  }

  @Transactional
  public void deleteComment(Integer id) {
    productDao.deleteComment(id);
  }

  @Transactional
  public void deleteCalendar(String productId, Date start, Date end) {
    productDao.deleteCalendar(productId, start, end);
  }

  public Long queryProductTotal(Map<String, Object> param) {
    return productDao.findProductTotal(createQueryProductCondition(param));
  }

  public List<Product> queryProduct(Map<String, Object> param, int page, int pageSize) {
    List<Product> products =
        productDao.findProductTotal(createQueryProductCondition(param), page, pageSize);
    for (Product product : products) {
      setProducTypeDetail(product);
    }
    return products;
  }

  private List<Criterion> createQueryProductCondition(Map<String, Object> param) {
    List<Criterion> propertyConditions = new ArrayList<>();
    for (String property : param.keySet()) {
      Object value = param.get(property);
      if ("name".equals(property)) {
        propertyConditions.add(Restrictions.like(property, (String) value, MatchMode.ANYWHERE));
      } else if ("popular".equals(property) || "recommend".equals(property)
          || "specialOffer".equals(property) || "published".equals(property)) {
        propertyConditions.add(((Boolean) value)
            ? Restrictions.eq(property, Boolean.TRUE)
            : Restrictions.or(Restrictions.eq(property, Boolean.FALSE),
                Restrictions.isNull(property)));
      } else {
        propertyConditions.add(Restrictions.eq(property, value));
      }
    }
    return propertyConditions;
  }

  public List<Product> getMaldivesProductByIsland(String islandId, int num) {
    List<String> productIds = productDao.getMaldivesProductIds(islandId, num);
    List<Product> result = new ArrayList<>();
    for (String id : productIds) {
      Product product = productDao.findById(id);
      if (product != null) {
        setProducTypeDetail(product);
        product.setPosters(imageDao.queryImages(Constant.IMAGE_PRODUCT_POSTER, id));
        result.add(product);
      }
    }
    return result;
  }

  public List<Product> getSpecialProduct(int number) {
    return getProductWithTrueProperty("specialOffer", number);// 特价
  }

  public List<Product> getRecommendedProduct(int number) {
    return getProductWithTrueProperty("recommend", number);// 推荐
  }

  public List<Product> getHotProduct(int number) {
    return getProductWithTrueProperty("popular", number);// 热门
  }

  private List<Product> getProductWithTrueProperty(String property, int number) {
    Conjunction condition = Restrictions.conjunction();
    condition.add(Restrictions.eq(property, true));
    condition.add(Restrictions.eq("published", true));// 已发布
    List<Product> products = null;
    if (number > 0) {
      products = productDao.find(condition, 0, number);
    } else {
      products = productDao.find(condition);
    }

    for (Product product : products) {
      product.setPosters(imageDao.queryImages(Constant.IMAGE_PRODUCT_POSTER, product.getId()));
      setProducTypeDetail(product);
    }

    return products;
  }

  public List<Product> queryProducts(ProductGroup productGroup, Set<ProductFilterItem> filterItems,
      Integer page, Integer pageSize) {
    Disjunction or = Restrictions.disjunction();
    Conjunction and = Restrictions.conjunction();
    generateGroupCondition(or, "recommendedMonths", productGroup.getGroupMonths());
    generateGroupCondition(or, "crowds", productGroup.getGroupCrowds());
    generateGroupCondition(or, "traffics", productGroup.getGroupTraffics());
    generateGroupCondition(or, "types", productGroup.getGroupTypes());
    generateGroupCondition(or, "grades", productGroup.getGroupGrades());
    generateGroupCondition(or, "keywords", productGroup.getGroupKeywords());
    generateGroupCondition(or, "departureCities", productGroup.getGroupDepartureCities());
    generateGroupCondition(or, "destinations", productGroup.getGroupDestinations());
    List<Product> products = productDao.getProducts(or, and, page, pageSize);
    if (pageSize != null) {
      for (Product product : products) {
        product.setPosters(imageDao.queryImages(Constant.IMAGE_PRODUCT_POSTER, product.getId()));
        setProducTypeDetail(product);
      }
    }
    return products;
  }

  private void generateGroupCondition(Junction condition, String property, String values) {
    if (values != null) {
      for (String value : values.split(",")) {
        condition.add(Restrictions.like(property, value, MatchMode.ANYWHERE));
      }
    }
  }

  private void setProducTypeDetail(Product product) {
    if (product.getProductType() == Product.MALDIVES) {
      product.setDetail(productDao.findById(ProductDetailMaldives.class, product.getId()));
    }
  }

  public List<Product> all() {
    return productDao.findAll();
  }

}

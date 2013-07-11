package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.criterion.Conjunction;
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
      List<ProductMaldives> maldives = productDao.getMaldives(id);
      product.setMaldives(maldives);
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
  public void delete(String[] ids) {
    productDao.delete(ids);
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

  public Long queryProductTotal(Boolean hot, Boolean red, Boolean spe, Boolean pub, String name) {
    return productDao.findProductTotal(hot, red, spe, pub, name);
  }

  public List<Product> queryProduct(Boolean hot, Boolean red, Boolean spe, Boolean pub,
      String name, int page, int pageSize) {
    return productDao.findProductTotal(hot, red, spe, pub, name, page, pageSize);
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

}

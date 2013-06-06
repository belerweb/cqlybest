package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductFilterItem;
import com.cqlybest.common.bean.ProductGroupItem;
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

  public Product get(String id) {
    Product product = productDao.findById(id);
    product.setPosters(imageDao.queryImagesWithoutData("product-poster", id));
    product.setPhotos(imageDao.queryImagesWithoutData("product-photo", id));
    return product;
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
  public void delete(String[] ids) {
    productDao.delete(ids);
  }

  public Long queryProductTotal() {
    return productDao.findProductTotal();
  }

  public List<Product> queryProduct(int page, int pageSize) {
    return productDao.findProductTotal(page, pageSize);
  }

  public List<Product> queryProducts(Set<ProductGroupItem> groupItems,
      Set<ProductFilterItem> filterItems, Integer page, Integer pageSize) {
    Disjunction or = Restrictions.disjunction();
    Conjunction and = Restrictions.conjunction();
    String[] propertyNames =
        new String[] {"recommendedMonths", "crowds", "traffics", "types", "grades", "keywords",
            "departureCities", "destinations"};
    for (ProductGroupItem item : groupItems) { // 聚合产品
      or.add(Restrictions.in(propertyNames[item.getGroupType()], item.getGroupValue().split(",")));
    }
    if (filterItems != null) {
      for (ProductFilterItem item : filterItems) {// 过滤产品
        or.add(Restrictions.eq(propertyNames[item.getType()], item.getId()));
      }
    }

    return productDao.getProducts(or, and, page, pageSize);
  }
}

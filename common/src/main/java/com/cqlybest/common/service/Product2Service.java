package com.cqlybest.common.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Product2;
import com.cqlybest.common.bean.ProductFilterItem;
import com.cqlybest.common.bean.ProductGroupItem;
import com.cqlybest.common.dao.ImageDao;
import com.cqlybest.common.dao.Product2Dao;

@Service
public class Product2Service {

  @Autowired
  private Product2Dao product2Dao;
  @Autowired
  private ImageDao imageDao;

  @Transactional
  public void add(Product2 product) {
    product.setId(UUID.randomUUID().toString());
    Date now = new Date();
    product.setCreatedTime(now);
    product.setLastUpdated(now);
    product2Dao.saveOrUpdate(product);
  }

  public Product2 get(String id) {
    Product2 product = product2Dao.findById(id);
    product.setPosters(imageDao.queryImagesWithoutData("product-poster", id));
    product.setPhotos(imageDao.queryImagesWithoutData("product-photo", id));
    return product;
  }

  @Transactional
  public void update(String id, String name, Object value) {
    product2Dao.update(id, name, value);
  }

  @Transactional
  public void update(String[] ids, String name, Object value) {
    product2Dao.update(ids, name, value);
  }

  @Transactional
  public void delete(String[] ids) {
    product2Dao.delete(ids);
  }

  public Long queryProductTotal() {
    return product2Dao.findProductTotal();
  }

  public List<Product2> queryProduct(int page, int pageSize) {
    return product2Dao.findProductTotal(page, pageSize);
  }

  public Long queryProductsTotal(Set<ProductGroupItem> groupItems,
      Set<ProductFilterItem> filterItems) {
    return product2Dao.findProductsTotal(groupItems, filterItems);
  }

  public List<Product2> queryProducts(Set<ProductGroupItem> groupItems,
      Set<ProductFilterItem> filterItems, Integer page, Integer pageSize) {
    List<Integer> productIds = new ArrayList<>();
    String[] tableNames =
        new String[] {"PRODUCT_RECOMMENDED_MONTH", "PRODUCT_CROWD", "PRODUCT_TRAFFIC",
            "PRODUCT_TYPE", "PRODUCT_GRADE", "PRODUCT_KEYWORD", "PRODUCT_DEPARTURE_CITY",
            "PRODUCT_DESTINATION"};
    String[] dictColumns = new String[] {"MONTH", "CROWD", "DID"};
    for (ProductGroupItem item : groupItems) { // 聚合产品
      int type = item.getGroupType();
      String ids = item.getGroupValue();
      productIds.addAll(product2Dao.findProductIdsByDict(tableNames[type], ids, type > 1
          ? "PID"
          : "ID", type > 1 ? dictColumns[2] : dictColumns[type]));
    }
    if (filterItems != null) {
      for (ProductFilterItem item : filterItems) {// 过滤产品
        int type = item.getType();
        Integer ids = item.getId();
        productIds.retainAll(product2Dao.findProductIdsByDict(tableNames[type], ids, type > 1
            ? "PID"
            : "ID", type > 1 ? dictColumns[2] : dictColumns[type]));
      }
    }

    return product2Dao.getProducts(productIds, page, pageSize);
  }
}

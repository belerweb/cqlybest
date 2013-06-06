package com.cqlybest.common.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.ProductGroup;
import com.cqlybest.common.dao.ProductGroupDao;

@Service
public class ProductGroupService {

  @Autowired
  private ProductGroupDao productGroupDao;

  @Transactional
  public void add(ProductGroup group) {
    group.setId(UUID.randomUUID().toString());
    productGroupDao.saveOrUpdate(group);
  }

  @Transactional
  public void update(String id, String name, Object value) {
    productGroupDao.update(id, name, value);
  }

  @Transactional
  public void delete(String id) {
    ProductGroup group = productGroupDao.findById(id);
    if (group != null) {
      productGroupDao.delete(group);
    }
  }

  public List<ProductGroup> getAllProductGroup() {
    return productGroupDao.findAll();
  }

  public ProductGroup getProductGroup(String id) {
    return productGroupDao.findById(id);
  }

}

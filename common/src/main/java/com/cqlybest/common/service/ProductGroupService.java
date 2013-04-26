package com.cqlybest.common.service;

import java.util.List;

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
  public void edit(ProductGroup group) {
    productGroupDao.saveOrUpdate(group);
  }

  @Transactional
  public void delete(String id) {
    ProductGroup group = productGroupDao.findById(id);
    if (group != null) {
      productGroupDao.delete(group);
    }
  }

  @Transactional
  public void togglePublished(String id, boolean published) {
    productGroupDao.togglePublished(id, published);
  }

  public List<ProductGroup> getAllProductGroup() {
    return productGroupDao.findAll();
  }

  public ProductGroup getProductGroup(String id) {
    return productGroupDao.findById(id);
  }

}

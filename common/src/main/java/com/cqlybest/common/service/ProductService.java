package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.dao.ProductDao;

@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

  /**
   * 编辑产品
   */
  @Transactional
  public void edit(Product product) {
    product.setPublished(false);
    product.setLastUpdate(new Date());
    productDao.saveOrUpdate(product);
  }

  /**
   * 删除产品
   */
  @Transactional
  public void delete(Integer id) {
    Product product = productDao.findById(id);
    if (product != null) {
      productDao.delete(product);
    }
  }

  public Long queryProductTotal() {
    return productDao.findProductTotal();
  }

  public List<Product> queryProduct(int page, int pageSize) {
    return productDao.findProductTotal(page, pageSize);
  }

  /**
   * 查询指定产品
   */
  public Product getProduct(Integer id) {
    return productDao.findById(id);
  }

  @Transactional
  public void togglePublished(Integer id, boolean published) {
    productDao.togglePublished(id, published);
  }

}

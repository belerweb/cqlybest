package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.dao.ProductDao;

@Service
@Transactional(readOnly = true)
public class ProductService {

  @Autowired
  private ProductDao productDao;

  @Transactional(readOnly = false)
  public void edit(Product product) {
    product.setPublished(false);
    product.setLastUpdate(new Date());
    productDao.saveOrUpdate(product);
  }

  @Transactional(readOnly = false)
  public void delete(Product product) {
    productDao.delete(product);
  }

  public Long queryProductTotal() {
    return productDao.findProductTotal();
  }

  public List<Product> queryProduct(int page, int pageSize) {
    return productDao.findProductTotal(page, pageSize);
  }

  @Transactional(readOnly = false)
  public void modify(Product updatedProduct) {
    updatedProduct.setPublished(false);
    updatedProduct.setLastUpdate(new Date());
    productDao.saveOrUpdate(updatedProduct);
  }

  public Product getProduct(Integer id) {
    return productDao.findById(id);
  }

}

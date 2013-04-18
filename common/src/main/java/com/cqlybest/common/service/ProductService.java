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
  public void addProduct(Product newProduct) {
    newProduct.setPublished(false);
    newProduct.setLastUpdate(new Date());
    productDao.saveOrUpdate(newProduct);
  }

  @Transactional(readOnly = false)
  public void deleteProduct(Integer productId) {
    Product product = new Product();
    product.setId(productId);
    productDao.delete(product);
  }

  public List<Product> queryProduct() {
    return productDao.findAll();
  }

  @Transactional(readOnly = false)
  public void modifyProduct(Product updatedProduct) {
    updatedProduct.setPublished(false);
    updatedProduct.setLastUpdate(new Date());
    productDao.saveOrUpdate(updatedProduct);
  }

}

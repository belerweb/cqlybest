package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductGroupFilterItem;
import com.cqlybest.common.bean.ProductGroupItem;
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
  public void delete(Integer[] ids) {
    productDao.delete(ids);
  }

  public Long queryProductTotal() {
    return productDao.findProductTotal();
  }

  public List<Product> queryProduct(int page, int pageSize) {
    return productDao.findProductTotal(page, pageSize);
  }

  public Long queryProductsTotal(Set<ProductGroupItem> groupItems,
      Set<ProductGroupFilterItem> filterItems) {
    return productDao.findProductsTotal(groupItems, filterItems);
  }

  public List<Product> queryProducts(Set<ProductGroupItem> groupItems,
      Set<ProductGroupFilterItem> filterItems, Integer page, Integer pageSize) {
    return productDao.findProducts(groupItems, filterItems, page, pageSize);
  }

  public void updateProperty(Integer[] ids, String prop, Object value) {
    productDao.updateProperty(ids, prop, value);
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

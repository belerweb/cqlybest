package com.cqlybest.common.dao;

import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Product;

@Repository
public class ProductDao extends AbstractDao<Product, Integer> {

  protected ProductDao() {
    super(Product.class);
  }

}

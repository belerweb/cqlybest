package com.cqlybest.common.mongo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.mongo.bean.Product;
import com.cqlybest.common.mongo.bean.ProductTransportation;
import com.cqlybest.common.mongo.bean.QueryResult;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.googlecode.mjorm.query.DaoQuery;

@Service("mongoProductService")
public class ProductService {

  @Autowired
  private MongoDb mongoDb;

  public Product addProduct(String type, String name) {
    Product product = new Product();
    product.setId(UUID.randomUUID().toString());
    product.setType(type);
    product.setName(name);

    Date now = new Date();
    product.setCreatedTime(now);
    product.setLastUpdated(now);
    return mongoDb.createObject("Product", product);
  }

  public Product getProduct(String id) {
    return mongoDb.findById("Product", Product.class, id);
  }

  public QueryResult<Product> queryProduct(int page, int pageSize) {
    QueryResult<Product> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDb.createQuery("Product");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Product.class).readAll());

    return result;
  }

  public void addTransportation(String productId, String name) {
    ProductTransportation transportation = new ProductTransportation();
    transportation.setId(UUID.randomUUID().toString());
    transportation.setName(name);
    mongoDb.createQuery("Product").eq("_id", productId).modify().push("transportations",
        mongoDb.unmap(transportation)).update();
  }

  public void updateTransportation(String id, String property, Object value) {
    Object _value = value;
    if ("detail".equals(property)) {
      _value = mongoDb.findById("Transportation", (String) value);
    }
    mongoDb.createQuery("Product").eq("transportations.id", id).modify().set(
        "transportations.$." + property, _value).update();
  }

  public void deleteTransportation(String id) {
    Map<String, String> transportation = new HashMap<>();
    transportation.put("id", id);
    mongoDb.createQuery("Product").eq("transportations.id", id).modify()
        .pull("transportations", transportation).update();
  }
}

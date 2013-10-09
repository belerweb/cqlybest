package com.cqlybest.common.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.IdcardUtils;
import com.cqlybest.common.bean.Customer;
import com.cqlybest.common.bean.CustomerEvent;
import com.cqlybest.common.bean.DateBean;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoQuery;
import com.googlecode.mjorm.query.Query;
import com.googlecode.mjorm.query.QueryGroup;

@Service
public class CustomerService {

  @Autowired
  private MongoDao mongoDao;

  public void importCustomer(List<Map<String, String>> customers) {
    for (Map<String, String> data : customers) {
      String idCard = data.get("idCard");
      String passport = data.get("passport");
      String mobile = data.get("mobile");
      String email = data.get("email");
      QueryGroup qGroup = new QueryGroup();
      if (idCard != null) {
        qGroup.add(Query.start().eq("idCard", idCard));
      }
      if (passport != null) {
        qGroup.add(Query.start().eq("passport", passport));
      }
      if (mobile != null) {
        qGroup.add(Query.start().eq("mobile", mobile));
      }
      if (email != null) {
        qGroup.add(Query.start().eq("email", email));
      }
      Customer customer = mongoDao.createQuery("Customer").or(qGroup).findObject(Customer.class);
      if (customer == null) {
        customer = new Customer();
      }

      if (idCard != null) {
        customer.setIdCard(idCard);
        try {
          customer.setBirth(new DateBean(DateUtils.parseDate(IdcardUtils.getBirthByIdCard(idCard),
              new String[] {"yyyyMMdd"})));
        } catch (ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        customer.setAncestralLocation(IdcardUtils.getProvinceByIdCard(idCard));
        customer.setSex(IdcardUtils.getGenderByIdCard(idCard));
      }
      if (passport != null) {
        customer.setPassport(passport);
      }
      if (mobile != null) {
        customer.setMobile(mobile);
      }
      if (email != null) {
        customer.setEmail(email);
      }
      String fullname = data.get("fullname");
      if (fullname != null) {
        customer.setFullname(fullname);
      }
      String sex = data.get("sex");
      if ("男".equals(sex)) {
        customer.setSex(1);
      }
      if ("女".equals(sex)) {
        customer.setSex(0);
      }
      String qq = data.get("qq");
      if (qq != null) {
        customer.setQq(qq);
      }

      if (customer.getId() == null) {
        customer.setId(UUID.randomUUID().toString());
        mongoDao.createObject("Customer", customer);
      } else {
        mongoDao.updateObject("Customer", customer.getId(), customer);
      }
    }
  }

  public List<CustomerEvent> getTodayCustomerEvent() {
    return mongoDao.createQuery("CustomerEvent").eq("created", DateTime.now().toString("yyyyMMdd"))
        .findObjects(CustomerEvent.class).readAll();
  }

  public QueryResult<Customer> getCustomer(String keyword, int pageSize) {
    return getCustomer(keyword, 0, pageSize);
  }

  public QueryResult<Customer> getCustomer(String keyword, int page, int pageSize) {
    QueryResult<Customer> result = new QueryResult<>(page, pageSize);
    QueryGroup queries = new QueryGroup();
    String pattern = ".*" + keyword + ".*";
    queries.add(Query.start().regex("fullname", pattern));
    queries.add(Query.start().regex("idCard", pattern));
    queries.add(Query.start().regex("passport", pattern));
    queries.add(Query.start().regex("mobile", pattern));
    queries.add(Query.start().regex("email", pattern));
    queries.add(Query.start().regex("qq", pattern));

    DaoQuery query = mongoDao.createQuery("Customer").or(queries);
    result.setTotal(query.countObjects());
    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Customer.class).readAll());

    return result;
  }

  public QueryResult<Customer> getCustomer(String property, String keyword, int pageSize) {
    return getCustomer(property, keyword, 0, pageSize);
  }

  public QueryResult<Customer> getCustomer(String property, String keyword, int page, int pageSize) {
    QueryResult<Customer> result = new QueryResult<>(page, pageSize);
    String pattern = ".*" + keyword + ".*";
    DaoQuery query = mongoDao.createQuery("Customer").regex(property, pattern);
    result.setTotal(query.countObjects());
    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(Customer.class).readAll());

    return result;
  }
}

package com.cqlybest.admin.util;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.Cn2Spell;
import com.cqlybest.common.bean.DepartureCity;
import com.cqlybest.common.bean.Destination;
import com.cqlybest.common.bean.Dict;
import com.cqlybest.common.bean.DictProductGrade;
import com.cqlybest.common.bean.DictProductType;
import com.cqlybest.common.bean.DictTraffic;
import com.cqlybest.common.bean.Keyword;
import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.QbProduct;
import com.cqlybest.common.bean.QbProductContent;
import com.cqlybest.common.bean.QbProductDetail;
import com.cqlybest.common.dao.ProductDao;
import com.cqlybest.common.service.DestinationService;
import com.cqlybest.common.service.DictService;

@Service
@Transactional(readOnly = false)
public class DataTransferService {

  private Map<String, Destination> destinations = new HashMap<>();
  private Map<String, DictTraffic> traffics = new HashMap<>();
  private Map<String, DictProductType> types = new HashMap<>();
  private Map<String, DictProductGrade> grades = new HashMap<>();
  private Map<String, Keyword> keywords = new HashMap<>();
  private Map<String, DepartureCity> departureCities = new HashMap<>();

  @Autowired
  private ProductDao productDao;
  @Autowired
  private DestinationService destinationService;
  @Autowired
  private DictService dictService;

  @SuppressWarnings("unchecked")
  public void transfer() {
    loadDestination();// 加载目的地
    loadDict(DictTraffic.class, traffics);// 加载交通方式
    loadDict(DictProductType.class, types);// 加载产品类型
    loadDict(DictProductGrade.class, grades);// 加载产品等级
    loadDict(Keyword.class, keywords);// 加载关键词
    loadDict(DepartureCity.class, departureCities);// 加载出发城市

    Session session = productDao.getCurrentSession();
    List<QbProduct> qbProducts = session.createCriteria(QbProduct.class).list();
    for (QbProduct qbProduct : qbProducts) {
      QbProductDetail detail = qbProduct.getDetail();
      QbProductContent content = qbProduct.getContent();
      if (detail == null) {
        detail = new QbProductDetail();
      }
      if (content == null) {
        content = new QbProductContent();
      }
      Product product = new Product();
      product.setName(qbProduct.getTitle());// 产品名称
      product.setDays(detail.getMy_xcts());// 行程天数
      product.setDaysUnit('d');// 行程天数单位 ：d/天，m/月，y/年
      product.setDescription(content.getContent());// 产品介绍
      product.setPrice(detail.getMy_price() * 100); // 价格
      product.setFriendlyReminder(detail.getMy_bbh());// 费用说明
      product.setTripCharacteristic(detail.getMy_tes());// 行程特色
      product.setServiceStandard(detail.getMy_fwbz());// 服务标准
      product.setFriendlyReminder(detail.getMy_yqts());// 友情提示
      product.setRecommendedItem(detail.getMy_tjxm());// 推荐项目

      // 目的地
      String myCity = detail.getMy_city() + detail.getMy_mdd();
      if (StringUtils.isNotBlank(myCity)) {
        Set<Destination> dests = new HashSet<>();
        String[] tmp = myCity.split("[、a-zA-Z\\s]");
        for (String s : tmp) {
          if (StringUtils.isNotBlank(s)) {
            Destination dest = destinations.get(s);
            if (dest == null) {
              dest = new Destination();
              dest.setName(s);
              dest.setPinyin(Cn2Spell.converterToSpell(s));
              dest.setPy(Cn2Spell.converterToFirstSpell(s));
              destinationService.add(dest);
              destinations.put(s, dest);
            }
            dests.add(dest);
          }
        }
        product.setDestinations(dests);
      }

      // 推荐月份
      String moth = detail.getMy_moth();
      if (StringUtils.isNotBlank(moth)) {
        Integer month = Integer.valueOf(moth.replaceAll("[^\\d]", ""));
        Set<Integer> recommendedMonths = new HashSet<>();
        recommendedMonths.add(month);
        product.setRecommendedMonths(recommendedMonths);
      }

      // 适合群体
      String renq = detail.getMy_renq();
      if (StringUtils.isNotBlank(moth)) {
        Set<Integer> crowds = new HashSet<>();
        if (renq.contains("个人")) {
          crowds.add(1);
        }
        if (renq.contains("团体")) {
          crowds.add(2);
        }
        product.setCrowds(crowds);
      }

      // 出发城市
      String chufd = detail.getMy_chufd();
      if (StringUtils.isNotBlank(chufd)) {
        Set<DepartureCity> ds = new HashSet<>();
        DepartureCity city = departureCities.get(chufd);
        if (city == null) {
          city = new DepartureCity();
          city.setName(chufd);
          dictService.addDict(city);
          departureCities.put(chufd, city);
        }
        ds.add(city);
        product.setDepartureCities(ds);
      }

      // 产品等级
      String dengj = detail.getMy_dengj();
      if (StringUtils.isNotBlank(dengj)) {
        Set<DictProductGrade> gs = new HashSet<>();
        DictProductGrade grade = grades.get(dengj);
        if (grade == null) {
          grade = new DictProductGrade();
          grade.setName(dengj);
          dictService.addDict(grade);
          grades.put(dengj, grade);
        }
        gs.add(grade);
        product.setGrades(gs);
      }

      // 产品类型
      String neix = detail.getMy_neix();
      if (StringUtils.isNotBlank(neix)) {
        Set<DictProductType> ts = new HashSet<>();
        DictProductType type = types.get(neix);
        if (type == null) {
          type = new DictProductType();
          type.setName(neix);
          dictService.addDict(type);
          types.put(neix, type);
        }
        ts.add(type);
        product.setTypes(ts);
      }

      // 交通方式
      String jtfs = detail.getMy_jtfs();
      if (StringUtils.isNotBlank(jtfs)) {
        Set<DictTraffic> ts = new HashSet<>();
        String[] tmp = jtfs.split("/");
        for (String s : tmp) {
          if (StringUtils.isNotBlank(s)) {
            DictTraffic traffic = traffics.get(s);
            if (traffic == null) {
              traffic = new DictTraffic();
              traffic.setName(s);
              dictService.addDict(traffic);
              traffics.put(s, traffic);
            }
            ts.add(traffic);
          }
        }
        product.setTraffics(ts);
      }

      // 关键词
      String kws = qbProduct.getKeywords();
      if (StringUtils.isNotBlank(kws)) {
        Set<Keyword> kwords = new HashSet<>();
        String[] tmp = kws.split("[、a-zA-Z\\s]");
        for (String s : tmp) {
          s = s.replaceAll("\\u3000", "");
          if (StringUtils.isNotBlank(s)) {
            Keyword kw = keywords.get(s);
            if (kw == null) {
              kw = new Keyword();
              kw.setName(s);
              System.out.println("================" + kw.getName());
              dictService.addDict(kw);
              keywords.put(s, kw);
            }
            kwords.add(kw);
          }
        }
        product.setKeywords(kwords);
      }

      product.setPublished(false);
      product.setLastUpdate(new Date());
      productDao.saveOrUpdate(product);
    }
  }

  private void loadDestination() {
    List<Destination> objs = destinationService.getTree();
    for (Destination obj : objs) {
      destinations.put(obj.getName(), obj);
    }
  }

  private <T extends Dict> void loadDict(Class<T> cls, Map<String, T> dicts) {
    List<T> objs = dictService.getDict(cls);
    for (T obj : objs) {
      dicts.put(obj.getName(), obj);
    }
  }

}

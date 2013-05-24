package com.cqlybest.common.dao;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductFilterItem;
import com.cqlybest.common.bean.ProductGroupItem;

@Repository
@SuppressWarnings("unchecked")
public class ProductDao extends AbstractDao<Product, Integer> {

  protected ProductDao() {
    super(Product.class);
  }

  public void delete(Integer[] ids) {
    getCurrentSession().createQuery("DELETE FROM  Product WHERE id IN (:ids)").setParameterList(
        "ids", ids).executeUpdate();
  }

  public Long findProductTotal() {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.setProjection(Projections.rowCount());
    return (Long) criteria.uniqueResult();
  }

  public List<Product> findProductTotal(int page, int pageSize) {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    return criteria.list();
  }

  public int togglePublished(Integer id, boolean published) {
    String hql = "UPDATE Product SET published = ?, lastUpdate = ? WHERE id = ?";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, published);
    query.setParameter(1, new Date());
    query.setParameter(2, id);
    return query.executeUpdate();
  }

  public int updateProperty(Integer[] ids, String prop, Object value) {
    String hql = "UPDATE Product SET " + prop + " = ?, lastUpdate = ? WHERE id IN (:ids)";
    Query query = getCurrentSession().createQuery(hql);
    query.setParameter(0, value);
    query.setParameter(1, new Date());
    query.setParameterList("ids", ids);
    return query.executeUpdate();
  }

  public List<Integer> findProductIdsByDict(String dictTable, Object ids, String pidColumn,
      String dictColumn) {
    Map<String, Object> param = new HashMap<>();
    param.put("dictTable", dictTable);
    param.put("ids", ids);
    param.put("pidColumn", pidColumn);
    param.put("dictColumn", dictColumn);
    return getSqlSession().selectList("ProductDao.findProductIdsByDict", param);
  }

  public List<Product> getProducts(List<Integer> ids, Integer page, Integer pageSize) {
    if (ids.isEmpty()) {
      return Collections.emptyList();
    }
    Criteria criteria = getCurrentSession().createCriteria(Product.class);
    criteria.add(Restrictions.in("id", ids));
    if (pageSize > 0) {
      criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
      criteria.setMaxResults(pageSize);
    }
    return criteria.list();
  }

  public Long findProductsTotal(Set<ProductGroupItem> groupItems, Set<ProductFilterItem> filterItems) {
    return (Long) createFindProductsCriteria(groupItems, filterItems).setProjection(
        Projections.countDistinct("id")).uniqueResult();
  }

  // 此方法效率太低
  public List<Product> findProducts(Set<ProductGroupItem> groupItems,
      Set<ProductFilterItem> filterItems, Integer page, Integer pageSize) {
    Criteria criteria = createFindProductsCriteria(groupItems, filterItems);
    if (pageSize > 0) {
      criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
      criteria.setMaxResults(pageSize);
    }
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return criteria.list();
  }

  private Criteria createFindProductsCriteria(Set<ProductGroupItem> groupItems,
      Set<ProductFilterItem> filterItems) {
    Criteria criteria = getCurrentSession().createCriteria(Product.class);
    criteria.createAlias("recommendedMonths", "month", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("crowds", "crowds", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("traffics", "traffic", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("types", "type", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("grades", "grade", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("keywords", "keyword", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("departureCities", "city", JoinType.LEFT_OUTER_JOIN);
    criteria.createAlias("destinations", "dest", JoinType.LEFT_OUTER_JOIN);
    Disjunction groupCondition = Restrictions.disjunction();
    for (ProductGroupItem groupItem : groupItems) {
      int type = groupItem.getGroupType();
      Integer[] ids = retrieveIntegers(groupItem.getGroupValue());
      if (type == 0) {
        groupCondition.add(Restrictions.in("month.elements", ids));
      }
      if (type == 1) {
        groupCondition.add(Restrictions.in("crowds.elements", ids));
      }
      if (type == 2) {
        groupCondition.add(Restrictions.in("traffic.id", ids));
      }
      if (type == 3) {
        groupCondition.add(Restrictions.in("type.id", ids));
      }
      if (type == 4) {
        groupCondition.add(Restrictions.in("grade.id", ids));
      }
      if (type == 5) {
        groupCondition.add(Restrictions.in("keyword.id", ids));
      }
      if (type == 6) {
        groupCondition.add(Restrictions.in("city.id", ids));
      }
      if (type == 7) {
        groupCondition.add(Restrictions.in("dest.id", ids));
      }
    }
    criteria.add(groupCondition);
    if (filterItems != null) {
      for (ProductFilterItem item : filterItems) {
        int type = item.getType();
        int id = item.getId();
        if (type == 0) {
          criteria.add(Restrictions.eq("month.elements", id));
        }
        if (type == 1) {
          criteria.add(Restrictions.eq("crowds.elements", id));
        }
        if (type == 2) {
          criteria.add(Restrictions.eq("traffic.id", id));
        }
        if (type == 3) {
          criteria.add(Restrictions.eq("type.id", id));
        }
        if (type == 4) {
          criteria.add(Restrictions.eq("grade.id", id));
        }
        if (type == 5) {
          criteria.add(Restrictions.eq("keyword.id", id));
        }
        if (type == 6) {
          criteria.add(Restrictions.eq("city.id", id));
        }
        if (type == 7) {
          criteria.add(Restrictions.eq("dest.id", id));
        }
      }
    }
    return criteria;
  }


  private Integer[] retrieveIntegers(String text) {
    String[] idText = text.split(",");
    Integer[] ids = new Integer[idText.length];
    for (int i = 0; i < idText.length; i++) {
      ids[i] = Integer.parseInt(idText[i]);
    }
    return ids;
  }

}

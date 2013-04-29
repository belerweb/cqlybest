package com.cqlybest.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cqlybest.common.bean.Product;
import com.cqlybest.common.bean.ProductGroupFilterItem;
import com.cqlybest.common.bean.ProductGroupItem;

@Repository
public class ProductDao extends AbstractDao<Product, Integer> {

  protected ProductDao() {
    super(Product.class);
  }

  public Long findProductTotal() {
    Criteria criteria = getCurrentSession().createCriteria(entityClass);
    criteria.setProjection(Projections.rowCount());
    return (Long) criteria.uniqueResult();
  }

  @SuppressWarnings("unchecked")
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


  public Long findProductsTotal(Set<ProductGroupItem> groupItems,
      Set<ProductGroupFilterItem> filterItems) {
    return (Long) createFindProductsCriteria(groupItems).setProjection(Projections.rowCount())
        .uniqueResult();
  }

  @SuppressWarnings("unchecked")
  public List<Product> findProducts(Set<ProductGroupItem> groupItems,
      Set<ProductGroupFilterItem> filterItems, Integer page, Integer pageSize) {
    Criteria criteria = createFindProductsCriteria(groupItems);
    criteria.setFirstResult((Math.max(page, 1) - 1) * pageSize);
    criteria.setMaxResults(pageSize);
    return criteria.list();
  }

  private Criteria createFindProductsCriteria(Set<ProductGroupItem> groupItems) {
    Criteria criteria = getCurrentSession().createCriteria(Product.class);
    for (ProductGroupItem groupItem : groupItems) {
      int type = groupItem.getGroupType();
      Integer[] ids = retrieveIntegers(groupItem.getGroupValue());
      if (type == 0) {
        criteria.createCriteria("recommendedMonths").add(Restrictions.in("elements", ids));
      }
      if (type == 1) {
        criteria.createCriteria("crowds").add(Restrictions.in("elements", ids));
      }
      if (type == 2) {
        criteria.createAlias("traffics", "traffic").add(Restrictions.in("traffic.id", ids));
      }
      if (type == 3) {
        criteria.createAlias("types", "type").add(Restrictions.in("type.id", ids));
      }
      if (type == 4) {
        criteria.createAlias("grades", "grade").add(Restrictions.in("grade.id", ids));
      }
      if (type == 5) {
        criteria.createAlias("keywords", "keyword").add(Restrictions.in("keyword.id", ids));
      }
      if (type == 6) {
        criteria.createAlias("departureCities", "city").add(Restrictions.in("city.id", ids));
      }
      if (type == 7) {
        criteria.createAlias("destinations", "dest").add(Restrictions.in("dest.id", ids));
      }
    }
    return criteria;
  }


  private Integer[] retrieveIntegers(String text) {
    String[] idText = text.split(",");
    Integer[] ids = new Integer[idText.length];
    for (int i = 0; i < idText.length; i++) {
      ids[i] = Integer.parseInt(idText[1]);
    }
    return ids;
  }

}

package com.cqlybest.common.service;

import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Menu;
import com.cqlybest.common.dao.MenuDao;

@Service
public class MenuService {

  @Autowired
  private MenuDao menuDao;

  @Transactional
  public void add(Menu menu) {
    menu.setId(UUID.randomUUID().toString());
    menu.setDisplayOrder(menuDao.total().intValue() + 1);
    menu.setPublished(false);
    menuDao.saveOrUpdate(menu);
  }

  @Transactional
  public void modify(Menu menu) {
    menu.setPublished(false);
    menuDao.saveOrUpdate(menu);
  }

  @Transactional
  public void delete(String id) {
    Menu menu = menuDao.findById(id);
    if (menu != null) {
      menuDao.delete(menu);
    }
  }

  @Transactional
  public void moveUp(String id) {
    Menu menu = menuDao.findById(id);
    int order = menu.getDisplayOrder();
    Menu prev = menuDao.prev(order);
    if (prev != null) {
      menu.setDisplayOrder(prev.getDisplayOrder());
      prev.setDisplayOrder(order);
      menuDao.saveOrUpdate(menu);
      menuDao.saveOrUpdate(prev);
    }
  }

  @Transactional
  public void moveDown(String id) {
    Menu menu = menuDao.findById(id);
    int order = menu.getDisplayOrder();
    Menu next = menuDao.next(order);
    if (next != null) {
      menu.setDisplayOrder(next.getDisplayOrder());
      next.setDisplayOrder(order);
      menuDao.saveOrUpdate(menu);
      menuDao.saveOrUpdate(next);
    }
  }

  @Transactional
  public void togglePublished(String id, boolean published) {
    menuDao.togglePublished(id, published);
  }

  public Menu get(String id) {
    return menuDao.findById(id);
  }

  public List<Menu> getAllMenus() {
    return menuDao.find(Order.asc("displayOrder"));
  }

  public List<Menu> getPublishedMenus() {
    return menuDao.find(Restrictions.eq("published", true), Order.asc("displayOrder"));
  }

}

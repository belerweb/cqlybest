package com.cqlybest.common.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.Site;
import com.cqlybest.common.dao.SiteDao;

@Service
public class SiteService {

  private Site cachedSite;

  @Autowired
  private SiteDao siteDao;

  /**
   * 获取最新的网站配置信息
   */
  public Site getSite() {
    checkCachedSite();
    return cachedSite;
  }

  /**
   * 修改网站配置信息
   */
  public void modifySite(Site newSite) {
    checkCachedSite();
    if (newSite != null && !newSite.equals(cachedSite)) {
      newSite.setLastUpdate(new Date());
      siteDao.saveOrUpdate(newSite);
      cachedSite = newSite;
    }
  }

  private void checkCachedSite() {
    if (cachedSite == null) {
      cachedSite = siteDao.findLatestSite();
    }
  }

}

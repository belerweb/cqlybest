package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.QQAuth;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.bean.WeiboAuth;
import com.cqlybest.common.dao.UserDao;

@Service
public class UserService {

  @Autowired
  private UserDao userDao;

  @Transactional
  public void addUser(LoginUser user) {
    user.setId(UUID.randomUUID().toString());
    userDao.saveOrUpdate(user);
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String usernameType = null;
    if (username.matches("^\\d+$")) {
      usernameType = "cellPhone";
    } else if (username.contains("@")) {
      usernameType = "email";
    } else {
      usernameType = "loginUsername";
    }
    LoginUser user = userDao.findOne(Restrictions.eq(usernameType, username));
    if (user == null) {
      user = userDao.findById(username);// 通过ID查找
      if (user == null) {
        throw new UsernameNotFoundException("用户不存在");
      }
      if (user.getPassword() == null) {
        // FIXME 是否有安全问题
        user.setPassword(username);
      }
    }
    user.setUsername(username);
    return user;
  }

  public LoginUser getUserByCellPhone(String phone) {
    return userDao.findOne(Restrictions.eq("cellPhone", phone));
  }

  /**
   * QQ 登录用户注册
   */
  @Transactional
  public LoginUser register(QQAuth auth) {
    QQAuth existed = userDao.findById(QQAuth.class, auth.getOpenid());
    if (existed == null) {
      auth.setCreatedTime(new Date());
      auth.setLastUpdate(new Date());
      userDao.saveOrUpdate(auth);
    } else {
      existed.setToken(auth.getToken());
      existed.setExpireIn(auth.getExpireIn());
      existed.setLastUpdate(new Date());
      userDao.saveOrUpdate(existed);
    }

    LoginUser user = userDao.findOne(Restrictions.eq("qqAuth", auth));
    if (user == null) {
      user = new LoginUser(auth);
      userDao.saveOrUpdate(user);
    }
    return user;
  }

  /**
   * 新浪微博登录用户注册
   */
  @Transactional
  public LoginUser register(WeiboAuth auth) {
    WeiboAuth existed = userDao.findById(WeiboAuth.class, auth.getUid());
    if (existed == null) {
      auth.setCreatedTime(new Date());
      auth.setLastUpdate(new Date());
      userDao.saveOrUpdate(auth);
    } else {
      existed.setToken(auth.getToken());
      existed.setExpireIn(auth.getExpireIn());
      existed.setLastUpdate(new Date());
      userDao.saveOrUpdate(existed);
    }

    LoginUser user = userDao.findOne(Restrictions.eq("weiboAuth", auth));
    if (user == null) {
      user = new LoginUser(auth);
      userDao.saveOrUpdate(user);
    }
    return user;
  }

  public Long getUserListTotal(Role role) {
    return userDao.findUserTotal(role.getRole());
  }

  public List<LoginUser> getUserList(Role role, int page, int pageSize) {
    return userDao.findUser(role.getRole(), page, pageSize);
  }

  public LoginUser getUser(String id) {
    return userDao.findById(id);
  }


  @Transactional
  public void update(String id, String name, Object value) {
    userDao.update(id, name, value);
  }

  @Transactional
  public void delete(LoginUser user) {
    userDao.delete(user);
  }

}

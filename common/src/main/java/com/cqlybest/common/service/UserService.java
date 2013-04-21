package com.cqlybest.common.service;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.QQAuth;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.dao.UserDao;

@Service
@Transactional(readOnly = true)
public class UserService {

  @Autowired
  private UserDao userDao;

  public void addUser(LoginUser user) {
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

  /**
   * QQ 登录用户注册
   */
  @Transactional(readOnly = false)
  public LoginUser register(QQAuth qqAuth) {
    QQAuth existed = userDao.findById(QQAuth.class, qqAuth.getOpenid());
    if (existed == null) {
      qqAuth.setCreatedTime(new Date());
      qqAuth.setLastUpdate(new Date());
      userDao.saveOrUpdate(qqAuth);
    } else {
      existed.setToken(qqAuth.getToken());
      existed.setExpireIn(qqAuth.getExpireIn());
      existed.setLastUpdate(new Date());
      userDao.saveOrUpdate(existed);
    }

    LoginUser user = userDao.findOne(Restrictions.eq("qqOpenid", qqAuth.getOpenid()));
    if (user == null) {
      user = new LoginUser(qqAuth);
      userDao.saveOrUpdate(user);
    }
    return user;
  }

  public List<LoginUser> getUserList(Role role, int page, int pageSize) {
    return userDao.findUser(role.getRole(), page, pageSize);
  }

}

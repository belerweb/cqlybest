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

import weibo4j.model.User;

import com.cqlybest.common.bean.LoginUser;
import com.cqlybest.common.bean.QQAuth;
import com.cqlybest.common.bean.Role;
import com.cqlybest.common.bean.WeiboAppAuth;
import com.cqlybest.common.bean.WeiboAuth;
import com.cqlybest.common.dao.UserDao;
import com.qq.connect.javabeans.qzone.UserInfoBean;

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
  public LoginUser register(QQAuth auth, UserInfoBean qzoneUser) {
    QQAuth _auth = userDao.findById(QQAuth.class, auth.getOpenid());
    if (_auth == null) {
      _auth = auth;
      _auth.setCreatedTime(new Date());
    } else {
      _auth.setToken(auth.getToken());
      _auth.setExpireIn(auth.getExpireIn());
    }
    _auth.setLevel(qzoneUser.getLevel());
    _auth.setGender(qzoneUser.getGender());
    _auth.setVip(qzoneUser.isVip());
    _auth.setYellowYearVip(qzoneUser.isYellowYearVip());
    _auth.setLastUpdate(new Date());
    userDao.saveOrUpdate(_auth);

    LoginUser user = userDao.findOne(Restrictions.eq("qqAuth", auth));
    if (user == null) {
      user = new LoginUser(auth);
    }
    user.setNickname(qzoneUser.getNickname());
    user.setAvatar(qzoneUser.getAvatar().getAvatarURL50());
    // userDao.saveOrUpdate(weiboUser);
    userDao.saveOrUpdate(user);
    return user;
  }

  /**
   * 新浪微博登录用户注册
   */
  @Transactional
  public LoginUser register(WeiboAuth auth, User weiboUser) {
    if (auth instanceof WeiboAppAuth) {
      // 新浪微博APP登录
      WeiboAppAuth weiboAppAuth =
          userDao.find(((WeiboAppAuth) auth).getAppId(), ((WeiboAppAuth) auth).getCid(), auth
              .getUid());
      if (weiboAppAuth == null) {
        weiboAppAuth = (WeiboAppAuth) auth;
        weiboAppAuth.setCreatedTime(new Date());
      } else {
        weiboAppAuth.setToken(auth.getToken());
        weiboAppAuth.setExpireIn(auth.getExpireIn());
      }
      weiboAppAuth.setLastUpdate(new Date());
      userDao.saveOrUpdate(weiboAppAuth);

      WeiboAuth weiboAuth = userDao.findById(WeiboAuth.class, auth.getUid());
      if (weiboAuth == null) {
        weiboAuth = new WeiboAuth(auth.getUid(), null, 0);
        weiboAuth.setCreatedTime(new Date());
      }
      weiboAuth.setLastUpdate(new Date());
      userDao.saveOrUpdate(weiboAuth);

      LoginUser user = userDao.findOne(Restrictions.eq("weiboAuth", weiboAuth));
      if (user == null) {
        user = new LoginUser(weiboAuth);
      }

      user.setWeiboAppAuth(weiboAppAuth);
      user.setNickname(weiboUser.getScreenName());
      user.setAvatar(weiboUser.getProfileImageUrl());
      userDao.saveOrUpdate(weiboUser);
      userDao.saveOrUpdate(user);
      return user;
    } else {
      // 新浪微博网站登录
      WeiboAuth weiboAuth = userDao.findById(WeiboAuth.class, auth.getUid());
      if (weiboAuth == null) {
        weiboAuth = auth;
        weiboAuth.setCreatedTime(new Date());
      } else {
        weiboAuth.setToken(auth.getToken());
        weiboAuth.setExpireIn(auth.getExpireIn());
      }
      weiboAuth.setLastUpdate(new Date());
      userDao.saveOrUpdate(weiboAuth);

      LoginUser user = userDao.findOne(Restrictions.eq("weiboAuth", weiboAuth));
      if (user == null) {
        user = new LoginUser(weiboAuth);
      }

      user.setNickname(weiboUser.getScreenName());
      user.setAvatar(weiboUser.getProfileImageUrl());
      userDao.saveOrUpdate(weiboUser);
      userDao.saveOrUpdate(user);
      return user;
    }
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

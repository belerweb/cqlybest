package com.cqlybest.common.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cqlybest.common.bean.QQUser;
import com.cqlybest.common.bean.QueryResult;
import com.cqlybest.common.bean.User;
import com.cqlybest.common.bean.WeiboUser;
import com.cqlybest.common.dao.MongoDao;
import com.googlecode.mjorm.query.DaoModifier;
import com.googlecode.mjorm.query.DaoQuery;
import com.mongodb.BasicDBObject;
import com.qq.connect.javabeans.qzone.UserInfoBean;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private MongoDao mongoDao;

  public QueryResult<User> queryUser(int page, int pageSize) {
    QueryResult<User> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDao.createQuery("User");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(User.class).readAll());

    return result;
  }

  public User register(WeiboUser weiboUser, weibo4j.model.User detail) {
    User user =
        mongoDao.createQuery("User").eq("weibo.id", weiboUser.getId()).findObject(User.class);
    if (user == null) {
      user = new User();
      user.setId("WEIBO-" + weiboUser.getId());
      user.setNickname(detail.getScreenName());
      user.setAvatar(detail.getProfileImageUrl());
      user.setSource("新浪微博帐号登录");
      user.setWeibo(weiboUser);
      Date current = new Date();
      user.setCreatedTime(current);
      user.setLastUpdated(current);
      user = mongoDao.createObject("User", user);
    } else {
      // TODO 更新原Token
    }
    return user;
  }

  public User register(QQUser qqUser, UserInfoBean detail) {
    User user = mongoDao.createQuery("User").eq("qzone.id", qqUser.getId()).findObject(User.class);
    if (user == null) {
      user = new User();
      user.setId("QZONE-" + qqUser.getId());
      user.setNickname(detail.getNickname());
      user.setAvatar(detail.getAvatar().getAvatarURL50());
      user.setSource("QQ帐号登录");
      user.setQzone(qqUser);
      Date current = new Date();
      user.setCreatedTime(current);
      user.setLastUpdated(current);
      user = mongoDao.createObject("User", user);
    } else {
      // TODO 更新原Token
    }
    return user;
  }

  public void subscribe(String type, String id) {
    BasicDBObject obj = new BasicDBObject();
    obj.put("type", id);
    mongoDao.createObject("Subscription", obj);
  }

  public void toggleRole(String id, String role, boolean toggle) {
    DaoModifier modify = mongoDao.createQuery("User").eq("_id", id).modify();
    if (toggle) {
      modify.push("roles", role);
    } else {
      modify.pull("roles", role);
    }
    modify.update();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String property = null;
    if (username.matches("^\\d+$")) {
      property = "mobile";
    } else if (username.contains("@")) {
      property = "email";
    } else {
      property = "username";
    };
    User user = mongoDao.createQuery("User").eq(property, username).findObject(User.class);
    if (user == null) {
      throw new UsernameNotFoundException("用户不存在");
    }
    return new User.UserWrapper(username, user);
  }

}

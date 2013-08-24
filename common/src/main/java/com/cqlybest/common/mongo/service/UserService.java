package com.cqlybest.common.mongo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cqlybest.common.mongo.bean.QQUser;
import com.cqlybest.common.mongo.bean.QueryResult;
import com.cqlybest.common.mongo.bean.User;
import com.cqlybest.common.mongo.bean.WeiboUser;
import com.cqlybest.common.mongo.dao.MongoDb;
import com.googlecode.mjorm.query.DaoQuery;
import com.qq.connect.javabeans.qzone.UserInfoBean;

@Service("mongoUserService")
public class UserService implements UserDetailsService {

  @Autowired
  private MongoDb mongoDb;

  public QueryResult<User> queryUser(int page, int pageSize) {
    QueryResult<User> result = new QueryResult<>(page, pageSize);
    DaoQuery query = mongoDb.createQuery("User");
    result.setTotal(query.countObjects());

    query.setFirstDocument(result.getStart());
    query.setMaxDocuments(result.getPageSize());
    result.setItems(query.findObjects(User.class).readAll());

    return result;
  }

  public User register(WeiboUser weiboUser, weibo4j.model.User detail) {
    User user =
        mongoDb.createQuery("User").eq("weibo.id", weiboUser.getId()).findObject(User.class);
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
      user = mongoDb.createObject("User", user);
    } else {
      // TODO 更新原Token
    }
    return user;
  }

  public User register(QQUser qqUser, UserInfoBean detail) {
    User user = mongoDb.createQuery("User").eq("qzone.id", qqUser.getId()).findObject(User.class);
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
      user = mongoDb.createObject("User", user);
    } else {
      // TODO 更新原Token
    }
    return user;
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
    User user = mongoDb.createQuery("User").eq(property, username).findObject(User.class);
    if (user == null) {
      throw new UsernameNotFoundException("用户不存在");
    }
    return new User.UserWrapper(username, user);
  }
}

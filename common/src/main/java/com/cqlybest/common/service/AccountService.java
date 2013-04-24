package com.cqlybest.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cqlybest.common.bean.Account;
import com.cqlybest.common.dao.AccountDao;

@Service
@Transactional(readOnly = true)
public class AccountService {

  @Autowired
  private AccountDao accountDao;

  /**
   * 增加账号
   * 
   * @param account
   */
  @Transactional(readOnly = false)
  public void add(Account account) {
    accountDao.saveOrUpdate(account);
  }

  /**
   * 删除账号
   * 
   * @param ccount
   */
  @Transactional(readOnly = false)
  public void delete(Account account) {
    accountDao.delete(account);
  }

  /**
   * 获取所有账号
   * 
   * @return
   */
  public List<Account> getAccounts() {
    return accountDao.findAll();
  }

  /**
   * 获取账号
   * 
   * @return
   */
  public Account getAccount(Integer id) {
    return accountDao.findById(id);
  }

  /**
   * 修改账号
   * 
   * @param account
   */
  @Transactional(readOnly = false)
  public void modify(Account account) {
    accountDao.saveOrUpdate(account);
  }


}

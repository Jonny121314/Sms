package com.zhoujianming.www.service;


import java.util.List;

import com.zhoujianming.www.pojo.Account;


/**
 * 多数据源service
 */
public interface AccountService {


    void insert(Account account);

    void createNewDataSource(Integer id,String dbName) throws Exception;

    List<Account> queryAll();
    
    boolean checkTable();
}

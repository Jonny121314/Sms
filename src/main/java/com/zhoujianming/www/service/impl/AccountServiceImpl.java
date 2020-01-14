package com.zhoujianming.www.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhoujianming.www.common.dataSource.utils.DataSourceLoad;
import com.zhoujianming.www.mapper.AccountMapper;
import com.zhoujianming.www.pojo.Account;
import com.zhoujianming.www.service.AccountService;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujianming
 * 创建数据库，并重新加载
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private DataSourceLoad dataSourceManager;

    @Value("${spring.datasource.common.url}")
    private String url;
    @Value("${spring.datasource.common.username}")
    private String username;
    @Value("${spring.datasource.common.password}")
    private String password;
    @Value("${spring.datasource.common.driver-class-name}")
    private String driverClassName;


    @Override
    public void insert(Account account) {
        accountMapper.insert(account);
    }

    /**
     * 创建数据库,并重新加载
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createNewDataSource(Integer id, String dbName) throws Exception {
        Account account = new Account();
        account.setId(id);
        account.setDbName(dbName);
        createDataBase(dbName);
        accountMapper.insert(account);
        dataSourceManager.loadDataSource();
    }

    /**
     * 创建数据库
     */
    private void createDataBase(String dbName) throws Exception {
//        accountMapper.createDatabase(dbName);
        Connection connection = null;
        Statement st = null;
        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            st = connection.createStatement();
			//创建数据库
			String sql="create database "+dbName + " DEFAULT CHARSET utf8 COLLATE utf8_general_ci";
			st.executeUpdate(sql);
        } catch (Exception e) {
            connection.rollback();
            throw e;
        } finally {
            if (st != null) {
                st.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 查询数据库获取所有数据源
     */
    @Override
    public List<Account> queryAll() {
        return accountMapper.select();
    }

	@Override
	public boolean checkTable() {
		boolean result = false;
		List<Map<String, Object>> list = accountMapper.selectDB();
		if (!list.isEmpty()) {
			result = true;
		}
		return result;
	}
}

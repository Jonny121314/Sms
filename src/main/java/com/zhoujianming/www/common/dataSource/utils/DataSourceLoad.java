package com.zhoujianming.www.common.dataSource.utils;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zhoujianming.www.common.dataSource.MybatisConfig;
import com.zhoujianming.www.pojo.Account;
import com.zhoujianming.www.service.AccountService;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始或者重载入数据源
 */
@Component
public class DataSourceLoad {

    @Autowired
    private AccountService accountService;   
    
    @Autowired
    private CustomSqlSessionTemplate sqlSessionTemplate;

    @Autowired
    private MybatisConfig mybatisConfig;

    @PostConstruct
    public void loadDataSource() throws Exception {
        List<Account> accountList = accountService.queryAll();
        Map<Object, SqlSessionFactory> newSqlSessionFactoryMap = new HashMap<>(50);
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = sqlSessionTemplate.getTargetSqlSessionFactorys();
        for (Account account : accountList) {
            SqlSessionFactory sqlSessionFactory = mybatisConfig.createSqlSessionFactory(mybatisConfig.getDataSource(account.getDbName()));
            newSqlSessionFactoryMap.put(account.getDbName(), sqlSessionFactory);
        }
        newSqlSessionFactoryMap.putAll(sqlSessionFactoryMap);
        this.sqlSessionTemplate.setTargetSqlSessionFactorys(newSqlSessionFactoryMap);
    }

}

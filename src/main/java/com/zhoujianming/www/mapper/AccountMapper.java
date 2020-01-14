package com.zhoujianming.www.mapper;

import org.apache.ibatis.annotations.Param;

import com.zhoujianming.www.pojo.Account;

import java.util.List;
import java.util.Map;

/**
 * @author zhoujianming
 * 多数据源mapper
 */
public interface AccountMapper {

    void insert(Account account);

    List<Account> select();

    void createDatabase(@Param("dbName") String dbName);

	List<Map<String, Object>> selectDB();
}

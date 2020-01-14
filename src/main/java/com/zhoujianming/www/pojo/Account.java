package com.zhoujianming.www.pojo;

/**
 * @author zjm
 * 数据源实体类
 */
public class Account {
	
    private int id;
    
    private String dbName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}

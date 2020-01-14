package com.zhoujianming.www.common.dataSource.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 解决多线程访问全局变量的问题
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static List<String> dataSourceKeys = new ArrayList<>();

    /**
     * 设置当前数据库。
     * @param dbType
     */
    public static void set(String dbType) {
        contextHolder.set(dbType);
        System.out.println("设置当前数据源为：{}"+dbType);
    }

    public static void setDefaultDataSource() {
    }

    /**
     * 获取当前使用的数据源
     */
    public static String get() {
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear() {
        contextHolder.remove();
        System.out.println("移除数据源");
    }

    /**
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId) {
        return dataSourceKeys.contains(dataSourceId);
    }

}

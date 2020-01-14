package com.zhoujianming.www.common.dataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.zhoujianming.www.common.dataSource.utils.DataSourceContextHolder;

/**
 * 获取当前数据源名称
 */
public class RoutingDataSource extends AbstractRoutingDataSource{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected Object determineCurrentLookupKey() {
        logger.info("当前数据源为：{}"+ DataSourceContextHolder.get());
        return DataSourceContextHolder.get();
	}
	

}

package com.zhoujianming.www.common.dataSource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.zhoujianming.www.common.dataSource.utils.DataSourceContextHolder;


/**
 *数据源切换切面类
 */
@Aspect
@Order(-1)
@Component
public class DataSourceBindAspect {

	Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.zhoujianming.www.service.impl.*.*(..))")
    public void pointCut() {
    }

    @Before("@annotation(targetDataSource)")
    public void doBefore(JoinPoint joinPoint, TargetDataSource targetDataSource) throws Throwable {
		String dataSourceKey = targetDataSource.dataSourceKey();
		if (dataSourceKey != DataSourceKeyEnum.DB_COMMON) {
			logger.info(String.format("设置数据源为  %s", dataSourceKey));
			DataSourceContextHolder.set(dataSourceKey);
		} else {
			logger.info(String.format("使用默认数据源  %s", DataSourceKeyEnum.DB_COMMON));
			DataSourceContextHolder.set(DataSourceKeyEnum.DB_COMMON);
		}
    }
}

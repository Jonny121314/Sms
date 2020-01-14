package com.zhoujianming.www.common.dataSource;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 使用AOP自定义注解的方式,切换数据注解 可以用于类或者方法级别 方法级别优先级 > 类级别
 * @Author zjm
 */
@Target({ElementType.METHOD, ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TargetDataSource {
	String dataSourceKey() default DataSourceKeyEnum.DB_COMMON;//默认使用公共数据库
}

package com.zhoujianming.www.common.dataSource;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.zhoujianming.www.common.dataSource.utils.CustomSqlSessionTemplate;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zjm
 * mybatis配置
 */

@Configuration
@Component
@MapperScan(basePackages = MybatisConfig.BASE_PACKAGE, sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig extends AbstractDataSourceConfig {

    //mapper模式下的接口层
    static final String BASE_PACKAGE = "com.zhoujianming.www.mapper";
    //对接数据库的实体层
    static final String ALIASES_PACKAGE = "com.zhoujianming.www.pojo";
    static final String MAPPER_LOCATION = "classpath:com/zhoujianming/www/mapper/*.xml";

    //创建新数据源时的配置信息
    @Value("${spring.datasource.template.url}")
    private String url;
    @Value("${spring.datasource.template.username}")
    private String username;
    @Value("${spring.datasource.template.password}")
    private String password;
    @Value("${spring.datasource.template.driver-class-name}")
    private String driverClassName;


    @Primary
    @Bean(name = "dataSourceSystem")
    public DataSource dataSourceOne(Environment env) {
        String prefix = "spring.datasource.common.";
        return getDataSource(env, prefix, DataSourceKeyEnum.DB_COMMON);
    }

    @Bean(name = "sqlSessionFactorySystem")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceSystem") DataSource dataSource)
            throws Exception {
        return createSqlSessionFactory(dataSource);
    }


    @Bean(name = "sqlSessionTemplate")
    public CustomSqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactorySystem") SqlSessionFactory factorySystem) throws Exception {
        Map<Object, SqlSessionFactory> sqlSessionFactoryMap = new HashMap<>();
        sqlSessionFactoryMap.put(DataSourceKeyEnum.DB_COMMON, factorySystem);
        CustomSqlSessionTemplate customSqlSessionTemplate = new CustomSqlSessionTemplate(factorySystem);
        customSqlSessionTemplate.setTargetSqlSessionFactorys(sqlSessionFactoryMap);
        return customSqlSessionTemplate;
    }

    /**
     * 创建数据源
     *
     * @param dataSource
     * @return
     */
    public SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setVfs(SpringBootVFS.class);
        bean.setTypeAliasesPackage(ALIASES_PACKAGE);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MAPPER_LOCATION));
        bean.setConfiguration(configuration());
        return bean.getObject();
    }

    public org.apache.ibatis.session.Configuration configuration() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        return configuration;
    }

    public DataSource getDataSource(String dataSourceName) {
        Properties prop = new Properties();
        prop.put("url", this.url.replace("{dbName}", dataSourceName));
        prop.put("username", this.username);
        prop.put("password", this.password);
        prop.put("driverClassName", this.driverClassName);
        AtomikosDataSourceBean ds = new AtomikosDataSourceBean();
        ds.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
        ds.setUniqueResourceName(dataSourceName);
        ds.setXaProperties(prop);
        return ds;
    }
}

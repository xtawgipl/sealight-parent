package com.osram.osram.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.sealight.page.dialect.DialectType;
import com.sealight.page.interceptor.PageInterceptor;
import com.sealight.page.starter.PageProterties;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
	
//	private Logger logger = LoggerFactory.getLogger(ItmsDataSourceConfig.class);
	
	// 精确到 itms 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.tydic.traffic.mapper";
    static final String MAPPER_LOCATION = "classpath:mapper/*.xml";
    
	@Primary
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix="datasource")
	public DataSource dataSource() {
        return new DruidDataSource();
	}

	@Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager itmsTransactionManager(@Qualifier("dataSource") DataSource itmsDataSource) {
        return new DataSourceTransactionManager(itmsDataSource);
    }
	
	@Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource itmsDataSource,
                                                   @Qualifier("pageProterties") PageProterties pageProterties) throws Exception {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("dialect", DialectType.MYSQL.getDialectName());
        properties.setProperty("count.isCount", String.valueOf(pageProterties.getCount().isCount()));
        properties.setProperty("count.expireAfterAccess", String.valueOf(pageProterties.getCount().getExpireAfterAccess()));
        properties.setProperty("count.maximumSize", String.valueOf(pageProterties.getCount().getMaximumSize()));
        pageInterceptor.setProperties(properties);
	    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(itmsDataSource);
        sessionFactory.setPlugins(new Interceptor[] { pageInterceptor });
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}






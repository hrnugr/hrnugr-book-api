package com.harunugur.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment environment;

    public DataSource standardDs(){
        DataSourceBuilder<HikariDataSource> dataSourceBuilder =
                DataSourceBuilder.create().type(HikariDataSource.class);

        dataSourceBuilder.driverClassName(environment.getProperty("spring.datasource.driver-class-name"));
        dataSourceBuilder.url(environment.getProperty("spring.datasource.url"));
        dataSourceBuilder.username(environment.getProperty("spring.datasource.username"));
        dataSourceBuilder.password(environment.getProperty("spring.datasource.password"));

        HikariDataSource dataSource = (HikariDataSource) dataSourceBuilder.build();
        dataSource.setMaximumPoolSize(Integer.valueOf(environment.getProperty("spring.datasource.configuration.maximum-pool")));
        dataSource.setMinimumIdle(1);
        dataSource.setIdleTimeout(6000000);
        dataSource.setConnectionTimeout(10000);

        return dataSource;
    }
}

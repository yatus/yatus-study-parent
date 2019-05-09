package cn.yatus.chapter1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slave1DataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean
    public DataSource routeDataSource(DataSource masterDataSource,DataSource slave1DataSource) {
        Map<Object, Object> dataSources = new HashMap<>();
        dataSources.put(DSRouteTypeEnum.MASTER,masterDataSource);
        dataSources.put(DSRouteTypeEnum.SLAVE,slave1DataSource);

        MasterSlaveRoutingDataSource dataSource = new MasterSlaveRoutingDataSource();
        dataSource.setDefaultTargetDataSource(masterDataSource);
        dataSource.setTargetDataSources(dataSources);

        return dataSource;
    }

}

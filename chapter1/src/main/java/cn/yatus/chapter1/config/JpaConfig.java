package cn.yatus.chapter1.config;

import cn.yatus.chapter1.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {
    @Autowired
    private JpaProperties jpaProperties;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource routeDataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        // 是否生成表
        vendorAdapter.setGenerateDdl(jpaProperties.isGenerateDdl());
        // 是否显示sql语句
        vendorAdapter.setShowSql(jpaProperties.isShowSql());
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        // 配置扫描的位置
        factory.setPackagesToScan(App.class.getPackage().getName());
        // 这个数据源设置为代理的数据源，----这是关键性配置！！！
        factory.setDataSource(routeDataSource);
        return factory;
    }


    @Bean
    public JpaTransactionManager transactionManager(DataSource routeDataSource,
                                                    LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setDataSource(routeDataSource);
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());
        return transactionManager;
    }

}

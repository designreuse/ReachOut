package com.bluntsoftware.ReachOut.modules.user_manager.config;

import com.genx.framework.jpa.repository.DefaultRepositoryFactoryBean;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration( "user_manager_config")
@EnableTransactionManagement
@PropertySource({ "classpath:reachout.jdbc.properties" })
@EnableJpaRepositories(entityManagerFactoryRef = "user_manager_EntityManagerFactory",
                         transactionManagerRef = "user_manager_TransactionManager",
                                  basePackages = {"com.bluntsoftware.ReachOut.modules.user_manager.repository"},
                    repositoryFactoryBeanClass = DefaultRepositoryFactoryBean.class )
public class PersistenceConfig {
    @Autowired
    private Environment env;

    public PersistenceConfig() {
        super();
    }
    
    @Qualifier("user_manager")
    @Bean(name="user_manager_EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan( "com.bluntsoftware.ReachOut.modules.user_manager.domain" );
        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }
    
    @Qualifier("user_manager")
    @Bean(name="user_manager_DataSource")
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.reachout.driverClassName")));
        dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.reachout.url")));
        dataSource.setUsername(Preconditions.checkNotNull(env.getProperty("jdbc.reachout.username")));
        dataSource.setPassword(Preconditions.checkNotNull(env.getProperty("jdbc.reachout.password")));
        return dataSource;
    }
    
    @Qualifier("user_manager")
    @Bean(name="user_manager_TransactionManager")
    public PlatformTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
    @Qualifier("user_manager")
    @Bean(name="user_manager_ExceptionTranslation")
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.reachout.hbm2ddl.auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.reachout.dialect"));
        hibernateProperties.setProperty("hibernate.ejb.entitymanager_factory_name","user_manager_EntityManagerFactory");

return hibernateProperties;
    }

}

package com.socialblackjack.dao.config;

/**
 * Created by javier on 27/10/15.
 */

import com.socialblackjack.dao.GameTableDao;
import com.socialblackjack.dao.impl.GameTableDaoImpl;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.socialblackjack.dao")
@EnableTransactionManagement
//@ImportResource("classpath:/GameConfiguration.xml")
public class DaoConfig {

    @Bean
    public Resource configurationLocation() {
        return new PathResource("src/test/resources/hibernate-table.cfg.xml");
    }

    @Bean
    public GameTableDao gameTableDao() {
        return new GameTableDaoImpl();
    }

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/blackjack");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        return dataSource;
    }

    @Bean
    public SessionFactory sessionFactory() {

        org.hibernate.cfg.Configuration configuration = new LocalSessionFactoryBuilder(dataSource());
        configuration.configure();

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL82Dialect");
//        configuration.setProperty("hibernate.current_session_context_class", "thread");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager hibernateTransactionManager = new HibernateTransactionManager();
        hibernateTransactionManager.setDataSource(dataSource());
        hibernateTransactionManager.setSessionFactory(sessionFactory());
        return hibernateTransactionManager;
    }

}

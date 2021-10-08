package com.github.sharifulineugene.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("com.github.sharifulineugene")
@EnableWebMvc
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {
    private final Environment environment;
    private final ApplicationContext context;

    @Autowired
    public SpringConfig(Environment environment, ApplicationContext context) {
        this.environment = environment;
        this.context = context;
    }

    HikariConfig hikariConfig (){
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("db.driver.name"));
        //String db_url = environment.getProperty();
        hikariConfig.setJdbcUrl(environment.getProperty("db.testUrl"));
        return hikariConfig;
    }

    @Bean
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());

    }


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.github.sharifulineugene.entity");
        Properties props = new Properties();
        props.setProperty("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        props.setProperty("hibernate.show_sql", "true");
        sessionFactory.setHibernateProperties(props);
        return sessionFactory;
    }

    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public ModelMapper mapper() {
        return new ModelMapper();
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:changelog.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

}

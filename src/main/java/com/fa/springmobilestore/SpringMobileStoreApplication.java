package com.fa.springmobilestore;

import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration(exclude = { //  
    DataSourceAutoConfiguration.class, //
    DataSourceTransactionManagerAutoConfiguration.class, //
    HibernateJpaAutoConfiguration.class})

@ComponentScan(basePackages = {"com.fa.springmobilestore.*", "com.fa.springmobilestore"})

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class SpringMobileStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMobileStoreApplication.class, args);
    }
    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getProperty(EnvironmentProperty //
                .DS_DRIVER_CLASS_NAME.getString()));
        dataSource.setUrl(env.getProperty(EnvironmentProperty //
                .DS_URL.getString()));
        dataSource.setUsername(env.getProperty(EnvironmentProperty //
                .DS_USERNAME.getString()));
        dataSource.setPassword(env.getProperty(EnvironmentProperty //
                .DS_PASSWORD.getString()));

        System.out.println("## getDataSource: " + dataSource);
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
        Properties properties = new Properties();

        properties.put(EnvironmentProperty.HB_DIALECT,
                env.getProperty(EnvironmentProperty //
                        .JPA_DIALECT.getString()));
        properties.put(EnvironmentProperty.HB_SHOW_SQL,
                env.getProperty(EnvironmentProperty //
                        .JPA_SHOW_SQL.getString()));
        properties.put(EnvironmentProperty.HB_CURRENT_SESSION_CONTEXT_CLASS,
                env.getProperty(EnvironmentProperty //
                        .JPA_CURRENT_SESSION_CONTEXT_CLASS.getString()));

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setPackagesToScan(new String[]{"com.fa.springmobilestore.entity"});
        factoryBean.setDataSource(dataSource);
        factoryBean.setHibernateProperties(properties);
        factoryBean.afterPropertiesSet();

        SessionFactory sessionFactory = factoryBean.getObject();
        System.out.println("## getSessionFactory: " + sessionFactory);
        return sessionFactory;
    }

    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

}

enum EnvironmentProperty {
    DS_DRIVER_CLASS_NAME("spring.datasource.driver-class-name"),
    DS_URL("spring.datasource.url"),
    DS_USERNAME("spring.datasource.username"),
    DS_PASSWORD("spring.datasource.password"),
    HB_DIALECT("hibernate.dialect"),
    HB_SHOW_SQL("hibernate.show_sql"),
    HB_CURRENT_SESSION_CONTEXT_CLASS("current_session_context_class"),
    JPA_DIALECT("spring.jpa.properties.hibernate.dialect"),
    JPA_SHOW_SQL("spring.jpa.show-sql"),
    JPA_CURRENT_SESSION_CONTEXT_CLASS //
            ("spring.jpa.properties.hibernate.current_session_context_class");

    private String envProperty;

    private EnvironmentProperty(String envProperty) {
        this.envProperty = envProperty;
    }

    public String getString() {
        return this.envProperty;
    }
}

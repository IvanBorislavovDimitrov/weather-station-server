package com.ivan.weather.station.persistence.session;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ivan.weather.station.persistence.entity.*;

@Configuration
public class SessionFactoryBean {

    @Bean
    public SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.setProperty("connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/weather_station");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "123456");
        properties.setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ManagedSessionContext");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
        configuration.addAnnotatedClass(AnomalyDetectionRule.class);
        configuration.addAnnotatedClass(TemperatureAnomalyDetectionRule.class);
        configuration.addAnnotatedClass(SubscribedEmail.class);
        configuration.addAnnotatedClass(HumidityAnomalyDetectionRule.class);
        configuration.addAnnotatedClass(Raspberry.class);
        configuration.addAnnotatedClass(Measurement.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        configuration.addAnnotatedClass(PowerPlug.class);
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        configuration.setProperties(properties);
        standardServiceRegistryBuilder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}

package com.drainshawty.lab1.config;

import jakarta.persistence.EntityManagerFactory;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT9S")
public class SchedulingConfig {

    @Bean(name = "shedDataSource")
    @ConfigurationProperties(prefix = "spring.sheddb")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LockProvider lockProvider(@Qualifier("shedDataSource") DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }
}

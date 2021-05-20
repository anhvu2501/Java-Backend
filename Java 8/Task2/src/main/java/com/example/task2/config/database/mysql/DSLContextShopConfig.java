package com.example.task2.config.database.mysql;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DSLContextShopConfig {
    private final HikariDataSource dataSource;

    public DSLContextShopConfig(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public DSLContext dslContext() {
        Settings settings = new Settings().withRenderSchema(false);
        return DSL.using(dataSource, SQLDialect.MYSQL, settings);
    }
}

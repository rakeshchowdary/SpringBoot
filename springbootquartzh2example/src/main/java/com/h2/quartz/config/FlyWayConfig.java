package com.h2.quartz.config;

import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlyWayConfig {
	public static final String FLYWAY = "flyway";
	@Autowired
    private DataSource dataSource;
	@Bean(name = FLYWAY)
    public Flyway dataSource() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(dataSource);
        flyway.migrate();
        return flyway;
    }

}

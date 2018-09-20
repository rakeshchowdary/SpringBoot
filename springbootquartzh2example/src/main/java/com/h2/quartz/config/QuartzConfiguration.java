package com.h2.quartz.config;

import javax.sql.DataSource;

import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@DependsOn(FlyWayConfig.FLYWAY)
public class QuartzConfiguration {
	@Bean
	public SchedulerFactoryBean schedularFactory(PlatformTransactionManager transactionManager,
			ApplicationContext applicationContext, DataSource dataSource,
			@Value("${quartz.properties.file}") Resource quartzProperties) {

		SchedulerFactoryBean factory = new SchedulerFactoryBean();
		factory.setApplicationContextSchedulerContextKey("applicationContext");
		factory.setJobFactory(jobFactory(applicationContext));
		factory.setTransactionManager(transactionManager);
		factory.setWaitForJobsToCompleteOnShutdown(true);
		factory.setConfigLocation(quartzProperties);
		factory.setDataSource(dataSource);
		return factory;

	}

	@Bean
	public JobFactory jobFactory(ApplicationContext applicationContext) {
		AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

}

package com.nusecond.suredeal.microservice.notification.config;

/**
 * @author Krishnamorthi Palanisamy
 *
 */
import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
// TODO: Auto-generated Javadoc

/**
 * The Class NotificationConfiguration.
 */
@Configuration
public class NotificationConfiguration {

	
	/** The environment. */
	@Inject
	private Environment environment;

	
	
	/**
	 * Retry template.
	 *
	 * @return the retry template
	 */
	@Bean
	public RetryTemplate retryTemplate() {
		RetryTemplate retryTemplate = new RetryTemplate();
		SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
		// how many attempts
		simpleRetryPolicy.setMaxAttempts(Integer.valueOf(environment.getProperty("spring.retry.attempt")));

		FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();

		// how much time (in milliseconds) before next attempt
		fixedBackOffPolicy.setBackOffPeriod(Integer.valueOf(environment.getProperty("spring.retry.waittime")));

		// setting the values
		retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
		retryTemplate.setRetryPolicy(simpleRetryPolicy);
		return retryTemplate;

	}
}

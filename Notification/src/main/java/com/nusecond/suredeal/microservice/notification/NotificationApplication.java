package com.nusecond.suredeal.microservice.notification;

/**
 * @author Krishnamorthi Palanisamy
 *
 */
import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.google.android.gcm.server.Sender;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationApplication.
 */
@ComponentScan
@SpringBootApplication
public class NotificationApplication {

	/** The environment. */
	@Inject
	private Environment environment;

	static Logger log = Logger.getLogger(NotificationApplication.class.getName());


	/**
	 * Notification Micro service Start method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
		log.info("Notification Micro Service Started");

	}

	/**
	 * Sender Bean .
	 *
	 * @return the sender
	 */
	@Bean
	public Sender sender() {
		return new Sender(environment.getProperty("spring.gcm.appKey"));
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/notify").allowedOrigins("http://localhost:9007");
            }
        };
       }

}

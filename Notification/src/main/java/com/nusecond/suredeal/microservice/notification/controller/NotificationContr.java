package com.nusecond.suredeal.microservice.notification.controller;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NotificationContr {
	
	/** The log. */
	static Logger log = Logger.getLogger(NotificationController.class.getName());
	
	private RestTemplate restTemplate = new TestRestTemplate();
	
	/** The environment. */
	@Inject
	private Environment environment;
	
	/*
	 * consumer notification
	 */
	@RequestMapping(value="/notification",method=RequestMethod.GET)
	public String consumerNotification(@RequestParam Integer consumerId){
		String url = environment.getProperty("spring.consumer.notification");
		log.info("====url====" + url);
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class,consumerId);
		return response.getBody();
	}
	
}

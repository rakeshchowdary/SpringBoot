/**
 * 
 */
package com.nusecond.suredeal.microservice.notification.service;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author krishna
 *
 */
@Service
public class SolutionInfiServericeProvider {

	static Logger log = Logger.getLogger(SolutionInfiServericeProvider.class.getName());
	private RestTemplate restTemplate = new TestRestTemplate();
	@Inject
	private Environment environment;
	
	public Boolean sendMessage(String to,String message)
	{
		
		String apiKey=environment.getProperty("APIkey");
		
		String sender=environment.getProperty("sender");
		
		log.info("--------Mobile num--------------" + to);
		String url="http://api-alerts.solutionsinfini.com/v3/?method=sms&api_key="+apiKey+"&to="+to+"&sender="+sender+"&message="+message;
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		try{
			HttpEntity entity = new HttpEntity( headers);
			ResponseEntity<String> rateResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
					 new ParameterizedTypeReference<String>() {
			} );
			System.out.println(rateResponse.getStatusCode());
			System.out.println(rateResponse.getBody());
			return true;
		}catch (Exception e) {
			log.error("---Error---" + e.getMessage());
			return false;
		}			
		
	}
}

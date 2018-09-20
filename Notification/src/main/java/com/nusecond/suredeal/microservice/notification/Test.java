package com.nusecond.suredeal.microservice.notification;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		RestTemplate restTemplate=new RestTemplate();
		
String apiKey="Adbbf63e1393d7cc0fa76c9f2f960a17b";
		
		String sender="NUKART";
		
		
		String url="http://api-alerts.solutionsinfini.com/v3/?method=sms&api_key="+apiKey+"&to="+"919500538670"+"&sender="+sender+"&message="+"testing";
		
		
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity entity = new HttpEntity( headers);
		ResponseEntity<String> rateResponse = restTemplate.exchange(url, HttpMethod.GET, entity,
				 new ParameterizedTypeReference<String>() {
		} );
		System.out.println(rateResponse);
		
	//	http://api-alerts.solutionsinfini.com/v3/?method=sms.json&api_key=Ad9e5XXXXXXXXXXXXX&format=xml&json=<Your JSON_data or JSON_url>
	}

}

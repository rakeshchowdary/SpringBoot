package com.nusecond.suredeal.microservice.notification.service;

import org.apache.log4j.Logger;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;

/**
 * Created by karun on 20/1/17.
 */
@Service
public class ClickToCall implements  CallService{

    static Logger log = Logger.getLogger(SolutionInfiServericeProvider.class.getName());
    private RestTemplate restTemplate = new TestRestTemplate();
    @Inject
    private Environment environment;
    @Override
    public Boolean clickToCall(String customer, String retailer) {

        String apiKey=environment.getProperty("clicktocall");



        log.info("retailer phone-- :" +retailer);
        
        log.info("--------consumer num--------------" + customer);
        String url="http://voice.solutionsinfini.com/api/v1/index.php?api_key="+apiKey+"&method=dial.click2call&format=json&receiver="+customer+"&caller="+retailer;
       
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            HttpEntity entity = new HttpEntity( headers);
            ResponseEntity<String> rateResponse = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<String>() {
                    } );
            System.out.println("status code"   + rateResponse.getStatusCode());
            System.out.println("response body"  + rateResponse.getBody());
            return true;
        }catch (Exception e) {
            log.error("---Error---" + e.getMessage());
            return false;
        }
    }
}

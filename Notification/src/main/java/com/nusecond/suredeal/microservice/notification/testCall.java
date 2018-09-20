package com.nusecond.suredeal.microservice.notification;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class testCall{
	
	public static void main(String [] args){
		RestTemplate restTemplate=new RestTemplate();
		String customer="8970762460";
    	String retailer="9500538670";
    	 String apiKey="A96dd00fda388654a93cd639e94726b76";
         System.out.println("--------consumer num--------------" + customer);
         String url="http://voice.solutionsinfini.com/api/v1/index.php?api_key="+apiKey+"&method=dial.click2call&format=json&receiver="+customer+"&caller="+retailer;
        System.out.println("--------url-----" + url);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        try{
            HttpEntity entity = new HttpEntity( headers);
            ResponseEntity<String> rateResponse = restTemplate.exchange(url, HttpMethod.GET, entity,
                    new ParameterizedTypeReference<String>() {
                    } );
            System.out.println(rateResponse.getStatusCode());
            System.out.println(rateResponse.getBody());
        }catch (Exception e) {
        	System.out.println("---Error---" + e.getMessage());
        }
	}

}

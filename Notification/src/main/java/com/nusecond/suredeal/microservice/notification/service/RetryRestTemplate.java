package com.nusecond.suredeal.microservice.notification.service;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Krishnamorthi Palanisamy
 *
 */
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;

// TODO: Auto-generated Javadoc
/**
 * The Class RetryRestTemplate.
 */
@Service
public class RetryRestTemplate {
	

	/** The log. */
	static Logger log = Logger.getLogger(RetryRestTemplate.class.getName());

	/** The retry template. */
	@Autowired
	RetryTemplate retryTemplate;
	
	/** The rest template. */
	private RestTemplate restTemplate = new TestRestTemplate();
	
	
	/**
	 * Consumer rest retry.
	 *
	 * @param urls the urls
	 * @param key the key
	 * @return the consumer
	 * @throws Exception the exception
	 */
	public Consumer consumerRestRetry(String urls,Integer key) throws Exception

	{
		
		final String url=urls;
		final Integer id=key;
		
		try{
		 return retryTemplate.execute(new RetryCallback<Consumer, Exception>() {

			@Override
			public Consumer doWithRetry(RetryContext arg0) throws Exception {
				
				
				try
				{
					
					log.info("Try number: "+arg0.getRetryCount()+""
					   		+ " attempt number: ");
				
					ResponseEntity<Consumer> con= restTemplate.getForEntity(url, Consumer.class, id);
				return con.getBody();
				}
				
				
				catch(ResourceAccessException e)
				{
					throw new Exception(e.getMessage());
				}
				
				catch(RestClientException e)
				{
					throw new Exception(e.getMessage());
				}
				
			}
		});
		}
		catch(Exception e)
		{
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		
	}
}

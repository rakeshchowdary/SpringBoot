package com.nusecond.suredeal.microservice.notification.service;
/**
 * @author Krishnamorthi Palanisamy
 *
 */

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.android.gcm.server.InvalidRequestException;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.nusecond.suredeal.microservice.notification.Data;
import com.nusecond.suredeal.microservice.notification.MessageLocal;
import com.nusecond.suredeal.microservice.notification.Notification;
import com.nusecond.suredeal.microservice.notification.campaign.model.Campaign;
import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;
import com.nusecond.suredeal.microservice.notification.consumer.model.Key;
import com.nusecond.suredeal.microservice.notification.consumer.model.NotifyPojo;
import com.nusecond.suredeal.microservice.notification.model.NotificationModel;
import com.nusecond.suredeal.microservice.notification.order.OrderManagement;
import com.nusecond.suredeal.microservice.notification.order.Product;
import com.nusecond.suredeal.microservice.notification.retailer.model.Retailer;
import com.nusecond.suredeal.microservice.notification.voucher.model.Voucher;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationService.
 */
@Service
public class NotificationService {

	/** The sender. */
	@Autowired
	Sender sender;

	/** The environment. */
	@Inject
	private Environment environment;
	/** The log. */
	static Logger log = Logger.getLogger(NotificationService.class.getName());
	
	private RestTemplate restTemplate = new TestRestTemplate();

	/**
	 * Push notification.
	 *
	 * @param gcmRegId
	 *            the gcm reg id
	 * @param msg
	 *            the msg
	 * @return true, if successful
	 */
	public boolean pushNotification(String gcmRegId, String msg) {

		log.info("Push Notification to Mobile App");
		String ret = environment.getProperty("spring.gcm.retry");

		final int retries = Integer.parseInt(ret);

		Message message = new Message.Builder().addData("notification", "nuKart App Notification")
				.addData("message", msg).build();

		try {

			Result result = sender.send(message, gcmRegId, retries);

			/**
			 * if you want to send to multiple then use below method
			 * send(Message message, List<String> regIds, int retries)
			 **/

			if (StringUtils.isEmpty(result.getErrorCodeName())) {

				log.info(result.toString());

				return true;
			}
			log.info(result.getErrorCodeName());

			// System.out.println("Error occurred while sending push
			// notification :" + result.getErrorCodeName());

		} catch (InvalidRequestException e) {
			log.info("Invalid Request" + e.getMessage());

		} catch (IOException e) {
			log.info("IO Exception" + e.getMessage());

		}
		return false;

	}
	
	/*
	 * order list
	 */
	
	 public List<OrderManagement> getNotification(Integer consumerId)
	   {
	       String url=environment.getProperty("spring.consumer.orderlist");
	       HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	       log.info("url==========="+url);
	       try{
	           log.info("-getNotification---Entered-------");
	           HttpEntity entity = new HttpEntity(headers);
	           ResponseEntity<List<OrderManagement>> rateResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
	                     new ParameterizedTypeReference<List<OrderManagement>>() {
	            },consumerId);
	           log.info("-getNotification---Done-------");
	           return rateResponse.getBody();
	       }
	       catch(Exception e){
	           log.error("-------getNotification---Error-------" + e.getMessage());
	       }        
	       return null;
	   }
	/*
	 * Firebase Notification Service
	 */	
	public void firebaseNotification(@RequestBody (required = false) NotifyPojo note){
		//log.info("------------Notification Called------"+note.getNotifyToken()+"--------");
		try{
			String url="https://fcm.googleapis.com/fcm/send";
			
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "key=" + "AIzaSyCM7KeXhkOefZIAawda5ZSM9MyZgwknIK8");// "AIzaSyDfHOiXVPg5dlCHLDFYAKy3_YRatYWb0IA");
			MessageLocal local=new MessageLocal();			
			Notification notification=new Notification();
			Data data = new Data();
			
			URL iconURL = new URL("https://suredeal.co/images/nukartlogo.png");
			
			notification.setText("Hi Arpitha");
			notification.setTitle("Group Notification");
			
			data.setScore("Score");
			notification.setIcon("https://suredeal.co/images/nukartlogo.png");
			
			local.setNotification(notification);
			local.setData(data);
			local.setTo("/topics/ABCD");
			
			HttpEntity<MessageLocal> entity = new HttpEntity<MessageLocal>(local, headers);
			ResponseEntity<String> rateResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
					 new ParameterizedTypeReference<String>() {
			} );
			log.info("------------Notification Sent--------------");
		}catch(Exception e){
			log.error("Failed to send message"+e.getMessage());
		}
	}
	
	/*
	 * consumer orders list
	 */
	public List<OrderManagement> consumerOrdersList(String consumerId){
		String url = environment.getProperty("spring.consumer.getNotification");
		try{
			log.info("----Entered-------");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			
			NotificationModel notificationModel = new NotificationModel();
			notificationModel.setUserId(consumerId);
			
			HttpEntity<NotificationModel> entity = new HttpEntity<NotificationModel>(notificationModel,headers);
			ResponseEntity<List<OrderManagement>> rateResponse = restTemplate.exchange(url, HttpMethod.POST, entity,
					 new ParameterizedTypeReference<List<OrderManagement>>() {
			} );
			log.info("-----------Success--------------");
			return rateResponse.getBody();
		}catch(Exception e){
			log.error("------Orders List----------" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * consumer email list
	 */
	public List<Key> emailContact(){
		String url = environment.getProperty("spring.consumerMS1.url");
		try{
			log.info("----Entered-------");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity<>(headers);
			ResponseEntity<List<Key>> rateResponse = restTemplate.exchange(url, HttpMethod.GET, entity,
					new ParameterizedTypeReference<List<Key>>() {
					});
			log.info("----Completed-------");
			return rateResponse.getBody();
		}
		catch(Exception e){
			log.error("Exception" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * consumer
	 */
	public Consumer consumerDetails(Integer id){
		String url = environment.getProperty("spring.consumerMS.url");
		try{
			log.info("----Entered----consumer service---");
			
			ResponseEntity<Consumer> response = restTemplate.getForEntity(url, Consumer.class, id);
			log.info("----Completed---consumer service----" + response.getBody().getKey().getPrimary() + url);
			return response.getBody();
		}
		catch(Exception e){
			log.error("Consumer service Exception" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * voucher
	 */
	public Voucher voucherDetails(Integer id){
		String url = environment.getProperty("voucher.url");
		try{
			log.info("----Vocher service-Started-----");
			ResponseEntity<Voucher> responseEntity = restTemplate.getForEntity(url, Voucher.class,id);
			//log.info("---Vouher details---" + responseEntity.getBody().getId() + responseEntity.getBody().getCode());
			log.info("----Vocher service- Completed-------");
			return responseEntity.getBody();
		}catch(Exception e){
			log.error("Voucher Service Exception" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * Retailer
	 */
	public Retailer retailerDetails(Integer id){
		String url = environment.getProperty("spring.retailerMS.url");
		try{
			log.info("----Entered----Retailer---"  + url);
			ResponseEntity<Retailer> response = restTemplate.getForEntity(url, Retailer.class, id);
			log.info("----Completed--Retailer-----" + url);
			return response.getBody();
		}
		catch(Exception e){
			log.error("Exception" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * Campaign
	 */
	
	public Campaign campaignDetails(Integer id){
		String url = environment.getProperty("spring.campaignMS.url");
		try{
			log.info("----Calling Campaign Service-------");
			ResponseEntity<Campaign> response = restTemplate.getForEntity(url, Campaign.class,id);
			log.info("----Campaign Service Executed-------");
			return response.getBody();
		}catch(Exception e){
			log.error("Exception" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * Product
	 */
	
	public Product productDetails(Integer id){
		String url = environment.getProperty("spring.productMS.url");
		try{
			log.info("----Calling Product Service-------");
			ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class,id);
			log.info("----Product Service Executed-------" + url);
			return response.getBody();
		}catch(Exception e){
			log.error("Exception" + e.getMessage());
		}
		return null;
	}

	
	public Integer getTotalPoints(Integer integer){
		String url=environment.getProperty("spring.consumer.getTotal");
		System.out.println(url);
		try
		{
			log.info("----Called-------");
			log.info("entered get total points");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity entity = new HttpEntity<>(headers);
			ResponseEntity<Integer> getPoint=restTemplate.getForEntity(url, Integer.class,integer);
			
			log.info("----Completed-------");
			return getPoint.getBody();
		}
		catch(Exception e){
			log.error("Exception" + e.getMessage());
		}
		return null;
	}

	
}

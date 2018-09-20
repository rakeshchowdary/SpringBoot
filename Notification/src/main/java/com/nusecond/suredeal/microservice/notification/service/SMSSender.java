package com.nusecond.suredeal.microservice.notification.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.google.gson.Gson;
import com.nusecond.suredeal.microservice.notification.campaign.model.Campaign;
import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;
import com.nusecond.suredeal.microservice.notification.consumer.model.Key;
import com.nusecond.suredeal.microservice.notification.consumer.model.NotifyPojo;
import com.nusecond.suredeal.microservice.notification.model.ClientRespose;
import com.nusecond.suredeal.microservice.notification.order.Offer;
import com.nusecond.suredeal.microservice.notification.order.OrderManagement;
import com.nusecond.suredeal.microservice.notification.order.Product;
import com.nusecond.suredeal.microservice.notification.retailer.model.Retailer;
import com.nusecond.suredeal.microservice.notification.voucher.model.Voucher;

// TODO: Auto-generated Javadoc
/**
 * The Class SMSSender.
 */
@Service
public class SMSSender {
	
	/** The log. */
	static Logger log = Logger.getLogger(SMSSender.class.getName());
	
	/** The rest template. */
	private RestTemplate restTemplate = new TestRestTemplate();
	
	/** The environment. */
	@Inject
	private Environment environment;
	
	@Autowired
	MessageService messageService;
	/** The notification service. */
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	SolutionInfiServericeProvider soltionInfo;
	
	@Autowired
	SmsServiceProvider smsProvider;
	@Autowired
	CallService callService;

	/** The gson. */
	Gson gson = new Gson();
	

	public Boolean sendOTP(String to,String token)
	{
			
			String body=environment.getProperty("message-body");
			
			return messageService.sendMessage(to, body+token);
			 
	}
	
	/**
	 * Send welcome SMS.
	 *
	 * @param consumer the consumer
	 * @return the response entity
	 */
	/*
	 * Welcome SMS
	 */
	public Boolean sendWelcomeSMS(Consumer consumer){		
		
		String body="Hi  " + consumer.getFirstName() +", Welcome to nuKart ,Shop now " + "https://nukart.in/" ;
		
		String to = "91"+consumer.getKey().getSecondary();
		
		log.info(to);
		return messageService.sendMessage(to, body);
	}
	
	
	/*
	 * Retailer on boarding SMS
	 */
	public Boolean sendOnboardingSMS(ClientRespose clientRespose){
		String body = "";
		String to = "91"+clientRespose.getMobile();
		if(clientRespose.getMobile() != null & clientRespose.getEmail() == null){
			body = "Hi Welcome to nukart, Thank you for On-boarding. Your user id: "+clientRespose.getMobile()+ " and password: "+clientRespose.getPassword()+".";
		}else if(clientRespose.getMobile() == null & clientRespose.getEmail() != null){
			body = "Hi Welcome to nukart, Thank you for On-boarding. Your user id: "+clientRespose.getEmail()+ " and password: "+clientRespose.getPassword()+".";
		}else{
			body = "Hi Welcome to nukart, Thank you for On-boarding. Your user id: "+clientRespose.getMobile()+ "/" + clientRespose.getEmail() +" and password: "+clientRespose.getPassword()+".";
		}
		log.info(body);
		log.info(to);
		return messageService.sendMessage(to, body);
	}
	
	/*
	 * send order status sms
	 */

/**
	 * Send oder SMS.
	 *
	 * @param order the order
	 * @return the response entity
	 */
	public Boolean sendOderSMS(List<OrderManagement> order){
    	log.info("-------SMS Service Called" + order.size());        
        for(int j=0; j<order.size(); j++){
        	try{          		
                Consumer consumer = notificationService.consumerDetails(order.get(j).getConsumerId());
                Voucher voucher = notificationService.voucherDetails(order.get(j).getVoucherId());               
                log.info("-------consumer id---------" + consumer.getKey().getSecondary());
                    for(int i=0; i<order.size(); i++){
                        String CartDetails = order.get(i).getCart();
                        
                        Offer offer = gson.fromJson(CartDetails, Offer.class);                         
                       /* String username=environment.getProperty("user-id");
                        String hashkey=environment.getProperty("hash-key");*/
                        String body="";
                        String rbody="";
                        
                        if(order.get(i).getState().contains("Cart") || order.get(i).getState().contains("Placed")){
                        	body="Your Order " + offer.getProduct().getName() + " with Order no. " + order.get(i).getOrderId() + " has been Placed";//+ ". For any queries contact us at "+environment.getProperty("spring.notify.num");
                        }
                        else if(order.get(i).getState().contains("Pickup")){
                        	//body="Your Order " + offer.getProduct().getName() + " with Vocuher Code " + order.get(i).getVoucherCode() + " is ready to pick from store "  +offer.getRetailer().getName() + " " + offer.getRetailer().getAddress();//+ ". For any queries contact us at "+environment.getProperty("spring.notify.num");
                        	//rbody = consumer.getFirstName() + " has ordered " + offer.getProduct().getName() + ". Voucher Code is  " + voucher.getCode()+"."; ;
                        	body="Your Order- " +order.get(i).getOrderId() +"("+ offer.getProduct().getName() +")"+ " with Vocuher Code " + order.get(i).getVoucherCode() + " is ready to pick from store "  +offer.getRetailer().getName() + " " + offer.getRetailer().getAddress();//+ ". For any queries contact us at "+environment.getProperty("spring.notify.num");
                        	rbody = consumer.getFirstName() + " has ordered " + offer.getProduct().getName()+"(Order Id-"+order.get(i).getOrderId() + "). Voucher Code is  " + voucher.getCode()+".";
                        }
                        else{
                        	body="Your Order " + offer.getProduct().getName() + " with Order No " + order.get(i).getOrderId() + " has been " + order.get(i).getState()+".";//+ ". For any queries contact us at "+environment.getProperty("spring.notify.num");                        	
                        }
                        
                        if(order.get(i).getState().contains("Cart") || order.get(i).getState().contains("Cancelled")){
                        	log.info("------Order cancel retailer sms-------");
                        	//rbody = consumer.getFirstName() + " has cancelled order for " + offer.getProduct().getName() + "with order id " + order.get(i).getOrderId()+"."; ;
                        	rbody = "We're sorry to inform you that order for "+ offer.getProduct().getName()+ "with Order No " + order.get(i).getOrderId() +" has been cancelled by " + consumer.getFirstName()+".";
                        }
                        
                       if(consumer != null && offer.getRetailer().getKey() != null){
                    	   if(consumer.getKey().getSecondary() != null){
                               String to = "91"+consumer.getKey().getSecondary();                  
                               messageService.sendMessage(to, body);
                           }
                           
                           if(offer.getRetailer().getKey().getSecondary() != null){
                           	if(environment.getProperty("retailer.send").equals("dev")){
                           		String to = "919741282850";
                           		//String to = "918970762460";
                           		messageService.sendMessage(to, rbody);
                           		log.info("---------Retailer sms sent to---------" + to);
                           	}
                           	else{
                           		 String to = "91"+offer.getRetailer().getKey().getSecondary();                  
                                 messageService.sendMessage(to, rbody);
                           	}
                           }
                       }
                       
                       else{
                    	   log.error("----Consumer/Retailer key object null----------");
                       }                        
                      
                    }
                log.info("-------SMS Service Closed");
                return true;
            }catch (Exception e) {
    			log.error("--send order----SMS Exception-------" + e.getMessage());
    		}
        }
        return false;		
	}
	
	/*
	 * send nukart campaign
	 */
	
/**
	 * Send campaign.
	 *
	 * @param order the order
	 * @return the response entity
	 */
	public ResponseEntity<String> sendCampaign(Campaign order){		
		
	List<Key> keys = new ArrayList<>();
	keys = notificationService.emailContact();
	log.info("-----SMS Called---------");
		
		
		String body=order.getName() + ".  Purchase Products through NUKART and Celebrate this Ganesh Chaturthi. Login to nukart.in" ;
		
		
		
		
//		List<String> mobNo = new ArrayList<>();
//		
//		mobNo.add("9620381902");
//		mobNo.add("8970762460");
		
		for(int i=0; i<keys.size();i++){
			log.info("-----FOR---------");
			String to ="91"+keys.get(i).getSecondary();
		    messageService.sendMessage(to, body);
		}
		
//		for(int i=0; i<keys.size();i++){
//			String mobileno = keys.get(i).getSecondary();
//			if(mobileno != null){
//				String numbers = "&numbers=91" + mobileno;
//					String data = user + hash + numbers +sender+ message;
//					String url="http://api.textlocal.in/send/?"+data;
//					ResponseEntity<String> s = restTemplate.postForEntity(url, null, String.class);
//						return s;
//			}
//		}
		log.info("-----SMS Sent---------");
		return null;
		
	}
	
	/**
	 * Send voucher SMS.
	 *
	 * @param consumer the consumer
	 * @param content the content
	 * @return the response entity
	 */
	/*
	 * Voucher validity SMS
	 */
public Boolean sendVoucherSMS(Consumer consumer,String content){	
	
	log.info("------Sending sendVoucherSMS----------");
	
	
	String body="Hi  " + consumer.getFirstName() +", " + 
	content;
	
	
	String to = "91"+consumer.getKey().getSecondary();
	
	
	return messageService.sendMessage(to, body);
}


	/**
	 * Sendsmsvoucher.
	 *
	 * @param voucher the voucher
	 * @param consumer the consumer
	 * @param campaign the campaign
	 * @param retailer the retailer
	 * @param points the points
	 * @return the response entity
	 */
	public Boolean  sendsmsvoucher(Voucher voucher, Consumer consumer, Campaign campaign, Retailer retailer,
			Integer points) {
	   
        
        String body="Congragulations on your purchase from Nukart,Your voucher code is: " + voucher.getCode() + "for product " + campaign.getAd().getProduct().getName() + "voucher valid upto " + voucher.getValidity()+ "You have redeemed "+points+"Store address:"+retailer.getName();
       
        if(consumer.getKey().getSecondary() != null){
            String to ="91"+consumer.getKey().getSecondary();            
            {
            	return messageService.sendMessage(to, body);
            }
        }
        return null;
		
	}


	
	public Boolean callRequestStore(Integer cosumerId,Integer retailerId){
		try{
			log.info("---Store based query-SMS-started execution-");
			Consumer consumer = notificationService.consumerDetails(cosumerId);
			Retailer retailer = notificationService.retailerDetails(retailerId);		
			
			if(consumer != null && retailer != null){
				
				log.info("----subscrption---" + retailer.getSubscription());
				
				//if(retailer.getSubscription().equals("Platinum")){
					if(environment.getProperty("retailer.send").equals("dev")){
						log.info("req store is dev");
						return	callService.clickToCall(consumer.getKey().getSecondary(),"9741282850");
						//return	callService.clickToCall(consumer.getKey().getSecondary(),"7829279271");
						}else{
							log.info("req store is not dev");
							log.info("---Store based query-SMS-completed execution-");
							return	callService.clickToCall(consumer.getKey().getSecondary(),retailer.getKey().getSecondary());				
					}
				//}
				/*else{
					log.error("--Retailer-dont have-Platinum subscription--");
				}*/
			}
			
			else{
				log.error("Consumer/Retailer object null");
			}
			
		}catch (Exception e) {
			log.error("call Request Exception " + e.getMessage());
		}
		return false;
	}
	
	public Boolean callRequestProduct(Integer cosumerId,Integer campaignId){
		try{
			log.info("---Product based query-SMS-started execution-");
			Consumer consumer = notificationService.consumerDetails(cosumerId);
			Campaign camp = notificationService.campaignDetails(campaignId);
			if(consumer != null && camp != null){
				if(camp.getAd().getProduct() != null && camp.getRetailer() != null){
					Product product = notificationService.productDetails(camp.getAd().getProduct().getId());
					Retailer retailer = notificationService.retailerDetails(camp.getRetailer().getId());
					if(product != null && retailer != null){
						log.info("----subscrption---" + retailer.getSubscription());
						//if(retailer.getSubscription().equals("Platinum") && retailer.getKey().getSecondary() != null){
							if(environment.getProperty("retailer.send").equals("dev")){
								log.info("req store is dev");
								log.info("---Product based query-SMS-completed execution-");
								return	callService.clickToCall(consumer.getKey().getSecondary(),"9741282850");
								//return	callService.clickToCall(consumer.getKey().getSecondary(),"7829279271");
							}else{
								log.info("---Product based query-SMS-completed execution-");
								return	callService.clickToCall(consumer.getKey().getSecondary(),retailer.getKey().getSecondary());
							}
						/*}else{
							log.error("--Retailer-dont have-Platinum subscription--");
						}*/
					}
				}
				else{
					log.error("--Product/Retailer object null");
				}
			}
			else{
				log.error("--Consumer/Campaign object null");
			}
		}catch (Exception e) {
			log.error("call Request Exception " + e.getMessage());
		}
		return false;
	}
	
	
	
	

	public void demoReqSMS(Consumer consumer, Retailer retailer,Product product,OrderManagement orders, Offer offer){
	    try{
	    	if(consumer != null && retailer != null && product != null){
	    		if(consumer.getKey().getSecondary() != null){
		    		log.info("Sending SMS to Consumer" + consumer.getKey().getSecondary());
		    		String to = consumer.getKey().getSecondary();
		    		String body = "Demo request for the product "+ product.getName() +" has been sent to "+ retailer.getName() +" concerned person will get back to  you very shortly.";
		    		if(offer.getCampaign().getDemo() != null && offer.getCampaign().getDemo().equals("Store")){
		    			body = "Your request for demo at " + retailer.getAddress() + " for the product "+ product.getName() +" has been sent.";
	    			}
	    			if(orders.getState().equals("Cancelled")){
	    				 body = "Demo request for the product "+ product.getName() +" has been Cancelled.";
	    			}
	    			messageService.sendMessage(to, body);	    			
		    		log.info("SMS Sent to Consumer" + consumer.getKey().getSecondary());
	    		}
	    		if(retailer.getKey().getSecondary() != null){
	    			String to = "";
	    			String body = consumer.getFirstName() + " has requested demo for " + product.getName() + " at " +orders.getDemoRequestAddress() + " on "+ orders.getDemoRequestDate()+".";
	    			if(offer.getCampaign().getDemo() != null && offer.getCampaign().getDemo().equals("Store")){
	    				body = consumer.getFirstName() + " has requested demo at your store for " + product.getName() + " on "+ orders.getDemoRequestDate()+".";
	    			}
	    			if(orders.getState().equals("Cancelled")){
	    				body = consumer.getFirstName() + " has cancelled demo request for the product " + product.getName()+".";
	    			}
	    			log.info("----Sending SMS-to retailer--");
	    			 if(environment.getProperty("retailer.send").equals("dev")){
			    		 to = "919741282850";
	    				 //to = "7829279271";
			    		  messageService.sendMessage(to, body);
			    		  log.info("----SMS Sent--to Retailer-" + to + body);	
		    		  }
	    			  else{
	    				  to = retailer.getKey().getSecondary();
				    	  messageService.sendMessage(to, body);
	    				  log.info("----SMS Sent--to Retailer-" + to + body);	
	    			  }
	    		}
	    	}
	    	else{
	    		log.info("Consumer/Retailer/Product Object null");
	    	}
	    	/*if(consumer != null){
	    		log.info("Consumer" + consumer.getKey().getSecondary());
	    	}
	    	if(product != null){
	    		log.info("Product" + product.getName());
	    	}
	    	if(retailer != null){
	    		log.info("Retailer" + retailer.getKey().getSecondary() + retailer.getName());
	    	}*/
	    }catch (Exception e) {
			log.error("--demo request sms exception--" + e.getMessage());
		}
	}

	
	/**
	 * Send store address.
	 *
	 * @param mobilenum the mobilenum
	 * @param conlatlon the conlatlon
	 * @param retlatlon the retlatlon
	 * @param consumer the consumer
	 * @param retailer the retailer
	 * @return the response entity
	 */
	public ResponseEntity<String> sendStoreAddress(String mobilenum,String conlatlon, String retlatlon, Consumer consumer, Retailer retailer)
	{
		
		String username=environment.getProperty("user-id");
		String hashkey=environment.getProperty("hash-key");	
		String sym= "%26";
		String body="Dear "+consumer.getFirstName()+","+ " Reatiler Name is : " + retailer.getName()+", click this link for Direction to RetailerStore:  http://maps.google.com/?saddr="+ conlatlon +sym+"daddr="+retlatlon;

		String user = "username=" +username;
		String hash = "&hash=" +hashkey;
		String sender = "&sender=" + "TXTLCL";
		String message = "&message=" + body;
		
		try{
	    	if(consumer.getKey().getSecondary() != null){
	    		  log.info("-Sending---SMS--to consumer-" );	  
	    		  /*String mobileno = consumer.getKey().getSecondary();
	              String numbers = "&numbers=91" + mobileno;
	    		  log.info(body + "----" + numbers);
	              String data = user + hash + numbers +sender+ message;
	              String url="http://api.textlocal.in/send/?"+data;
	              ResponseEntity<String> ss = restTemplate.postForEntity(url, null, String.class);
	            
	              log.info(ss.getBody());*/
	              
	              log.info("----SMS Sent--to consumer-" +consumer.getKey().getSecondary());	
	              
	              messageService.sendMessage("91"+consumer.getKey().getSecondary(), body);
	    	}
		}
	    	catch (Exception e) {
				log.error("--demo request sms exception--" + e.getMessage());
			}
		return null;
		}
		

}

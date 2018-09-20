package com.nusecond.suredeal.microservice.notification.controller;
/**
 * @author Krishnamorthi Palanisamy
 *
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
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
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nusecond.suredeal.microservice.notification.MessageLocal;
import com.nusecond.suredeal.microservice.notification.Notification;
import com.nusecond.suredeal.microservice.notification.campaign.model.Campaign;
import com.nusecond.suredeal.microservice.notification.consumer.model.CLocation;
import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;
import com.nusecond.suredeal.microservice.notification.consumer.model.Key;
import com.nusecond.suredeal.microservice.notification.consumer.model.NotifyPojo;
import com.nusecond.suredeal.microservice.notification.model.ClientRespose;
import com.nusecond.suredeal.microservice.notification.model.EmailnuKart;
import com.nusecond.suredeal.microservice.notification.model.NotificationModel;
import com.nusecond.suredeal.microservice.notification.order.Offer;
import com.nusecond.suredeal.microservice.notification.order.OrderManagement;
import com.nusecond.suredeal.microservice.notification.order.Product;
import com.nusecond.suredeal.microservice.notification.retailer.model.Retailer;
import com.nusecond.suredeal.microservice.notification.service.NotificationService;
import com.nusecond.suredeal.microservice.notification.service.RetryRestTemplate;
import com.nusecond.suredeal.microservice.notification.service.SMSSender;
import com.nusecond.suredeal.microservice.notification.service.SmtpMailSender;
import com.nusecond.suredeal.microservice.notification.voucher.model.Voucher;

import javassist.bytecode.stackmap.BasicBlock.Catch;

// TODO: Auto-generated Javadoc
/**
 * The Class NotificationController.
 */
@RestController
@RequestMapping("/notify")
public class NotificationController {

	/** The log. */
	static Logger log = Logger.getLogger(NotificationController.class.getName());

	/** The notification service. */
	@Autowired
	NotificationService notificationService;
	
	/** The java mail sender. */
	@Autowired
	private JavaMailSender javaMailSender;
	
	/** The smtp mail sender. */
	@Autowired
	private SmtpMailSender smtpMailSender;
	/** The retry rest template. */
	@Autowired
	RetryRestTemplate retryRestTemplate;
	
	/** The smssender. */
	@Autowired
	private SMSSender smssender;

	/** The rest template. */
	private RestTemplate restTemplate = new TestRestTemplate();
	
	/** The environment. */
	@Inject
	private Environment environment;
	
	/** The gson. */
	Gson gson = new Gson();
	
/*
 * ----------------EMAIL/SMS-------------------------------------	
 */
	
	/**
 * Send email.
 *
 * @param consumer the consumer
 * @return the boolean
 */
/*
	 * send welcome email/sms
	 * 	
	 */
	@RequestMapping(value="/welcome",method=RequestMethod.POST)
	public Boolean send_email(@RequestBody Consumer consumer){
		
		try{
			smtpMailSender.sendWelcomeEmail(consumer);				
			smssender.sendWelcomeSMS(consumer);		
			return true;
		}
		catch(Exception e){
			log.info(e.getMessage());
		}
		
			return false;
			
	}
	
	@RequestMapping(value="/onboard",method=RequestMethod.POST)
	public Boolean retailer_welcome(@RequestBody ClientRespose clientRespose){
		try{
			smssender.sendOnboardingSMS(clientRespose);
			return true;
		}catch (Exception e) {
			log.info(e.getMessage());
		}
		return false;
	}
	
	/*
	 * send nukart campaign email 
	 */
	
	/**
	 * Send email campaign.
	 *
	 * @param order the order
	 * @return the string
	 */
	@RequestMapping(value="/nuKartCamp",method=RequestMethod.POST)
	public String send_email_campaign(@RequestBody Campaign order){		
		try{
			smtpMailSender.sendnuKartCampaignEmail(order);
			smssender.sendCampaign(order);
			return "Email Sent";
		}catch(Exception e){
			log.error("--------Error to send welcome email--------"  +   e.getMessage());
		}
		return "Failed";
	}
	
	/**
	 * Send email.
	 *
	 * @param camp the camp
	 * @return the boolean
	 */
	/*
	 * Test method
	 */
	@RequestMapping(value="/testnew",method=RequestMethod.GET)
	public Boolean sendEmail(Campaign camp){
		try{
			log.info("---Controller-Called-----------");
				smtpMailSender.sendTestEmail();
			log.info("---Controller-Completed-----------");
			return true;
		}catch(Exception e){
			log.error("-----Error-----Controller---"+e.getMessage());
		}
		return false;
	}
	
	
	/**
	 * Order notification.
	 *
	 * @param order the order
	 * @return the boolean
	 */
	/*
	 * order sms email
	 */
	@RequestMapping(value="/orderNo",method=RequestMethod.POST)
    public Boolean orderNotification(@RequestBody List<OrderManagement> order){	
		  try{       
	        	 log.info("----controller called----" + order.size());        	 
	             smtpMailSender.sendorder(order);             
	             smssender.sendOderSMS(order);
	        	 log.info("----controller done----");
	             return true;
	        }
	        catch(Exception e)
	        {
	            log.info("=="+e.getMessage());
	        }
	        return false;
    }
	
	
	@RequestMapping(value="/productnotify",method=RequestMethod.POST)
	public Boolean brandNotification(@RequestParam(required=false)String brand,@RequestParam(required=false)String category)
	{
		log.info("brand="+brand+"category"+category);
		try{
			smtpMailSender.sendbrandnotification(brand, category);
			return true;
		}
		catch(Exception e){
			log.info("==="+e.getMessage());
		}
		return false;
	}
	
	/**
	 * Demo notification.
	 *
	 * @param order the order
	 * @return the boolean
	 * @throws MessagingException the messaging exception
	 */
	/*
	 * Demo box
	 */
	@RequestMapping(value="/Requestdemo",method=RequestMethod.POST)
	public Boolean demoNotification(@RequestBody List<OrderManagement> order) throws MessagingException
	{
		log.info("--------DemoNotify--service called-----");
		Consumer consumer = new Consumer();
		Retailer retailer = new Retailer();
		Product product = new Product();
		
		for(int i=0;i<order.size();i++){
			log.info("order size" + order.size());
			OrderManagement orders=order.get(i);
			Offer offer = new Offer();
			String CartDetails = orders.getCart();
			
			offer = gson.fromJson(CartDetails, Offer.class);
			try{
				consumer = notificationService.consumerDetails(orders.getConsumerId());
			
				if(offer.getRetailer().getKey()!=null)
				{
					log.error("--Offer is not null--" + " Retailer " + offer.getRetailer().getId() +" Product "+ offer.getProduct().getId());
					retailer=notificationService.retailerDetails(offer.getRetailer().getId());
					product = notificationService.productDetails(offer.getProduct().getId());
					smtpMailSender.DemoNotify(consumer,retailer,offer,orders);
					smssender.demoReqSMS(consumer, retailer, product,orders,offer);
					return true;
				}
			}catch (Exception e) {
				log.error("Demo request exception--" + e.getMessage());
			}
				
		}
		return false;
	}
	
	
	
	
	
	/**
	 * Contactnu kart.
	 *
	 * @param emailnuKart the emailnu kart
	 * @return the boolean
	 */
	/*
	 * Email from consumer to nuKart
	 */
	@RequestMapping(value="/nuKart",method=RequestMethod.POST)
	public Boolean contactnuKart(@RequestBody EmailnuKart emailnuKart){
		try{
			 smtpMailSender.sendEmailTonuKart(emailnuKart);
			 return true;
		}catch(Exception e){
			log.error("------Error in Controller-------" + e.getMessage());
			return false;
		}
	}
	
	/**
	 * Gets the notification.
	 *
	 * @param consumerId the consumer id
	 * @return the notification
	 */
	/*
	 * consumer order detail
	 */
	@RequestMapping(value="/notifictation",method=RequestMethod.GET)
	public List<OrderManagement> getNotification(@RequestParam String consumerId){		
		try{
			log.info("---Controller-Called-----------");
			List<OrderManagement> orderList = 	notificationService.consumerOrdersList(consumerId);
			log.info("---Completed-----------");
			return orderList;
		}catch(Exception e){
			log.error("-------Get Notification---------" + e.getMessage());
		}
		return null;
	}
	
	/*
	 * Email to customer care when new retailer added
	 */
	@RequestMapping(value="/newRetailer",method=RequestMethod.POST)
	public Boolean newRetailerNotify(@RequestBody Retailer retailer){
		try{
			return smtpMailSender.newRetailerNotification(retailer);
		}catch (Exception e) {
			log.error("-------New Retailer Notification--Error-------" + e.getMessage());
		}
		return false;
	}
	
	/**
	 * Send camp notification.
	 *
	 * @param note the note
	 * @return the boolean
	 */
	/*
	 * Firebase Campaign Notification
	 */
	@RequestMapping(value="/fire",method=RequestMethod.POST)
	public Boolean sendCampNotification(@RequestBody (required = false) NotifyPojo note){
		try{
			notificationService.firebaseNotification(note);
			return true;
		}catch(Exception e){
			log.info("----------Error Notification Send Failed------------");
		}
		return false;
	}
	
	/**
	 * Voucher consumer exp.
	 *
	 * @param consumerId the consumer id
	 * @param content the content
	 * @return the boolean
	 */
	/*
	 *Consumer Voucher Expire Notification
	 */
	@RequestMapping(value="/vouchExp",method=RequestMethod.GET)
	public Boolean voucherConsumerExp(@RequestParam  Integer consumerId, @RequestParam String content){
		try{
			log.info("--Content------" + content);
			
			Consumer consumer = notificationService.consumerDetails(consumerId);
			log.info("------------Consumer Details------"+consumer.getKey().getPrimary() + consumer.getKey().getSecondary());
			
			if(consumer != null){
				smtpMailSender.voucherConsumerExpire(consumer, content);
				smssender.sendVoucherSMS(consumer, content);
			}
			
			return true;
		}catch(Exception e){
			log.error("-----Voucher  Error Controller-------" + e.getMessage());
		}
		return false;
	}
	
	/**
	 * Voucher ret expire.
	 *
	 * @param retailerId the retailer id
	 * @param content the content
	 * @return the boolean
	 */
	/*
	 *Retailer Voucher Expire Notification
	 */
	@RequestMapping(value="/campExp",method=RequestMethod.GET)
	public Boolean voucherRetExpire(@RequestParam Integer retailerId,@RequestParam String content){
		try{
			log.info("--Content------" + content);
			smtpMailSender.voucherRetailerExpire(retailerId, content);
			return true;
		}catch(Exception e){
			log.error("-----Voucher  Error Controller-------" + e.getMessage());
		}
		return false;
	}

	/**
	 * Send test.
	 *
	 * @return the boolean
	 * @throws Exception the exception
	 */
//	@RequestMapping(method = RequestMethod.GET)
	public Boolean sendtest() throws Exception {
		return notificationService.pushNotification(
				"ckHgNic667U:APA91bEQ9rkueuF2X01Er-w8wYKFSDvQQqw767ffGxwP2yeilebCroGIZnxStXdtgF8Ca9Mzg319_1R6UZvfaHDWdbUEl4LBOJFW7fPnNTvgMqtmW0aZddtMB9Vf_GcJb1au6aYVvGeJ",
				"Hi Karun");
		
		
	}

	/**
	 * Send compaign notifation to consumer.
	 *
	 * @param notifymap
	 *            the notifymap
	 * @return the hash map
	 * @throws JsonParseException
	 *             the json parse exception
	 * @throws JsonMappingException
	 *             the json mapping exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */

	@RequestMapping(method = RequestMethod.POST, value = "/icare")
	public HashMap<Integer, Campaign> notifyConsumers(@RequestBody HashMap<Integer, Campaign> notifymap)
			throws JsonParseException, JsonMappingException, IOException {
		log.info("Notify Consumer process");
		String msg1 = environment.getProperty("spring.notify.msg1");
		String msg2 = environment.getProperty("spring.notify.msg2");

		HashMap<Integer, Campaign> failedNotify = new HashMap<Integer, Campaign>();

		HashMap<Integer, Campaign> sucessNotify = new HashMap<Integer, Campaign>();

		log.info("Notification campaign size" + notifymap.size());
		if (notifymap != null)
			for (Entry<Integer, Campaign> entry : notifymap.entrySet()) {
				Integer key = entry.getKey();
				Campaign value = entry.getValue();

				try {
					String consumerUrl = environment.getProperty("spring.consumerMS.url");
					Consumer consumer=null;
					try {
						consumer = retryRestTemplate.consumerRestRetry(consumerUrl, key);
					} catch (Exception e) {
						
						log.error("error in consumer service"+e.getMessage());
					}

					if (key != null && consumer != null && consumer.getGcmToken() != null) {
						if (notificationService.pushNotification(consumer.getGcmToken(),
								msg1 + value.getName() + msg2)) {
							sucessNotify.put(key, value);
						} else {
							failedNotify.put(key, value);
						}
					}
				} catch (RestClientException e) {
					log.error("error in rest" + e.getMessage());

				}

			}

		log.info("Sucessfull notify Campaign" + sucessNotify);
		log.info("Failled  notify Campaign" + failedNotify);

		return failedNotify;

	}

	/**
	 * New user notification.
	 *
	 * @param consumer
	 *            the consumer
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/newuser")
	public String newUserNotification(@RequestBody Consumer consumer) {
		log.info("new Consumer notify");

		if (consumer != null && consumer.getGcmToken() != null) {
			if (notificationService.pushNotification(consumer.getGcmToken(),
				environment.getProperty("spring.notify.new") + consumer.getFirstName() + "!")) {
				return "Success";
			} else {
				return "Fail";
			}
		}
		return null;

	}

	/**
	 * Old user notification.
	 *
	 * @param consumer
	 *            the consumer
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/olduser")
	public String oldUserNotification(@RequestBody Consumer consumer) {
		log.info("Old Consumer notify");
		if (consumer != null && consumer.getGcmToken() != null) {
			if (notificationService.pushNotification(consumer.getGcmToken(),
					environment.getProperty("spring.notify.old") + consumer.getFirstName() + "!")) {
				return "Success";
			} else {
				return "Fail";
			}
		}
		return null;

	}
	
	/**
	 * Send voucher mail.
	 *
	 * @param voucherobj the voucherobj
	 * @return the string
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/voucher")
	public String sendVoucherMail(@RequestBody Voucher voucherobj) throws JsonParseException, JsonMappingException, IOException, MessagingException
	{
		if(voucherobj!=null)
		{
		try
		{
			Integer points=0;
		
		Consumer consumer=null;
		Campaign campaign=null;
		Retailer retailer=null;
	
		
		String consumerurl = environment.getProperty("spring.consumerMS.url");
		String campaignurl = environment.getProperty("spring.campaignMS.url");
		String retailerurl = environment.getProperty("spring.retailerMS.url");
		String consumerloyaltyurl = environment.getProperty("spring.consumerloyaltyMS.url");
		try {
			if(voucherobj.getConsumerId()!=null)
			{
				Integer consumerid=voucherobj.getConsumerId();
				consumer=restTemplate.getForObject(consumerurl,Consumer.class,consumerid);
				log.info("-------consumer object------" + consumer.getKey().getPrimary() + consumer.getKey().getSecondary());
			}
		
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error in fetching Consumers: " + e.getMessage());
		}
		
		try {
			
			if(voucherobj.getCampaignId()!=null)
			{
			Integer campaignid=voucherobj.getCampaignId();
			campaign=restTemplate.getForObject(campaignurl,Campaign.class,campaignid);
			log.info("-------campaign object------" + campaign.getName() + campaign.getAd().getName());
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error in fetching campaign: " + e.getMessage());
		}
		
		try {
			
			if(campaign!=null)
			{
				if(campaign.getRetailer()!=null)
				{
					Integer retailerid=campaign.getRetailer().getId();
					retailer=restTemplate.getForObject(retailerurl,Retailer.class,retailerid);
					log.info("-------retailer object------" + retailer.getName());
				}
			
			}
			
		
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error in fetching Retailer: " + e.getMessage());
		}
		
		try {
			if(voucherobj.getConsumerId()!=null)
			{
				Integer consumerid=voucherobj.getConsumerId();
				 points=restTemplate.getForObject(consumerloyaltyurl,Integer.class,consumerid);
			}
			
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Error in fetching Consumer Loyal: " + e.getMessage());
		}
		
		
		
		
		smtpMailSender.sendmailvoucher(voucherobj,consumer,campaign,retailer,points);
		smssender.sendsmsvoucher(voucherobj, consumer, campaign, retailer, points);
		return "sucess";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
		}
		else
		{
			return "voucher null";
		}
	
		
		}
	
	
	/**
	 * Send mail.
	 *
	 * @param map the map
	 * @return the string
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public String sendMail(@RequestBody Map<String,Object> map) throws JsonParseException, JsonMappingException, IOException, MessagingException
	{
		
		
		Consumer consumer=new Consumer();
		Campaign campaign=new Campaign();
		Retailer retailer=new Retailer();
		
		
		for (Map.Entry<String, Object> e : map.entrySet()) {
			ObjectMapper mapper = new ObjectMapper();
			Gson gson = new Gson();
			
			if(e.getKey().equals("consumer"))
			{
			
				String json = gson.toJson(e.getValue());
				consumer=mapper.readValue(json, Consumer.class);
			}
		
			if(e.getKey().equals("campaign"))
			{
				String json = gson.toJson(e.getValue());
				campaign=mapper.readValue(json, Campaign.class);
				
			}
			if(e.getKey().equals("retailer"))
			{
				String json = gson.toJson(e.getValue());
				retailer=mapper.readValue(json, Retailer.class);
				
			}
		
			}
		smtpMailSender.sendmail(consumer, campaign, retailer);
		
		
		return "sucess";
		
		}
	
	/**
	 * Test.
	 *
	 * @throws EmailException the email exception
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/test")
	public void test() throws EmailException, MessagingException
	{
		
		Consumer consumer=new Consumer();
		Retailer retailer=new Retailer();
		Campaign campaign=new Campaign();
		smtpMailSender.sendmail(consumer, campaign, retailer);
	}
	
	/**
	 * Send voucher mail 2.
	 *
	 * @return the string
	 * @throws JsonParseException the json parse exception
	 * @throws JsonMappingException the json mapping exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String sendVoucherMail2() throws JsonParseException, JsonMappingException, IOException, MessagingException
	{
		
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		helper = new MimeMessageHelper(message,true);
		
		helper.setSubject("Test");
		helper.setTo("krishnamoorthi@outlook.com");
		helper.setText("test",true);
		javaMailSender.send(message);
		return "sucess";
}
	
	/**
	 * Send OTP email.
	 *
	 * @param id the id
	 * @param otp the otp
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(method = RequestMethod.POST,value = "/otp")
	public void sendOTPEmail(@RequestParam(value = "id") String id,@RequestParam(value = "otp") String otp) throws MessagingException
	{
		
		if(id.contains("@"))
		{
			
			if(otp.length()>6)
			{
				smtpMailSender.sendEmailLink(id,otp);	
			}
			else
			{
				
			}
			smtpMailSender.sendOTP(id,otp);
		}
		
		else
		{
			smssender.sendOTP(id, otp);
		
		}	
		
	}
	
	/**
	 * Retailercall back.
	 *
	 * @param consumerID the consumer ID
	 * @param retailerID the retailer ID
	 * @param campaignID the campaign ID
	 * @return the string
	 */
	/*
	 * Request call 
	 */
	@RequestMapping(method=RequestMethod.POST,value="/reqCall")
	public Boolean retailercallBack(@RequestParam(required = true) Integer consumerID,@RequestParam (required = false) Integer retailerID,@RequestParam (required = false) Integer campaignID){
		log.info("consumerID--" + consumerID + "--retailerID--"+ retailerID  + "--campaignID--" + campaignID);
		Boolean res=true;
		if(retailerID != null){
			log.info("---------Retailer is not null--"+retailerID);
			Boolean email = smtpMailSender.requestCallFromConsumer(consumerID, campaignID, retailerID);
			//Boolean email =true;
			Boolean sms = smssender.callRequestStore(consumerID, retailerID);
			if(email && sms){
				return res;
			}
			else if(email && !sms){
				//res = "Email sent/call request failed";
				return res;
			}
		}
		else if(campaignID != null){
			log.info("---------Campaign is not null--"+campaignID);
			//Boolean email =true;
			Boolean email = smtpMailSender.requestCallFromConsumer(consumerID, campaignID, retailerID);
			Boolean sms =smssender.callRequestProduct(consumerID, campaignID);
			if(email && sms){
				return res;
			}
			else if(email && !sms){
				//res = "Email sent/call request failed";
				return res;
			}
		}
		return false;
	}
	
	/**
	 * Req add product.
	 *
	 * @param model the model
	 * @return the boolean
	 * @throws MessagingException the messaging exception
	 */
	/*
	 * add product req from retailer
	 */
	@RequestMapping(method=RequestMethod.POST,value="/addProReq")
	public Boolean reqAddProduct(@RequestBody NotificationModel model) throws MessagingException{
		log.info("-Model--" + model.getName()+model.getBrand()+model.getCategory()+model.getMrp()+model.getProductModel());
		return smtpMailSender.addNewProduct(model);
	}
	
	
	/**
	 * Notify store.
	 *
	 * @param consumerID the consumer ID
	 * @param retailerID the retailer ID
	 * @param location the location
	 * @return the boolean
	 * @throws MessagingException the messaging exception
	 */
	@RequestMapping(method=RequestMethod.POST,value="/maplinktocons")
	public Boolean NotifyStore(@RequestParam(required = true) Integer consumerID,
			@RequestParam(required = false) Integer retailerID,@RequestBody CLocation location) throws MessagingException
	{			
		try{
			final String consumerUrl= environment.getProperty("spring.consumerMS.url");
			final String retailerUrl= environment.getProperty("spring.retailerMS.url");
			Consumer consumer = restTemplate.getForObject(consumerUrl,Consumer.class,consumerID);
			Retailer retailer = restTemplate.getForObject(retailerUrl,Retailer.class,retailerID);				
		
			String conlatlong=location.getLatitude()+","+location.getLongitude();
			
			log.info("consumer lat long"  + conlatlong);
			
			String retlatlong=retailer.getLocation().getLatitude()+","+retailer.getLocation().getLongitude();
			log.info("retailer lat long"  + retlatlong);
			
			String mobileno =consumer.getKey().getSecondary();
			System.out.println(mobileno);
			
				smssender.sendStoreAddress(mobileno, conlatlong, retlatlong, consumer, retailer);
				
				return true;
			
		}catch (Exception e) {
			log.error("SMS exception" + e.getMessage());
		}
		return false;
					
	}
}

package com.nusecond.suredeal.microservice.notification.service;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.google.gson.Gson;
import com.nusecond.suredeal.microservice.notification.campaign.model.Campaign;
import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;
import com.nusecond.suredeal.microservice.notification.consumer.model.Key;
import com.nusecond.suredeal.microservice.notification.consumer.model.NotifyPojo;
import com.nusecond.suredeal.microservice.notification.controller.NotificationController;
import com.nusecond.suredeal.microservice.notification.model.EmailnuKart;
import com.nusecond.suredeal.microservice.notification.model.NotificationModel;
import com.nusecond.suredeal.microservice.notification.order.Offer;
import com.nusecond.suredeal.microservice.notification.order.OrderManagement;
import com.nusecond.suredeal.microservice.notification.order.Product;
import com.nusecond.suredeal.microservice.notification.retailer.model.Retailer;
import com.nusecond.suredeal.microservice.notification.voucher.model.Voucher;

@Service
public class SmtpMailSender {

	static Logger log = Logger.getLogger(SmtpMailSender.class.getName());
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	NotificationService notificationService;
	
	private RestTemplate restTemplate = new TestRestTemplate();
	
	@Inject
	private Environment environment;

	Gson gson = new Gson();

	/*
	 * Welcome Email
	 * 
	 * 
	 */
	public void sendWelcomeEmail(Consumer consumer) {// ,String fullname){
		try {

			final Context context = new Context();
			Date date = new Date();
			context.setVariable("name", consumer.getFirstName());
			context.setVariable("image", "http://nukart.in/assets/images/logo.png");
			context.setVariable("message", environment.getProperty("no.reply.message"));
			final String nukartWelcome = this.templateEngine.process("email-welcome", context);
			final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
			welcomeEmail.setSubject("Welcome to nuKart");
			welcomeEmail.setTo(consumer.getKey().getPrimary());
			welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
			welcomeEmail.setText(nukartWelcome, true);
			javaMailSender.send(welcomeMsg);

			log.info("--------Success--------");
		} catch (Exception e) {
			log.error("--------Error to send welcome email--------" + e.getMessage());
		}
	}
	
	/*
	 * New Retailer Notification
	 */
	public Boolean newRetailerNotification(Retailer retailer){
		try {
			log.info("----retailer email--started----");
            final Context context = new Context();
            Date date = new Date();
            context.setVariable("retailer",retailer);
            context.setVariable("image", "https://www.suredeal.online/assets/images/logo.png");
            context.setVariable("message", environment.getProperty("no.reply.message"));
            final String nukartWelcome = this.templateEngine.process("email-cc-retailer", context);
            final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
            final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
            welcomeEmail.setSubject("Welcome to nuKart");
            if(retailer.getKey().getPrimary() != null){
                //welcomeEmail.setTo("arpitha@nukart.in");
            	welcomeEmail.setTo(retailer.getKey().getPrimary());
            }else{
                welcomeEmail.setTo(environment.getProperty("customercare.emailid"));
            }
            welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
            welcomeEmail.setText(nukartWelcome, true);
            javaMailSender.send(welcomeMsg);
            log.info("----retailer email--done----");
			return true;
		} catch (Exception e) {
			log.error("--------New retailer email error--------" + e.getMessage());
		}
		return false;
	}
	
	public void sendbrandnotification(String brand,String category){
		try{
			final Context context=new Context();
			context.setVariable("brand", brand);
			context.setVariable("category", category);
			final String sendNotify=this.templateEngine.process("email-brand", context);
			final MimeMessage brandMessage = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper brandEmail = new MimeMessageHelper(brandMessage, "UTF-8");
			if(brand!=null && category!=null)
			{
			log.info(brand+""+category);	
				
			brandEmail.setSubject("Request for category and brand");
			brandEmail.setTo(environment.getProperty("nukart.test.emailid"));
			brandEmail.setText(sendNotify,true);
			javaMailSender.send(brandMessage);
			}
			else{
				
				if(brand!=null){
				log.info("when brand not equal to null"+brand);	
					
				brandEmail.setSubject("Request For brand");
				brandEmail.setTo(environment.getProperty("nukart.test.emailid"));
				brandEmail.setText(sendNotify,true);
				javaMailSender.send(brandMessage);
				}
				else{
					log.info("when category not equal to null"+""+category);
					brandEmail.setSubject("Request For category");
					brandEmail.setTo(environment.getProperty("nukart.test.emailid"));
					brandEmail.setText(sendNotify,true);
					javaMailSender.send(brandMessage);
					
				}
			}
			
			
			
		}
		catch(Exception e){
			log.error("error to send email"+e.getMessage());
		}
	}
	
	
	

	/*
	 * Consumer Voucher Expire Notification
	 */
	public void voucherConsumerExpire(Consumer consumer, String content) {
		try {
			log.info("-----Voucher SMTPmailsender Entered-------");
			final Context context = new Context();

			context.setVariable("consumer", consumer);
			context.setVariable("content", content);
			context.setVariable("image", "http://nukart.in/assets/images/logo.png");
			context.setVariable("message", environment.getProperty("no.reply.message"));
			final String voucherExpire = this.templateEngine.process("voucher-consumer-validity", context);
			final MimeMessage voucherMsg = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper voucherEmail = new MimeMessageHelper(voucherMsg, "UTF-8");
			voucherEmail.setSubject("Voucher Validity!");
			voucherEmail.setTo(consumer.getKey().getPrimary());
			voucherEmail.setCc(environment.getProperty("customercare.emailid"));
			voucherEmail.setText(voucherExpire, true);
			voucherEmail.setReplyTo(environment.getProperty("no.reply"));			
			javaMailSender.send(voucherMsg);
			log.info("-----Email Sent to Consumer-------");

			log.info("-----Voucher SMTPmailsender Done-------");
		} catch (Exception e) {
			log.error("-----Voucher  Error SMTPmailsender-------" + e.getMessage());
		}
	}

	/*
	 * Retailer Voucher Expire Notification
	 */
	public void voucherRetailerExpire(Integer retailerId, String content) {
		try {
			log.info("-----Voucher SMTPmailsender Entered-------");
			final Context context = new Context();

			Retailer retailer = notificationService.retailerDetails(retailerId);
			log.info("-----Retailer-------" + retailer.getKey().getPrimary());
			context.setVariable("retailer", retailer);
			context.setVariable("content", content);
			context.setVariable("message", environment.getProperty("no.reply.message"));
			context.setVariable("image", "http://nukart.in/assets/images/logo.png");
			final String voucherExpire = this.templateEngine.process("voucher-retailer-validity", context);
			final MimeMessage voucherMsg = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper voucherEmail = new MimeMessageHelper(voucherMsg, "UTF-8");
			voucherEmail.setSubject("Voucher Validity!");
			voucherEmail.setTo(retailer.getKey().getPrimary());
			voucherEmail.setCc(environment.getProperty("customercare.emailid"));
			voucherEmail.setText(voucherExpire, true);
			voucherEmail.setReplyTo(environment.getProperty("no.reply"));
			javaMailSender.send(voucherMsg);
			log.info("-----Email Sent to Retailer-------");

			log.info("-----Voucher SMTPmailsender Done-------");
		} catch (Exception e) {
			log.error("-----Voucher  Error SMTPmailsender-------" + e.getMessage());
		}

	}

	/*
	 * Send new nuKart campain email
	 */
	public void sendnuKartCampaignEmail(Campaign order) {
		try {

			log.info("----Email----Called--------");

			List<Key> keys = new ArrayList<>();
			keys = notificationService.emailContact();

			// List<String> li=new ArrayList<String>();
			//
			// li.add("arpitha@ghjkfgk.com");
			// li.add("arpithasomanna@gmail.com");

			final Context context = new Context();
			context.setVariable("campname", order.getName());
			context.setVariable("campimg", order.getImage());
			context.setVariable("message", environment.getProperty("no.reply.message"));
			context.setVariable("image", "http://nukart.in/assets/images/logo.png");
			final String nukartCampaign = this.templateEngine.process("email-nukart-campaign", context);
			final MimeMessage newCampaign = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper campaignEmail = new MimeMessageHelper(newCampaign, "UTF-8");
			campaignEmail.setSubject("New nuKart Campaign");
			campaignEmail.setReplyTo(environment.getProperty("no.reply"));
			for (int i = 0; i < keys.size(); i++) {
				campaignEmail.setTo(keys.get(i).getPrimary());
				campaignEmail.setText(nukartCampaign, true);
				try {
					javaMailSender.send(newCampaign);
					log.info("--------Success--------" + keys.get(i).getPrimary());
				} catch (Exception e) {
					log.error(e.getMessage());
				}
			}

			campaignEmail.setCc(environment.getProperty("customercare.emailid"));
			// for(int i=0; i<li.size();i++){
			// campaignEmail.setTo(li.get(i));
			// campaignEmail.setText(nukartCampaign,true);
			// javaMailSender.send(newCampaign);
			// log.info("----Email----Success--------" + li.get(i));
			// }

		} catch (Exception e) {
			log.error("--------Error to send campaign email--------" + e.getMessage());
		}
	}

	/*
	 * order status
	 */

	public void sendorder(List<OrderManagement> order) throws MessagingException {
		int result = 0;
		int totalamount = 0;
		log.info("--------send Email--service called-----" + order.size());
		final Context ctx = new Context();
		final Context retCtx = new Context();
		Date dt = new Date();
		String validity = null;
		Map<String, List<Offer>> offermap = new HashMap<>();
		Map<String, List<OrderManagement>> ordermap = new HashMap<>();
		log.info("----------Consumer ID-------" + order.get(0).getConsumerId());

		Consumer consumer = notificationService.consumerDetails(order.get(0).getConsumerId());
		if(consumer != null){
			Voucher voucher = null;
			// Retailer notification template

			Offer offer = new Offer();
			List<Offer> off = new ArrayList<>();
			try {
				log.info("-------Offer---Called----");

				for (int i = 0; i < order.size(); i++) {

					OrderManagement orderManagement = order.get(i);
					log.info("--------Redeem Points------" + orderManagement.getRedeemedPoints());
					log.info("---Voucher id ---" + orderManagement.getVoucherId());
					if(orderManagement.getVoucherId() != null){
						voucher = notificationService.voucherDetails(orderManagement.getVoucherId());
						//log.info("----Vocher-----" + voucher.getCode() + voucher.getValidity());
						if(voucher != null){
							Date date = voucher.getValidity();
							log.info("-----date--" +  date);
							DateFormat outputFormatter = new SimpleDateFormat("MM/dd/yyyy");
							validity = outputFormatter.format(date);
							log.info("oooooooooooooutput" + validity);
						}
						else{
							log.error("---null object voucher---");
						}
					}
					else{
						log.error("-----Voucher id is null----");
					}

					String CartDetails = orderManagement.getCart();

					offer = gson.fromJson(CartDetails, Offer.class);

					log.info("cart details " + offer.getProduct().getName());
					if (offer.getRetailer() != null) {
						if (offer.getRetailer().getKey() != null) {

							if (offer.getRetailer().getKey().getPrimary() != null
									&& offer.getRetailer().getKey().getPrimary().contains("@")) {
								log.info("----------Retailer ID-------" + offer.getRetailer().getKey().getPrimary());
								// Retailer retailer =
								// notificationService.retailerDetails(offer.getRetailer().getId());

								retCtx.setVariable("order", orderManagement);
								retCtx.setVariable("orderstatus", orderManagement.getState());
								retCtx.setVariable("offer", offer);
								retCtx.setVariable("consumer", consumer);
								if(voucher != null){
									retCtx.setVariable("voucher", voucher.getCode());
									retCtx.setVariable("validity", validity);
								}
								retCtx.setVariable("message", environment.getProperty("no.reply.message"));
								retCtx.setVariable("image", "http://nukart.in/assets/images/logo.png");

								final String retailerNot = this.templateEngine.process("email-retailer", retCtx);
								final MimeMessage retailerMsg = this.javaMailSender.createMimeMessage();
								final MimeMessageHelper retailerEmail = new MimeMessageHelper(retailerMsg, "UTF-8");

								if (orderManagement.getState().contains("Placed")
										|| orderManagement.getState().contains("Cart")
										|| orderManagement.getState().contains("Pickup")) {
									retailerEmail.setSubject("New Order!");
								} else if (orderManagement.getState().contains("Cancelled")) {
									retailerEmail.setSubject("Order Cancelled!");
								}
								//retailerEmail.setTo(offer.getRetailer().getKey().getPrimary());
								retailerEmail.setTo(environment.getProperty("nukart.test.emailid"));
								//retailerEmail.setTo("arpithasomanna@gmail.com");
								log.info("------retailer email sent to--------"+environment.getProperty("nukart.test.emailid"));
								retailerEmail.setCc(environment.getProperty("customercare.emailid"));
								log.info("------retailer cc email sent to--------"+environment.getProperty("customercare.emailid"));
								retailerEmail.setText(retailerNot, true);
								retailerEmail.setReplyTo(environment.getProperty("no.reply"));
								javaMailSender.send(retailerMsg);
							}
						}
					}

					off.add(offer);
				}

				offermap.put("offers", off);
				ordermap.put("order", order);

				ctx.setVariable("image", "http://nukart.in/assets/images/logo.png");
				ctx.setVariable("message", environment.getProperty("no.reply.message"));
				ctx.setVariables(offermap);
				ctx.setVariables(ordermap);
				ctx.setVariable("orderstatus", order.get(0).getState());
				//String url = environment.getProperty("domain.name");
	           // url = "https://"+environment.getProperty("domain.name")+"/trackorder?id=";
				String url ="https://"+environment.getProperty("domain.name")+"/trackorder?id=";
				ctx.setVariable("track", url);
				if(voucher != null){
					ctx.setVariable("vouch", voucher.getCode());
					ctx.setVariable("validity", validity);
				}

				if (order.size() == 1) {
					log.info("------Entered--------IF-------");
					log.info("orDERiD " +order.get(0).getOrderId());
					ctx.setVariable("orderID", order.get(0).getOrderId());
				}
				ctx.setVariable("address", order.get(0).getShippingAddress());
				ctx.setVariable("consumername", consumer.getFirstName());
				ctx.setVariable("mobile", consumer.getKey().getSecondary());
				ctx.setVariable("order_status", order.get(0).getState());
				ctx.setVariable("voucher", voucher);
				log.info("pro image============" + off.get(0).getProduct().getLabel());
				
				log.info("---------------order state------------" + order.get(0).getState());
				log.info("order size is" + order.size());
				

				for (int i = 0; i < order.size(); i++) {

					Integer price;
					OrderManagement orderManagement = order.get(i);
					price = orderManagement.getAmountPayable();
					log.info("price" + price);

					totalamount = totalamount + price;

				}
				log.info("---------------total------------" + totalamount);
				ctx.setVariable("total", totalamount);

				final String nukartWelcome = this.templateEngine.process("email-order-template", ctx);
				final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
				final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");

				if (order.get(0).getState().contains("Placed") || order.get(0).getState().contains("Cart")
						|| order.get(0).getState().contains("Pickup")) {
					welcomeEmail.setSubject("Your Order has been Placed ");
				} else if (order.get(0).getState().contains("Shipped") || order.get(0).getState().contains("Cancelled")) {
					welcomeEmail.setSubject("Your Order has been " + order.get(0).getState());
				} else {
					welcomeEmail.setSubject("Order Delivered!");
				}
				welcomeEmail.setTo(consumer.getKey().getPrimary());
				welcomeEmail.setCc(environment.getProperty("customercare.emailid"));
				welcomeEmail.setText(nukartWelcome, true);
				welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
				if (consumer.getKey().getPrimary() != null) {
					log.info("==========Entered if============");
				//	Thread.sleep(5000);
				//	log.info("=========After 1000 ms============");
					javaMailSender.send(welcomeMsg);
				}

				log.info("-------Offer---Done--");
			} catch (Exception e) {
				log.error("-------send order email error-----" + e.getMessage());
			}

			log.info("--------send Email--service done-----");
		}
		else{
			log.error("--Consumer object is null--");
		}
	}
	
	/*
	 * Demo Order Email
	 */
	
	public void DemoNotify(Consumer consumer, Retailer retailer, Offer offer,OrderManagement orders)throws MessagingException{
		final Context ctx = new Context();		
		try{
			ctx.setVariable("image", "http://nukart.in/assets/images/logo.png");
			ctx.setVariable("consumer", consumer);
			ctx.setVariable("product", offer.getProduct());
			ctx.setVariable("retailer", offer.getRetailer());
			ctx.setVariable("order", orders);
			ctx.setVariable("message", environment.getProperty("no.reply.message"));
			ctx.setVariable("state", true);
			ctx.setVariable("demo", false);
			
			if(orders.getState().equals("Cancelled")){
				ctx.setVariable("state", false);
			}
			
			if(offer.getCampaign().getDemo() != null && offer.getCampaign().getDemo().equals("Store")){
				ctx.setVariable("demo", true);
			}
	
			
			if(consumer.getKey().getPrimary() != null){
				ctx.setVariable("isConsumer", true);
				log.info("--Sending Email to consumer" + consumer.getKey().getPrimary());
				final String nukartDemo = this.templateEngine.process("request-demo-template", ctx);
				final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
				final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
				welcomeEmail.setSubject("Product demo request");
				welcomeEmail.setTo(consumer.getKey().getPrimary());
				welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
				welcomeEmail.setText(nukartDemo, true);
				javaMailSender.send(welcomeMsg);
				log.info("--Sent Email to consumer" + consumer.getKey().getPrimary());
			}
			
			
			if(retailer.getKey().getPrimary() != null){
				ctx.setVariable("isConsumer", false);
				log.info("--Sending Email to retailer" + retailer.getKey().getPrimary());
				final String nukartDemo = this.templateEngine.process("request-demo-template", ctx);
				final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
				final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
				//welcomeEmail.setSubject("Requesting for product demo");
				welcomeEmail.setSubject("Product demo request");
				if(environment.getProperty("retailer.send").equals("dev")){
					welcomeEmail.setTo(environment.getProperty("nukart.test.emailid"));
				}
				else{
					welcomeEmail.setTo(consumer.getKey().getPrimary());
				}
				welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
				welcomeEmail.setText(nukartDemo, true);
				javaMailSender.send(welcomeMsg);
				log.info("--Sent Email to consumer" + retailer.getKey().getPrimary());
			}
		}catch (Exception e) {
			log.error("--demo request mail sender exception--" + e.getMessage());
		}
	}
	

	public void send(String to, String subject, String body) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;

		helper = new MimeMessageHelper(message, true);

		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(body, true);
		javaMailSender.send(message);

	}

	public void sendmailvoucher(Voucher voucher, Consumer consumer, Campaign campaign, Retailer retailer,
			Integer points) {
		// log.info("voucher"+voucher+"consumer"+consumer+"campaign"+campaign+"retailer"+retailer);
		try {
			final Context ctx1 = new Context();
			final Context ctx2 = new Context();
			String email = null;
			if (retailer != null) {
				ctx1.setVariable("retailerName", retailer.getName());

				ctx2.setVariable("retailerName", retailer.getName());
				ctx2.setVariable("retailerAddress", retailer.getAddress());
				ctx2.setVariable("retailerContact", retailer.getContact_no());
			}

			if (consumer != null) {
				ctx1.setVariable("consumerName", consumer.getFirstName());

				ctx2.setVariable("consumerName", consumer.getFirstName());
				email = consumer.getKey().getPrimary();
			}
			if (campaign != null) {
				ctx1.setVariable("campaignName", campaign.getName());
				ctx1.setVariable("adId", campaign.getId());

				ctx2.setVariable("campaignName", campaign.getName());
				ctx2.setVariable("productName", campaign.getAd().getProduct().getName());
				ctx2.setVariable("adId", campaign.getId());
			}

			if (voucher != null) {
				ctx1.setVariable("voucherCode", voucher.getCode());

				ctx2.setVariable("voucherCode", voucher.getCode());

				ctx2.setVariable("validity", voucher.getValidity());
				ctx2.setVariable("totalloyaltyPoints", points);
				ctx2.setVariable("loyaltyPoints", voucher.getRedeemedPoints());
			}

			Date date = new Date();
			ctx1.setVariable("Date", date.toString());
			final String nukartContent = this.templateEngine.process("email-simple-voucher", ctx1);
			final MimeMessage nukartMessage = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper nukartmail = new MimeMessageHelper(nukartMessage, "UTF-8");
			nukartmail.setSubject("Alert:Voucher Generated For " + campaign.getName());

			nukartmail.setTo("nukartinfo@gmail.com");
			nukartmail.setCc(environment.getProperty("customercare.emailid"));
			nukartmail.setText(nukartContent, true /* isHtml */);

			Date date1 = new Date();
			ctx2.setVariable("Date", date1.toString());

			ctx1.setVariable("Date", date.toString());

			final String consumerContent = this.templateEngine.process("email-simple-voucher-consumer", ctx2);
			final MimeMessage consumerMessage = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper consumerMail = new MimeMessageHelper(consumerMessage, "UTF-8");
			consumerMail.setSubject("Alert:Voucher Generated For " + campaign.getName());
			consumerMail.setTo(email);
			consumerMail.setCc(environment.getProperty("customercare.emailid"));
			consumerMail.setText(consumerContent, true /* isHtml */);
			try {
				javaMailSender.send(nukartMessage);

			} catch (Exception e) {
				log.info("error in nukart mail" + e.getMessage());
			}

			try {
				javaMailSender.send(consumerMessage);
			} catch (Exception e) {
				log.info("error in consumer mail" + e.getMessage());
			}

		} catch (Exception e) {
			// log.info("voucher"+voucher+"consumer"+consumer+"campaign"+campaign+"retailer"+retailer);
			log.info("Error in" + e.getMessage());
			e.printStackTrace();
		}

	}

	public void sendmail(Consumer consumer, Campaign campaign, Retailer retailer) throws MessagingException {

		final Context ctx = new Context();

		ctx.setVariable("retailerName", retailer.getName());
		ctx.setVariable("consumerName", consumer.getFirstName());
		ctx.setVariable("campaignName", campaign.getName());
		final String htmlContent = this.templateEngine.process("email-simple", ctx);
		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setSubject("Alert:" + campaign.getName() + "    Viewed");

		message.setTo("nusecondinfo@gmail.com");
		message.setText(htmlContent, true /* isHtml */);

		javaMailSender.send(mimeMessage);
	}

	public void test() throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("nukartinfo@gmail.com", "nukart0116"));
		email.setSSL(true);
		email.setFrom("nukartinfo@gmail.com");
		email.setSubject("TestMail");
		// email.setSslSmtpPort("587");

		email.setMsg("This is a test mail ... :-)");
		email.addTo("krishna@nusecond.in");
		email.send();
	}

	public void sendOTP(String id, String otp) throws MessagingException {

		final Context ctx = new Context();

		ctx.setVariable("otp", otp);
		ctx.setVariable("image", "http://nukart.in/assets/images/logo.png");
		final String htmlContent = this.templateEngine.process("otp", ctx);
		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setSubject("One Time Password for nukart");

		message.setTo(id);
		message.setText(htmlContent, true /* isHtml */);

		javaMailSender.send(mimeMessage);
	}

	public void sendEmailLink(String id, String otp) throws MessagingException {
		// TODO Auto-generated method stub
		final Context ctx = new Context();

		String link = "https://suredeal.co/emailverify?email=" + id + "&ID=" + otp;

		ctx.setVariable("link", link);
		ctx.setVariable("image", "http://nukart.in/assets/images/logo.png");

		final String htmlContent = this.templateEngine.process("emaillink", ctx);
		final MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
		message.setSubject("Email verify for your nukart");

		message.setTo(id);
		message.setText(htmlContent, true /* isHtml */);

		javaMailSender.send(mimeMessage);
	}

	/*
	 * Contact nuKart
	 * 
	 * 
	 */
	public Boolean sendEmailTonuKart(EmailnuKart emailnuKart) {
		try {
			log.info("----------Sending Email to nuKart------------");
			final Context context = new Context();
			Date date = new Date();
			context.setVariable("emailDetails", emailnuKart);
			log.info("--details--" + emailnuKart.getEmail() + emailnuKart.getEmailbody() + emailnuKart.getName());
			final String nukartContact = this.templateEngine.process("consumer-nukart", context);
			final MimeMessage consumerMsg = this.javaMailSender.createMimeMessage();
			final MimeMessageHelper consumerEmail = new MimeMessageHelper(consumerMsg, "UTF-8");
			consumerEmail.setSubject("Consumer query from: " + emailnuKart.getEmail());
			consumerEmail.setTo(environment.getProperty("nukart.test.emailid"));
			log.info("--email id---"+ environment.getProperty("nukart.test.emailid"));
			consumerEmail.setText(nukartContact, true);
			javaMailSender.send(consumerMsg);
			log.info("--------Success--------");
			return true;
		} catch (Exception e) {
			log.error("--------Error to send welcome email--------" + e.getMessage());
			return false;
		}
	}

	/*
	 * test email
	 */

	public void sendTestEmail() throws MessagingException, IOException {
		log.info("---Service-Called-----------");
		final Context context = new Context();
		context.setVariable("name", "Kishore");
		context.setVariable("image", "http://nukart.in/assets/images/logo.png");
		final String nukartWelcome = this.templateEngine.process("email-welcome", context);
		final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
		final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
		welcomeEmail.setSubject("Welcome to nuKart");
		welcomeEmail.setTo("kishore@nusecond.com");
		welcomeEmail.setText(nukartWelcome, true);

		javaMailSender.send(welcomeMsg);

		log.info("---Service-Completed-----------");
	}
	
	/*
	 * request phn call
	 */
	public Boolean requestCallFromConsumer	(Integer consumerId,Integer campaignID,Integer retailerID){
		try{
			Consumer consumer = notificationService.consumerDetails(consumerId);
			
			if(consumer != null){
				Retailer retailer = new Retailer();
				Product product = new Product();
				final Context context = new Context();
				context.setVariable("consumer", consumer);
				context.setVariable("name", consumer.getFirstName());
				context.setVariable("image", "http://nukart.in/assets/images/logo.png");
				context.setVariable("message", environment.getProperty("no.reply.message"));
				
				if(campaignID != null){
					log.info("---Product based query-email-started executing-");
						Campaign campaign = notificationService.campaignDetails(campaignID);
						if(campaign != null){
							if(campaign.getAd() != null){
								if(campaign.getAd().getProduct() != null){
									product = notificationService.productDetails(campaign.getAd().getProduct().getId());			
									context.setVariable("product", product);
								}
							}
							if(campaign.getRetailer() != null){
								log.info("Retailer exists in campaign");
								retailer = notificationService.retailerDetails(campaign.getRetailer().getId());
								log.info("Retailer name" + retailer.getName());							
								context.setVariable("retailer", retailer);
							}//-------------Email Consumer
							
							if(product != null & retailer != null){
								log.info("product and retailer not null");
								if(consumer.getKey().getPrimary() != null){
									log.info("-------Sending email to consumer--" + consumer.getKey().getPrimary());
									context.setVariable("isretailer", false);
									context.setVariable("isconsumer",true);
									final String nukartWelcome = this.templateEngine.process("email-request-call", context);
									final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
									final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
									welcomeEmail.setSubject("Product enquiry call request");
									welcomeEmail.setTo(consumer.getKey().getPrimary());
									welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
									welcomeEmail.setText(nukartWelcome, true);
									
									javaMailSender.send(welcomeMsg);
									
									
									log.info("-------Email/SMS sent to consumer--" + consumer.getKey().getPrimary() + consumer.getKey().getSecondary());
								}
								
								//-------------Email Retailer
								if(retailer.getKey().getPrimary() != null){
									log.info("-------Sending email to retailer--" + retailer.getKey().getPrimary());
									context.setVariable("isretailer", true);
									context.setVariable("isconsumer",false);
									final String retailerWelcome = this.templateEngine.process("email-request-call", context);
									final MimeMessage retailerMsg = this.javaMailSender.createMimeMessage();
									final MimeMessageHelper retailerEmail = new MimeMessageHelper(retailerMsg, "UTF-8");
									retailerEmail.setSubject("Product enquiry call request");
									if(environment.getProperty("retailer.send").equals("dev")){
										retailerEmail.setTo(environment.getProperty("nukart.test.emailid"));
									}
									else{
										retailerEmail.setTo(retailer.getKey().getPrimary());
									}
									retailerEmail.setReplyTo(environment.getProperty("no.reply"));
									retailerEmail.setText(retailerWelcome, true);
									javaMailSender.send(retailerMsg);
									
									
									log.info("-------Email sent to consumer--" + retailer.getKey().getPrimary());
								}
							}
							
							log.info("---Product based query--email completed execution-");
							return true;				
							}
						else{
							log.error("---Product or Retailer  object is null---");
							return false;
						}
				}
				else if(retailerID != null){
					log.info("---Store based query-email-started executing-");
						retailer = notificationService.retailerDetails(retailerID);			
						context.setVariable("retailer", retailer);
						if(retailer != null){
							//-------------Email Consumer
							if(consumer.getKey().getPrimary() != null){
								log.info("-------Sending email to consumer--" + consumer.getKey().getPrimary());
								context.setVariable("isretailer", false);
								context.setVariable("isconsumer",true);
								final String nukartWelcome = this.templateEngine.process("email-request-call", context);
								final MimeMessage welcomeMsg = this.javaMailSender.createMimeMessage();
								final MimeMessageHelper welcomeEmail = new MimeMessageHelper(welcomeMsg, "UTF-8");
								welcomeEmail.setSubject("Product enquiry call request");
								welcomeEmail.setTo(consumer.getKey().getPrimary());
								welcomeEmail.setReplyTo(environment.getProperty("no.reply"));
								welcomeEmail.setText(nukartWelcome, true);
								javaMailSender.send(welcomeMsg);
								
								log.info("-------Email sent to consumer--" + consumer.getKey().getPrimary());
							}
							
							//-------------Email Retailer
							if(retailer.getKey().getPrimary() != null){
								log.info("-------Sending email to retailer--" + retailer.getKey().getPrimary());
								context.setVariable("isretailer", true);
								context.setVariable("isconsumer",false);
								final String retailerWelcome = this.templateEngine.process("email-request-call", context);
								final MimeMessage retailerMsg = this.javaMailSender.createMimeMessage();
								final MimeMessageHelper retailerEmail = new MimeMessageHelper(retailerMsg, "UTF-8");
								retailerEmail.setSubject("Product enquiry call request");
								if(environment.getProperty("retailer.send").equals("dev")){
									retailerEmail.setTo(environment.getProperty("nukart.test.emailid"));
								}
								else{
									retailerEmail.setTo(retailer.getKey().getPrimary());
								}
								retailerEmail.setReplyTo(environment.getProperty("no.reply"));
								retailerEmail.setText(retailerWelcome, true);
								javaMailSender.send(retailerMsg);
								
								
								log.info("-------Email sent to consumer--" + retailer.getKey().getPrimary());
							}
							
						log.info("---Store based query-email-completed execution-");
						return true;
						}else{
							log.error("---Retailer object is null---");
							return false;
						}
				}
				
				else{
					log.error("-Both-CampaignID/RetailerID null");
					return false;
				}
				
		
			}
			
			else{
				log.error("Icare is not returning consumer object");
				return false;
			}
			
		}catch (Exception e) {
			log.error("---Product based query Exception---" + e.getMessage());
			return false;
		}
	}
	/*
	 * Request to add new product model
	 */
	
		public Boolean addNewProduct(NotificationModel model) throws MessagingException{
			log.info("---------Sending add product request email to---"+environment.getProperty("nukart.test.emailid"));
			final Context context = new Context();
			context.setVariable("image", "http://nukart.in/assets/images/logo.png");
			context.setVariable("message", environment.getProperty("no.reply.message"));
			context.setVariable("model",model);
			try{
				final String proReqTemp = this.templateEngine.process("email-pro-req", context);
				final MimeMessage proReqMsg = this.javaMailSender.createMimeMessage();
				final MimeMessageHelper proReqEmail = new MimeMessageHelper(proReqMsg, "UTF-8");
				proReqEmail.setSubject(model.getName()  + " has requested to add new product");
				proReqEmail.setTo(environment.getProperty("nukart.test.emailid"));
				proReqEmail.setReplyTo(environment.getProperty("no.reply"));
				proReqEmail.setText(proReqTemp, true);
				javaMailSender.send(proReqMsg);
				log.info("----Add product request-----Email sent to ----"+environment.getProperty("nukart.test.emailid"));
				return true;
			}catch (Exception e) {
				log.error("Add product model exception "  + e.getMessage());
				return false;
			}
		}

}

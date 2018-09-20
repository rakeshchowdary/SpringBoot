package com.nusecond.suredeal.microservice.notification.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;
import com.nusecond.suredeal.microservice.notification.order.Offer;
import com.nusecond.suredeal.microservice.notification.order.OrderManagement;
import com.nusecond.suredeal.microservice.notification.service.NotificationService;

@Controller
public class WebController {
	
	/** The log. */
	static Logger log = Logger.getLogger(WebController.class.getName());
	
	Gson gson = new Gson();
	
	@Autowired NotificationService notificationService;
	
	@Inject
	private Environment environment;
	
@RequestMapping(value="/notify",method=RequestMethod.GET)
public String notificationList(@RequestParam Integer consumerId,Model model){
        
        log.info("----Entered------Web Controller----------------");
        List<OrderManagement> orderList = new ArrayList<>();
        Offer offer = new Offer();
       List<Offer> off = new ArrayList<>();
       
        try{
            
            orderList = notificationService.getNotification(consumerId);
            log.info("----Orderlist size------------" + orderList.size());
            
            if(orderList.size() != 0){
                log.info("--------Entered IF-------");
                Consumer consumer =    notificationService.consumerDetails(orderList.get(0).getConsumerId());
                log.info("----------Consumer -------" + consumer.getFirstName());
                
                for(int i=0;i<orderList.size();i++){
                    OrderManagement orderManagement = orderList.get(i);//orderManagement
                    String CartDetails = orderManagement.getCart();
                    offer = gson.fromJson(CartDetails, Offer.class);
                    off.add(offer);
                }
                String url = environment.getProperty("domain.name");
                url = "https://"+url+"/trackorder?id=";
                model.addAttribute("domain",url);
                model.addAttribute("orders",orderList);
                model.addAttribute("offers",off);
                model.addAttribute("consumer",consumer);
            }
            
            else{
                log.info("--------Entered ELSE-------");
                model.addAttribute("size","Zero");
            }
            
            log.info("----Done------Web Controller----------------");
            return "email-notification";
        }catch(Exception e){
            log.error("-----Error-------" + e.getMessage());
        }        
        
        return "email-notification";
    }

}

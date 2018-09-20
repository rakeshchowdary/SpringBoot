package com.nusecond.suredeal.microservice.notification.consumer.model;


import java.util.List;
import com.nusecond.suredeal.microservice.notification.campaign.model.Campaign;
import com.nusecond.suredeal.microservice.notification.order.OrderManagement;

public class NotifyPojo {
	
	private List<String> emailid;
	private Campaign campaign;
	private Consumer consumer;
	private String notifyToken;
	private OrderManagement orderManagement;
	
	public OrderManagement getOrderManagement() {
		return orderManagement;
	}
	public void setOrderManagement(OrderManagement orderManagement) {
		this.orderManagement = orderManagement;
	}
	public String getNotifyToken() {
		return notifyToken;
	}
	public void setNotifyToken(String notifyToken) {
		this.notifyToken = notifyToken;
	}
	public Consumer getConsumer() {
		return consumer;
	}
	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	public List<String> getEmailid() {
		return emailid;
	}
	public void setEmailid(List<String> emailid) {
		this.emailid = emailid;
	}
}

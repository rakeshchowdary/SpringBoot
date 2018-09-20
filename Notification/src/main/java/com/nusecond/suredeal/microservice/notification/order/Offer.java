package com.nusecond.suredeal.microservice.notification.order;

import java.io.Serializable;

import com.nusecond.suredeal.microservice.notification.campaign.model.Campaign;
import com.nusecond.suredeal.microservice.notification.consumer.model.Consumer;
import com.nusecond.suredeal.microservice.notification.retailer.model.Retailer;

public class Offer implements Serializable{
	
	
	/**
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	

	

	/**
	 * 
	 */


	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Offer() {
	
	}

	public Retailer getRetailer() {
		return retailer;
	}

	public void setRetailer(Retailer retailer) {
		this.retailer = retailer;
	}

	private Campaign campaign;
	
	private Retailer retailer;
	
	private Product product;
	

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	private Consumer consumer;
	
	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	private int purchasedQuantity;
	
	public int getPurchasedQuantity() {
		return purchasedQuantity;
	}
	public void setPurchasedQuantity(int purchasedQuantity) {
		this.purchasedQuantity = purchasedQuantity;
	}

	public Offer(Campaign campaign,Product product,Retailer retailer,int purchasedQuantity) {

		this.campaign = campaign;
		this.product=product;
		this.retailer=retailer;
		this.purchasedQuantity = purchasedQuantity;
	}
}
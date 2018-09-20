package com.nusecond.suredeal.microservice.notification.voucher.model;


import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// TODO: Auto-generated Javadoc
/**
 * The Class Voucher.
 */
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "id",
    "code",
    "terms",
    "min amount",
    "campaign id",
    "ad id",
    "retailer id",
    "consumer id",
    "validity",
    "created",
    "modified",
    "redeemed Points",
    "redeemed Amount",
    "status"
    
})
public class Voucher  implements Comparable<Voucher> ,  Serializable {

	/** The Constant serialVersionUID. */
private static final long serialVersionUID = 1L;

	/** The Constant serialVersionUID. */
	/** The id. */
	@JsonProperty("id")
	
    private Integer id;
	
	/** The code. */
	@JsonProperty("code")
    private String code;
	
	/** The validity. */
	@JsonProperty("validity")
    private Date validity;
	
	/** The terms. */
	@JsonProperty("terms")
    private String terms;
	
	/** The min amount. */
	@JsonProperty("min amount")
    private Integer minAmount;
	
	/** The campaign id. */
	@JsonProperty("campaign id")
    private Integer campaignId;
	
	/** The ad id. */
	@JsonProperty("ad id")
    private Integer adId;

	/** The retailer id. */
	@JsonProperty("retailer id")
    private Integer retailerId;
	
	/** The consumer id. */
	@JsonProperty("consumer id")
    private Integer consumerId;
	
	/** The created. */
	@JsonProperty("created")
    private Date created;
	
	
	/** The redeemed points. */
    private Integer redeemedPoints;
	
	
	/** The redeemed amount. */
    private Integer redeemedAmount;
	
	/** The status. */
    private String status;
	
	/** The consumerNotify. */
	private String consumerNotify;

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	 protected void onCreate()  {
	   modified = created =new Date();
	   Date m = new Date();
	   Calendar cal = Calendar.getInstance();  
	   cal.setTime(m);  
	   cal.add(Calendar.DATE, 7);   
	   m = cal.getTime();   
	   validity=m;
	   terms="ONLY FOR ONLINE PAYMENTS";
	    	
	    }

	    /**
	     * On update.
	     */
	    protected void onUpdate() {
	    modified =new Date();
	    }

	
	     /**
	      * Gets the modified.
	      *
	      * @return the modified
	      */
	    public Date getModified() {
	    return modified;
	    	}
	
	/**
	 * Sets the modified.
	 *
	 * @param modified the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}


	/** The modified. */
	@JsonProperty("modified")
    private Date modified;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@JsonProperty("id")
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	@JsonProperty("code")
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * Gets the validity.
	 *
	 * @return the validity
	 */
	@JsonProperty("validity")
	public Date getValidity() {
		return validity;
	}
	
	/**
	 * Sets the validity.
	 *
	 * @param validity the new validity
	 */
	@JsonProperty("validity")
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	
	/**
	 * Gets the terms.
	 *
	 * @return the terms
	 */
	@JsonProperty("terms")
	public String getTerms() {
		return terms;
	}
	
	/**
	 * Sets the terms.
	 *
	 * @param terms the new terms
	 */
	@JsonProperty("terms")
	public void setTerms(String terms) {
		this.terms = terms;
	}
	
	/**
	 * Gets the min amount.
	 *
	 * @return the min amount
	 */
	@JsonProperty("min amount")
	public Integer getMinAmount() {
		return minAmount;
	}
	
	/**
	 * Sets the min amount.
	 *
	 * @param minAmount the new min amount
	 */
	@JsonProperty("min amount")
	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}
	
	/**
	 * Gets the campaign id.
	 *
	 * @return the campaign id
	 */
	@JsonProperty("campaign id")
	public Integer getCampaignId() {
		return campaignId;
	}
	
	/**
	 * Sets the campaign id.
	 *
	 * @param campaignId the new campaign id
	 */
	@JsonProperty("campaign id")
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
	
	/**
	 * Gets the ad id.
	 *
	 * @return the ad id
	 */
	@JsonProperty("ad id")
	public Integer getAdId() {
		return adId;
	}
	
	/**
	 * Sets the ad id.
	 *
	 * @param adId the new ad id
	 */
	@JsonProperty("ad id")
	public void setAdId(Integer adId) {
		this.adId = adId;
	}
	
	/**
	 * Gets the retailer id.
	 *
	 * @return the retailer id
	 */
	@JsonProperty("retailer id")
	public Integer getRetailerId() {
		return retailerId;
	}
	
	/**
	 * Sets the retailer id.
	 *
	 * @param retailerId the new retailer id
	 */
	@JsonProperty("retailer id")
	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}
	
	/**
	 * Gets the consumer id.
	 *
	 * @return the consumer id
	 */
	@JsonProperty("consumer id")
	public Integer getConsumerId() {
		return consumerId;
	}
	
	/**
	 * Sets the consumer id.
	 *
	 * @param consumerId the new consumer id
	 */
	@JsonProperty("consumer id")
	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	
	
	/**
	 * Gets the redeemed points.
	 *
	 * @return the redeemed points
	 */
	public Integer getRedeemedPoints() {
		return redeemedPoints;
	}

	/**
	 * Sets the redeemed points.
	 *
	 * @param redeemedPoints the new redeemed points
	 */
	public void setRedeemedPoints(Integer redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}

	
	/**
	 * Gets the redeemed amount.
	 *
	 * @return the redeemed amount
	 */
	public Integer getRedeemedAmount() {
		return redeemedAmount;
	}

	/**
	 * Sets the redeemed amount.
	 *
	 * @param redeemedAmount the new redeemed amount
	 */
	public void setRedeemedAmount(Integer redeemedAmount) {
		this.redeemedAmount = redeemedAmount;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the consumerNotify.
	 *
	 * @return the consumerNotify
	 */
	public String getConsumerNotify() {
		return consumerNotify;
	}

	/**
	 * Sets the consumerNotify.
	 *
	 * @param the String consumerNotify
	 */
	public void setConsumerNotify(String consumerNotify) {
		this.consumerNotify = consumerNotify;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Voucher arg0) {
		// TODO Auto-generated method stub
		int id=((Voucher)arg0).getId();
		return this.id-id;
	}

	
	
}
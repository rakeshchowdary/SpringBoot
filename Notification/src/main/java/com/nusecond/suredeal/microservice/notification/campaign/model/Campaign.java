/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.campaign.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Campaign.
 */
@Generated("org.jsonschema2pojo")

public class Campaign implements Serializable  {

	/**
 * 
 */
private static final long serialVersionUID = 1L;
	/** The id. */
	
    private Integer id;
	
	/** The name. */
	
    private String name;
    
    private Double salePrice;
	
	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	/** The targeted consumer. */
	
    private String targetedConsumer;
	
	/** The catagory. */
	
 

    private String category;
	
	
    private String subCategory;
	
  public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	/** The Type . */
	
	private String type;
	
	/** The Subscription. */
	private String subscription;
	
	/** The retailer. */
    private Retailer retailer;
	
	/** The location. */
    private Location location;
    
    /** The image. */
    private String image;
    
    /** The video. */
    private String video;
    
    /** The ad. */
    private Ad ad;
    
    /** The created. */
    private Date created;
    
    private String timeSlots;
    
    public String getTimeSlots() {
		return timeSlots;
	}

	public void setTimeSlots(String timeSlots) {
		this.timeSlots = timeSlots;
	}

    private String demo;

	public String getDemo() {
		return demo;
	}

	public void setDemo(String demo) {
		this.demo = demo;
	}
    
	
	
	private String freebie;
	

	public String getFreebie() {
		return freebie;
	}

	public void setFreebie(String freebie) {
		this.freebie = freebie;
	}
	
	
    private String speciality;
	
	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	
	
	
    /** The modified. */
    private Date modified;
    
    /**
     * On create.
     */
    
    protected void onCreate()  
     {
    	modified = created =new Date();
     	}

    /**
     * On update.
     */
    
    protected void onUpdate() 
      {
    	modified =new Date();
      	}

    /**
     * Gets the id.
     *
     * @return     The id
     */
    public Integer getId() 
      {
        return id;
      	}

    /**
     * Sets the id.
     *
     * @param id     The id
     */
    public void setId(Integer id) 
      {
        this.id = id;
      	}

    /**
     * Gets the name.
     *
     * @return     The name
     */
    public String getName() 
      {
        return name;
      	}

    /**
     * Sets the name.
     *
     * @param name     The name
     */
    public void setName(String name) 
      {
        this.name = name;
      	}

    /**
     * Gets the targeted consumer.
     *
     * @return     The targetedConsumer
     */
    public String getTargetedConsumer() 
      {
        return targetedConsumer;
      	}

    /**
     * Sets the targeted consumer.
     *
     * @param targetedConsumer     The targetedConsumer
     */
    public void setTargetedConsumer(String targetedConsumer) 
      {
        this.targetedConsumer = targetedConsumer;
      	}

    /**
     * Gets the catagory.
     *
     * @return     The catagory
     */
   
    /**
     * Sets the sub catagory.
     *
     * @param subCatagory     The subCatagory
     */
  

    /**
     * Gets the type.
     *
     * @return     The type
     */
    public String getType() {
		return type;
	}

    /**
     * Sets the type.
     *
     * @param type     The type
     */
	public void setType(String type) {
		this.type = type;
	}

	/**
     * Gets the subscription.
     *
     * @return     The subscription
     */
	public String getSubscription() {
		return subscription;
	}

	 /**
     * Sets the subscription.
     *
     * @param subscription     The subscription
     */
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	/**
     * Gets the retailer.
     *
     * @return     The retailer
     */
    public Retailer getRetailer() 
      {
        return retailer;
      	}

    /**
     * Sets the retailer.
     *
     * @param retailer     The retailer
     */
    public void setRetailer(Retailer retailer) 
      {
        this.retailer = retailer;
      	}

    /**
     * Gets the location.
     *
     * @return     The location
     */
    public Location getLocation() 
      {
        return location;
      	}

    /**
     * Sets the location.
     *
     * @param location     The location
     */
    public void setLocation(Location location) 
      {
        this.location = location;
      	}

    /**
     * Gets the image.
     *
     * @return     The image
     */
    public String getImage() 
      {
        return image;
      	}

    /**
     * Sets the image.
     *
     * @param image     The image
     */
    public void setImage(String image) 
      {
        this.image = image;
      	}

    /**
     * Gets the video.
     *
     * @return     The video
     */
    public String getVideo() 
      {
        return video;
      	}

    /**
     * Sets the video.
     *
     * @param video     The video
     */
    public void setVideo(String video) 
      {
        this.video = video;
      	}

    /**
     * Gets the ad.
     *
     * @return     The ad
     */
    public Ad getAd() 
      {
        return ad;
      	}

    /**
     * Sets the ad.
     *
     * @param ad     The ad
     */
    public void setAd(Ad ad) 
      {
        this.ad = ad;
      	}

    /**
     * Gets the created.
     *
     * @return     The created
     */
    public Date getCreated() 
      {
        return created;
    		}

    /**
     * Sets the created.
     *
     * @param created     The created
     */
    public void setCreated(Date created) 
      {
        this.created = created;
      	}

    /**
     * Gets the modified.
     *
     * @return     The modified
     */
    public Date getModified() 
      {
        return modified;
      	}
    private String mobileImage;
    
    public String getMobileImage() {
		return mobileImage;
	}

	public void setMobileImage(String mobileImage) {
		this.mobileImage = mobileImage;
	}
    
    /**
     * Sets the modified.
     *
     * @param modified     The modified
     */
    public void setModified(Date modified) 
      {
        this.modified = modified;
      	}
    }

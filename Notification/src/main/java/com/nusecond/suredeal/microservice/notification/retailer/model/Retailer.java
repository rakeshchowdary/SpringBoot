package com.nusecond.suredeal.microservice.notification.retailer.model;

import java.io.Serializable;
import java.util.Date;




// TODO: Auto-generated Javadoc
/**
 * The Class Retailer.
 */
public class Retailer implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	
    /** The id. */
    private Integer id;

	/** The name. */
	private String name;
	
	/**subscription**/
	private String subscription;
	
	public String getSubscription() {
		return subscription;
	}

	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
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
 	public void setId(Integer id) {
	        this.id = id;
	    }
	

	/** The address. */
	private String address;
	
	/** The contact no. */
	private String contact_no;
	
	/** The email. */
	private String email;
	
	/** The contact person. */
	private String contact_person;
	
    
    /** The key. */
    private Key key;
    
    /**
     * Gets the key.
     *
     * @return the key
     */
    public Key getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(Key key) {
		this.key = key;
	}
	
	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}
	
	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}
	
	/**
	 * Gets the deliver option.
	 *
	 * @return the deliver option
	 */
	public DeliverOption getDeliverOption() {
		return deliverOption;
	}
	
	/**
	 * Sets the deliver option.
	 *
	 * @param deliverOption the new deliver option
	 */
	public void setDeliverOption(DeliverOption deliverOption) {
		this.deliverOption = deliverOption;
	}


    /** The location. */
    private Location location;
    
	/** The deliver option. */
	private DeliverOption deliverOption;
	
	/** The image url. */
	private String image_url;
	
	/** The sub title. */
	private String sub_title;
	
	/** The rating. */
	private String rating;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the contact no.
	 *
	 * @return the contact no
	 */
	public String getContact_no() {
		return contact_no;
	}

	/**
	 * Sets the contact no.
	 *
	 * @param contact_no the new contact no
	 */
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the contact person.
	 *
	 * @return the contact person
	 */
	public String getContact_person() {
		return contact_person;
	}

	/**
	 * Sets the contact person.
	 *
	 * @param contact_person the new contact person
	 */
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}



	/**
	 * Gets the image url.
	 *
	 * @return the image url
	 */
	public String getImage_url() {
		return image_url;
	}

	/**
	 * Sets the image url.
	 *
	 * @param image_url the new image url
	 */
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	/**
	 * Gets the sub title.
	 *
	 * @return the sub title
	 */
	public String getSub_title() {
		return sub_title;
	}

	/**
	 * Sets the sub title.
	 *
	 * @param sub_title the new sub title
	 */
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	/**
	 * Gets the rating.
	 *
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating the new rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets the owner.
	 *
	 * @param owner the new owner
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * Gets the business type.
	 *
	 * @return the business type
	 */
	public String getBusiness_type() {
		return business_type;
	}

	/**
	 * Sets the business type.
	 *
	 * @param business_type the new business type
	 */
	public void setBusiness_type(String business_type) {
		this.business_type = business_type;
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

	/** The owner. */
	private String owner;
	
	/** The business type. */
	private String business_type;
	
	/** The status. */
	private String status;
	
	/** The is OEM. */
	private String isOEM;
	
	/** The created by. */
	private String createdBy;
	
	/** The modified by. */
	private String modifiedBy;
	
	
	
/** The created. */
    
    private Date created;
    
    /** The modified. */
    
    private Date modified;
    
    
    /** The store 360. */
    private String store360;
    
    
	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
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
	
	/**
	 * Gets the checks if is OEM.
	 *
	 * @return the checks if is OEM
	 */
	public String getIsOEM() {
		return isOEM;
	}
	
	/**
	 * Sets the checks if is OEM.
	 *
	 * @param isOEM the new checks if is OEM
	 */
	public void setIsOEM(String isOEM) {
		this.isOEM = isOEM;
	}
	
	
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	
	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	/**
	 * Gets the store 360.
	 *
	 * @return the store 360
	 */
	public String getStore360() {
		return store360;
	}
	
	/**
	 * Sets the store 360.
	 *
	 * @param store360 the new store 360
	 */
	public void setStore360(String store360) {
		this.store360 = store360;
	}

	/** The store video. */
	
	private String storeVideo;

	public String getStoreVideo() {
		return storeVideo;
	}

	public void setStoreVideo(String storeVideo) {
		this.storeVideo = storeVideo;
	}
	
	
	/** The productSale. */
	
	private String productSale;

	public String getProductSale() {
		return productSale;
	}

	public void setProductSale(String productSale) {
		this.productSale = productSale;
	}

	
	
	

}

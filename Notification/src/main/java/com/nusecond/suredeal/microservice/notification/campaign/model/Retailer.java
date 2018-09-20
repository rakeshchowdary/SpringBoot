/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.campaign.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Retailer.
 */
@Generated("org.jsonschema2pojo")
public class Retailer implements Serializable  {

	/**
 * 
 */
private static final long serialVersionUID = 1L;

	/** The retailer id. */
    private Integer Id;
	
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
     * Gets the id.
     *
     * @return     The id
     */
    public Integer getId() {
		return Id;
	}
    
    /**
     * Sets the id.
     *
     * @param id     The id
     */

	public void setId(Integer id) {
		Id = id;
	}

	/**
     * Gets the name.
     *
     * @return     The name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name     The name
     */
    public void setName(String name) {
        this.name = name;
    }


    
}

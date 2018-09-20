/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.campaign.model;

import java.io.Serializable;

import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Discount.
 */
@Generated("org.jsonschema2pojo")

public class Discount implements Serializable  {

	/**
 * 
 */
private static final long serialVersionUID = 1L;

	/** The type. */
    private String type;
	
	/** The percentage. */
    private Double percentage;

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
     * Gets the percentage.
     *
     * @return     The percentage
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * Sets the percentage.
     *
     * @param percentage     The percentage
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

}

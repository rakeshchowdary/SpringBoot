/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.campaign.model;

import java.io.Serializable;

import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 */
@Generated("org.jsonschema2pojo")
public class Product implements Serializable  {

	/**
 * 
 */
private static final long serialVersionUID = 1L;


	/** The id. */
    private Integer Id;
	
	/** The  name. */
    private String name;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

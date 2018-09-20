/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.order;
/**
 * @author Krishnamorthi Palanisamy
 *
 */

import java.io.Serializable;



import com.fasterxml.jackson.annotation.JsonProperty;

// TODO: Auto-generated Javadoc
/**
 * The Class CLocation.
 */
public class Location implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The idloc. */
	

	/** The nameloc. */
	@JsonProperty
	private String conName;
	@JsonProperty
	private String address1;
	
	@JsonProperty
	private String landmark;

	

	/** The area. */
	@JsonProperty
	private Integer pinCode;

	/** The city. */
	@JsonProperty
	private String mobile;
	
	@JsonProperty
	private String area;

	/** The state. */
	@JsonProperty
	private String city;

	/** The pin code. */
	

	/** The country. */
	@JsonProperty
	private String locationState;

	
	
	

	

	public String getLocationState() {
		return locationState;
	}

	public void setLocationState(String locationState) {
		this.locationState = locationState;
	}

	public String getConName() {
		return conName;
	}

	public void setConName(String conName) {
		this.conName = conName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Integer getPinCode() {
		return pinCode;
	}

	public void setPinCode(Integer pinCode) {
		this.pinCode = pinCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

	
	
	
	
	
	
	

}

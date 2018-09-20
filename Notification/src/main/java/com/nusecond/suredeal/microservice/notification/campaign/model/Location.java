/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.campaign.model;

import java.io.Serializable;

import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Location.
 */
@Generated("org.jsonschema2pojo")

public class Location implements Serializable  {

	/**
 * 
 */
private static final long serialVersionUID = 1L;

	/** The id. */
    private Integer id;
	
	/** The name. */
    private String name;
	
	/** The area. */
    private String area;
	
	/** The city. */
    private String city;
	
	/** The state. */
    private String state;
	
	/** The pin code. */
    private Integer pinCode;
	
	/** The country. */
    private String country;
	
	/** The longitude. */
    private String longitude;
	
	/** The lattitude. */
	private String latitude;

    /**
     * Gets the id.
     *
     * @return     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id     The id
     */
    public void setId(Integer id) {
        this.id = id;
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

    /**
     * Gets the area.
     *
     * @return     The area
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the area.
     *
     * @param area     The area
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * Gets the city.
     *
     * @return     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state.
     *
     * @return     The state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state.
     *
     * @param state     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the pin code.
     *
     * @return     The pinCode
     */
    public Integer getPinCode() {
        return pinCode;
    }

    /**
     * Sets the pin code.
     *
     * @param pinCode     The pinCode
     */
    public void setPinCode(Integer pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * Gets the country.
     *
     * @return     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country.
     *
     * @param country     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets the longitude.
     *
     * @return     The longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude.
     *
     * @param longitude     The longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


    

}

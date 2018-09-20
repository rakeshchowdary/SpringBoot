/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.consumer.model;
/**
 * @author Krishnamorthi Palanisamy
 *
 */

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class PrefAffiAttribute.
 */
public class PresAffiAttribute implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The product. */
	private String product;

	/** The brand. */
	private String brand;

	/** The make. */
	private String make;

	/**
	 * Gets the product.
	 *
	 * @return The product
	 */
	public String getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product
	 *            The product
	 */
	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 * Gets the brand.
	 *
	 * @return The brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Sets the brand.
	 *
	 * @param brand
	 *            The brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Gets the make.
	 *
	 * @return The make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Sets the make.
	 *
	 * @param make
	 *            The make
	 */
	public void setMake(String make) {
		this.make = make;
	}

}

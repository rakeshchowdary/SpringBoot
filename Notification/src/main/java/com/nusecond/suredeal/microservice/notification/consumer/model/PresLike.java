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
 * The Class PrefLike.
 */
public class PresLike implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The color. */
	private String key;

	/** The size. */
	private String value;

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}

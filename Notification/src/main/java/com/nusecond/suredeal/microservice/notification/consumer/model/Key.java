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
 * The Class Key.
 */
public class Key implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The primarykey. */
	private String primary;

	/** The secondary. */
	private String secondary;

	/**
	 * Gets the primary.
	 *
	 * @return The primary
	 */
	public String getPrimary() {
		return primary;
	}

	/**
	 * Sets the primary.
	 *
	 * @param primary
	 *            The primary
	 */
	public void setPrimary(String primary) {
		this.primary = primary;
	}

	/**
	 * Gets the secondary.
	 *
	 * @return The secondary
	 */
	public String getSecondary() {
		return secondary;
	}

	/**
	 * Sets the secondary.
	 *
	 * @param secondary
	 *            The secondary
	 */
	public void setSecondary(String secondary) {
		this.secondary = secondary;
	}

}

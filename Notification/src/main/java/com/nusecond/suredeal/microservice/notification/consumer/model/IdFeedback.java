package com.nusecond.suredeal.microservice.notification.consumer.model;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class IdFeedback.
 */
public class IdFeedback implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The consumer id. */
	public Integer consumerId;

	/** The campaign id. */
	public Integer campaignId;

	/**
	 * Gets the consumer id.
	 *
	 * @return the consumer id
	 */
	public Integer getConsumerId() {
		return consumerId;
	}

	/**
	 * Gets the campaign id.
	 *
	 * @return the campaign id
	 */
	public Integer getCampaignId() {
		return campaignId;
	}

	/**
	 * Sets the consumer id.
	 *
	 * @param consumerId
	 *            the new consumer id
	 */
	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	/**
	 * Sets the campaign id.
	 *
	 * @param campaignId
	 *            the new campaign id
	 */
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}
}

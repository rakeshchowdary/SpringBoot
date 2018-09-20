package com.nusecond.suredeal.microservice.notification.consumer.model;

import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class Feedback.
 */
public class Feedback {

	/** The consumer id. */
	private Integer consumerId;

	/** The campaign id. */
	private Integer campaignId;

	/** The ad id. */
	private Integer adId;

	/** The product. */
	private Product product;

	/** The rating. */
	private Double rating;

	/** The comment. */
	private String comment;

	/** The created. */
	private Date created;

	/** The modified. */
	private Date modified;

	/**
	 * Gets the consumer id.
	 *
	 * @return The consumerId
	 */
	public Integer getConsumerId() {
		return consumerId;
	}

	/**
	 * Sets the consumer id.
	 *
	 * @param consumerId
	 *            The consumerId
	 */
	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	/**
	 * Gets the campaign id.
	 *
	 * @return The campaignId
	 */
	public Integer getCampaignId() {
		return campaignId;
	}

	/**
	 * Sets the campaign id.
	 *
	 * @param campaignId
	 *            The campaignId
	 */
	public void setCampaignId(Integer campaignId) {
		this.campaignId = campaignId;
	}

	/**
	 * Gets the ad id.
	 *
	 * @return The adId
	 */
	public Integer getAdId() {
		return adId;
	}

	/**
	 * Sets the ad id.
	 *
	 * @param adId
	 *            The adId
	 */
	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	/**
	 * Gets the product.
	 *
	 * @return The product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product
	 *            The product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Gets the rating.
	 *
	 * @return The rating
	 */
	public Double getRating() {
		return rating;
	}

	/**
	 * Sets the rating.
	 *
	 * @param rating
	 *            The rating
	 */
	public void setRating(Double rating) {
		this.rating = rating;
	}

	/**
	 * Gets the comment.
	 *
	 * @return The comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment
	 *            The comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * On create.
	 *
	 * @return The created
	 */

	/**
	 * 
	 * @return The modified
	 */

	/**
	 * 
	 * @param modified
	 *            The modified
	 */

	protected void onCreate() {
		modified = created = new Date();
	}

	/**
	 * On update.
	 */
	protected void onUpdate() {
		modified = new Date();
	}

}

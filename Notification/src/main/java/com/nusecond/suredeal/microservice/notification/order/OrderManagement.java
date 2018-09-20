
package com.nusecond.suredeal.microservice.notification.order;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Transient;

import org.springframework.core.annotation.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({ "id", "state", "created", "modified","cart","consumerId" })

public class OrderManagement implements Comparable<OrderManagement>, Serializable {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderManagement other = (OrderManagement) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 4256464694078512928L;
	/**
	 * 
	 */
	
	@JsonProperty("id")

	private Integer id;
	@JsonProperty("state")
	private String state;
	
	/*@JsonProperty("order_id")
	@Column(name="order_id", nullable=false, unique=true, insertable = false,    updatable = false,
	        columnDefinition = "BIGINT DEFAULT nextval('order_idgen_seq')"
	)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq_gen")
    @SequenceGenerator(name = "order_seq_gen", sequenceName ="order_idgen_seq")
	private String orderId;
	
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}*/
	@JsonProperty("order_id")
	
	private Integer orderId;
	
	@JsonProperty
	
	private Integer amountPayable;
	
	@JsonProperty
	
	private String  paymentMode;
	
	
	@JsonProperty
	
	private Integer  salePrice;
	
	public Integer getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Integer salePrice) {
		this.salePrice = salePrice;
	}



	@JsonProperty
	
	private Integer  redeemedPoints;//redeemed_points
	
	public Integer getRedeemedPoints() {
		return redeemedPoints;
	}

	public void setRedeemedPoints(Integer redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	@JsonProperty
	
	private String demoRequestDate;
	
	public String getDemoRequestDate() {
		return demoRequestDate;
	}

	public void setDemoRequestDate(String demoRequestDate) {
		this.demoRequestDate = demoRequestDate;
	}

	
	@JsonProperty
	private Integer quantityPrice;
	
	public Integer getQuantityPrice() {
		return quantityPrice;
	}

	public void setQuantityPrice(Integer quantityPrice) {
		this.quantityPrice = quantityPrice;
	}



	@JsonProperty
	
	private String primaryOrderId;
	
	
	
	public String getPrimaryOrderId() {
		return primaryOrderId;
	}

	public void setPrimaryOrderId(String primaryOrderId) {
		this.primaryOrderId = primaryOrderId;
	}



	/** The home address. */
	private String shippingAddress;

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Integer getAmountPayable() {
		return amountPayable;
	}

	public void setAmountPayable(Integer amountPayable) {
		this.amountPayable = amountPayable;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}



	@JsonProperty("status")
	private String status;
	@JsonProperty("voucher_id")
	private Integer voucherId;
	
	
	@JsonProperty
	private Integer productQuantity;
	
	
	@JsonProperty
	private Integer redeemAmount;
	
	public Integer getRedeemAmount() {
		return redeemAmount;
	}

	public void setRedeemAmount(Integer redeemAmount) {
		this.redeemAmount = redeemAmount;
	}

	public Integer getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(Integer productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getVoucherId() {
		return voucherId;
	}

	public void setVoucherId(Integer voucherId) {
		this.voucherId = voucherId;
	}

	

	@JsonProperty("consumerId")
	private Integer consumerId;
	
	public Integer getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(Integer consumerId) {
		this.consumerId = consumerId;
	}

	@JsonProperty
	private String cart;
	
	
	@JsonProperty
	
	private String buyNow;
	public String getBuyNow() {
		return buyNow;
	}

	public void setBuyNow(String buyNow) {
		this.buyNow = buyNow;
	}

	private String voucherCode;
	

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	@JsonProperty("created")
	private Date created;

	/*@Embedded
	private Location location;
	public Location getLocation() {
		return location;
	}*/

	/*public void setLocation(Location location) {
		this.location = location;
	}*/

	
	protected void onCreate() {
		modified = created = new Date();
		Date m = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(m);
		cal.add(Calendar.DATE, 7);
		m = cal.getTime();

	}

	
	protected void onUpdate() {
		modified = new Date();
	}

	@JsonProperty("modified")
	private Date modified;

	/**
	 * 
	 * @return The id
	 */

	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            The id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 
	 * @return The state
	 */
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            The state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return The created
	 */
	public Date getCreated() {
		return created;
	}

	


	public String getCart() {
		return cart;
	}

	public void setCart(String cart) {
		this.cart = cart;
	}

	/**
	 * 
	 * @param created
	 *            The created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * 
	 * @return The modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * 
	 * @param modified
	 *            The modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public int compareTo(OrderManagement arg0) {
		// TODO Auto-generated method stub
		int id = ((OrderManagement) arg0).getId();
		return this.id - id;
	}

	@Transient
	private String message;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonProperty
	private Integer retailerId;
	
	public Integer getRetailerId() {
		return retailerId;
	}

	public void setRetailerId(Integer retailerId) {
		this.retailerId = retailerId;
	}
	
	@JsonProperty
	private String demoRequestAddress;
	
	public String getDemoRequestAddress() {
		return demoRequestAddress;
	}

	public void setDemoRequestAddress(String demoRequestAddress) {
		this.demoRequestAddress = demoRequestAddress;
	}
	

}
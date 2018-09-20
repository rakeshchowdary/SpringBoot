/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.campaign.model;

import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Ad.
 */
@Generated("org.jsonschema2pojo")

public class Ad implements Serializable  {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		/** The id. */
		
		private Integer id;
		
		/** The name. */
		
		private String name;
	
		/** The sub categories. */
		
		private String subCategories;
	
		/** The start date. */
		
		private Date startDate;
		
		/** The end date. */
		
		private Date endDate;
	
		/** The duration. */
		
		private String duration;
	
		/** The discount. */
		
		private Discount discount;
	
		/** The product. */
		
		private Product product;

		/**
		* Gets the id.
		*
		* @return     The id
		*/
		public Integer getId() 
		 {
           return id;
		   }

		/**
		 * Sets the id.
		 *
		 * @param id     The id
		 */
		public void setId(Integer id) 
		  {
			this.id = id;
		  	}

		/**
		 * Gets the name.
		 *
		 * @return     The name
		 */
		public String getName() 
		 {
           return name;
		 	}

		/**
		 * Sets the name.
		 *
		 * @param name     The name
		 */
		public void setName(String name) 
		 {
			this.name = name;
		 	}

		/**
		 * Gets the sub categories.
		 *
		 * @return     The subCategories
		 */
		public String getSubCategories() 
		 {
			return subCategories;
		 	}

		/**
		 * Sets the sub categories.
		 *
		 * @param subCategories     The subCategories
		 */
		public void setSubCategories(String subCategories) 
		  {
			this.subCategories = subCategories;
		  	}

		/**
		 * Gets the start date.
		 *
		 * @return     The startDate
		 */
		public Date getStartDate() 
		 {
			return startDate;
		 	}

		/**
		 * Sets the start date.
		 *
		 * @param startDate     The startDate
		 */
		public void setStartDate(Date startDate) 
		  {
			this.startDate = startDate;
		  	}

		/**
		 * Gets the duration.
		 *
		 * @return     The duration
		 */
		public String getDuration() 
		  {
			return duration;
		  	}

		/**
		 * Sets the duration.
		 *
		 * @param duration     The duration
		 */
		public void setDuration(String duration) 
		  {
			this.duration = duration;
		  	}

		/**
		 * Gets the discount.
		 *
		 * @return     The discount
		 */
		public Discount getDiscount() 
		 {
			return discount;
		 	}

		/**
		 * Sets the discount.
		 *
		 * @param discount     The discount
		 */
		public void setDiscount(Discount discount) 
		  {
			this.discount = discount;
		  	}

		/**
		 * Gets the product.
		 *
		 * @return     The product
		 */
		public Product getProduct() 
		  {
			return product;
		  	}

		/**
		 * Sets the product.
		 *
		 * @param product     The product
		 */
		public void setProduct(Product product) 
		  {
			this.product = product;
		  	}

		
		
		

		public Date getEndDate() {
			return endDate;
		}

		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
		}

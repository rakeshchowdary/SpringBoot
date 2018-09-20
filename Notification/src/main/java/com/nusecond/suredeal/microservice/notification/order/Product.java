
package com.nusecond.suredeal.microservice.notification.order;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;


// TODO: Auto-generated Javadoc
/**
 * The Class Product.
 */
@Generated("org.jsonschema2pojo")

public class Product implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	private Integer id;
	
	/** The category. */
	private String category;
	
	
	/** The type. */
	private String type;
	/** The label. */
	private String label;
	
	/** The brand. */
	private String brand;
	
	/** The name. */
	
	private String name;
	
	
	/** The price range. */
	private String price;
	
	
	/** The color. */
	private String color;
	/** The features. */
	private String features;

	private String model;
	
	private String description;
	
	private String vertical;
	
	
	private String segment;
	
	private String family;
	
	
	private String familyclass;
	
	
	private String brick;
	
	private String parent;
	/** The created. */
	private Date created;
	
	/** The modified. */
	private Date modified;



	/*@Transient
	Specification  specification;
	
	
	public Specification getSpecification() {
		return specification;
	}

	public void setSpecification(Specification specification) {
		this.specification = specification;
	}*/

	
	protected void onCreate() {
		modified = created = new Date();
	}

	/**
	 * On update.
	 */
	
	protected void onUpdate() {
		modified = new Date();
	}

	/**
	 * Gets the category.
	 *
	 * @return The category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param category            The category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getFamilyclass() {
		return familyclass;
	}

	public void setFamilyclass(String familyclass) {
		this.familyclass = familyclass;
	}

	public String getBrick() {
		return brick;
	}

	public void setBrick(String brick) {
		this.brick = brick;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

		

}
/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.consumer.model;

/**
 * @author Krishnamorthi Palanisamy
 *
 */
import java.util.Date;
import java.util.Locale;


import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * The Class Facebook.
 */
public class Facebook {

	/** The id. */
	private String id;

	/** The birthday. */
	private String birthday;

	/** The first name. */
	private String firstName;

	/** The gender. */
	private String gender;

	/** The last name. */
	private String lastName;

	/** The link. */
	private String link;

	/** The email. */
	@JsonIgnore
	private String email;

	/** The image url. */
	private String imageUrl;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email
	 *            the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/** The location. */
	private Location location;

	/** The locale. */
	private Locale locale;

	/** The namefb. */
	private String name;

	/** The timezone. */
	private Float timezone;

	/** The updated time. */
	private Date updatedTime;

	/** The verified. */
	private Boolean verified;

	/**
	 * Gets the verified.
	 *
	 * @return the verified
	 */
	public Boolean getVerified() {
		return verified;
	}

	/**
	 * Sets the verified.
	 *
	 * @param verified
	 *            the new verified
	 */
	public void setVerified(Boolean verified) {
		this.verified = verified;
	}

	/** The token. */
	private String token;

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            The id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the birthday.
	 *
	 * @return The birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * Sets the birthday.
	 *
	 * @param birthday
	 *            The birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * Gets the first name.
	 *
	 * @return The firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            The first_name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return The gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            The gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the last name.
	 *
	 * @return The lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            The last_name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the link.
	 *
	 * @return The link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * Sets the link.
	 *
	 * @param link
	 *            The link
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * Gets the location.
	 *
	 * @return The location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location
	 *            The location
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * Gets the locale.
	 *
	 * @return The locale
	 */
	public Locale getLocale() {
		return locale;
	}

	/**
	 * Sets the locale.
	 *
	 * @param locale
	 *            The locale
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	/**
	 * Gets the name.
	 *
	 * @return The name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            The name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the timezone.
	 *
	 * @return The timezone
	 */
	public Float getTimezone() {
		return timezone;
	}

	/**
	 * Sets the timezone.
	 *
	 * @param timezone
	 *            The timezone
	 */
	public void setTimezone(Float timezone) {
		this.timezone = timezone;
	}

	/**
	 * Gets the updated time.
	 *
	 * @return The updatedTime
	 */
	public Date getUpdatedTime() {
		return updatedTime;
	}

	/**
	 * Sets the updated time.
	 *
	 * @param updatedTime
	 *            The updated_time
	 */
	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	/**
	 * Gets the image url.
	 *
	 * @return the image url
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * Sets the image url.
	 *
	 * @param imageUrl
	 *            the new image url
	 */
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}

/*
 * 
 */

package com.nusecond.suredeal.microservice.notification.consumer.model;

/**
 * @author Krishnamorthi Palanisamy
 *
 */
import java.io.Serializable;

import java.util.Date;


// TODO: Auto-generated Javadoc
/**
 * The Class Consumer.
 */
public class Consumer implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The first name. */
	private String firstName;

	/** The middle name. */
	private String middleName;

	/** The last name. */
	private String lastName;

	/** The nick name. */
	private String nickName;

	/** The dob. */
	private Date dob;

	/** The status. */
	private String status;

	/** The home address. */
	private String homeAddress;

	/** The work address. */
	private String workAddress;

	/** The income. */
	private String income;

	/**
	 * Gets the gcmtoken.
	 *
	 * @return the gcmtoken
	 */
	public String getGcmToken() {
		return gcmToken;
	}

	/**
	 * Sets the gcmtoken.
	 *
	 * @param gcmToken
	 *            the new gcm token
	 */
	public void setGcmToken(String gcmToken) {
		this.gcmToken = gcmToken;
	}

	/** The gcmtoken. */

	private String gcmToken;

	/** The modified. */

	private Date modified;

	/** The created. */

	private Date created;

	/**
	 * On create.
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

	/** The contact. */
	private Contact contact;

	/** The pres pref. */
	private PresPref presPref;

	/** The location. */
	private CLocation location;

	/** The conf pref. */
	private ConfPref confPref;
	

	private Local local;

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	/** The classification. */
	

	private Classification classification;

	/** The dislike. */

	/** The notif opt. */

	/** The key. */
	
	private Key key;

	/** The facebook. */
	
	private Facebook facebook;

	/** The google. */
	
	private Google google;

	/**
	 * Gets the facebook.
	 *
	 * @return the facebook
	 */
	public Facebook getFacebook() {
		return facebook;
	}

	/**
	 * Sets the facebook.
	 *
	 * @param facebook
	 *            the new facebook
	 */
	public void setFacebook(Facebook facebook) {
		this.facebook = facebook;
	}

	/**
	 * Gets the google.
	 *
	 * @return the google
	 */
	public Google getGoogle() {
		return google;
	}

	/**
	 * Sets the google.
	 *
	 * @param google
	 *            the new google
	 */
	public void setGoogle(Google google) {
		this.google = google;
	}

	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            The id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 *            The firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the middle name.
	 *
	 * @return The middleName
	 */
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middle name.
	 *
	 * @param middleName
	 *            The middleName
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
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
	 *            The lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the nick name.
	 *
	 * @return The nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * Sets the nick name.
	 *
	 * @param nickName
	 *            The nickName
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * Gets the key.
	 *
	 * @return The key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key
	 *            The key
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * Gets the dob.
	 *
	 * @return The dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob
	 *            The dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the status.
	 *
	 * @return The status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status
	 *            The status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the home address.
	 *
	 * @return The homeAddress
	 */
	public String getHomeAddress() {
		return homeAddress;
	}

	/**
	 * Sets the home address.
	 *
	 * @param homeAddress
	 *            The homeAddress
	 */
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	/**
	 * Gets the work address.
	 *
	 * @return The workAddress
	 */
	public String getWorkAddress() {
		return workAddress;
	}

	/**
	 * Sets the work address.
	 *
	 * @param workAddress
	 *            The workAddress
	 */
	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	/**
	 * Gets the income.
	 *
	 * @return The income
	 */
	public String getIncome() {
		return income;
	}

	/**
	 * Sets the income.
	 *
	 * @param income
	 *            The income
	 */
	public void setIncome(String income) {
		this.income = income;
	}

	/**
	 * Gets the contact.
	 *
	 * @return The contact
	 */
	public Contact getContact() {
		return contact;
	}

	/**
	 * Sets the contact.
	 *
	 * @param contact
	 *            The contact
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}

	/**
	 * Gets the pres pref.
	 *
	 * @return The presPref
	 */
	public PresPref getPresPref() {
		return presPref;
	}

	/**
	 * Sets the pres pref.
	 *
	 * @param presPref
	 *            The presPref
	 */
	public void setPresPref(PresPref presPref) {
		this.presPref = presPref;
	}

	/**
	 * Gets the conf pref.
	 *
	 * @return The location
	 */

	/**
	 * 
	 * @return The confPref
	 */
	public ConfPref getConfPref() {
		return confPref;
	}

	/**
	 * Sets the conf pref.
	 *
	 * @param confPref
	 *            The confPref
	 */
	public void setConfPref(ConfPref confPref) {
		this.confPref = confPref;
	}

	/**
	 * Gets the modified.
	 *
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * Sets the modified.
	 *
	 * @param modified
	 *            the new modified
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Gets the created.
	 *
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Sets the created.
	 *
	 * @param created
	 *            the new created
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * Gets the c location.
	 *
	 * @return the c location
	 */
	public CLocation getCLocation() {
		return location;
	}

	/**
	 * Sets the c location.
	 *
	 * @param location
	 *            the new c location
	 */
	public void setCLocation(CLocation location) {
		this.location = location;
	}

	/**
	 * Gets the classification.
	 *
	 * @return the classification
	 */
	public Classification getClassification() {
		return classification;
	}

	/**
	 * Sets the classification.
	 *
	 * @param classification
	 *            the new classification
	 */
	public void setClassification(Classification classification) {
		this.classification = classification;
	}

}

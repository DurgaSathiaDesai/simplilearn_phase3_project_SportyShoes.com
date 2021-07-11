package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name="userinfo")
public class userInfo 
{
	@Id
	@GeneratedValue
	@Column(name = "userid")
	private long userId;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "emailid")
	private String emailId;
	
	@Column(name = "contactno")
	private String contactNo;
	
	@Column(name = "address")
	private String address;
	
	@OneToOne(optional = false)
	@JoinColumn(name="loginid")
	private loginCredentials userCredentials;

	public userInfo()
	{
		
	}
	
	public userInfo(String firstname, String lastname, String emailId, String contactNo, String address,
			loginCredentials userCredentials) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.emailId = emailId;
		this.contactNo = contactNo;
		this.address = address;
		this.userCredentials = userCredentials;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public loginCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(loginCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	public long getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "userInfo [userId=" + userId + ", firstname=" + firstname + ", lastname=" + lastname + ", emailId="
				+ emailId + ", contactNo=" + contactNo + ", address=" + address + ", userCredentials=" + userCredentials
				+ "]";
	}
}

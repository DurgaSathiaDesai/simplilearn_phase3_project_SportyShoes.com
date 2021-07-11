package com.simplilearn.phase3.assessment.project.SportyshoesWebAppl.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Table(name="logincredentials")
public class loginCredentials 
{
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long Id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "userpassword")
	private String password;
	
	@Column(name = "userrole")
	private String role;
	
	@Column(name = "accstatus")
	private String accountstatus;

	@OneToOne(mappedBy="userCredentials")
	private userInfo userinfo;
	
	public loginCredentials()
	{
		
	}
	
	public loginCredentials(String username, String password, String role, String accountstatus) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
		this.accountstatus = accountstatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccountstatus() {
		return accountstatus;
	}

	public void setAccountstatus(String accountstatus) {
		this.accountstatus = accountstatus;
	}

	public long getId() {
		return Id;
	}

	@Override
	public String toString() {
		return "loginCredentials [Id=" + Id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", accountstatus=" + accountstatus + "]";
	}
	
}

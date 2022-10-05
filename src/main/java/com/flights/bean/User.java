package com.flights.bean;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name="app_user")
public class User {
	
	@Id
	@GeneratedValue
	private int userId;
	private Role userType;
	private String userName;
	private String userPassword;
	private String userPhone;
	private String userEmail;

	public User(){}
	public User(String userType, String userName, String userPassword, String userPhone, String userEmail) {
		super();
		if(userType.equals("admin") || userType.equals("Admin") || userType.equals("ADMIN"))
			this.userType = Role.ADMIN;
		else
			this.userType=Role.CUSTOMER;

		this.userName = userName;
		this.userPassword = userPassword;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType.toString();
	}

	public void setUserType(String userType) {
		if(userType.equals("admin") || userType.equals("Admin") || userType.equals("ADMIN"))
			this.userType = Role.ADMIN;
		else
			this.userType=Role.CUSTOMER;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}

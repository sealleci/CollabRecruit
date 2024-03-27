package com.rai.domain.po;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class UserInfo implements Serializable{
	private Integer userId;
	private String username;
	private String password;
	private Date borndate;
	private String gender;
	private String city;
	private String telephone;
	private String email;
	private String address;
	private Integer state;
	
	public UserInfo() {
		super();
	}
	
	public UserInfo(String username, String password, Date borndate, String gender, String city, String telephone,
			String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.borndate = borndate;
		this.gender = gender;
		this.city = city;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.state=0;
	}

	public UserInfo(Integer userId, String username, String password, Date borndate, String gender, String city,
			String telephone, String email, String address) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.borndate = borndate;
		this.gender = gender;
		this.city = city;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.state=0;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public Date getBorndate() {
		return borndate;
	}
	public void setBorndate(Date borndate) {
		this.borndate = borndate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", username=" + username + ", password=" + password + ", borndate="
				+ borndate + ", gender=" + gender + ", city=" + city + ", telephone=" + telephone + ", email=" + email
				+ ", address=" + address + ", state=" + state + "]";
	}
}
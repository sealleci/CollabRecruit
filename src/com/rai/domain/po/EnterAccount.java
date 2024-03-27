package com.rai.domain.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EnterAccount implements Serializable{
	private Integer eid;
	private String eaname;
	private String password;
	private String telephone;
	private String email;
	private String address;
	private Integer state;
	
	public EnterAccount() {
		super();
	}

	
	public EnterAccount(String eaname, String password, String telephone, String email, String address) {
		super();
		this.eaname = eaname;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.state=0;
	}


	public EnterAccount(Integer eid, String eaname, String password, String telephone, String email, String address) {
		super();
		this.eid = eid;
		this.eaname = eaname;
		this.password = password;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.state=0;
	}

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEaname() {
		return eaname;
	}

	public void setEaname(String eaname) {
		this.eaname = eaname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "EnterAccount [eid=" + eid + ", eaname=" + eaname + ", password=" + password + ", telephone=" + telephone
				+ ", email=" + email + ", address=" + address + ", state=" + state + "]";
	}

	
}
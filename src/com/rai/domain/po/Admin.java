package com.rai.domain.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Admin implements Serializable{
	private Integer adid;
	private String adname;
	private String password;
	private String email;
	
	public Admin() {
		super();
	}

	public Admin(String adname, String password, String email) {
		super();
		this.adname = adname;
		this.password = password;
		this.email = email;
	}

	public Admin(Integer adid, String adname, String password, String email) {
		super();
		this.adid = adid;
		this.adname = adname;
		this.password = password;
		this.email = email;
	}

	public Integer getAdid() {
		return adid;
	}

	public void setAdid(Integer adid) {
		this.adid = adid;
	}

	public String getAdname() {
		return adname;
	}

	public void setAdname(String adname) {
		this.adname = adname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Admin [adid=" + adid + ", adname=" + adname + ", password=" + password + ", email=" + email + "]";
	}
}

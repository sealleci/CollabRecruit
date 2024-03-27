package com.rai.domain.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Delivery implements Serializable{
	private Integer did;
	private Integer reid;
	private Integer userid;
	private Integer state;
	
	public Delivery() {
		super();
	}
	public Delivery(Integer reid, Integer userid, Integer state) {
		super();
		this.reid = reid;
		this.userid = userid;
		this.state = state;
	}
	public Delivery(Integer did, Integer reid, Integer userid, Integer state) {
		super();
		this.did = did;
		this.reid = reid;
		this.userid = userid;
		this.state = state;
	}
	public Integer getDid() {
		return did;
	}
	public void setDid(Integer did) {
		this.did = did;
	}
	public Integer getReid() {
		return reid;
	}
	public void setReid(Integer reid) {
		this.reid = reid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Delivery [did=" + did + ", reid=" + reid + ", userid=" + userid + ", state=" + state + "]";
	}
	
}

package com.rai.domain.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EnterpriseInfo implements Serializable{
	private Integer eid;
	private String ename;
	private String eintro;
	private String intustry;
	private String artiperson;
	private Integer regmoney;
	private String entertype;
	private Integer examineState;
	
	public EnterpriseInfo() {
		super();
	}

	
	public EnterpriseInfo(String ename, String eintro, String intustry, String artiperson, Integer regmoney,
			String entertype) {
		super();
		this.ename = ename;
		this.eintro = eintro;
		this.intustry = intustry;
		this.artiperson = artiperson;
		this.regmoney = regmoney;
		this.entertype = entertype;
		this.examineState=0;
	}

	public EnterpriseInfo(Integer eid, String ename, String eintro, String intustry, String artiperson,
			Integer regmoney, String entertype) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.eintro = eintro;
		this.intustry = intustry;
		this.artiperson = artiperson;
		this.regmoney = regmoney;
		this.entertype = entertype;
		this.examineState=0;
	}


	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getEintro() {
		return eintro;
	}

	public void setEintro(String eintro) {
		this.eintro = eintro;
	}

	public String getIntustry() {
		return intustry;
	}

	public void setIntustry(String intustry) {
		this.intustry = intustry;
	}

	public String getArtiperson() {
		return artiperson;
	}

	public void setArtiperson(String artiperson) {
		this.artiperson = artiperson;
	}

	public Integer getRegmoney() {
		return regmoney;
	}

	public void setRegmoney(Integer regmoney) {
		this.regmoney = regmoney;
	}

	public String getEntertype() {
		return entertype;
	}

	public void setEntertype(String entertype) {
		this.entertype = entertype;
	}
	
	
	public Integer getExamineState() {
		return examineState;
	}


	public void setExamineState(Integer examineState) {
		this.examineState = examineState;
	}


	@Override
	public String toString() {
		return "EnterpriseInfo [eid=" + eid + ", ename=" + ename + ", eintro=" + eintro + ", intustry=" + intustry
				+ ", artiperson=" + artiperson + ", regmoney=" + regmoney + ", entertype=" + entertype
				+ ", examineState=" + examineState + "]";
	}
}
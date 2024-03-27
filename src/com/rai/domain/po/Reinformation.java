package com.rai.domain.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Reinformation implements Serializable{
	private Integer reid;
	private String rePositionName;
	private String reSalary;
	private String reCity;
	private String reArea;
	private String reWorkYears;
	private String reEducation;
	private Integer rePersonNum;
	private String reLightPoint;
	private String reJobDesc;
	private String reDepartment;
	private String reProfessional;
	private String reReportPerson;
	private Integer reSubordinates;
	private EnterpriseInfo e;
	
	public Reinformation() {
		super();
	}
	
	public Reinformation(String rePositionName, String reSalary, String reCity, String reArea, String reWorkYears,
			String reEducation, Integer rePersonNum, String reLightPoint, String reJobDesc, String reDepartment,
			String reProfessional, String reReportPerson, Integer reSubordinates, EnterpriseInfo e) {
		super();
		this.rePositionName = rePositionName;
		this.reSalary = reSalary;
		this.reCity = reCity;
		this.reArea = reArea;
		this.reWorkYears = reWorkYears;
		this.reEducation = reEducation;
		this.rePersonNum = rePersonNum;
		this.reLightPoint = reLightPoint;
		this.reJobDesc = reJobDesc;
		this.reDepartment = reDepartment;
		this.reProfessional = reProfessional;
		this.reReportPerson = reReportPerson;
		this.reSubordinates = reSubordinates;
		this.e = e;
	}

	public Reinformation(Integer reid, String rePositionName, String reSalary, String reCity, String reArea,
			String reWorkYears, String reEducation, Integer rePersonNum, String reLightPoint, String reJobDesc,
			String reDepartment, String reProfessional, String reReportPerson, Integer reSubordinates, EnterpriseInfo e) {
		super();
		this.reid = reid;
		this.rePositionName = rePositionName;
		this.reSalary = reSalary;
		this.reCity = reCity;
		this.reArea = reArea;
		this.reWorkYears = reWorkYears;
		this.reEducation = reEducation;
		this.rePersonNum = rePersonNum;
		this.reLightPoint = reLightPoint;
		this.reJobDesc = reJobDesc;
		this.reDepartment = reDepartment;
		this.reProfessional = reProfessional;
		this.reReportPerson = reReportPerson;
		this.reSubordinates = reSubordinates;
		this.e = e;
	}

	public Integer getReid() {
		return reid;
	}
	public void setReid(Integer reid) {
		this.reid = reid;
	}
	public String getRePositionName() {
		return rePositionName;
	}
	public void setRePositionName(String rePositionName) {
		this.rePositionName = rePositionName;
	}
	public String getReSalary() {
		return reSalary;
	}
	public void setReSalary(String reSalary) {
		this.reSalary = reSalary;
	}
	public String getReCity() {
		return reCity;
	}
	public void setReCity(String reCity) {
		this.reCity = reCity;
	}
	public String getReArea() {
		return reArea;
	}
	public void setReArea(String reArea) {
		this.reArea = reArea;
	}
	public String getReWorkYears() {
		return reWorkYears;
	}
	public void setReWorkYears(String reWorkYears) {
		this.reWorkYears = reWorkYears;
	}
	public String getReEducation() {
		return reEducation;
	}
	public void setReEducation(String reEducation) {
		this.reEducation = reEducation;
	}
	public Integer getRePersonNum() {
		return rePersonNum;
	}
	public void setRePersonNum(Integer rePersonNum) {
		this.rePersonNum = rePersonNum;
	}
	public String getReLightPoint() {
		return reLightPoint;
	}
	public void setReLightPoint(String reLightPoint) {
		this.reLightPoint = reLightPoint;
	}
	public String getReJobDesc() {
		return reJobDesc;
	}
	public void setReJobDesc(String reJobDesc) {
		this.reJobDesc = reJobDesc;
	}
	public String getReDepartment() {
		return reDepartment;
	}
	public void setReDepartment(String reDepartment) {
		this.reDepartment = reDepartment;
	}
	public String getReProfessional() {
		return reProfessional;
	}
	public void setReProfessional(String reProfessional) {
		this.reProfessional = reProfessional;
	}
	public String getReReportPerson() {
		return reReportPerson;
	}
	public void setReReportPerson(String reReportPerson) {
		this.reReportPerson = reReportPerson;
	}
	public Integer getReSubordinates() {
		return reSubordinates;
	}
	public void setReSubordinates(Integer reSubordinates) {
		this.reSubordinates = reSubordinates;
	}
	public EnterpriseInfo getE() {
		return e;
	}
	public void setE(EnterpriseInfo e) {
		this.e = e;
	}

	@Override
	public String toString() {
		return "Reinformation [reid=" + reid + ", rePositionName=" + rePositionName + ", reSalary=" + reSalary
				+ ", reCity=" + reCity + ", reArea=" + reArea + ", reWorkYears=" + reWorkYears + ", reEducation="
				+ reEducation + ", rePersonNum=" + rePersonNum + ", reLightPoint=" + reLightPoint + ", reJobDesc="
				+ reJobDesc + ", reDepartment=" + reDepartment + ", reProfessional=" + reProfessional
				+ ", reReportPerson=" + reReportPerson + ", reSubordinates=" + reSubordinates + ", e=" + e + "]";
	}
}
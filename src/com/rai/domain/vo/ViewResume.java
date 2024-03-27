package com.rai.domain.vo;

import java.math.BigDecimal;
import java.util.Date;

public class ViewResume {
	private String username;
	private Date borndate;
	private String gender;
	private String telephone;
	private String email;
	private String address;
	private String company;
	private String position;
	private Date workStartTime;
	private Date workEndTime;
	private BigDecimal salary;
	private String duty;
	private String schoolName;
	private String education;
	private String professional;
	private Date entranceTime;
	private Date graduateTime;
	
	public ViewResume() {
		super();
	}
	
	public ViewResume(String username, Date borndate, String gender, String telephone, String email, String address,
			String company, String position, Date workStartTime, Date workEndTime, BigDecimal salary, String duty,
			String schoolName, String education, String professional, Date entranceTime, Date graduateTime) {
		super();
		this.username = username;
		this.borndate = borndate;
		this.gender = gender;
		this.telephone = telephone;
		this.email = email;
		this.address = address;
		this.company = company;
		this.position = position;
		this.workStartTime = workStartTime;
		this.workEndTime = workEndTime;
		this.salary = salary;
		this.duty = duty;
		this.schoolName = schoolName;
		this.education = education;
		this.professional = professional;
		this.entranceTime = entranceTime;
		this.graduateTime = graduateTime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Date getWorkStartTime() {
		return workStartTime;
	}
	public void setWorkStartTime(Date workStartTime) {
		this.workStartTime = workStartTime;
	}
	public Date getWorkEndTime() {
		return workEndTime;
	}
	public void setWorkEndTime(Date workEndTime) {
		this.workEndTime = workEndTime;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public Date getEntranceTime() {
		return entranceTime;
	}
	public void setEntranceTime(Date entranceTime) {
		this.entranceTime = entranceTime;
	}
	public Date getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(Date graduateTime) {
		this.graduateTime = graduateTime;
	}
	
	@Override
	public String toString() {
		return "ViewResume [username=" + username + ", borndate=" + borndate + ", gender=" + gender + ", telephone="
				+ telephone + ", email=" + email + ", address=" + address + ", company=" + company + ", position="
				+ position + ", workStartTime=" + workStartTime + ", workEndTime=" + workEndTime + ", salary=" + salary
				+ ", duty=" + duty + ", schoolName=" + schoolName + ", education=" + education + ", professional="
				+ professional + ", entranceTime=" + entranceTime + ", graduateTime=" + graduateTime + "]";
	}
}
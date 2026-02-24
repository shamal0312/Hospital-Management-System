package com.hospitalink.model;

public class Patient {
    private int patientId;
    private String fullName;
    private String gender;
    private String birthDate;
    private String contactNumber;
    private String address;
    private String createdAt;
    
	public int getPatientId() {
		return patientId;
	}
	public String getFullName() {
		return fullName;
	}
	public String getGender() {
		return gender;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
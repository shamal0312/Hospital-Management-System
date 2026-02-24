package com.hospitalink.model;

public class Doctor {
	private int doctorId;
    private String fullName;
    private String specialty;
    private String contactNumber;
    private String email;
    private String createdAt;
    
	public int getDoctorId() {
		return doctorId;
	}
	public String getFullName() {
		return fullName;
	}
	public String getSpecialty() {
		return specialty;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
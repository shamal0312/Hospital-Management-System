package com.hospitalink.model;

public class Admin {
    private int adminId;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private String createdAt;
    
	public int getAdminId() {
		return adminId;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getFullName() {
		return fullName;
	}
	public String getRole() {
		return role;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
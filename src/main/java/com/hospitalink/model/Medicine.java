package com.hospitalink.model;

public class Medicine {
	private int medicineId;
    private String name;
    private String description;
    private int stockQuantity;
    private String expiryDate;
    private String createdAt;
    
	public int getMedicineId() {
		return medicineId;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
package com.hospitalink.model;

public class Report {
	private int reportId;
    private int patientId;
    private int doctorId;
    private String reportTitle;
    private String reportDetails;
    private String createdAt;

    private String patientName;   // From medical_reports_view
    private String doctorName;    // From medical_reports_view
    
	public int getReportId() {
		return reportId;
	}
	public int getPatientId() {
		return patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public String getReportTitle() {
		return reportTitle;
	}
	public String getReportDetails() {
		return reportDetails;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public String getPatientName() {
		return patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}
	public void setReportDetails(String reportDetails) {
		this.reportDetails = reportDetails;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
}
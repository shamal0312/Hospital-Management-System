package com.hospitalink.model;

public class Appointment {
	private int appointmentId;
    private int patientId;
    private int doctorId;
    private String appointmentDate;
    private String status;
    private String createdAt;

    private String patientName;    // From appointments_view
    private String doctorName;     // From appointments_view
    
	public int getAppointmentId() {
		return appointmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public String getPatientName() {
		return patientName;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public String getAppointmentDate() {
		return appointmentDate;
	}
	public String getStatus() {
		return status;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
}
# 🏥 HospitaLink – Hospital Management System

**HospitaLink** is a comprehensive web-based application designed to streamline and automate the administrative and clinical operations of a hospital.  
Built using **Java (JSP/Servlet)** and **MySQL**, this system provides a user-friendly interface for patients, doctors, and hospital administrators to manage appointments, patients, inventory, and reports efficiently.

---

## 🚀 Key Features

### 1. Admin Module
- **Dashboard:** Overview of total doctors, patients, and appointments.  
- **User Management:** Add, update, or remove doctors and staff members.  
- **System Monitoring:** Manage hospital departments and services.  

### 2. Doctor Module
- **Appointment Management:** View scheduled appointments and patient details.  
- **Patient History:** Access patient medical records and previous visits.  
- **Prescriptions:** Issue digital prescriptions to patients.  

### 3. Patient Module
- **Registration:** Easy account creation for new patients.  
- **Online Booking:** Search for doctors and book appointments online.  
- **Dashboard:** View upcoming appointments and medical history.  

### 4. Inventory & Pharmacy
- **Medicine Management:** Track medicine stock levels.  
- **Inventory Control:** Add new medicines and update stock details.  

### 5. Reporting
- Generate reports on appointments, patient visits, and revenue.  

---

## 🛠️ Technologies Used

- **Frontend:** HTML5, CSS3, Bootstrap, JavaScript, JSP (JavaServer Pages)  
- **Backend:** Java (J2EE), Java Servlets  
- **Database:** MySQL  
- **Server:** Apache Tomcat 9  
- **IDE:** Visual Studio Code (VS Code)  

---

## ⚙️ Prerequisites

Before running this project, ensure you have the following installed:

- Java Development Kit (JDK) (Version 8 or higher)  
- Apache Tomcat Server (Version 9 recommended)  
- MySQL Server (via XAMPP or MySQL Workbench)  
- Visual Studio Code with **Extension Pack for Java** and **Community Server Connectors**  

---

## 📥 Installation & Setup Guide

### Step 1: Database Configuration
1. Open your MySQL Database Manager (phpMyAdmin or Workbench).  
2. Create a new database named `hospital_db` (or check `DBConnection.java` for the exact name).  
3. Import the `Database.sql` file provided in the project folder to create the necessary tables.  

### Step 2: Configure the Project
1. Open the project in VS Code.  
2. Navigate to `src/main/java/com/hospitalink/util/DBConnection.java`.  
3. Update the database username and password inside the code to match your local MySQL credentials:  

```java
DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital_db", "root", "your_password");

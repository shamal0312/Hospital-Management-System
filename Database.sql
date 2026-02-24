-- 1. Create Database
CREATE DATABASE hospitalink_db;
USE hospitalink_db;

-- 2. Admins Table (for Login)
CREATE TABLE admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    role ENUM('Admin', 'Staff') DEFAULT 'Admin',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. Patients Table
CREATE TABLE patients (
    patient_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    gender ENUM('Male', 'Female', 'Other') NOT NULL,
    birth_date DATE,
    contact_number VARCHAR(20),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. Doctors Table
CREATE TABLE doctors (
    doctor_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255),
    contact_number VARCHAR(20),
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. Appointments Table
CREATE TABLE appointments (
    appointment_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    appointment_date DATETIME NOT NULL,
    status ENUM('Pending', 'Completed', 'Cancelled') DEFAULT 'Pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_appointment_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    CONSTRAINT fk_appointment_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

-- 6. Medicines Table
CREATE TABLE medicines (
    medicine_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    stock_quantity INT DEFAULT 0,
    expiry_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. Medical Reports Table
CREATE TABLE medical_reports (
    report_id INT AUTO_INCREMENT PRIMARY KEY,
    patient_id INT NOT NULL,
    doctor_id INT NOT NULL,
    report_title VARCHAR(255) NOT NULL,
    report_details TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_report_patient FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    CONSTRAINT fk_report_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id) ON DELETE CASCADE
);

-- 8. Views

-- 8.1 Appointments View (to simplify JOINs)
CREATE VIEW appointments_view AS
SELECT 
    a.appointment_id,
    p.full_name AS patient_name,
    d.full_name AS doctor_name,
    a.appointment_date,
    a.status,
    a.created_at
FROM appointments a
JOIN patients p ON a.patient_id = p.patient_id
JOIN doctors d ON a.doctor_id = d.doctor_id;

-- 8.2 Medical Reports View
CREATE VIEW medical_reports_view AS
SELECT 
    r.report_id,
    p.full_name AS patient_name,
    d.full_name AS doctor_name,
    r.report_title,
    r.report_details,
    r.created_at
FROM medical_reports r
JOIN patients p ON r.patient_id = p.patient_id
JOIN doctors d ON r.doctor_id = d.doctor_id;

-- 9. Sample Data Inserts

-- 9.1 Admins Sample Data
INSERT INTO admins (username, password, full_name, role)
VALUES 
('admin', 'admin123', 'System Administrator', 'Admin'),
('staff1', 'staff123', 'Staff Member 1', 'Staff');

-- 9.2 Patients Sample Data
INSERT INTO patients (full_name, gender, birth_date, contact_number, address)
VALUES 
('John Doe', 'Male', '1985-06-15', '0712345678', '123 Main Street, City A'),
('Jane Smith', 'Female', '1992-11-30', '0723456789', '456 Oak Avenue, City B'),
('Emily Johnson', 'Female', '1978-03-22', '0734567890', '789 Pine Lane, City C'),
('Michael Brown', 'Male', '2000-01-10', '0745678901', '321 Maple Drive, City D');

-- 9.3 Doctors Sample Data
INSERT INTO doctors (full_name, specialty, contact_number, email)
VALUES 
('Dr. Alice Walker', 'Cardiologist', '0771234567', 'alice.walker@hospitalink.com'),
('Dr. Robert King', 'Neurologist', '0772345678', 'robert.king@hospitalink.com'),
('Dr. Susan Taylor', 'Pediatrician', '0773456789', 'susan.taylor@hospitalink.com');

-- 9.4 Appointments Sample Data
INSERT INTO appointments (patient_id, doctor_id, appointment_date, status)
VALUES
(1, 1, '2025-04-25 10:30:00', 'Pending'),
(2, 2, '2025-04-26 11:00:00', 'Completed'),
(3, 1, '2025-04-27 09:00:00', 'Pending'),
(4, 3, '2025-04-28 14:00:00', 'Cancelled');

-- 9.5 Medicines Sample Data
INSERT INTO medicines (name, description, stock_quantity, expiry_date)
VALUES 
('Paracetamol', 'Pain reliever and fever reducer.', 100, '2026-01-15'),
('Amoxicillin', 'Antibiotic used to treat infections.', 50, '2025-12-31'),
('Ibuprofen', 'Nonsteroidal anti-inflammatory drug.', 20, '2026-06-30'),
('Cough Syrup', 'Used for relieving cough.', 8, '2025-09-20');

-- 9.6 Medical Reports Sample Data
INSERT INTO medical_reports (patient_id, doctor_id, report_title, report_details)
VALUES 
(1, 1, 'Annual Heart Checkup', 'Normal ECG readings. Slight cholesterol elevation.'),
(2, 2, 'Neurological Assessment', 'Patient reported migraines; MRI scan normal.'),
(3, 1, 'Follow-up Heart Consultation', 'Blood pressure slightly high; recommended diet change.'),
(4, 3, 'Child Vaccination Report', 'All mandatory vaccinations completed.');
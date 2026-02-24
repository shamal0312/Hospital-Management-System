<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>HospitaLink | Admin Dashboard</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/hospitalink/assets/favicon.png" type="image/x-icon">

  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-gray-100 dark:bg-gray-900 text-gray-900 dark:text-gray-100 min-h-screen flex">

  <!-- Sidebar -->
  <%@ include file="./partials/sidebar.jsp" %>

  <!-- Main Content -->
  <div class="flex-1 flex flex-col">

    <!-- Topbar -->
    <%@ include file="./partials/header.jsp" %>

    <!-- Main Area -->
    <main class="flex-1 p-6 space-y-6">

      <!-- Statistics Cards Section -->
      <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">

        <!-- Total Patients -->
        <div class="p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg flex items-center justify-between">
          <div>
            <h3 class="text-lg font-semibold">Total Patients</h3>
            <p class="text-2xl mt-2 font-bold">${totalPatients}</p>
          </div>
          <i class="fas fa-user-injured fa-2x text-blue-500"></i>
        </div>

        <!-- Total Doctors -->
        <div class="p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg flex items-center justify-between">
          <div>
            <h3 class="text-lg font-semibold">Total Doctors</h3>
            <p class="text-2xl mt-2 font-bold">${totalDoctors}</p>
          </div>
          <i class="fas fa-user-md fa-2x text-green-500"></i>
        </div>

        <!-- Total Appointments -->
        <div class="p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg flex items-center justify-between">
          <div>
            <h3 class="text-lg font-semibold">Appointments</h3>
            <p class="text-2xl mt-2 font-bold">${totalAppointments}</p>
          </div>
          <i class="fas fa-calendar-check fa-2x text-yellow-500"></i>
        </div>

        <!-- Total Medicines -->
        <div class="p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg flex items-center justify-between">
          <div>
            <h3 class="text-lg font-semibold">Medicines</h3>
            <p class="text-2xl mt-2 font-bold">${totalMedicines}</p>
          </div>
          <i class="fas fa-pills fa-2x text-pink-500"></i>
        </div>

        <!-- Total Reports -->
        <div class="p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg flex items-center justify-between">
          <div>
            <h3 class="text-lg font-semibold">Medical Reports</h3>
            <p class="text-2xl mt-2 font-bold">${totalReports}</p>
          </div>
          <i class="fas fa-file-medical fa-2x text-purple-500"></i>
        </div>

        <!-- Total Admins -->
        <div class="p-6 bg-white dark:bg-gray-800 rounded-lg shadow-lg flex items-center justify-between">
          <div>
            <h3 class="text-lg font-semibold">Admins</h3>
            <p class="text-2xl mt-2 font-bold">${totalAdmins}</p>
          </div>
          <i class="fas fa-users-cog fa-2x text-red-500"></i>
        </div>

      </div>

    </main>

  </div>

  <!-- JavaScript -->
  <%@ include file="./partials/script.jsp" %>

</body>
</html>
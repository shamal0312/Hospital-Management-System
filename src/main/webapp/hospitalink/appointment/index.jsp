<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en" class="dark">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>HospitaLink | Appointments</title>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/hospitalink/assets/favicon.png" type="image/x-icon">

  <!-- Tailwind CSS -->
  <script src="https://cdn.tailwindcss.com"></script>

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-gray-100 dark:bg-gray-900 text-gray-900 dark:text-gray-100 min-h-screen flex">

  <!-- Sidebar -->
  <%@ include file="../partials/sidebar.jsp" %>

  <!-- Main Content -->
  <div class="flex-1 flex flex-col">

    <!-- Topbar -->
    <%@ include file="../partials/header.jsp" %>

    <!-- Main Area -->
    <main class="flex-1 p-6 space-y-6">

      <!-- Actions: Create & Search -->
      <div class="flex flex-col md:flex-row md:justify-between md:items-center space-y-4 md:space-y-0">

        <a href="${pageContext.request.contextPath}/appointment?action=create" class="flex items-center gap-2 px-5 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg font-semibold transition">
          <i class="fas fa-plus"></i> Create New
        </a>

        <div class="relative w-full md:w-1/3">
          <input id="search" type="text" placeholder="Search appointments..."
            class="w-full pl-10 pr-4 py-2 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-300 dark:border-gray-600 focus:ring-2 focus:ring-blue-500 focus:outline-none text-gray-900 dark:text-gray-100">
          <i class="fas fa-search absolute left-3 top-3 text-gray-400 dark:text-gray-300"></i>
        </div>

      </div>

      <!-- Data Table -->
      <div class="overflow-x-auto bg-white dark:bg-gray-800 shadow-lg rounded-lg">
        <table class="w-full text-sm text-left">
          <thead class="text-xs uppercase bg-gray-100 dark:bg-gray-700 text-gray-700 dark:text-gray-300">
            <tr>
              <th class="px-6 py-4">ID</th>
              <th class="px-6 py-4">Patient</th>
              <th class="px-6 py-4">Doctor</th>
              <th class="px-6 py-4">Appointment Date</th>
              <th class="px-6 py-4">Status</th>
              <th class="px-6 py-4">Created At</th>
              <th class="px-6 py-4 text-center">Actions</th>
            </tr>
          </thead>
          <tbody id="userTable" class="text-gray-700 dark:text-gray-300">
            <c:choose>
              <c:when test="${not empty appointments}">
                <c:forEach var="appointment" items="${appointments}">
                  <tr class="border-b dark:border-gray-700 hover:bg-gray-50 dark:hover:bg-gray-700/50">
                    <td class="px-6 py-4">${appointment.appointmentId}</td>
                    <td class="px-6 py-4">${appointment.patientName}</td>
                    <td class="px-6 py-4">${appointment.doctorName}</td>
                    <td class="px-6 py-4">${appointment.appointmentDate}</td>
                    <td class="px-6 py-4">${appointment.status}</td>
                    <td class="px-6 py-4">${appointment.createdAt}</td>
                    <td class="px-6 py-4 text-center space-x-3">
                      <a href="${pageContext.request.contextPath}/appointment?action=edit&id=${appointment.appointmentId}" class="text-green-500 hover:text-green-600">
                        <i class="fas fa-edit"></i>
                      </a>
                      <a href="${pageContext.request.contextPath}/appointment?action=delete&id=${appointment.appointmentId}" onclick="return confirmDelete(this.href);" class="text-red-500 hover:text-red-600">
                        <i class="fas fa-trash"></i>
                      </a>
                    </td>
                  </tr>
                </c:forEach>
              </c:when>
              <c:otherwise>
                <tr>
                  <td colspan="7" class="px-6 py-4 text-center text-gray-500 dark:text-gray-400">
                    No appointments found.
                  </td>
                </tr>
              </c:otherwise>
            </c:choose>
          </tbody>
        </table>
      </div>

    </main>

  </div>

  <!-- JavaScript -->
  <%@ include file="../partials/script.jsp" %>

</body>
</html>
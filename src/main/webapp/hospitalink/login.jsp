<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="dark">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HospitaLink | Login</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/hospitalink/assets/favicon.png" type="image/x-icon">

    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>

    <!-- Font Awesome CDN -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
</head>

<body class="bg-gray-100 dark:bg-gray-900 flex items-center justify-center min-h-screen px-4">
    <div class="w-full max-w-md p-8 space-y-6 bg-white dark:bg-gray-800 shadow-2xl rounded-3xl">

        <!-- Title -->
        <h2 class="text-4xl font-bold text-center text-gray-900 dark:text-white">HospitaLink Login</h2>
        <p class="text-center text-gray-600 dark:text-gray-400 text-sm mb-6">Please enter your credentials to access the system.</p>

        <!-- Error Message -->
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
        %>
            <div class="w-full bg-red-100 dark:bg-red-500/20 border border-red-400 dark:border-red-500 text-red-700 dark:text-red-300 px-4 py-3 rounded-lg text-center text-sm" role="alert">
                <i class="fas fa-exclamation-circle mr-2"></i>
                <%= error %>
            </div>
        <%
            }
        %>

        <!-- Login Form -->
        <form class="space-y-5" action="${pageContext.request.contextPath}/login" method="POST">
            
            <!-- Username -->
            <div class="relative">
                <label for="username" class="block mb-1 text-gray-700 dark:text-gray-300 font-medium">Username</label>
                <div class="flex items-center relative">
                    <input id="username" name="username" type="text" required
                        class="w-full pl-10 pr-4 py-2 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        placeholder="Enter your username">
                    <i class="fas fa-user absolute left-3 text-gray-400 dark:text-gray-300"></i>
                </div>
            </div>

            <!-- Password -->
            <div class="relative">
                <label for="password" class="block mb-1 text-gray-700 dark:text-gray-300 font-medium">Password</label>
                <div class="flex items-center relative">
                    <input id="password" name="password" type="password" required
                        class="w-full pl-10 pr-10 py-2 rounded-lg bg-gray-50 dark:bg-gray-700 border border-gray-300 dark:border-gray-600 text-gray-900 dark:text-gray-100 focus:ring-2 focus:ring-blue-500 focus:outline-none"
                        placeholder="Enter your password">
                    <i class="fas fa-lock absolute left-3 text-gray-400 dark:text-gray-300"></i>
                    <button type="button" onclick="togglePassword()" class="absolute right-3 text-gray-400 dark:text-gray-300 focus:outline-none">
                        <i id="eyeIcon" class="fas fa-eye"></i>
                    </button>
                </div>
            </div>

            <!-- Submit Button -->
            <div>
                <button type="submit"
                    class="w-full px-4 py-2 text-white bg-blue-600 hover:bg-blue-700 dark:bg-blue-500 dark:hover:bg-blue-600 font-semibold rounded-lg shadow-md transition duration-200">
                    Sign In
                </button>
            </div>
        </form>

        <!-- Footer Note -->
        <p class="text-center text-gray-600 dark:text-gray-400 text-xs mt-6">HospitaLink - Secure Hospital Management System</p>

    </div>

    <!-- Password Toggle Script -->
    <script>
        function togglePassword() {
            const passwordInput = document.getElementById("password");
            const eyeIcon = document.getElementById("eyeIcon");

            if (passwordInput.type === "password") {
                passwordInput.type = "text";
                eyeIcon.classList.remove("fa-eye");
                eyeIcon.classList.add("fa-eye-slash");
            } else {
                passwordInput.type = "password";
                eyeIcon.classList.remove("fa-eye-slash");
                eyeIcon.classList.add("fa-eye");
            }
        }
    </script>

</body>
</html>
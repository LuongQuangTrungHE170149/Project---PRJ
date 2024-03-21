<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Job" %>
<%@ page import="Model.Candidate" %>
<%
    // Lấy thông tin về vai trò của người dùng từ session, cơ sở dữ liệu, hoặc bất kỳ cách nào phù hợp với ứng dụng của bạn
    User user = (User) session.getAttribute("user");
    String role = user.getRole(); // Giả sử trường role chứa vai trò của người dùng

    // Kiểm tra xem người dùng có vai trò là "manager" không
    boolean isManager = "manager".equals(role);
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Interviews</title>
    </head>
    <body>
        <jsp:include page="sidebar.jsp" />
        <div class="content">
            <h2>Interviews</h2>
            <form action="SearchInterviewServlet" method="get">
                <input type="text" name="searchValue" placeholder="Search interview...">
                <input type="submit" value="Search">
            </form>
            <table border="1">        
                <tr>
                    <th>ID</th>
                    <th>Interview Date</th>
                    <th>Candidate Name</th>
                    <th>Job Title</th>
                    <th>Interviewer</th>
                        <% if (isManager) { %>
                    <!-- Hiển thị nút bấm add, edit, delete -->
                    <th>Action</th>
                        <% } %>

                </tr>
                <c:forEach items="${interviews}" var="interview">
                    <tr>
                        <td>${interview.getId()}</td>
                        <td>${interview.getInterviewDate()}</td>
                        <td>${interview.candidate.getName()}</td>
                        <td>${interview.job.getTitle()}</td>
                        <td>${interview.interviewer.getUsername()}</td>
                        <% if (isManager) { %>
                        <!-- Hiển thị nút bấm add, edit, delete -->
                        <td>
                            <a href="UpdateInterviewServlet?id=${interview.getId()}">Edit</a> | 
                            <a href="DeleteInterviewServlet?id=${interview.getId()}">Delete</a>
                        </td>
                        <% } %>


                    </tr>
                </c:forEach>
            </table>
            <% if (request.getAttribute("errorMessage") != null) { %>
            <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
            <% } %>
            <% if (isManager) { %>
            <!-- Hiển thị nút bấm add, edit, delete -->
            <<a href="AddInterviewServlet" class="add-button">Add New Interview</a>
            <% } %>


        </div>

        <style>
            body {
                font-family: Arial, sans-serif; /* Lựa chọn font chữ chung */
                margin: 0; /* Loại bỏ margin mặc định */
                padding: 0; /* Loại bỏ padding mặc định */
                display: flex; /* Sử dụng flexbox */
                flex-direction: row;

            }

            .sidebar {
                width: 200px; /* Chiều rộng của sidebar */
                background-color: #333; /* Màu nền cho sidebar */
                color: #fff; /* Màu chữ cho sidebar */
                flex: 1;
            }

            .logo {
                text-align: center; /* Căn giữa nội dung trong sidebar */
                padding: 20px 0; /* Khoảng cách giữa nội dung và biên */
            }

            .logo img {
                width: 80px; /* Chiều rộng của logo */
            }

            .logo h1 {
                margin-top: 10px; /* Khoảng cách giữa logo và tiêu đề */
            }

            .sidebar ul {
                list-style-type: none; /* Loại bỏ dấu đầu dòng của danh sách */
                padding: 0; /* Loại bỏ padding mặc định */
                margin: 0; /* Loại bỏ margin mặc định */
            }

            .sidebar ul li {
                padding: 10px 20px; /* Khoảng cách giữa nội dung và biên */
            }

            .sidebar ul li a {
                color: #fff; /* Màu chữ cho các liên kết trong sidebar */
                text-decoration: none; /* Loại bỏ gạch chân của liên kết */
            }

            .sidebar ul li a:hover {
                background-color: #444; /* Màu nền khi di chuột qua liên kết */
            }

            .user-info{
                margin-left: 20px;
            }

            .content {
                flex: 5; /* Sử dụng phần còn lại của không gian */
                padding: 20px; /* Khoảng cách giữa nội dung và biên */
            }

            h2 {
                color: #333; /* Màu chữ cho tiêu đề */
                text-align: center;
            }

            form {
                margin-bottom: 20px; /* Khoảng cách giữa form và bảng dữ liệu */
                text-align: end;
            }

            input[type="text"],
            input[type="submit"] {
                padding: 8px; /* Khoảng cách giữa nội dung và biên */
                border: 1px solid #ccc; /* Viền nền */
                border-radius: 5px; /* Bo tròn các góc */
            }

            input[type="submit"] {
                background-color: #007bff; /* Màu nền cho nút Submit */
                color: #fff; /* Màu chữ cho nút Submit */
                cursor: pointer; /* Hiển thị con trỏ khi rê chuột qua nút */
            }

            table {
                border-collapse: collapse; /* Loại bỏ khoảng cách giữa các ô */
                width: 100%; /* Chiều rộng của bảng */
            }

            table, th, td {
                border: 1px solid #ddd; /* Viền cho bảng và các ô */
            }

            th, td {
                padding: 10px; /* Khoảng cách giữa nội dung và biên */
                text-align: left; /* Căn lề nội dung sang trái */
            }

            th {
                background-color: #f2f2f2; /* Màu nền cho hàng tiêu đề */
                color: #333; /* Màu chữ cho hàng tiêu đề */
            }

            a {
                text-decoration: none; /* Loại bỏ gạch chân của liên kết */
                color: #007bff; /* Màu chữ cho liên kết */
            }

            a:hover {
                text-decoration: underline; /* Gạch chân khi di chuột qua liên kết */
            }

            p {
                color: red; /* Màu chữ cho thông báo lỗi */
            }

            a.add-button {
                display: inline-block; /* Hiển thị nút thêm mới trên cùng */
                text-align: end;
                margin-top: 20px; /* Khoảng cách giữa bảng và nút thêm mới */
                padding: 10px 20px; /* Khoảng cách giữa nội dung và biên */
                background-color: #28a745; /* Màu nền cho nút thêm mới */
                color: #fff; /* Màu chữ cho nút thêm mới */
                border: none; /* Loại bỏ đường viền của nút */
                border-radius: 5px; /* Bo tròn các góc của nút */
            }
        </style>

    </body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.User" %>
<%@ page import="Model.Job" %>
<%@ page import="Model.Candidate" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Update Interview</title>
    </head>
    <body>
        <%@ include file="sidebar.jsp" %>
        <div class="content">
            <h2>Update Interview</h2>
            <form action="UpdateInterviewServlet" method="post">
                <input type="hidden" name="interviewId" value="${interview.id}">
                <label>Candidate: <span name="candidateName" > ${interview.getCandidate().getName()} </span></label>
                <input type="hidden" name="candidateId" value="${interview.getCandidate().getId()}">


                <label>Job:  <span name="JobTitle" value="${interview.getJob().getId()}"> ${interview.getJob().getTitle()} </span></label>
                <input type="hidden" name="jobId" value="${interview.getJob().getId()}">

                <label>Interview Date:</label>
                <input type="date" name="interviewDate" value="${interview.interviewDate}" required><br>
                <label>Interviewer:</label>
                <Select name="interviewerId" required>
                    <c:forEach items="${interviewers}" var="interviewer">
                        <option  value="${interviewer.getId()}">${interviewer.getUsername()}</option>
                    </c:forEach>
                </Select>
                <% if (request.getAttribute("errorMessage") != null) { %>
                <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
                <% } %>
                <input type="submit" value="Update Interview">
            </form>
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
                text-align: center; /* Căn giữa tiêu đề */
            }

            form {
                max-width: 400px; /* Đặt chiều rộng tối đa của form */
                margin: 0 auto; /* Căn giữa form */
                padding: 20px; /* Khoảng cách giữa nội dung và biên */
                border: 1px solid #ccc; /* Đặt viền cho form */
                border-radius: 5px; /* Bo tròn các góc của form */
            }


            label {
                display: block; /* Hiển thị label dưới dạng block */
                margin-bottom: 5px; /* Khoảng cách giữa các label */
            }

            select,
            input[type="date"] {

                padding: 8px; /* Khoảng cách giữa nội dung và biên */
                margin-bottom: 10px; /* Khoảng cách giữa các select và input[type="date"] */
                border: 1px solid #ccc; /* Viền nền */
                border-radius: 5px; /* Bo tròn các góc */
            }

            select{
                width: 100%;
            }

            input[type="date"]{
                width: 95%;
            }
            input[type="submit"] {
                background-color: #007bff; /* Màu nền cho nút Submit */
                color: #fff; /* Màu chữ cho nút Submit */
                cursor: pointer; /* Hiển thị con trỏ khi rê chuột qua nút */
                padding: 10px 20px; /* Khoảng cách giữa nội dung và biên */
                border: none; /* Loại bỏ đường viền của nút */
                border-radius: 5px; /* Bo tròn các góc của nút */
            }

            input[type="submit"]:hover {
                background-color: #0056b3; /* Màu nền khi di chuột qua nút */
            }

            p {
                color: red; /* Màu chữ cho thông báo lỗi */
            }
        </style>
    </body>
</html>
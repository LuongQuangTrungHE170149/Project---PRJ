<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="sidebar">
    <div class="logo">
        <img src="/logo.png" alt="Logo">
        <h1>Personnel recruitment</h1>
    </div>
    <ul>
        <li><a href="CandidateServlet">Candidates</a></li>
        <li><a href="JobServlet">Jobs</a></li>
        <li><a href="InterviewServlet">Interviews</a></li>
        <!-- Add more menu items as needed -->
        <c:if test="${not empty user}">
            <li><a href="LogoutServlet">Logout</a></li>
            </c:if>
    </ul>
    <div class="user-info">
        <c:if test="${not empty user}">
            <p>Welcome, ${user.getUsername()}</p>
        </c:if>
    </div>
</div>
<style>
    
</style>
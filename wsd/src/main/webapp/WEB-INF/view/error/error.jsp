<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 표준 JSTL core 태그 선언 -->

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
</head>
<body>
    <h1>An error occurred</h1>

    <p><strong>Request URI:</strong> ${requestScope["jakarta.servlet.error.request_uri"]}</p>
    <p><strong>Status Code:</strong> ${requestScope["jakarta.servlet.error.status_code"]}</p>
    <c:if test="${not empty requestScope['jakarta.servlet.error.exception']}">
        <p><strong>Exception:</strong> ${exception.getClass().getName()}</p>
        <p><strong>Exception Message:</strong> ${exception.getMessage()}</p>
    </c:if>
    <c:if test="${empty requestScope['jakarta.servlet.error.exception']}">
        <p><strong>Error Message:</strong> ${requestScope["jakarta.servlet.error.message"]}</p>
    </c:if>
</body>
</html>

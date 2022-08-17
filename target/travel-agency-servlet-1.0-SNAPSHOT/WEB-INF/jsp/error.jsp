
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <%@include file="header.jsp" %>
        <%@ include file="include.jsp" %>
        <style type="text/css"><%@include file="/resources/css/style.css"%></style>
        <fmt:setLocale value="${sessionScope.localization}"/>
        <fmt:setBundle basename="localization.local" var="local"/>
        <fmt:bundle basename="localization">
            <fmt:message key="local.error" var="errorMes"/>
        </fmt:bundle>
    </head>
    <body>
    <div class = "error">
        <h2 style="color: red; font-weight: bold">${errorMes}</h2>
        <h3 style="color: red; font-weight: bold">ERROR</h3>
    </div>
    </body>
</html>

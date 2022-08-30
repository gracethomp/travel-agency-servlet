<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/forRegister.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.welcome" var="welcom"/>
        <fmt:message key="local.contact" var="contact"/>
        <fmt:message key="local.registerMessage" var="registrMessage"/>
        <fmt:message key="local.name" var="name"/>
        <fmt:message key="local.surname" var="surname"/>
        <fmt:message key="local.money" var="money"/>
        <fmt:message key="local.email" var="email"/>
        <fmt:message key="local.login" var="login"/>
        <fmt:message key="local.password" var="password"/>
        <fmt:message key="local.send" var="send"/>
        <fmt:message key="local.follow" var="follow"/>
    </fmt:bundle>
</head>
<body>
<header>
    <div>
        <div>
            <a href="${pageContext.request.contextPath}/main.jsp">
                <img src="<c:url value="/resources/images/logo.png"/>"
                     alt="logotype" class="logo"/>
            </a>
        </div>
        <div class="welcom">
            <h3>${welcom}</h3>
        </div>
        <div style="position: absolute; right: 350px; top: 35px;">
            <a href="Controller?command=register_page" style="color: white;">Личный кабинет
                <img src="${pageContext.request.contextPath}/resources/images/1177568.png"
                     alt = "logotype" style="height: 20px;">
            </a>
        </div>
        <div>
            <ul id="contact-info" class="contact-info">
                <li>${contact}</li>
                <li>+380 63 351 12 20</li>
            </ul>
        </div>
    </div>
</header>
<form>
    <div class="container">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>

        <label><b>Login</b></label>
        <input type="text" placeholder="Enter Login" name="login" required>

        <label><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="psw" required>

    </div>

    <div class="container signin">
        <p>Already have an account? <a href="#">Sign in</a>.</p>
    </div>
</form>
<div class="footer" id="footercontext" align="center">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">${follow}
            <a href="https://twitter.com" target="_blank">
                <img src="${pageContext.request.contextPath}/resources/images/twitter.png"
                     alt="twitter" class="twitter"/>
            </a>
            <a href="https://www.facebook.com" target="_blank">
                <img src="<c:url value="/resources/images/Facebook_Logo_(2019).png"/>"
                     alt="facebook" class="facebook"/>
            </a>
            <a href="https://www.instagram.com" target="_blank">
                <img src="<c:url value="/resources/images/2048px-Instagram_icon.png"/>" alt="camera" class="camera"/>
            </a>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4"><ctg:CopyRight/></div>
        <div class="col-sm-4"></div>
    </div>
</div>
</body>
</html>
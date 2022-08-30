<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>

    <fmt:bundle basename="localization">
        <fmt:message key="local.welcome" var="welcom"/>
        <fmt:message key="local.contact" var="contact"/>
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
</body>
</html>

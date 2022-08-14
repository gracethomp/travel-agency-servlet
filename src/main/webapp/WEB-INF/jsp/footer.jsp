<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
    <%@include file="/WEB-INF/jsp/include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>

    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>

    <fmt:bundle basename="localization">
        <fmt:message key="local.follow" var="follow"/>
    </fmt:bundle>
</head>

<body>

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
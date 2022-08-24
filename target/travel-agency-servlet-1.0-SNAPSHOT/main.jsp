<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <%@include file="/WEB-INF/jsp/header.jsp" %>
    <%@include file="/WEB-INF/jsp/include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.welcome" var="welcome"/>
        <fmt:message key="local.register" var="register"/>
        <fmt:message key="local.logIn" var="logIn"/>
        <fmt:message key="local.logOut" var="logOut"/>
        <fmt:message key="local.loginMessage" var="loginMessage"/>
        <fmt:message key="local.registerMessage" var="registerMessage"/>
        <fmt:message key="local.en" var="en"/>
        <fmt:message key="local.ua" var="ua"/>
        <fmt:message key="local.viewAllVaucher" var="viewAllVaucher"/>
        <fmt:message key="local.Greece" var="Greece"/>
        <fmt:message key="local.Poland" var="Poland"/>
        <fmt:message key="local.Spain" var="Spain"/>
        <fmt:message key="local.Montenegro" var="Montenegro"/>
        <fmt:message key="local.Russia" var="Russia"/>
        <fmt:message key="local.Bulgaria" var="Bulgaria"/>
        <fmt:message key="local.beach" var="beach"/>
        <fmt:message key="local.shopping" var="shopping"/>
        <fmt:message key="local.excursion" var="excursion"/>
        <fmt:message key="local.fitness" var="fitness"/>
        <fmt:message key="local.vacation" var="vacation"/>
    </fmt:bundle>
</head>
<body>
<div class="">
    <div class="content">
        <div align="center" style="margin-top: 0; margin-bottom: 250px">
            <table width=800px border="1"  style="border: 5px ridge rgba(10,112,27,0.24)">
                <tr>
                    <td colspan="2">
                        <div class="locale">
                            <p style="font-weight: bold">
                                <a href="Controller?command=change_locale&localization=en"
                                style="color: rgba(0,26,7,0.58);">${en}
                                    <img src="resources/images/united_kingdom_icon_127821.png"
                                         alt="logo" style="height: 15px">
                                </a>
                                <a href="Controller?command=change_locale&localization=ua"
                                   style="color: rgba(0,26,7,0.58);">${ua}
                                    <img src="resources/images/ukraine_icon_127856.png" alt="logo" style="height: 15px">
                                </a>
                            </p>
                            <p align="center" style="font-weight: bold; font-size: 16px; font-family: Verdana, serif">
                                <a href="Controller?command=view_all_vauchers" class="button"
                                   style="color: rgba(0,26,7,0.58);">${viewAllVaucher}</a>
                            </p>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <p align="center" style="color: rgba(0,26,7,0.58); font-style: normal">
                            <a class="button" style="color: rgba(0,26,7,0.58);">${shopping}</a><a> | </a>
                            <a class="button" style="color: rgba(0,26,7,0.58);">${excursion}</a><a> | </a>
                            <a class="button" style="color: rgba(0,26,7,0.58);">${vacation}</a>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
        <div align="center" style="margin-top: 300px; margin-bottom: 0">
            <table width=800px border="1"  style="border: 5px ridge rgba(10,112,27,0.24)">
                <tr>
                    <td>
                        <p align="center" style="color: rgba(0,26,7,0.58); font-weight: bold;
                        font-size: 18px; margin: 10px">
                            <a class="button" style="color: rgba(0,26,7,0.58);">${loginMessage}
                                <img src="${pageContext.request.contextPath}/resources/images/1177568.png"
                                     alt = "logotype" style="height: 20px;">
                            </a>
                        </p>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>
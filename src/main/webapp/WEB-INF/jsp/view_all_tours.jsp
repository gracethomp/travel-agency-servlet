<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>View vauchers</title>
    <%@include file="header.jsp" %>
    <%@ include file="include.jsp" %>
    <style type="text/css"><%@include file="/resources/css/style.css"%></style>
    <fmt:setLocale value="${sessionScope.localization}"/>
    <fmt:setBundle basename="localization.local" var="local"/>
    <fmt:bundle basename="localization">
        <fmt:message key="local.country" var="country"/>
        <fmt:message key="local.dateFrom" var="dateFrom"/>
        <fmt:message key="local.dateTo" var="dateTo"/>
        <fmt:message key="local.tour.type" var="tourtype"/>
        <fmt:message key="local.tour.price" var="tourprice"/>
        <fmt:message key="local.tour.hot" var="tourhot"/>
        <fmt:message key="local.hotel.name" var="hotelname"/>
        <fmt:message key="local.hotel.pricePerDay" var="hotelpricePerDay"/>
        <fmt:message key="local.transport" var="transport"/>
        <fmt:message key="local.en" var="en"/>
        <fmt:message key="local.ua" var="ua"/>
        <fmt:message key="local.description" var="description"/>
        <fmt:message key="local.priceAsc" var="priceAsc"/>
        <fmt:message key="local.priceDesc" var="priceDesc"/>
        <fmt:message key="local.personAsc" var="personAsc"/>
        <fmt:message key="local.personDesc" var="personDesc"/>
        <fmt:message key="local.hotelAsc" var="hotelAsc"/>
        <fmt:message key="local.hotelDesc" var="hotelDesc"/>
        <fmt:message key="local.sort" var="sort"/>
    </fmt:bundle>
</head>
<body>
<div align="center">
    <table width=800px border="1"  style="border: 5px ridge rgba(10,112,27,0.24)">
        <tr>
            <td colspan="2">
                <div class="locale">
                    <p style="font-weight: bold">
                        <a href="Controller?command=change_locale&localization=en"
                           style="color: rgba(0,26,7,0.58);">${en}
                            <img src="${pageContext.request.contextPath}/resources/images/united_kingdom_icon_127821.png"
                                 alt="logo" style="height: 15px">
                        </a>
                        <a href="Controller?command=change_locale&localization=ua"
                           style="color: rgba(0,26,7,0.58);">${ua}
                            <img src="${pageContext.request.contextPath}/resources/images/ukraine_icon_127856.png" alt="logo" style="height: 15px">
                        </a>
                    </p>
                </div>
                <ul class="mmenuu">
                    <li><a href=#>${sort}</a>
                        <ul class="ssubmenuu">
                            <li><a href="Controller?command=order_by_price&sort=asc&type=<c:out value="${tourType}"/>">${priceAsc}</a></li>
                            <li><a href="Controller?command=order_by_price&sort=desc&type=<c:out value="${tourType}"/>">${priceDesc}</a></li>
                            <li><a href="Controller?command=order_by_number_of_person&sort=asc&type=<c:out value="${tourType}"/>">${personAsc}</a></li>
                            <li><a href="Controller?command=order_by_number_of_person&sort=desc&type=<c:out value="${tourType}"/>">${personDesc}</a></li>
                            <c:if test="${tourType == '1'}">
                                <li><a href="Controller?command=order_by_hotel&sort=asc&type=1">${hotelAsc}</a></li>
                                <li><a href="Controller?command=order_by_hotel&sort=desc&type=1">${hotelDesc}</a></li>
                            </c:if>
                        </ul>
                    </li>
                </ul>
            </td>
        </tr>
    </table>
</div>
<div align="center" style="margin-bottom: 50px">
    <table border="1"  style="border: 3px ridge rgba(0,26,7,0.58)">
        <tr align="center" style="font-weight: bold; ">
            <td>???</td>
            <td>${country}</td>
            <td>${dateFrom}</td>
            <td>${dateTo}</td>
            <td>${tourtype}</td>
            <td>${tourprice}</td>
            <td>${tourhot}</td>
            <td>${description}</td>
            <td></td>
        </tr>
        <c:forEach var="tour" items="${tours}" varStatus="status">
            <tr>
                <td><c:out value="${status.count}"/></td>
                <td><c:out value="${tour.city.country.name}"/></td>
                <td><c:out value="${tour.dateFrom}"/></td>
                <td><c:out value="${tour.dateTo}"/></td>
                <td><c:out value="${tour.type.title}"/></td>
                <td><c:out value="${tour.price}"/> UAN</td>
                <td><c:out value="${tour.hot}"/></td>
                <td style="width: 300px"><c:out value="${tour.description}"/><br>Amount person:<c:out value="${tour.amountPerson}"/></td>
                <td style="height: 200px; width: 30%; margin: 0; min-width: 200px;">
                    <img style="width: 100%; margin: 0;" alt="" src=<c:out value="${tour.path}"/>>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<div style="margin-bottom: 150px">
    <h2 align="center" style="color: red; font-weight: bold">${error}</h2>
</div>
<div style="margin-top: 200px; margin-bottom: 500px">
</div>
<%@ include file="footer.jsp" %>
</body>
</html>

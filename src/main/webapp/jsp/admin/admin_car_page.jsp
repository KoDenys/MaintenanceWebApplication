<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../head.jsp"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=adminMenu">CarOk</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:if test="${locale == 'uk'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-ua"> </span><fmt:message key="admin_page_jsp.header.ua_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=en"><span class="flag-icon flag-icon-us"> </span> <fmt:message key="admin_page_jsp.header.en_language"/></a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${locale == 'en'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-us"></span><fmt:message key="admin_page_jsp.header.en_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=uk"><span class="flag-icon flag-icon-ua"> </span><fmt:message key="admin_page_jsp.header.ua_language"/></a>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=showRequests"><fmt:message key="admin_page_jsp.header.repair"/> (${repairs.size()})</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">Admin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="admin_page_jsp.header.logout"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br>
<br>
<br>
<div class="container-fluid">
<table id="example" cellspacing=0 class="table table-striped " width=100%>
    <thead>
    <tr>
        <th class="text-center" scope="col"><fmt:message key="admin_car_page_jsp.table.header.carNumber"/></th>
        <th class="text-center" scope="col"><fmt:message key="admin_car_page_jsp.table.header.owner"/></th>
        <th class="text-center" scope="col"><fmt:message key="admin_car_page_jsp.table.header.carName"/></th>
        <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_owner"/></th>
        <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_mileage"/></th>
        <th class="text-center" scope="col"><fmt:message key="admin_page_jsp.table.header.block"/></th>
        <th class="text-center" scope="col"><fmt:message key="button.delete"/></th>
    </tr>
    </thead>
    <tbody>
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${cars}">
    <c:set var="k" value="${k+1}"/>
    <tr>
        <td class="text-center">${item.carNumber}</td>
        <td class="text-center">${item.carName}</td>
        <td class="text-center">${item.carModel}</td>
        <td class="text-center">${item.user.userInfo.firstName} ${item.user.userInfo.lastName}</td>
        <td class="text-center">${item.carMileage}</td>
         <c:if test="${user.active ne false}">
        <td class="text-center">
            <c:choose>
                <c:when test="${item.blocked eq false}">
                    <form name="blockUser" method="post" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="blockCar">
                        <input type="hidden" name="state" value="true">
                        <input type="hidden" name="carId" value="${item.carId}">
                        <input type="hidden" name="userId" value="${item.userId}">
                        <button type="submit" class="btn btn-outline-danger"><fmt:message key="button.block"/></button>
                    </form>
                </c:when>
                <c:when test="${item.blocked eq true}">
                    <form name="blockUser" method="post" action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="blockCar">
                        <input type="hidden" name="state" value="false">
                        <input type="hidden" name="carId" value="${item.carId}">
                        <input type="hidden" name="userId" value="${item.userId}">
                        <button type="submit" class="btn btn-outline-success"><fmt:message key="button.unblock"/></button>
                    </form>
                </c:when>
            </c:choose>
        </c:if>
        </td>
        <td class="text-center">
            <form name="removeCar" method="post" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="removeCar">
                <input type="hidden" name="state" value="true">
                <input type="hidden" name="carId" value="${item.carId}">
            <button class="btn btn-outline-danger" type="submit">
                <fmt:message key="button.delete"/></button>
            </form>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>

<script>
    $(document).ready(function () {
        $('#example').DataTable({
            <c:if test="${locale == 'uk'}">
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/Ukranian.json"
            }
            </c:if>
        });
    });
</script>

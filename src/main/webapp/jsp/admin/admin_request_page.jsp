<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../head.jsp"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=adminMenu">CarOk</a>
        <div class="collapse navbar-collapse" align="right" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <c:if test="${locale == 'uk'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
                                class="flag-icon flag-icon-ua"> </span><fmt:message
                                key="admin_page_jsp.header.ua_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=en"><span
                                    class="flag-icon flag-icon-us"> </span> <fmt:message
                                    key="admin_page_jsp.header.en_language"/></a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${locale == 'en'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="dropdown09" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false"><span
                                class="flag-icon flag-icon-us"></span><fmt:message
                                key="admin_page_jsp.header.en_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item"
                               href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=uk"><span
                                    class="flag-icon flag-icon-ua"> </span><fmt:message
                                    key="admin_page_jsp.header.ua_language"/></a>
                        </div>
                    </li>
                </c:if>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=showRequests"><fmt:message
                            key="admin_page_jsp.header.repair"/> (${repairs.size()})</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=showAllCars"><fmt:message
                            key="admin_page_jsp.table.header.user_cars"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="">Admin</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message
                            key="admin_page_jsp.header.logout"/></a>
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
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_date"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_sum"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_worker"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_status"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_type"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_description"/></th>
            <th class="text-center" scope="col"><fmt:message key="admin_car_page_jsp.table.header.carNumber"/></th>
            <th class="text-center" scope="col"><fmt:message key="admin_car_page_jsp.table.header.carName"/></th>
            <th class="text-center" scope="col"><fmt:message key="admin_car_page_jsp.table.header.carModel"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_owner"/></th>
            <th class="text-center" scope="col"><fmt:message key="button.details"/></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${repairs}">
            <c:set var="k" value="${k+1}"/>

                    <c:if test="${item.description == 'Request to remove car'}">
                        <tr class="table-danger">
                    </c:if>
                    <c:if test="${item.description != 'Request to remove car'}">
                         <tr >
                    </c:if>
                    <td class="text-center">${item.repairDateTime}</td>
                    <td class="text-center">${item.repairSum}</td>
                    <td class="text-center">${item.worker.userInfo.firstName} ${item.worker.userInfo.lastName}</td>
                 <c:if test="${locale == 'uk'}">
                    <td class="text-center">${item.repairState.stateValueUk}</td>
                    <td class="text-center">${item.repairType.repairTypeNameUk}</td>
                 </c:if>
                 <c:if test="${locale == 'en'}">
                        <td class="text-center">${item.repairState.stateValueEn}</td>
                        <td class="text-center">${item.repairType.repairTypeNameEn}</td>
                 </c:if>
                    <td class="text-center">${item.description}</td>
                    <td class="text-center">${item.repairedCar.carNumber}</td>
                    <td class="text-center">${item.repairedCar.carName}</td>
                    <td class="text-center">${item.repairedCar.carModel}</td>
                    <td class="text-center">${item.owner.userInfo.firstName} ${item.owner.userInfo.lastName}</td>
                    <td class="text-center" width="250px">
                        <form action="controller" name="submitRequest" method="get">
                            <input type="hidden" name="repairId" value="${item.repairId}">
                            <input type="hidden" name="repair" value="${item}">
                            <input type="hidden" name="carId" value="${item.repairedCar.carId}">
                            <input type="hidden" name="locale" value="${locale}">
                            <button type="submit" class="btn btn-warning" name="command" value="editRequestPage"><fmt:message key="button.edit"/></button>
                            <button type="submit" class="btn btn-danger" name="command" value="removeRequest" formaction="${pageContext.request.contextPath}/controller"><fmt:message key="button.delete"/></button>
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

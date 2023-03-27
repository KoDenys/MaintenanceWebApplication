<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/car.css" %>
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="controller?command=main">CarOK</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
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
                    <a class="nav-link" href="#"><fmt:message key="label.total_balance"/>(${user.userInfo.money})</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="label.total_bonus"/>(${user.coupon.balance})</a>
                </li>
                <li class="nav-item">
                <li class="nav-item">
                    <a class="nav-link" href="#">${user.userInfo.firstName} ${user.userInfo.lastName}</a>
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
<div class="d-flex flex-row justify-content-center bd-highlight mb-3" style="background: transparent">
    <a>
        <div class="container d-flex justify-content-center text-black mt-5" style="background: transparent">
            <div class="card p-4">
                <div class="card p-4" style="background: transparent">
                    <form id="selectCar" method="GET" action="${pageContext.request.contextPath}/controller">
                        <div class="card-header" style=" background-color: ${car.carColor}"></div>
                        <div class="card-top">
                            <input type="hidden" name="carId" value="${car.carId}">
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6><fmt:message key="admin_car_page_jsp.table.header.carName"/></h6>
                                <h5>${car.carName}</h5>
                            </div>
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6><fmt:message key="admin_car_page_jsp.table.header.carModel"/></h6>
                                <h5>${car.carModel}</h5>
                            </div>
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6><fmt:message key="admin_car_page_jsp.table.header.carNumber"/></h6>
                                <h5>${car.carNumber}</h5>
                            </div>
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6><fmt:message key="admin_car_page_jsp.table.header.carYear"/></h6>
                                <h5>${car.carYear}</h5>
                            </div>
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6><fmt:message key="admin_car_page_jsp.table.header.carMileage"/></h6>
                                <h5>${car.carMileage}</h5>
                            </div>
                            <div class="d-flex flex-row justify-content-between align-items-center">
                                <h6>Власник</h6>
                                <h5>${user.userInfo.firstName} ${user.userInfo.lastName}</h5>
                            </div>
                        </div>
                        <div class="card-bottom text-center">
                            <div class="row">
                                <div class="container-fluid items-center">
                                    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                                    <c:if test="${car.blocked eq false and user.active eq true }">
                                        <button class="btn btn-outline-success "  type="submit" name="command" value="addRequestPage" formaction="${pageContext.request.contextPath}/controller">
                                            <fmt:message key="button.repair"/></button>
                                        <button class="btn btn-outline-danger" type="submit" name="command" value="removeCar" formaction="${pageContext.request.contextPath}/controller">
                                            <fmt:message key="button.delete"/></button>
                                    </c:if>
                                        </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </a>

</div>

<br>

<div class="container-fluid">
    <table id="example" cellspacing=0 class="table table-striped " width=100%>
        <thead>
        <tr>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_date"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_sum"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_status"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_type"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_count"/></th>
            <th class="text-center" scope="col"><fmt:message key="repairs_menu.table.header.repair_description"/></th>
            <th class="text-center" scope="col"><fmt:message key="button.details"/></th>
            <th class="text-center" scope="col"><fmt:message key="button.edit_pay"/></th>
        </tr>
        </thead>
        <tbody>
        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${repairlist}">
            <c:set var="k" value="${k+1}"/>

                <c:if test="${locale == 'uk'}">
                    <tr>
                        <td class="text-center">${item.repairDateTime}</td>
                        <td class="text-center">${item.repairSum}</td>
                        <td class="text-center">${item.repairState.stateValueUk}</td>
                        <td class="text-center">${item.repairType.repairTypeNameUk}</td>
                        <td class="text-center">${item.count}</td>
                        <td class="text-center">${item.description}</td>
                        <td class="text-center">
                            <form action="controller" name="submitRepair" method="get">
                                <input type="hidden" name="repair" value="${item}">
                                <input type="hidden" name="repairId" value="${item.repairId}">
                                <input type="hidden" name="carId" value="${car.carId}">
                                <input type="hidden" name="locale" value="${locale}">
                                <button type="submit" name="command" value="submitPage" class="btn btn-success"><fmt:message key="button.details"/></button>
                                <button type="submit" class="btn btn-danger" name="command" value="removeRequest" formaction="${pageContext.request.contextPath}/controller">
                                    <fmt:message key="button.delete"/></button>
                            </form>
                        </td>
                        <td class="text-center">
                            <c:if test="${item.repairSum != '0' and item.repairState.stateValueEn == 'Waiting payment'}">
                                <form action="controller" name="payRepair" method="post">
                                    <input type="hidden" name="repair" value="${item}">
                                    <input type="hidden" name="repairId" value="${item.repairId}">
                                    <button type="submit" class="btn btn-warning" name="command" value="payPageCommand" formaction="${pageContext.request.contextPath}/controller">
                                        <fmt:message key="label.pay"/></button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:if>
            <c:if test="${locale == 'en'}">
                <tr>
                <td class="text-center">${item.repairDateTime}</td>
                <td class="text-center">${item.repairSum}</td>
                <td class="text-center">${item.repairState.stateValueEn}</td>
                <td class="text-center">${item.repairType.repairTypeNameEn}</td>
                <td class="text-center">${item.count}</td>
                <td class="text-center">${item.description}</td>
                <td class="text-center">
                    <form action="controller" name="submitRepair" method="get">
                        <input type="hidden" name="repair" value="${item}">
                        <input type="hidden" name="repairId" value="${item.repairId}">
                        <input type="hidden" name="carId" value="${car.carId}">
                        <input type="hidden" name="locale" value="${locale}">
                        <button type="submit" name="command" value="submitPage" class="btn btn-success"><fmt:message key="button.details"/></button>
                        <button type="submit" class="btn btn-danger" name="command" value="removeRequest" formaction="${pageContext.request.contextPath}/controller">
                            <fmt:message key="button.delete"/></button>
                    </form>
                </td>
                    <td class="text-center">
                        <c:if test="${item.repairSum != '0' and item.repairState.stateValueEn == 'Waiting payment'}">
                            <form action="controller" name="payRepair" method="post">
                                <input type="hidden" name="repair" value="${item}">
                                <input type="hidden" name="repairId" value="${item.repairId}">
                            <button type="submit" class="btn btn-warning" name="command" value="payPageCommand" formaction="${pageContext.request.contextPath}/controller">
                                <fmt:message key="label.pay"/></button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:if>
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
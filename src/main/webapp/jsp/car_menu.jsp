<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/car.css" %>
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=main">CarOK</a>
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
                    <a class="nav-link" href="#"><fmt:message key="label.total_balance"/>(${user.userInfo.money})</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><fmt:message key="label.total_bonus"/>(${user.coupon.balance})</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">${user.userInfo.firstName} ${user.userInfo.lastName}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="admin_page_jsp.header.logout"/></a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<br>
<div class="container-fluid">
    <div class="row">
    <c:set var="k" value="0"/>
    <c:forEach var="item" items="${user.cars}">
        <c:set var="k" value="${k+1}"/>
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
        <div class="container-fluid d-flex justify-content-center text-black mt-5">
                    <div class="card p-4" style="background: transparent">
                        <form id="selectCar" method="GET" action="${pageContext.request.contextPath}/controller">
                        <div class="card-header" style=" background-color: ${item.carColor}"></div>
                            <div class="card-top">
                                <input type="hidden" name="carId" value="${item.carId}">
                                <div class="d-flex flex-row justify-content-between align-items-center">
                                    <h6><fmt:message key="admin_car_page_jsp.table.header.carName"/></h6>
                                     <h5>${item.carName}</h5>
                                </div>
                                <div class="d-flex flex-row justify-content-between align-items-center">
                                    <h6><fmt:message key="admin_car_page_jsp.table.header.carModel"/></h6>
                                    <h5>${item.carModel}</h5>
                                </div>
                                <div class="d-flex flex-row justify-content-between align-items-center">
                                    <h6><fmt:message key="admin_car_page_jsp.table.header.carNumber"/></h6>
                                    <h5>${item.carNumber}</h5>
                                </div>
                                <div class="d-flex flex-row justify-content-between align-items-center">
                                    <h6><fmt:message key="admin_car_page_jsp.table.header.carYear"/></h6>
                                    <h5>${item.carYear}</h5>
                                </div>
                                <div class="d-flex flex-row justify-content-between align-items-center">
                                    <h6><fmt:message key="admin_car_page_jsp.table.header.carMileage"/></h6>
                                    <h5>${item.carMileage}</h5>
                                </div>
                        </div>
                            <div class="card-bottom text-center">
                            <div class="row">
                            <div class="container-fluid items-center">
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 ">
                                <button class="btn btn-outline-info " type="submit" name="command" value="getCar">
                                    <fmt:message key="button.details"/></button>
                                    <c:if test="${item.blocked eq false and user.active eq true }">
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
    </c:forEach>
    <c:if test="${(user.active eq true)}">
        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
            <div class="container-fluid d-flex justify-content-center text-black mt-5">
                <div class="card p-4" style="background: transparent">
                    <button type="button" class="btn btn-outline-success btn-lg btn-block" data-toggle="modal" data-target="#modalAddCar"><fmt:message key="button.add_car"/></button>
                </div>
            </div>
        </div>
        <div class="modal fade" id="modalAddCar" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title"><fmt:message key="label.adding_car"/></h4>
                        <button class="close" data-dismiss="modal">x</button>
                    </div>
                    <div class="modal-body">
                        <form id="regForm" name="carForm" method="POST" action="${pageContext.request.contextPath}/controller" onsubmit="return validate();">
                            <input type="hidden" name="command" value="addCar"/>
                            <div class="form-label-group">
                                <label for="carNum"><fmt:message key="admin_car_page_jsp.table.header.carNumber"/></label>
                                <input type="text" id="carNum" required class="form-control" name="carNumber"
                                       placeholder="<fmt:message key="admin_car_page_jsp.table.header.carNumber"/>" autofocus
                                       minlength="8"
                                       maxlength="8"
                                       oninput="this.setCustomValidity('')"
                                       oninvalid="this.setCustomValidity('This field must have 8 characters')"
                                       oninput="this.setCustomValidity('')">

                            </div>
                            <div class="form-label-group">
                                <label for="carName"><fmt:message key="admin_car_page_jsp.table.header.carName"/></label>
                                <input type="text" id="carName" required class="form-control" name="carName"
                                       placeholder="<fmt:message key="admin_car_page_jsp.table.header.carName"/>" autofocus
                                       oninvalid="this.setCustomValidity('This field can\'t be empty')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                            <div class="form-label-group">
                                <label for="carModel"><fmt:message key="admin_car_page_jsp.table.header.carModel"/></label>
                                <input type="text" id="carModel" required class="form-control" name="carModel"
                                       placeholder="<fmt:message key="admin_car_page_jsp.table.header.carModel"/>" autofocus
                                       oninvalid="this.setCustomValidity('This field can\'t be empty')"
                                       oninput="this.setCustomValidity('')">
                            </div>
                            <div class="form-label-group">
                                <label for="carColor"><fmt:message key="admin_car_page_jsp.table.header.carColor"/></label>
                                <input type="color" id="carColor" class="form-control" name="carColor">
                                <label for="carModel"></label>
                            </div>
                            <div class="form-label-group">
                                <label for="carYear"><fmt:message key="admin_car_page_jsp.table.header.carYear"/></label>
                                <input type="text" id="carYear" class="form-control" name="carYear"
                                       placeholder="<fmt:message key="admin_car_page_jsp.table.header.carYear"/>" autofocus required
                                       minlength="1" pattern="^[0-9]*$"
                                       oninput="this.setCustomValidity('')"
                                       oninvalid="this.setCustomValidity('Must contain only digits')">
                            </div>
                            <div class="form-label-group">
                                <label for="carMileage"><fmt:message key="admin_car_page_jsp.table.header.carMileage"/></label>
                                <input type="text" id="carMileage" class="form-control" name="carMileage"
                                       placeholder="<fmt:message key="admin_car_page_jsp.table.header.carMileage"/>" autofocus required
                                       minlength="1" pattern="^[0-9]*$"
                                       oninput="this.setCustomValidity('')"
                                       oninvalid="this.setCustomValidity('Must contain only digits')">
                            </div>
                                <br/>
                            <button class="btn btn-lg btn-success btn-block btn-login text-uppercase font-weight-bold mb-2"
                                    type="submit"><fmt:message key="button.add_car"/></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:if>
    </div>
</div>

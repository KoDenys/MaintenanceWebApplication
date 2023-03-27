<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/login.css" %>
</style>


<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
        <a class="navbar-brand">CarOK</a>
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
            </ul>
        </div>
    </div>
</nav>


<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6 "/><!--bg-image"><img src="https://thumbs.dreamstime.com/b/логотип-ремонта-автомоби-ей-в-наговоре-79791012.jpg" />--></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <br>
                            <br>
                            <h3 class="login-heading mb-4"><fmt:message key="registration.label"/></h3>
                            <form id="regForm" name="loginForm" method="POST" action="controller" onsubmit="return validate();">
                                <input type="hidden" name="command" value="reg"/>
                                <div class="form-label-group">
                                    <input type="text" id="login" class="form-control" name="login"
                                           placeholder="<fmt:message key="login.form.label.login"/>" autofocus
                                           oninvalid="this.setCustomValidity('This field can\'t be empty')"
                                           oninput="this.setCustomValidity('')">
                                    <label for="login"><fmt:message key="login.form.label.login"/></label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="password" class="form-control" name="pass"
                                           placeholder="<fmt:message key="login.form.label.password"/>" required
                                           minlength="8" oninput="this.setCustomValidity('')"
                                           oninvalid="this.setCustomValidity('Password must be longer than 8 characters')">
                                    <label for="password"><fmt:message key="login.form.label.password"/></label>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="firstName" class="form-control" name="firstName"
                                           placeholder="<fmt:message key="registration.form.first_name"/>" autofocus required
                                           pattern="^[A-ZА-ЯЇІЄ'][a-zа-яїіє']+"
                                           oninput="this.setCustomValidity('')"
                                           oninvalid="this.setCustomValidity('First name must start with a capital letter and contain only letters')">
                                    <label for="firstName"><fmt:message key="registration.form.first_name"/></label>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="lastName" class="form-control" name="lastName"
                                           placeholder="<fmt:message key="registration.form.last_name"/>" autofocus required
                                           pattern="^[A-ZА-ЯЇІЄ'][a-zа-яїіє']+" oninput="this.setCustomValidity('')"
                                           oninvalid="this.setCustomValidity('Last name must start with a capital letter and contain only letters')">
                                    <label for="lastName"><fmt:message key="registration.form.last_name"/></label>
                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="phoneNumber" class="form-control" name="phoneNumber"
                                           placeholder="<fmt:message key="registration.form.phone_number"/>" autofocus required
                                           minlength="10" maxlength="10" pattern="^[0-9]*$"
                                           oninput="this.setCustomValidity('')"
                                           oninvalid="this.setCustomValidity('Phone number must contain only digits and be 10 in length')">
                                    <label for="phoneNumber"><fmt:message key="registration.form.phone_number"/></label>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                        type="submit"><fmt:message key="button.create_account"/></button>
                                <a href="controller?command=login_page"
                                   class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2"
                                   type="submit"><fmt:message key="button.signin"/></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
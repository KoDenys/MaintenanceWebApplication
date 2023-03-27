<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<c:import url="head.jsp"/>

<style><%@include file="/styles/login.css"%></style>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container">
        <a class="navbar-brand">CarOK</a>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <c:if test="${locale == 'en'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-us"></span><fmt:message key="admin_page_jsp.header.en_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=uk"><span class="flag-icon flag-icon-ua"> </span><fmt:message key="admin_page_jsp.header.ua_language"/></a>
                        </div>
                    </li>
                </c:if>
                <c:if test="${locale == 'uk'}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown09" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="flag-icon flag-icon-ua"> </span><fmt:message key="admin_page_jsp.header.ua_language"/></a>
                        <div class="dropdown-menu" aria-labelledby="dropdown09">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=switchLanguage&locale=en"><span class="flag-icon flag-icon-us"> </span> <fmt:message key="admin_page_jsp.header.en_language"/></a>
                        </div>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row no-gutter">
        <div class="d-none d-md-flex col-md-4 col-lg-6"></div>
        <div class="col-md-8 col-lg-6">
            <div class="login d-flex align-items-center py-5">
                <div class="container">
                    <div class="row">
                        <div class="col-md-9 col-lg-8 mx-auto">
                            <h3 class="login-heading mb-4"><fmt:message key="login.label"/></h3>
                            <form id="login" name="loginForm" method="post" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="login"/>
                                <div class="form-label-group">
                                    <input type="text" id="inputLogin" class="form-control" name="login" placeholder="<fmt:message key="login.form.label.login"/>" required autofocus value="${login}">
                                    <label for="inputLogin"><fmt:message key="login.form.label.login"/></label>
                                </div>
                                <div class="form-label-group">
                                    <input type="password" id="inputPassword" class="form-control" name="pass" placeholder="<fmt:message key="login.form.label.password"/>" required>
                                    <label for="inputPassword"><fmt:message key="login.form.label.password"/></label>
                                </div>
                                <button class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="submit"><fmt:message key="button.signin"/></button>
                                <a href="${pageContext.request.contextPath}/controller?command=reg_page" class="btn btn-lg btn-primary btn-block btn-login text-uppercase font-weight-bold mb-2" type="button"><fmt:message key="button.signup"/></a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%--<script>--%>
<%--    $(document).ready(function() {--%>
<%--        $('form[id="login"]').validate({--%>
<%--            rules: {--%>
<%--                inputLogin: 'required',--%>
<%--                inputPassword: {--%>
<%--                    required: true,--%>
<%--                }--%>
<%--            },--%>
<%--            messages: {--%>
<%--                inputLogin: 'This field is required',--%>
<%--                inputPassword: 'This field is required',--%>
<%--            },--%>
<%--            errorElement: "div",--%>
<%--            errorPlacement: function(error, element) {--%>
<%--                error.insertAfter(element);--%>
<%--            },--%>
<%--            submitHandler: function(form) {--%>
<%--                form.submit();--%>
<%--            }--%>
<%--        });--%>
<%--    });--%>
<%--</script>--%>

<script>
    jQuery.validator.setDefaults({
        debug: true,
        success: "valid"
    });
    $( "#login" ).validate({
        rules: {
            pass: {
                required: true,
                minlength: 3
            }
        }
    });
</script>


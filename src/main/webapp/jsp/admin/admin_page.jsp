<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="../head.jsp"/>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=adminMenu">CarOk</a>
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
                    <a class="nav-link"
                       href="${pageContext.request.contextPath}/controller?command=showRequests"><fmt:message
                            key="admin_page_jsp.header.repair"/>(${repairs.size()})</a>
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
    <table id="example" cellspacing=0 class="table table-hover" width=100%>
        <thead>
        <tr>
            <th scope="col"><fmt:message key="admin_page_jsp.table.header.first_name"/></th>
            <th scope="col"><fmt:message key="admin_page_jsp.table.header.last_name"/></th>
            <th class="text-center" scope="col"><fmt:message key="registration.form.phone_number"/></th>
            <th class="text-center" scope="col"> <fmt:message key="admin_page_jsp.table.header.user_type"/> </th>
            <th class="text-center" scope="col"><fmt:message key="admin_page_jsp.table.header.block"/></th>
            <th class="text-center" scope="col"><fmt:message key="admin_page_jsp.table.header.user_cars"/></th>
            <th class="text-center" scope="col"><fmt:message key="admin_page_jsp.table.header.user_sum"/></th>
            <th class="text-center" scope="col"><fmt:message key="admin_page_jsp.table.header.user_send"/></th>
        <tbody>
        <c:set var="k" value="0"/>
        <c:forEach var="item" items="${users}">
            <c:set var="k" value="${k+1}"/>
        <tr>

            <td>${item.userInfo.firstName}</td>
            <td>${item.userInfo.lastName}</td>
            <td class="text-center">${item.userInfo.phoneNumber}</td>
            <td>${item.userType.userType}</td>
            <td class="text-center">
                <c:choose>
                <c:when test="${item.active eq true}">
                <form name="blockUser" method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="blockUser">
                    <input type="hidden" name="user" value="${item.userId}">
                    <button type="submit" class="btn btn-outline-danger"><fmt:message key="button.block"/></button>
                </form>
                </c:when>
                <c:when test="${item.active eq false}">
                <form name="blockUser" method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="command" value="unblockUser">
                    <input type="hidden" name="user" value="${item.userId}">
                    <button type="submit" class="btn btn-outline-success"><fmt:message key="button.unblock"/></button>
                </form>
                </c:when>
                </c:choose>
            <td class="text-center">
                <form name="showCars" method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="user" value="${item.userId}">
                    <div class="container">
                    <button type="submit" class="btn btn-outline-info" name="command" value="showCars"><fmt:message
                            key="admin_page_jsp.button.show_cars"/></button>
                    </div>
                </form>
        </td>
            <td>
                <form name="giveMoney" id="#giveMoney" method="post" action="${pageContext.request.contextPath}/controller">
                    <input type="hidden" name="user" value="${item.userId}">
                            <input type="number" id="sum" class="form-control" name="sum"
                                   placeholder="<fmt:message key="admin_page_jsp.table.header.user_justSum"/>"
                                   minlength="1" pattern="^[0-9]*$"
                                   oninput="this.setCustomValidity('')"
                                   oninvalid="this.setCustomValidity('Must contain only digits')">
                </form>
                </td>
                 <td>
                    <button type="submit" form="#giveMoney" class="btn btn-outline-success" name="command" value="giveMoney"><fmt:message
                            key="admin_page_jsp.button.give_money"/></button>

                 </td>
                </c:forEach>

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

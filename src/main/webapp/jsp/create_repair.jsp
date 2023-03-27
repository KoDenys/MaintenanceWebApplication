<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="head.jsp"/>

<style>
    <%@include file="/styles/create_request.css" %>
</style>

<div class="card mt-50 mb-50">
    <div class="card-title mx-auto"><fmt:message key="button.create_repair"/></div>
    <form name="createRepair" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="addRepair">
        <input type="hidden" name="locale" id="local" value="${locale}">
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
        <div class="row-1">
            <select class="typeRepair" id="repairType" required name="typeRepair" onchange="selectClick(this)" style="background: transparent; width: 100%" >
                <option value=""><fmt:message key="label.select_repair_type"/></option>
             <c:set var="k" value="0"/>
             <c:forEach var="item" items="${repairtypelist}">
             <c:set var="k" value="${k+1}"/>
                 <c:if test="${locale == 'uk'}">
                    <option value="${item.repairTypeId}" >${item.repairTypeNameUk}, <fmt:message key="label.price"/> = ${item.priceList.price}</option>
                 </c:if>
                 <c:if test="${locale == 'en'}">
                     <option value="${item.repairTypeId}">${item.repairTypeNameEn}, <fmt:message key="label.price"/> = ${item.priceList.price}</option>

                 </c:if>
             </c:forEach>
            </select>

        </div>
        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"><fmt:message key="label.count"/></span> </div>
            <div class="row row-2"> <input type="number" id="count" name="count" min="1" value="1"/></div>
        </div>
        <button type="submit" class="btn d-flex mx-auto"><b><fmt:message key="button.add_create_some"/></b></button>
        <button type="submit" form="#cancel" class="btn d-flex mx-auto btn-danger" ><b><fmt:message key="button.add_cancel"/></b></button>
    </form>
    <form name="cancel" method="get" id="#cancel" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="main">
        <input type="hidden" name="locale"  value="${locale}">
    </form>
</div>

<script>
    function selectClick(typeRepair) {
       var lc = document.getElementById("local").getAttribute('value');
      if(typeRepair.value==="777") {
          if(lc==="en") {
              alert("You are selecting category another. " + "\r\nPlease visit our service station."+"\r\nOur specialists will check your vehicle.");
          }
          if(lc==="uk"){
              alert("Ви обрали категорію інше.\r\nБудь ласка відвідайте нашу сервісну станцію.\r\nНаші спеціалісти перевірять ваше авто.");
          }
      }
    }


</script>

<<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="head.jsp"/>

<style>
  <%@include file="/styles/create_request.css" %>
</style>

<div class="card">
  <div class="card-title mx-auto"><fmt:message key="label.pay"/></div>
  <form name="createPayment" method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="payCommand">
    <input type="hidden" name="locale" id="local" value="${locale}">
    <input type="hidden" name="repair" id="repair" value="${repair}">
    <input type="hidden" name="summ" id="summ" value="${repair.repairSum}">
    <input type="hidden" name="max" id="max" value="${repair.owner.coupon.balance}">
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
      <h6><fmt:message key="submit_page.label.repair_id"/></h6>
      <h5>${repair.repairId}</h5>
    </div>
    <c:if test="${locale == 'uk'}">
    <div class="d-flex flex-row justify-content-between align-items-center">
      <h6><fmt:message key="repairs_menu.table.header.repair_type"/></h6>
      <h5>${repair.repairType.repairTypeNameUk}</h5>
    </div>
      <div class="d-flex flex-row justify-content-between align-items-center">
      <h6><fmt:message key="repairs_menu.table.header.repair_status"/></h6>
      <h5>${repair.repairState.stateValueUk}</h5>
      </div>
    </c:if>
    <c:if test="${locale == 'en'}">
      <div class="d-flex flex-row justify-content-between align-items-center">
        <h6><fmt:message key="repairs_menu.table.header.repair_type"/></h6>
        <h5>${repair.repairType.repairTypeNameEn}</h5>
      </div>
      <div class="d-flex flex-row justify-content-between align-items-center">
        <h6><fmt:message key="repairs_menu.table.header.repair_status"/></h6>
        <h5>${repair.repairState.stateValueEn}</h5>
      </div>
    </c:if>
    <div class="d-flex flex-row justify-content-between align-items-center">
      <h6><fmt:message key="repairs_menu.table.header.repair_sum"/></h6>
      <h5>${repair.repairSum}</h5>
    </div>
    <div class="d-flex flex-row justify-content-between align-items-center">
      <h6><fmt:message key="repairs_menu.table.header.repair_count"/></h6>
      <h5>${repair.count}</h5>
    </div>
    <div class="row-1">
      <div class="row row-2"> <span id="card-inner"><fmt:message key="repairs_menu.table.header.repair_discount"/> max ${repair.owner.coupon.balance}</span> </div>
      <div class="row row-2"> <input type="number" id="#discount" name="discount" oninput="getTotal(this.value)" min="0" max="${repair.owner.coupon.balance}" value="0"/></div>
    </div>
    <div class="row-1">
      <div class="row row-2"> <span id="card-inner"><fmt:message key="submit_page.label.total_sum"/></span> </div>
      <div class="row row-2"> <input type="number" disabled id="#totalSum" name="totalSum" value="${repair.repairSum}"/></div>
    </div>
    <button type="submit" class="btn d-flex mx-auto"><b><fmt:message key="button.add_create_some"/></b></button>
  </form>
  <form name="back" method="get" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="getCar">
    <input type="hidden" name="locale" id="local" value="${locale}">
    <input type="hidden" name="repair" id="repair" value="${repair}">
    <input type="hidden" name="carId" value="${car.carId}">
    <button type="submit" class="btn d-flex mx-auto btn-danger" ><b><fmt:message key="button.add_cancel"/></b></button>
  </form>
</div>

<script>
  function getTotal(discount) {
    max = Number.parseFloat(document.getElementById("max").getAttribute('value'));
    if( discount > max){
      discount=max;
    }
    var sm = document.getElementById("summ").getAttribute('value');
    var ttl = sm-discount;
    document.getElementById("#totalSum").setAttribute('value', ttl);
    document.getElementById("#discount").setAttribute('value', discount);
  }


</script>

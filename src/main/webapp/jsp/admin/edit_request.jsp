<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:import url="/jsp/head.jsp"/>

<style>
    <%@include file="/styles/create_request.css" %>
</style>

<div class="card">
    <div class="card-title mx-auto"><fmt:message key="button.edit_repair"/></div>
    <form name="createPayment" method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="editRequest">
        <input type="hidden" name="locale" id="local" value="${locale}">
        <input type="hidden" name="carId"  value="${repair.repairedCar.carId}">
        <input type="hidden" name="repairId"  value="${repair.repairId}">
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="admin_car_page_jsp.table.header.carName"/></h6>
            <h5>${repair.repairedCar.carName}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="admin_car_page_jsp.table.header.carModel"/></h6>
            <h5>${repair.repairedCar.carModel}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="admin_car_page_jsp.table.header.carNumber"/></h6>
            <h5>${repair.repairedCar.carNumber}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="admin_car_page_jsp.table.header.carYear"/></h6>
            <h5>${repair.repairedCar.carYear}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="admin_car_page_jsp.table.header.carMileage"/></h6>
            <h5>${repair.repairedCar.carMileage}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
        <h6><fmt:message key="repairs_menu.table.header.repair_owner"/></h6>
        <h5>${repair.owner.userInfo.firstName} ${repair.owner.userInfo.lastName}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="submit_page.label.repair_id"/></h6>
            <h5>${repair.repairId}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="submit_page.label.repair_time"/></h6>
            <h5>${repair.repairDateTime}</h5>
        </div>
        <div class="d-flex flex-row justify-content-between align-items-center">
            <h6><fmt:message key="repairs_menu.table.header.repair_guarantee"/></h6>
            <h5>${repair.priceList.guarantee}</h5>
        </div>

        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"><fmt:message key="admin_car_page_jsp.table.header.carMileage"/></span> </div>
            <div class="row row-2"> <input type="text"  name="currentMileage" value="${repair.currentMileage}"/></div>
        </div>

        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"><fmt:message key="repairs_menu.table.header.repair_sum"/></span> </div>
            <div class="row row-2"> <input type="text"  name="repairSum" value="${repair.repairSum}"/></div>
        </div>
        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"><fmt:message key="repairs_menu.table.header.repair_discount"/></span> </div>
            <div class="row row-2"> <input type="text"  name="discount" value="${repair.discount}"/></div>
        </div>
        <div class="row-1">
            <div class="row row-2"> <span id="card-inner"> <fmt:message key="label.count"/></span> </div>
            <div class="row row-2"> <input type="text"  name="count" value="${repair.count}"/></div>
        </div>

        <div class="row-1">
            <select  id="repairType" name="repairType"  style="background: transparent; width: 100%" >
                <c:if test="${locale == 'uk'}">
                    <option selected value="${repair.repairTypeId}">${repair.repairType.repairTypeNameUk}</option>
                </c:if>
                <c:if test="${locale == 'en'}">
                    <option selected value="${repair.repairTypeId}">${repair.repairType.repairTypeNameEn}</option>
                </c:if>

                <c:set var="k" value="0"/>
                <c:forEach var="item" items="${repairtypelist}">
                    <c:set var="k" value="${k+1}"/>

                    <c:if test="${locale == 'uk'}">
                        <option value="${item.repairTypeId}" >${item.repairTypeNameUk}</option>
                    </c:if>
                    <c:if test="${locale == 'en'}">
                        <option value="${item.repairTypeId}">${item.repairTypeNameEn}</option>

                    </c:if>
                </c:forEach>
            </select>

        </div>
        <div class="row-1">
            <select  id="repairState" name="repairState"  style="background: transparent; width: 100%" >
                <c:if test="${locale == 'uk'}">
                    <option selected value="${repair.repairStateId}">${repair.repairState.stateValueUk}</option>
                </c:if>
                <c:if test="${locale == 'en'}">
                    <option selected value="${repair.repairStateId}">${repair.repairState.stateValueEn}</option>
                </c:if>

                <c:set var="k" value="0"/>
                <c:forEach var="item" items="${statelist}">
                    <c:set var="k" value="${k+1}"/>

                    <c:if test="${locale == 'uk'}">
                        <option value="${item.repairStateId}" >${item.stateValueUk}</option>
                    </c:if>
                    <c:if test="${locale == 'en'}">
                        <option value="${item.repairStateId}">${item.stateValueEn}</option>

                    </c:if>
                </c:forEach>
            </select>
        </div>

        <div class="row-1">
            <select class="typeRepair" id="staff" name="worker"  style="background: transparent; width: 100%" >
                <option selected value="${repair.workerId}">${repair.worker.userType.userType} ${repair.worker.userInfo.firstName} ${repair.worker.userInfo.lastName}</option>
                <c:set var="k" value="0"/>
                <c:forEach var="item" items="${staff}">
                    <c:set var="k" value="${k+1}"/>
                    <option value="${item.userId}" >${item.userType.userType} ${item.userInfo.firstName} ${item.userInfo.lastName}</option>
                </c:forEach>
            </select>

        </div>
        <div class="form-group">
            <label for="exampleFormControlTextarea1"><fmt:message key="repairs_menu.table.header.repair_description"/></label>
            <textarea class="form-control" id="exampleFormControlTextarea1" name="description" rows="3" placeholder="${repair.description}"></textarea>
        </div>
            <button type="submit" class="btn d-flex mx-auto"><b><fmt:message key="button.edit"/></b></button>
            <button type="submit" class="btn d-flex mx-auto btn-danger" formaction="${pageContext.request.contextPath}/controller?command=showRequests"><b><fmt:message key="button.add_cancel"/></b></button>
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

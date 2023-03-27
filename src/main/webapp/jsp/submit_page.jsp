<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/customtags.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<c:import url="head.jsp"/>

<body style="margin: 0 !important; padding: 0 !important; background-color: #eeeeee;" bgcolor="#eeeeee">

<table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
        <td align="center" style="background-color: #eeeeee;" bgcolor="#eeeeee">
            <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%" style="max-width:600px;">
                <tr>
                    <td align="center" style="padding: 35px 35px 20px 35px; background-color: #ffffff;"
                        bgcolor="#ffffff">
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%"
                               style="max-width:600px;">
                            <tr>
                                <td align="center"
                                    style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 15px;">
                                    <h2 style="font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;">
                                        <fmt:message key="submit_page.label.repair"/></h2>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" style="padding-top: 20px;">
                                    <table  cellspacing="0" cellpadding="0" border="0" width="100%">
                                        <tr>
                                            <td width="50%" align="left" bgcolor="#eeeeee"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                                <fmt:message key="submit_page.label.repair_id"/>
                                            </td>
                                            <td width="50%" align="left" bgcolor="#eeeeee"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;">
                                                ${repair.repairId}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="submit_page.label.repair_time"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.repairDateTime}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="admin_car_page_jsp.table.header.carNumber"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                               ${repair.repairedCar.carNumber}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="admin_car_page_jsp.table.header.carName"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.repairedCar.carName}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="admin_car_page_jsp.table.header.carModel"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.repairedCar.carModel}
                                            </td>
                                        </tr>
                                        <tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="admin_car_page_jsp.table.header.carMileage"/>

                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.currentMileage}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_owner"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${owner.userInfo.firstName} ${owner.userInfo.lastName}
                                            </td>
                                        </tr>
                                        <c:if test="${locale == 'en'}">
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_type"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
                                                ${repair.repairType.repairTypeNameEn}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_status"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                               ${repair.repairState.stateValueEn}
                                            </td>
                                        </tr>
                                        </c:if>
                                        <c:if test="${locale == 'uk'}">
                                            <tr>
                                                <td width="50%" align="left"
                                                    style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
                                                    <fmt:message key="repairs_menu.table.header.repair_type"/>
                                                </td>
                                                <td width="50%" align="left"
                                                    style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;">
                                                        ${repair.repairType.repairTypeNameUk}
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="50%" align="left"
                                                    style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                    <fmt:message key="repairs_menu.table.header.repair_status"/>
                                                </td>
                                                <td width="50%" align="left"
                                                    style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                        ${repair.repairState.stateValueUk}
                                                </td>
                                            </tr>
                                        </c:if>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_guarantee"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.priceList.guarantee}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_sum"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.repairSum}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_discount"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.discount}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="label.count"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${repair.count}
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                <fmt:message key="repairs_menu.table.header.repair_worker"/>
                                            </td>
                                            <td width="50%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;">
                                                ${worker.userInfo.firstName} ${worker.userInfo.lastName}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" style="padding-top: 20px;">
                                    <table cellspacing="0" cellpadding="0" border="0" width="100%">
                                        <tr>
                                            <td width="75%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
                                                <fmt:message key="submit_page.label.total_sum"/>
                                            </td>
                                            <td width="25%" align="left"
                                                style="font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;">
                                                ${repair.repairSum - repair.discount}
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

            </table>
        </td>
    </tr>
    <br>
    <br>
</table>
</body>
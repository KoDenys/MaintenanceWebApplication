package com.epam.kosyi.sto;

public class Path {
    public static final String PAGE_LOGIN = "/jsp/login.jsp";
    public static final String CAR_MENU = "/jsp/car_menu.jsp";
    public static final String REG_PAGE = "/jsp/registration.jsp";
    public static final String REPAIRS_MENU = "/jsp/repairs_menu.jsp";
    public static final String PAY_PAGE = "/jsp/pay_page.jsp";
    public static final String ADD_REQUEST_PAGE = "/jsp/create_repair.jsp";
    public static final String SUBMIT_REQUEST_PAGE = "/jsp/submit_page.jsp";
    public static final String SUCCESS_REQUEST_PAGE = "/jsp/success_page.jsp";
    public static final String FAILED_PAGE = "/jsp/failed_page.jsp";
    public static final String ADMIN_MENU = "/jsp/admin/admin_page.jsp";
    public static final String ADMIN_CAR_PAGE = "/jsp/admin/admin_car_page.jsp";
    public static final String ADMIN_REQUESTS_PAGE = "/jsp/admin/admin_request_page.jsp";
    public static final String MANAGER_REQUESTS_PAGE = "/jsp/admin/manager_page.jsp";
    public static final String EDIT_REQUEST_PAGE = "/jsp/admin/edit_request.jsp";

    private final String pageUrl;
    private final boolean isRedirect;


    public Path(String pageUrl, boolean isRedirect) {
        this.pageUrl = pageUrl;
        this.isRedirect = isRedirect;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public boolean isRedirect() {
        return isRedirect;
    }
}

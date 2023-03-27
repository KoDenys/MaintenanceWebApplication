package com.epam.kosyi.sto.sql;

public class CouponSQL {
    public static final String COUPON_ID = "coupon_id";
    public static final String BALANCE = "balance";
    public static final String USER = "user";

    public static final String CREATE_COUPON_FOR_USER = "INSERT INTO coupon SET user=(?)";
    public static final String GET_COUPON_FOR_USER = "SELECT * FROM coupon WHERE user = (?)";
    public static final String UPDATE_COUPON_BALANCE = "UPDATE coupon SET balance = (?) WHERE coupon_id = (?)";
}

package com.epam.kosyi.sto.sql;

public class UserTypeSQL {
    public static final String USER_TYPE_ID = "type_id";
    public static final String USER_TYPE = "type";

    public static final String GET_USER_TYPE_BY_ID = "SELECT * FROM user_type WHERE type_id = (?)";
}

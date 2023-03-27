package com.epam.kosyi.sto.sql;

public class UserSQL {
    public static final String ID = "user_id";
    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String IS_ACTIVE = "is_active";
    public static final String USER_TYPE = "user_type";
    public static final String USER_INFO = "user_info";

    public static final String GET_USER_BY_LOGIN_AND_PASS =
            "SELECT * FROM user WHERE login = (?) AND password = (?)";
    public static final String GET_USER_BY_ID =
            "SELECT * FROM user WHERE user_id = (?)";
    public static final String GET_ALL_USERS = "SELECT * FROM user WHERE user_type = (?)";
    public static final String GET_STAFF_USERS = "SELECT * FROM user WHERE user_type != (?)";
    public static final String BLOCK_USER = "UPDATE user SET is_active = (?) WHERE user_id = (?)";
    public static final String ADD_USER = "INSERT INTO user " +
            "(login, password, is_active, user_type, user_info) VALUES (?, ?, ?, ?, ?)";
}

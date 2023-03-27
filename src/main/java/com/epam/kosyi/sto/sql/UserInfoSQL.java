package com.epam.kosyi.sto.sql;

public class UserInfoSQL {
    public static final String USER_INFO_ID = "user_info_id";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String MONEY = "money";
    public static final String PHONE_NUMBER = "phone_number";

    public static final String GET_USER_INFO_BY_ID = "SELECT * FROM user_info WHERE user_info_id = (?)";
    public static final String UPDATE_MONEY = "UPDATE user_info SET money =(?) WHERE user_info_id = (?)";
    public static final String ADD_USER_INFO = "INSERT INTO user_info  " +
            "(first_name, last_name, money, phone_number) VALUES (?, ?, ?, ?)";

}

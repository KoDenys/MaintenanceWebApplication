package com.epam.kosyi.sto.sql;

public class CarSQL {
    public static final String CAR_ID = "car_id";
    public static final String CAR_NUMBER = "car_number";
    public static final String CAR_NAME = "car_name";
    public static final String CAR_MODEL = "car_model";
    public static final String CAR_COLOR = "car_color";
    public static final String CAR_YEAR = "car_year";
    public static final String MILEAGE = "mileage";
    public static final String BLOCKED = "blocked";
    public static final String USER = "user_id";

    public static final String ADD_MILEAGE = "UPDATE car SET mileage = (?) WHERE car_id = (?)";
    public static final String GET_CAR_FOR_USER = "SELECT * FROM car WHERE user_id = (?)";
    public static final String GET_CAR = "SELECT * FROM car WHERE car_id = (?)";
    public static final String GET_ALL_CARS = "SELECT * FROM car ";
    public static final String SET_ACTIVE_FOR_CAR = "UPDATE car SET blocked = (?) WHERE car_id = (?)";
    public static final String GET_CAR_BY_NUMBER = "SELECT * FROM car WHERE car_number = (?)";
    public static final String ADD_CAR = "INSERT INTO car " +
            "(car_number, car_name, car_model, car_color, car_year, mileage, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String BLOCK_CARD = "UPDATE bank_card SET is_blocked = (?) WHERE card_id = (?)";
    public static final String REMOVE_CAR = "DELETE FROM car WHERE car_id = (?)";
   // public static final String SET_REQUEST_STATUS = "UPDATE bank_card SET is_request_sent = (?) WHERE card_id = (?)";
  //  public static final String GET_REQUESTS = "SELECT * FROM bank_card WHERE is_request_sent = (?)";
}

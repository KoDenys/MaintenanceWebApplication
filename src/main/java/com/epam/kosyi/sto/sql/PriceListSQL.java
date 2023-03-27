package com.epam.kosyi.sto.sql;

public class PriceListSQL {
    public static final String PRICE_LIST_ID = "price_list_id";
    public static final String CAR_NAME = "car_name";
    public static final String CAR_MODEL = "car_model";
    public static final String YEAR_MIN = "year_min";
    public static final String YEAR_MAX = "year_max";
    public static final String PRICE = "price";
    public static final String GUARANTEE = "guarantee";
    public static final String REPAIR_TYPE = "repair_type";

    public static final String GET_PAYMENT_NAME = "SELECT payment_type FROM payment_type_locale WHERE payment_type_id = (?) AND locale_id_loc = (?)";
    public static final String GET_PRICE_LIST_FOR_CAR = "SELECT price_list_id, price, repair_type FROM price_list WHERE car_name = (?) AND car_model = (?) AND year_min <= (?) AND year_max >= (?)";
    public static final String GET_PRICE_LIST_BY_REPAIR_ID_FOR_CAR = "SELECT *  FROM price_list WHERE repair_type = (?) AND car_name = (?) AND car_model = (?) AND year_min <= (?) AND year_max >= (?)";
}

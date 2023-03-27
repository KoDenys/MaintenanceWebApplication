package com.epam.kosyi.sto.sql;

public class RepairSQL {
    public static final String REPAIR_ID = "repair_id";
    public static final String REPAIR_SUM = "repair_sum";
    public static final String REPAIR_DESCRIPTION = "description";
    public static final String REPAIRE_DATETIME = "repaire_datetime";
    public static final String DISCOUNT = "discount";
    public static final String COUNT = "count";
    public static final String CURRENT_MILEAGE = "current_mileage";
    public static final String WORKER = "worker";
    public static final String REPAIR_STATE = "repair_state";
    public static final String PRICE_LIST = "price_list";
    public static final String REPAIR_TYPE = "repair_type";
    public static final String CAR = "car";

    public static final String SET_REPAIR_STATE = "UPDATE repair SET repair_state = (?) WHERE payment_id = (?)";
    public static final String REMOVE_REPAIR = "DELETE FROM repair WHERE repair_id = (?)";
    public static final String GET_REPAIR = "SELECT * FROM repair WHERE car = (?)";
    public static final String GET_ALL_REPAIRS = "SELECT * FROM repair";
    public static final String CREATE_REPAIR = "INSERT INTO repair (repair_sum, description, discount, count, worker, repair_state, car, repair_type, price_list, current_mileage) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_REPAIR = "UPDATE repair SET repair_sum=(?), description=(?), discount=(?), count=(?), worker=(?), repair_state=(?), car=(?), repair_type=(?), price_list=(?), current_mileage=(?) WHERE repair_id = (?) ";
    public static final String GET_REPAIR_BY_ID = "SELECT * FROM repair WHERE repair_id = (?)";

}

package com.epam.kosyi.sto.sql;

public class RepairStateSQL {
    public static final String REPAIR_STATE_ID = "repair_state_id";
    public static final String STATE_VALUE_EN = "state_value_en";
    public static final String STATE_VALUE_UK = "state_value_uk";

    public static final String GET_ALL_REPAIR_STATE = "SELECT * FROM repair_state";
    public static final String GET_REPAIR_STATE_BY_NAME = "SELECT * FROM repair_state WHERE state_value_en =(?)";
    public static final String GET_REPAIR_STATES_BY_ID = "SELECT * FROM repair_state where repair_state_id = (?)";
}

package com.epam.kosyi.sto.sql;

public class RepairTypeSQL {
    public static final String REPAIR_TYPE_ID = "repair_type_id";
    public static final String REPAIR_TYPE_NAME_EN = "repair_type_name_en";
    public static final String REPAIR_TYPE_NAME_UK = "repair_type_name_uk";

    public static final String GET_ALL_REPAIR_TYPE = "SELECT * FROM repair_type";
    public static final String GET_REPAIR_TYPE_BY_ID = "SELECT * FROM repair_type WHERE repair_type_id = (?)";
}

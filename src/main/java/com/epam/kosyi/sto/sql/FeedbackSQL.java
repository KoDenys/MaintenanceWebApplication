package com.epam.kosyi.sto.sql;

public class FeedbackSQL {
    public static final String FEEDBACK_ID = "feedback_id";
    public static final String TEXT = "text";
    public static final String REPAIR_ID = "repair_repair_id";
    public static final String STARS = "stars";

    public static final String CREATE_FEEDBACK = "INSERT INTO feedback (text, stars, repair_repair_id) VALUES(?, ?, ?)";
    public static final String GET_FEEDBACK = "SELECT * FROM feedback WHERE repair_repair_id = (?)";
}

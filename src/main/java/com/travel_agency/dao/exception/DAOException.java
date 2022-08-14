package com.travel_agency.dao.exception;

public class DAOException extends Exception {
    public static final String SQL_EXCEPTION_FINDING_COUNTRIES = "SQL Exception while finding countries";
    public static final String SQL_CREATING = "SQL Exception while creating";
    public static final String SQL_UPDATING = "SQL Exception while updating ";
    public static final String SQL_DELETING = "SQL Exception while deleting";
    public static final String SQL_FINDING_BY_ID = "SQL Exception while finding by id";
    public static final String SQL_EXCEPTION_FINDING_CITIES = "SQL Exception while finding cities";

    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}

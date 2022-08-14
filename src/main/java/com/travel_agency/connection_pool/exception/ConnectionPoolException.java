package com.travel_agency.connection_pool.exception;

public class ConnectionPoolException extends Exception {
    public static final String INIT_SQL_EXCEPTION_MESSAGE = "While connection initialization was caught SQLException";
    public static final String INIT_CLASS_NOT_FOUND_EXCEPTION_MESSAGE
            = "While connection initialization was caught ClassNotFoundException";
    public static final String TAKE_INTERRUPTED_EXCEPTION_MESSAGE = "While taking was caught InterruptedException";
    public static final String CLOSING_SQL_EXCEPTION_MESSAGE = "While connections closing was caught SQLException";
    public static final String VALUE_NOT_FOUND = "Value wasn't found";

    public ConnectionPoolException() {
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}

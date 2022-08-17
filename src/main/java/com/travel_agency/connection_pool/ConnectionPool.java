package com.travel_agency.connection_pool;

import com.travel_agency.connection_pool.exception.ConnectionPoolException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final String DRIVER = "driver-name";
    private static final String URL = "connection-url";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_COUNT = "connection-count";
    private static final String CONFIG_FILE_NAME = "database";

    private final ResourceBundle bundle = ResourceBundle.getBundle(CONFIG_FILE_NAME);

    private static ConnectionPool instance = null;

    private BlockingQueue<ProxyConnection> availableConnections;
    private String url;
    private int count;
    private String driver;
    private String user;
    private String password;

    private ConnectionPool() {
        this.url = getValue(URL);
        this.count = Integer.parseInt(getValue(CONNECTION_COUNT));
        this.driver = getValue(DRIVER);
        this.user = getValue(USER);
        this.password = getValue(PASSWORD);

        try {
            initialize();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance(){
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private  void initialize() throws ConnectionPoolException {
        try {
            Class.forName(driver);
            availableConnections = new ArrayBlockingQueue<>(count);
            for (int i = 0; i < count; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.add(proxyConnection);
            }
        }
        catch (SQLException e) {
            throw new ConnectionPoolException(ConnectionPoolException.INIT_SQL_EXCEPTION_MESSAGE, e);
        }
        catch (ClassNotFoundException e) {
            throw new ConnectionPoolException(ConnectionPoolException.INIT_CLASS_NOT_FOUND_EXCEPTION_MESSAGE, e);
        }
    }
    public Connection getConnection() throws ConnectionPoolException {
        try {
            return availableConnections.take();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(ConnectionPoolException.TAKE_INTERRUPTED_EXCEPTION_MESSAGE, e);
        }
    }

    public void releaseConnection(ProxyConnection connection) {
        try {
            connection.setAutoCommit(true);
            availableConnections.add(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePoolConnections() throws ConnectionPoolException {
        for (int i = 0; i < availableConnections.size(); i++) {
            try {
                availableConnections.take().close2();
            }
            catch (InterruptedException e) {
                throw new ConnectionPoolException(ConnectionPoolException.TAKE_INTERRUPTED_EXCEPTION_MESSAGE);
            } catch (SQLException e) {
                throw new ConnectionPoolException(ConnectionPoolException.CLOSING_SQL_EXCEPTION_MESSAGE);
            }
        }
    }
    private String getValue(String value) {
        String result = null;
        if (value != null) {
            try {
                result = bundle.getString(value);
            } catch (MissingResourceException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

package com.epam.kosyi.sto.dao.factory;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Connector {
    private static volatile Connector connector;

    private final static Logger LOGGER = Logger.getLogger(Connector.class);

    public static Connector getInstance() {
        Connector localInstance = connector;
        if (localInstance == null) {
            synchronized (Connector.class) {
                localInstance = connector;
                if (localInstance == null) {
                    connector = localInstance = new Connector();
                }
            }
        }
        return localInstance;
    }

    private Connector(){}

    private static DataSource pool;

    /**
     *
     * @return connection from connection pool
     */
    public static synchronized Connection getConnection() {
        if (pool == null) {
            try {
                Context ctx = new InitialContext();
                pool  = (DataSource)ctx.lookup("java:comp/env/jdbc/websto_db");

            } catch (NamingException e) {
                LOGGER.error(e.getMessage());
            }
        }

        try {
            return pool.getConnection();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }
    }
}

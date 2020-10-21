package com.benito.dalmeida.app.arrange.movie;

import com.benito.dalmeida.app.arrange.tvshow.TvShowArrangerProccess;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MovieDBConnection {
    private static final Log LOGGER = LogFactory.getLog(MovieDBConnection.class);
    private String sqliteDatabaseFilePath;

    public void connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:"+sqliteDatabaseFilePath;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            LOGGER.info("MovieDBConnection to SQLite has been established.");

        } catch (SQLException e) {
            LOGGER.error("Cannot connect to "+sqliteDatabaseFilePath,e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                LOGGER.error("Cannot close the connect",ex);
            }
        }
    }
}

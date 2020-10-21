package com.benito.dalmeida.app.arrange.movie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MovieDBConnection {
    private static final Log LOGGER = LogFactory.getLog(MovieDBConnection.class);
    private String sqliteDatabaseFilePath;
    private Connection conn = null;

    public void connect() {
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

    public void disconnect(){
        try {
            if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                LOGGER.error("Cannot close the connect",ex);
            }
    }

    
    public void insert(List<AlloCineMovieInfo> alloCineMovieInfos){
        for(AlloCineMovieInfo : alloCineMovieInfos){

        }

    }

    private void insert(AlloCineMovieInfo alloCineMovieInfo) {
        String sql = "INSERT INTO warehouses(name,capacity) VALUES(?,?)";

                try (Connection conn = this.connect();
                     PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setDouble(2, capacity);
                    pstmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
    }
}

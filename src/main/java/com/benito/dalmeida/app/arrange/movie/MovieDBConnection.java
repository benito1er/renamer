package com.benito.dalmeida.app.arrange.movie;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MovieDBConnection {
    private static final Log LOGGER = LogFactory.getLog(MovieDBConnection.class);

    private String sqliteDatabaseFilePath;


    public Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:" + sqliteDatabaseFilePath;
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            LOGGER.info("MovieDBConnection to SQLite has been established.");

        } catch (SQLException e) {
            LOGGER.error("Cannot connect to " + sqliteDatabaseFilePath, e);
        }

        return conn;
    }

    public void disconnect(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            LOGGER.error("Cannot close the connect", ex);
        }
    }


    public void insertInTable(String movieArrangedDir, List<AlloCineMovieInfo> alloCineMovieInfos) throws IOException {
        for (AlloCineMovieInfo alloCineMovieInfo : alloCineMovieInfos) {
            insertInTable(alloCineMovieInfo);
            moveToGenreDirectory(movieArrangedDir, alloCineMovieInfo);
        }

    }

    private void moveToGenreDirectory(String movieArrangedDir, AlloCineMovieInfo alloCineMovieInfo) throws IOException {
        File destDir = new File(movieArrangedDir + File.separator + alloCineMovieInfo.getGenre());
        FileUtils.moveFileToDirectory(alloCineMovieInfo.getMovieFile(), destDir, true);
    }

    private void insertInTable(AlloCineMovieInfo alloCineMovieInfo) {
        String sql = " INSERT INTO List (\n" +
                " filename , \n" +
                " titleKey  , \n" +
                " titre  , \n" +
                " movieLink  , \n" +
                " titreOriginal  , \n" +
                " dateDeSortie  , \n" +
                " anneeDeSortie  , \n" +
                " publicType  , \n" +
                " duree  , \n" +
                " genre  , \n" +
                " realisateur  , \n" +
                " acteurs  , \n" +
                " notePresse  , \n" +
                " noteSpec  , \n" +
                " synopsis  , \n" +
                " affiche  ) \n" +
                " VALUES (\n" +
                " ? , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ?  , \n" +
                " ? \n" +
                " )";
        Connection conn = null;
        try {
            conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, alloCineMovieInfo.getFilename());
            pstmt.setString(2, alloCineMovieInfo.getTitleKey());
            pstmt.setString(3, alloCineMovieInfo.getTitre());
            pstmt.setString(4, alloCineMovieInfo.getMovieLink());
            pstmt.setString(5, alloCineMovieInfo.getTitreOriginal());
            pstmt.setString(6, alloCineMovieInfo.getDateDeSortie());
            pstmt.setString(7, alloCineMovieInfo.getAnneeDeSortie());
            pstmt.setString(8, alloCineMovieInfo.getPublicType());
            pstmt.setString(9, alloCineMovieInfo.getDuree());
            pstmt.setString(10, alloCineMovieInfo.getGenre());
            pstmt.setString(11, alloCineMovieInfo.getRealisateur());
            pstmt.setString(12, alloCineMovieInfo.getActeurs());
            pstmt.setString(13, alloCineMovieInfo.getNotePresse());
            pstmt.setString(14, alloCineMovieInfo.getNoteSpec());
            pstmt.setString(15, alloCineMovieInfo.getNoteSpec());
            pstmt.setString(16, alloCineMovieInfo.getAffiche());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Cannot insert " + alloCineMovieInfo, e);
        } finally {
            disconnect(conn);
        }
    }


}

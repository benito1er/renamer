package com.benito.dalmeida.app.arrange.movie;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.benito.dalmeida.app.Constant.MY_MOVIES_MAIN_DIR;

public class MovieArrangerProccess {

    private MovieXmlFileParser movieXmlFileParser;

    private MovieDBConnection movieDBConnection;

    public List<AlloCineMovieInfo> getInfos(File moviesDir) throws IOException, SAXException, ParserConfigurationException {
        movieXmlFileParser = new MovieXmlFileParser();
        List<AlloCineMovieInfo> alloCineMovieInfos = new ArrayList<>();
        for (File movieDir : moviesDir.listFiles()) {
            List<AlloCineMovieInfo> currents = movieXmlFileParser.getInfo(movieDir);
            alloCineMovieInfos.addAll(currents);
        }
        movieDBConnection = new MovieDBConnection();
        movieDBConnection.insertInTable(MY_MOVIES_MAIN_DIR, alloCineMovieInfos);
        return alloCineMovieInfos;
    }


}

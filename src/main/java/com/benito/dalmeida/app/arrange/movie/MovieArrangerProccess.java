package com.benito.dalmeida.app.arrange.movie;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieArrangerProccess {

    private MovieXmlFileParser movieXmlFileParser;

    public  List<AlloCineMovieInfo> getInfos(File moviesDir) throws IOException, SAXException, ParserConfigurationException {
        // for in directories
        //
        movieXmlFileParser =  new MovieXmlFileParser();
        List<AlloCineMovieInfo> alloCineMovieInfos = new ArrayList<>();
      for(File movieDir :  moviesDir.listFiles()){
          AlloCineMovieInfo current = movieXmlFileParser.getInfo(movieDir);
          alloCineMovieInfos.add(current);
      }

      return alloCineMovieInfos;
    }


}

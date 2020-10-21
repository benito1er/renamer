package com.benito.dalmeida.app.arrange.movie;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MovieXmlFileParserTest {
    private MovieXmlFileParser movieXmlFileParser;
    @Test
    public void testGetInfo() throws IOException, SAXException, ParserConfigurationException {
        movieXmlFileParser =  new MovieXmlFileParser();
        movieXmlFileParser.getInfo(new File("V:\\Videos\\Movies\\To_Import\\matched\\2 Fast 2 Furious (2003)"));
    }
}

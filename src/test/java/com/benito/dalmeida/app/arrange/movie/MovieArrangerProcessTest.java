package com.benito.dalmeida.app.arrange.movie;

import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MovieArrangerProcessTest {
    private MovieArrangerProccess movieArrangerProccess =  new MovieArrangerProccess();

    @Test
    public void testArrange() throws ParserConfigurationException, SAXException, IOException {
        String [] sampleDirs = {"W:\\Téléchargé"};
        movieArrangerProccess.getInfos(new File("V:\\Videos\\Movies\\To_Import\\matched"));
    }
}

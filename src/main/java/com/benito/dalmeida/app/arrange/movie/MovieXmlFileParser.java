package com.benito.dalmeida.app.arrange.movie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class MovieXmlFileParser {
    private static final Log LOGGER = LogFactory.getLog(MovieXmlFileParser.class);

    public AlloCineMovieInfo getInfo(File movieDir) throws ParserConfigurationException, IOException, SAXException {
        String moviePath = movieDir.getCanonicalPath();
        File inputFile = new File(moviePath + "/Movie.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(inputFile);
        doc.getDocumentElement().normalize();
        LOGGER.info("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("Title");

        for (int i = 0; i < nList.getLength(); i++) {
            Node nNode = nList.item(i);
            LOGGER.info("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String Allocine = eElement
                        .getElementsByTagName("Allocine")
                        .item(0)
                        .getTextContent();


                String localTitle = eElement
                        .getElementsByTagName("LocalTitle")
                        .item(0)
                        .getTextContent();
                String originalTitle = eElement
                        .getElementsByTagName("OriginalTitle")
                        .item(0)
                        .getTextContent();
                String description = eElement
                        .getElementsByTagName("Description")
                        .item(0)
                        .getTextContent();
                String iMDBrating = eElement
                        .getElementsByTagName("IMDBrating")
                        .item(0)
                        .getTextContent();
                String productionYear = eElement
                        .getElementsByTagName("ProductionYear")
                        .item(0)
                        .getTextContent();
                String genre = eElement
                        .getElementsByTagName("Genre")
                        .item(0)
                        .getTextContent();
                NodeList personsNodes =  eElement
                        .getElementsByTagName("Person");
                int l=personsNodes.getLength();


                String personDirector = ((Element)personsNodes.item(0)).getElementsByTagName("Name").item(0).getTextContent();
                String personActor = "";
                for (int j = 1; j < personsNodes.getLength(); j++) {
                    Element personEl = (Element) personsNodes.item(j);
                    personActor+=personEl.getElementsByTagName("Name").item(0).getTextContent()+", ";

                }


            }
        }

        return new AlloCineMovieInfo();
    }
}

package com.benito.dalmeida.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benito.dalmeida.app.arrange.TvShowArranger;
import com.benito.dalmeida.app.rename.FileRenamer;

import static com.benito.dalmeida.app.RemoveBadWordMain.TÉLÉCHARGEMENTS;
import static com.benito.dalmeida.app.RemoveBadWordMain.TÉLÉCHARGÉ;

public class ArrangeMain {
    private static final Log LOGGER = LogFactory.getLog(RemoveBadWordMain.class);
    public static String[] sampleDirs = { "Y:" + FileRenamer.fileSeparator + "Téléchargé",
            "Y:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS ,
            "T:" + FileRenamer.fileSeparator + "Telechargement",
            "Y:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "Z:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "V:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "W:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            // "T:"+ FileRenamer.fileSeparator+"torrents"+ FileRenamer.fileSeparator + "_old"
            //"W:"+ FileRenamer.fileSeparator +"Videos"+ FileRenamer.fileSeparator +"Manga"+ FileRenamer.fileSeparator +"Dragon.Ball.Super",
            //"T:"+ FileRenamer.fileSeparator+"Videos"+ FileRenamer.fileSeparator+"XXX_Adult"+ FileRenamer.fileSeparator+"Hentai"
    };
	public static void main(String[] args) {

        TvShowArranger tvShowArranger =  new TvShowArranger();
        tvShowArranger.arrange(sampleDirs);
        LOGGER.debug("fini");
	}
}

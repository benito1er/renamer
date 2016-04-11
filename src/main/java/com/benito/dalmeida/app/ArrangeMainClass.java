package com.benito.dalmeida.app;

import com.benito.dalmeida.app.arrange.TvShowArranger;
import com.benito.dalmeida.app.rename.FileRenamer;

public class ArrangeMainClass {

	public static void main(String[] args) {
	    String [] sampleDirs = {"Y:" + FileRenamer.fileSeparator + "Téléchargé"};
        TvShowArranger tvShowArranger =  new TvShowArranger();
        tvShowArranger.arrange(sampleDirs);
        System.out.println("fini");
	}
}

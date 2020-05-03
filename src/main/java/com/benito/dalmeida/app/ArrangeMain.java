package com.benito.dalmeida.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benito.dalmeida.app.arrange.TvShowArranger;
import com.benito.dalmeida.app.rename.FileRenamer;

import static com.benito.dalmeida.app.Constant.*;

public class ArrangeMain {
    private static final Log LOGGER = LogFactory.getLog(RemoveBadWordMain.class);

	public static void main(String[] args) {

        TvShowArranger tvShowArranger =  new TvShowArranger();
        tvShowArranger.arrange(Constant.DOWNLOAD_DIR);
        LOGGER.debug("fini");
	}
}

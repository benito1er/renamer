package com.benito.dalmeida.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benito.dalmeida.app.arrange.tvshow.TvShowArrangerProccess;

public class ArrangeMain {
    private static final Log LOGGER = LogFactory.getLog(ArrangeMain.class);

	public static void main(String[] args) {

        TvShowArrangerProccess tvShowArrangerProccess =  new TvShowArrangerProccess();
        tvShowArrangerProccess.arrange(Constant.DOWNLOAD_DIR);
        LOGGER.debug("fini");
	}
}

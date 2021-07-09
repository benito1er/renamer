package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class BorutoTvShowArranger  extends AbstractTvShowArranger {
    private BorutoTvShowArranger (){           }
    private static BorutoTvShowArranger INSTANCE = null;

    public static BorutoTvShowArranger getInstance(){
        if(INSTANCE == null)
            INSTANCE =  new BorutoTvShowArranger();
        return INSTANCE;
    }
    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        return StringUtils.containsIgnoreCase(lowerFileName, "boruto") && StringUtils.containsIgnoreCase(lowerFileName, "next");
    }

    public String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);
        if (ep < 27) {
            season = "01";
        } else if (ep < 52) {
            season = "02";
        } else if (ep < 76) {
            season = "03";
        } else if (ep < 101) {
            season = "04";
        } else if (ep < 127) {
            season = "05";
        } else if (ep < 151) {
            season = "06";
        } else {
            season = "07";
        }
        return season;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Boruto.Naruto.Next.Generations";
    }


}

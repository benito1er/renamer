package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class OverlordTvShowArranger extends AbstractTvShowArranger {
    private OverlordTvShowArranger() {
    }

    private static OverlordTvShowArranger INSTANCE = null;

    public static OverlordTvShowArranger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OverlordTvShowArranger();
        return INSTANCE;
    }

    @Override
    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        return StringUtils.startsWithIgnoreCase(lowerFileName, "overlord");
    }

    @Override
    public String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        if (StringUtils.containsIgnoreCase(fileName, "iii")) {
            season = "03";
        } else if (StringUtils.containsIgnoreCase(fileName, "ii")) {
            season = "02";
        } else if (StringUtils.containsIgnoreCase(fileName, "i")) {
            season = "01";
        }
        return season;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Overlord";
    }


}

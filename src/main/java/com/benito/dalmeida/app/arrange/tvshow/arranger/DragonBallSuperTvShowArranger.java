package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class DragonBallSuperTvShowArranger extends AbstractTvShowArranger {
    private DragonBallSuperTvShowArranger() {
    }

    private static DragonBallSuperTvShowArranger INSTANCE = null;

    public static DragonBallSuperTvShowArranger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DragonBallSuperTvShowArranger();
        return INSTANCE;
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "DBS") || StringUtils.containsIgnoreCase(lowerFileName, "DBSuper"))
            return true;
        return StringUtils.containsIgnoreCase(lowerFileName, "Dragon")
                && StringUtils.containsIgnoreCase(lowerFileName, "ball")
                && !StringUtils.containsIgnoreCase(lowerFileName, "z")
                && StringUtils.containsIgnoreCase(lowerFileName, "super");
    }


    public String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep < 15) {
            season = "01";
        } else if (ep < 28) {
            season = "02";
        } else if (ep < 47) {
            season = "03";
        } else if (ep < 77) {
            season = "04";
        } else {
            season = "05";
        }
        return season;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Dragon.Ball.Super";
    }

}

package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class DragonBallZTvShowArranger extends AbstractTvShowArranger {
    private DragonBallZTvShowArranger() {
    }

    private static DragonBallZTvShowArranger INSTANCE = null;

    public static DragonBallZTvShowArranger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DragonBallZTvShowArranger();
        return INSTANCE;
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        return StringUtils.containsIgnoreCase(lowerFileName, "Dragon Ball Z") ||
                StringUtils.containsIgnoreCase(lowerFileName, "Dragon.Ball.Z");
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Dragon.Ball.Z";
    }

    public String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep < 40) {
            season = "01";
        } else if (ep < 75) {
            season = "02";
        } else if (ep < 108) {
            season = "03";
        } else if (ep < 150) {
            season = "04";
        } else if (ep < 166) {
            season = "05";
        } else if (ep <  195) {
            season = "06";
        } else if (ep < 220) {
            season = "07";
        } else if (ep < 254) {
            season = "08";
        } else {
            season = "09";
        }
        return season;
    }

}

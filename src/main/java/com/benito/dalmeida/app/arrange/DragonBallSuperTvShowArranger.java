package com.benito.dalmeida.app.arrange;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DragonBallSuperTvShowArranger implements TvShowArranger {

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "DBS") || StringUtils.containsIgnoreCase(lowerFileName, "DBSuper"))
            return true;
        if (StringUtils.containsIgnoreCase(lowerFileName, "Dragon") && StringUtils.containsIgnoreCase(lowerFileName, "ball") && StringUtils.containsIgnoreCase(lowerFileName, "super"))
            return true;
        return false;
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
    public Map<String,String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir){
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = getTvShowName(currentDir);
        TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(lowerFileName).invoke();

        String season = tvShowKnowName.getSeason();

        String episode = tvShowKnowName.getEpisode();
        //        } else if (isDragonBallSuper(lowerFileName)) {
//            tvShowName =
//                    TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(this, lowerFileName).invoke();
//            season = tvShowKnowName.getSeason();
//            episode = tvShowKnowName.getEpisode();
            if (episode != null && season == null) {
                season = getTvShowSeason(fileName, episode);
            } else {
                if (episode == null) {
                    String numbers = lowerFileName.replaceAll("\\D+", " ");
                    episode = StringUtils.split(numbers, " ")[0];
                    season = getTvShowSeason(fileName, episode);
                }


            }
        Map<String,String> result = new HashMap<>();
        result.put("tvShowName",tvShowName);
        result.put("season",season);
        result.put("episode",episode);
        return result;
    }
}

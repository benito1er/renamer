package com.benito.dalmeida.app.arrange;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DragonBallSuperHeroesTvShowArranger implements TvShowArranger {
    DragonBallSuperTvShowArranger dragonBallSuper = new DragonBallSuperTvShowArranger();

    public String getTvShowSeason(String fileName, String episode) {
        String season = "01";
        if (!dragonBallSuper.isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        return season;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Dragon.Ball.Super.Heroes";
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        if (dragonBallSuper.isThisTvShowArrangerFile(lowerFileName) && StringUtils.containsIgnoreCase(lowerFileName, "heroes"))
            return true;
        return false;
    }

    public Map<String,String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir){
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = getTvShowName(currentDir);
        TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(lowerFileName).invoke();

        String season = tvShowKnowName.getSeason();

        String episode = tvShowKnowName.getEpisode();
        //        } else if (isDragonBallSuperHeroes(lowerFileName)) {
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
//
//
            }
//        }
        Map<String,String> result = new HashMap<>();
        result.put("tvShowName",tvShowName);
        result.put("season",season);
        result.put("episode",episode);
        return result;
    }
}

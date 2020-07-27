package com.benito.dalmeida.app.arrange;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class OnePieceKaiTvShowArranger implements TvShowArranger {

    public String getTvShowSeason(String fileName, String episode) {
        String season = "01";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep < 7) {
            season = "01";
        } else if (ep < 12) {
            season = "02";
        } else if (ep < 14) {
            season = "03";
        } else if (ep < 20) {
            season = "04";
        } else if (ep < 25) {
            season = "05";
        } else if (ep < 30) {
            season = "06";
        } else if (ep < 38) {
            season = "07";
        } else if (ep < 45) {
            season = "08";
        } else if (ep < 65) {
            season = "09";
        } else if (ep < 69) {
            season = "10";
        } else {
            season = "11";
        }
        return season;

    }

    @Override
    public String getTvShowName(File currentDir) {
        return "One.Piece.Kai";
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        OnePieceTvShowArranger onePieceTvShowArranger = new OnePieceTvShowArranger();
        return (onePieceTvShowArranger.isThisTvShowArrangerFile(lowerFileName) && StringUtils.containsIgnoreCase(lowerFileName, "kai")
                || StringUtils.containsIgnoreCase(lowerFileName, "OPK Saga"));
    }

    public Map<String, String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir) {
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = getTvShowName(currentDir);
        TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(lowerFileName).invoke();

        String season = tvShowKnowName.getSeason();

        String episode = tvShowKnowName.getEpisode();
        //  if (isOnePieceKaiFile(lowerFileName)) {
//            tvShowName ="";
//            TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(this, lowerFileName).invoke();
//            season = tvShowKnowName.getSeason();
//            episode = tvShowKnowName.getEpisode();

        if (episode != null && season == null) {
            season = getTvShowSeason(fileName, episode);
        } else {
            if (episode == null) {
                String numbers = lowerFileName.replaceAll("\\D+", " ");
                if (StringUtils.isBlank(numbers)) {
                    numbers = "0";
                }

                episode = StringUtils.split(numbers, " ")[0];
                season = getTvShowSeason(fileName, episode);
            }


        }
//        }
        Map<String, String> result = new HashMap<>();
        result.put("tvShowName", tvShowName);
        result.put("season", season);
        result.put("episode", episode);
        return result;
    }
}

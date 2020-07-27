package com.benito.dalmeida.app.arrange;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AttackOnTitanTvShowArranger implements TvShowArranger {
    public boolean isThisTvShowArrangerFile(String lowerFileName){
        if( (StringUtils.containsIgnoreCase(lowerFileName, "attack") && StringUtils.containsIgnoreCase(lowerFileName, "on")&& StringUtils.containsIgnoreCase(lowerFileName, "titan"))
            ||  (StringUtils.containsIgnoreCase(lowerFileName, "attaque") && StringUtils.containsIgnoreCase(lowerFileName, "des")&& StringUtils.containsIgnoreCase(lowerFileName, "titan"))
            ||  (StringUtils.containsIgnoreCase(lowerFileName, "(shingeki") && StringUtils.containsIgnoreCase(lowerFileName, "no")&& StringUtils.containsIgnoreCase(lowerFileName, "kyojin")))
            return true;
        else
            return false;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return  "Attack.On.Titan";
    }

    public  String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep <26) {
            season = "01";
        } else if (ep < 38) {
            season = "02";
        } else if (ep < 60) {
            season = "03";
        } else {
            season = "04";
        }
        return season;

    }
    public Map<String,String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir){
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = getTvShowName(currentDir);
        TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(lowerFileName).invoke();

        String season = tvShowKnowName.getSeason();

        String episode = tvShowKnowName.getEpisode();
        //       else if (isOnePieceFile(lowerFileName)) {
//            tvShowName = "One.Piece";
//            TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(this, lowerFileName).invoke();
//            season = tvShowKnowName.getSeason();
//            episode = tvShowKnowName.getEpisode();
//
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
        Map<String,String> result = new HashMap<>();
        result.put("tvShowName",tvShowName);
        result.put("season",season);
        result.put("episode",episode);
        return result;
    }
}

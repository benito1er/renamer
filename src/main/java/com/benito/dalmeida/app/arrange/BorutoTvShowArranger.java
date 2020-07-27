package com.benito.dalmeida.app.arrange;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BorutoTvShowArranger implements TvShowArranger {
    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "boruto") && StringUtils.containsIgnoreCase(lowerFileName, "next"))
            return true;
        else
            return false;
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

    public Map<String, String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir) {
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = getTvShowName(currentDir);
        TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(lowerFileName).invoke();

        String season = tvShowKnowName.getSeason();

        String episode = tvShowKnowName.getEpisode();
        //      else if (isBorutoFile(lowerFileName)) {
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
        Map<String, String> result = new HashMap<>();
        result.put("tvShowName", tvShowName);
        result.put("season", season);
        result.put("episode", episode);
        return result;
    }
}

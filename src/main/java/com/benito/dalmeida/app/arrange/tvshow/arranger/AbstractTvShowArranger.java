package com.benito.dalmeida.app.arrange.tvshow.arranger;

import com.benito.dalmeida.app.arrange.tvshow.TvShowArranger;
import com.benito.dalmeida.app.arrange.tvshow.TvShowKnownPatternName;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTvShowArranger  implements TvShowArranger  {

    public Map<String,String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir){
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = getTvShowName(currentDir);
        TvShowKnownPatternName tvShowKnowName = new TvShowKnownPatternName(lowerFileName).invoke();

        String season = tvShowKnowName.getSeason();

        String episode = tvShowKnowName.getEpisode();

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

     public File renameFile(File currentFile, Map<String, String> tvShowinfo){
        return currentFile;
    }

}

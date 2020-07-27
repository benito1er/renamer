package com.benito.dalmeida.app.arrange;

import java.io.File;
import java.util.Map;

public interface TvShowArranger {
     boolean  isThisTvShowArrangerFile(String lowerFileName) ;

     String getTvShowSeason(String fileName, String episode);

     String getTvShowName(File currentDir);

     Map<String,String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir);
}

package com.benito.dalmeida.app.arrange.tvshow;

import java.io.File;
import java.util.Map;

public interface TvShowArranger {
     boolean  isThisTvShowArrangerFile(String lowerFileName) ;

     String getTvShowSeason(String fileName, String episode);

     String getTvShowName(File currentDir);

     Map<String,String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir);

     File renameFile(File currentFile, Map<String, String> tvShowinfo);
}

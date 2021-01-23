package com.benito.dalmeida.app.arrange.tvshow;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.util.*;



public class TvShowArrangerProccess {
    private static final Log LOGGER = LogFactory.getLog(TvShowArrangerProccess.class);
    public static String[] SHORT_PATTERNS = {
            "ep(\\d{1,3})",
            "ep.(\\d{1,3})",
            " e(\\d{1,3}) ",
            " e(\\d{1,3}) ",
            "_e(\\d{1,3})",
            "\\.e(\\d{1,3})",
            "s(\\d{1,3})\\.e(\\d{1,3})",
            "s(\\d{1,3}) (\\p{Punct}) episode (\\d{1,3})",
            "s(\\d{1,3})e(\\d{1,3})",
            "(\\d{1,3})x(\\d{1,3})",
            "s(\\d{1,3}) - e(\\d{1,3})",
    };

    public static String[] PATTERNS = {
            "s(\\d{1,2})e(\\d{1,3})",
            "s(\\d{1,2}).ep(\\d{1,3})",
            "s(\\d{1,2}) e(\\d{1,3})",
            "(\\d{1,2})x(\\d{1,3})",
            "s(\\d{1,2})-(\\d{1,3})",
            "s(\\d{1,3})\\.e(\\d{1,3})",
            "s(\\d{1,2})e(\\d{1,2})",
            "s(\\d{1,2}).ep(\\d{1,2})",
            "s(\\d{1,2}) e(\\d{1,2})",
            "(\\d{1,2})x(\\d{1,2})",
            "s(\\d{1,2})-(\\d{1,2})",
            " (\\d{1,3})",
           // "e - (\\d{1,3})",
            "- (\\d{1,3})",
            "_(\\d{1,3})",
            "_e(\\d{1,3})",
            "ep(\\d{1,3})",
            "episode (\\d{1,3})",
            "episode (\\d{1,3})",
            "(\\d{1,2}) episode (\\d{1,3})",
            "s(\\d{1,2}) - (\\d{1,2})"};
    //"\\p{Punct}(\\d{1,3})\\p{Punct}"};


    private void manageOneFile(File currentDir, Map<String, List<File>> arrangeMap) {
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        TvShowArranger currentTvShowArranger = TvShowArrangerInvoker.getTvShowArranger(lowerFileName);
        Map<String, String> tvShowinfo = currentTvShowArranger.getTvShowSeasonAndEpisodeAsMapValues(currentDir);
        String tvShowName = tvShowinfo.get("tvShowName");
        String season = tvShowinfo.get("season");
        String episode = tvShowinfo.get("episode");
        LOGGER.info(String.format("processing tvShow %s, season %s, and episode %s",tvShowName,season,episode));
        File newCurrentFile = currentTvShowArranger.renameFile(currentDir, tvShowinfo);

        StringBuilder sb = new StringBuilder();
        sb.append(tvShowName).append("#").append(season);
        String tvShowAndSeason = sb.toString();
        List<File> multimediaFiles = arrangeMap.get(tvShowAndSeason);
        if (multimediaFiles == null) {
            multimediaFiles = new ArrayList<>();
            arrangeMap.put(tvShowAndSeason, multimediaFiles);
        }
        multimediaFiles.add(newCurrentFile);

    }


    public void arrange(String[] dirs) {


        for (String rootDir : dirs) {
            File currentDir = new File(rootDir);
            Map<String, List<File>> arrangeMap = new HashMap<>();
            Map<String, List<File>> tempArrangeMap = getMapOfFiles(currentDir, arrangeMap);
            String rootBaseDir = rootDir;

            for (Map.Entry<String, List<File>> tempArrangeMapEntry : tempArrangeMap.entrySet()) {
                String key = tempArrangeMapEntry.getKey();
                String brutTvShowName = StringUtils.substringBefore(key, "#");
                String brutTvShowNameAndSeason = StringUtils.replace(key, "#", ".S");
                String tvShowDir = formatTvShowName(brutTvShowName);
                String tvShowAndSeasonDir = formatTvShowName(brutTvShowNameAndSeason);
                String newDirName = rootBaseDir + File.separator + tvShowDir + File.separator + tvShowAndSeasonDir;
                for (File srcFile : tempArrangeMapEntry.getValue()) {
                    File destDirFile = new File(newDirName);
                    if (!destDirFile.exists())
                        destDirFile.mkdirs();
                    String destFileName = newDirName + File.separator + srcFile.getName();
                    File destFile = new File(destFileName);
                    System.out.println("moving  ... to " + destFileName);
                    boolean rename = srcFile.renameTo(destFile);
                    if (!rename) {
                        try {
                            FileUtils.moveFile(srcFile, destFile);
                        } catch (IOException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }

    private String formatTvShowName(String brutTvShowNameAndSeason) {
        brutTvShowNameAndSeason = StringUtils.replace(StringUtils.replace(brutTvShowNameAndSeason, " - ", "."), ".", " ");
        StringBuilder sb = new StringBuilder();
        String[] tab = StringUtils.split(brutTvShowNameAndSeason, " ");
        for (int i = 0; i < tab.length; i++) {
            String name = tab[i];
            sb.append(StringUtils.capitalize(name));
            if (i != (tab.length - 1)) {
                sb.append(".");
            }
        }
        String tvShowDir = sb.toString();
        return tvShowDir;
    }

    protected Map<String, List<File>> getMapOfFiles(File currentDir, Map<String, List<File>> arrangeMap) {
        if (currentDir.isDirectory()) {
            if (currentDir.listFiles() != null)
                for (File tvShowDir : currentDir.listFiles()) {
                    if (!tvShowDir.isDirectory()) {
                        manageOneFile(tvShowDir, arrangeMap);
                    } else {
                        arrangeMap.putAll(getMapOfFiles(tvShowDir, arrangeMap));
                    }
                }
        } else {
            manageOneFile(currentDir, arrangeMap);
        }
        return arrangeMap;

    }

}

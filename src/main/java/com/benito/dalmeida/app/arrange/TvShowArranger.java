package com.benito.dalmeida.app.arrange;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class TvShowArranger {
    Pattern normalPattern = Pattern.compile("s(\\d{1,2})e(\\d{1,2})");
    Pattern seasonCrossEpPattern = Pattern.compile("(\\d{1,2})x(\\d{1,2})");
    Pattern epOnlyPattern = Pattern.compile(" (\\d{1,3})");
    Pattern _epOnlyPattern = Pattern.compile("_(\\d{1,3})");

    public void arrange(String[] dirs) {

        Map<String, List<File>> arrangeMap = new HashMap<>();
        for (String rootDir : dirs) {
            File currentDir = new File(rootDir);
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
        brutTvShowNameAndSeason = StringUtils.replace(brutTvShowNameAndSeason, ".", " ");
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

    private void manageOneFile(File currentDir, Map<String, List<File>> arrangeMap) {
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);

        Matcher normalMatcher = normalPattern.matcher(lowerFileName);
        Matcher seasonCroosEpMatcher = seasonCrossEpPattern.matcher(lowerFileName);
        Matcher epOnlyMatcher = epOnlyPattern.matcher(lowerFileName);
        Matcher _epOnlyMatcher = _epOnlyPattern.matcher(lowerFileName);
        String tvShowName;
        String season;
        String episode;
        boolean normalMGroup = normalMatcher.groupCount() >= 2;
        boolean normalFinder = normalMatcher.find();
        if (normalFinder && normalMGroup) {
            String test = normalMatcher.group();
            season = normalMatcher.group(1);
            episode = normalMatcher.group(2);
            tvShowName = StringUtils.substringBefore(lowerFileName, "s" + season);
            if (tvShowName.endsWith(".")) {
                tvShowName = StringUtils.removeEnd(tvShowName, ".");
            }
        } else {
            boolean seasonCroosEpFind = seasonCroosEpMatcher.find();
            boolean seasonCroosEpGroup = seasonCroosEpMatcher.groupCount() >= 2;
            if (seasonCroosEpFind && seasonCroosEpGroup) {
                season = seasonCroosEpMatcher.group(1);
                episode = seasonCroosEpMatcher.group(2);
                tvShowName = StringUtils.substringBefore(lowerFileName, season);
            } else {
                boolean epOnlyFind = epOnlyMatcher.find();
                boolean epOnlyGroup = epOnlyMatcher.groupCount() >= 2;
                if (epOnlyFind) {
                    System.out.println(lowerFileName + "  matched with   " + epOnlyPattern);
                    season = "01";
                    episode = epOnlyMatcher.group(1);
                    tvShowName = StringUtils.substringBefore(lowerFileName, episode);
                } else if (_epOnlyMatcher.find()) {
                    System.out.println(lowerFileName + "  matched with   " + _epOnlyPattern);
                    season = "01";
                    episode = _epOnlyMatcher.group(1);
                    tvShowName = StringUtils.substringBefore(lowerFileName, episode);
                } else {
                    season = "";
                    episode = null;
                    tvShowName = "MOVIES";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(tvShowName).append("#").append(season);
        String tvShowAndSeason = sb.toString();
        List<File> multimediaFiles = arrangeMap.get(tvShowAndSeason);
        if (multimediaFiles == null) {
            multimediaFiles = new ArrayList<>();
            arrangeMap.put(tvShowAndSeason, multimediaFiles);
        }
        try {
            multimediaFiles.add(manageOnePiece(currentDir, episode));
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private File manageOnePiece(File currentDir, String episode) throws IOException {
        String lowerFileName = StringUtils.lowerCase(currentDir.getCanonicalPath());
        String fileName = currentDir.getCanonicalPath();
        if (StringUtils.containsIgnoreCase(lowerFileName, "one") && StringUtils.containsIgnoreCase(lowerFileName, "piece")) {
            File destFile = new File(StringUtils.replace(fileName, episode, "E" + episode));
            FileUtils.moveFile(currentDir, destFile);
            return destFile;
        } else {
            return currentDir;
        }
    }

}

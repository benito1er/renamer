package com.benito.dalmeida.app.arrange;


import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class TvShowArranger {
    String[] patterns = {"s(\\d{1,2})e(\\d{1,2})",
            "(\\d{1,2})x(\\d{1,2})",
            "s(\\d{1,2})-(\\d{1,2})",
            " (\\d{1,3})",
            "_(\\d{1,3})",
            "_e(\\d{1,3})",
            "s(\\d{1,2}) - (\\d{1,2})",
            "\\p{Punct}(\\d{1,3})\\p{Punct}"};
//
//    Pattern normalPattern = Pattern.compile("s(\\d{1,2})e(\\d{1,2})");
//    Pattern seasonCrossEpPattern = Pattern.compile("(\\d{1,2})x(\\d{1,2})");
//    Pattern spaceEpOnlyPattern = Pattern.compile(" (\\d{1,3})");
//    Pattern _epOnlyPattern = Pattern.compile("_(\\d{1,3})");
//    Pattern eEpOnlyPattern = Pattern.compile("_e(\\d{1,3})");
//    Pattern dotSeasonEpPattern = Pattern.compile("\\p{Punct}(\\d{1,3})\\p{Punct}");
//    Pattern seasonSpace_EpPattern = Pattern.compile("s(\\d{1,2}) - (\\d{1,2})");


    private void manageOneFile(File currentDir, Map<String, List<File>> arrangeMap) {
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
//
//        Matcher normalMatcher = normalPattern.matcher(lowerFileName);
//        Matcher seasonCroosEpMatcher = seasonCrossEpPattern.matcher(lowerFileName);
//        Matcher spaceEpOnlyMatcher = spaceEpOnlyPattern.matcher(lowerFileName);
//        Matcher eEpOnlyPatternMatcher = eEpOnlyPattern.matcher(lowerFileName);
//        Matcher _epOnlyMatcher = _epOnlyPattern.matcher(lowerFileName);
//        Matcher dotSeasonEpPatternMatcher = dotSeasonEpPattern.matcher(lowerFileName);
//        Matcher seasonSpace_EpPatternMatcher = seasonSpace_EpPattern.matcher(lowerFileName);
        String tvShowName=null;
        String season=null;
        String episode;
        System.out.println(lowerFileName + " to matched with  ---> ");
        if (isOnePieceFile(lowerFileName)) {
            String numbers = lowerFileName.replaceAll("\\D+", " ");
            if (StringUtils.isBlank(numbers)) {
                numbers = "0";
            }
            episode = StringUtils.split(numbers, " ")[0];
            season = getOnePieceSeason(fileName, episode);
            tvShowName = "One.Piece";
        } else if (isDragonBallSuper(lowerFileName)) {
            String numbers = lowerFileName.replaceAll("\\D+", " ");
            episode = StringUtils.split(numbers, " ")[0];
            season = getDragonBallSuperSeason(fileName, episode);
            tvShowName = "Dragon.Ball.Super";
        } else if (isBorutoFile(lowerFileName)) {
            String numbers = lowerFileName.replaceAll("\\D+", " ");
            episode = StringUtils.split(numbers, " ")[0];
            season = getBorutoSeason(fileName, episode);
            tvShowName = "Boruto.Naruto.Next.Generations";
        } else {

            List<String>  filesPatterns =      Arrays.asList(patterns).stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
            for (String currentPatternValue : filesPatterns) {
                int nbGroup = StringUtils.countMatches(currentPatternValue, "\\d{");
                Pattern currentPattern = Pattern.compile(currentPatternValue);
                Matcher currentPatternMatcher = currentPattern.matcher(lowerFileName);
                if (currentPatternMatcher.find() && currentPatternMatcher.groupCount() >= nbGroup) {
                    System.out.println(lowerFileName + "  matched with   " + currentPattern);

                    episode = nbGroup > 1 ? currentPatternMatcher.group(2) : currentPatternMatcher.group(1);
                    season = nbGroup > 1 ? currentPatternMatcher.group(1) : "01";
                    String temp = nbGroup > 1 ? (currentPatternValue.startsWith("s") ? "s" + season : season) : episode;
                    tvShowName = StringUtils.removeEnd(StringUtils.substringBefore(lowerFileName, temp),".");

                    break;
                }
            }
           if(tvShowName == null ){
                season = "";
                tvShowName = "MOVIES";
            }
            /*else if (seasonSpace_EpPatternMatcher.find() && seasonSpace_EpPatternMatcher.groupCount() >= 1) {
            System.out.println(lowerFileName + "  matched with   " + seasonSpace_EpPattern);
            season = seasonSpace_EpPatternMatcher.group(1);
            episode = seasonSpace_EpPatternMatcher.group(2);
            tvShowName = StringUtils.substringBefore(lowerFileName, episode);
        } else if (normalMatcher.groupCount() >= 2 && normalMatcher.find()) {
            season = normalMatcher.group(1);
            episode = normalMatcher.group(2);
            tvShowName = StringUtils.substringBefore(lowerFileName, "s" + season);
            if (tvShowName.endsWith(".")) {
                tvShowName = StringUtils.removeEnd(tvShowName, ".");
            }
        } else if (seasonCroosEpMatcher.find() && seasonCroosEpMatcher.groupCount() >= 2) {
            System.out.println(lowerFileName + "  matched with   " + seasonCrossEpPattern);
            season = seasonCroosEpMatcher.group(1);
            episode = seasonCroosEpMatcher.group(2);
            tvShowName = StringUtils.substringBefore(lowerFileName, season);
        } else if (_epOnlyMatcher.find() && _epOnlyMatcher.groupCount() >= 1) {
            System.out.println(lowerFileName + "  matched with   " + _epOnlyPattern);
            season = "01";
            episode = _epOnlyMatcher.group(1);
            tvShowName = StringUtils.substringBefore(lowerFileName, episode);
        } else if (eEpOnlyPatternMatcher.find() && eEpOnlyPatternMatcher.groupCount() >= 1) {
            System.out.println(lowerFileName + "  matched with   " + eEpOnlyPattern);
            season = "01";
            episode = eEpOnlyPatternMatcher.group(1);
            tvShowName = StringUtils.substringBefore(lowerFileName, episode);
        } else if (spaceEpOnlyMatcher.find() && spaceEpOnlyMatcher.groupCount() >= 1) {

            System.out.println(lowerFileName + "  matched with   " + spaceEpOnlyPattern);
            String temp = spaceEpOnlyMatcher.group(1);
            episode = temp.length() > 2 ? temp.substring(temp.length() - 2, temp.length()) : temp;
            season = StringUtils.isNotBlank(StringUtils.substringBefore(temp, episode)) ? StringUtils.substringBefore(temp, episode) : "01";
            tvShowName = StringUtils.substringBefore(lowerFileName, episode);
        } else if (dotSeasonEpPatternMatcher.find() && dotSeasonEpPatternMatcher.groupCount() >= 1) {
            System.out.println(lowerFileName + "  matched with   " + dotSeasonEpPattern);
            String temp = dotSeasonEpPatternMatcher.group(1);
            int beginIndex = temp.length() - 2;
            if (beginIndex > 0) {
                episode = temp.substring(beginIndex, temp.length());
                season = StringUtils.substringBefore(temp, episode);
                tvShowName = StringUtils.substringBefore(lowerFileName, season + episode);
                season = "0" + season;
            } else {
                season = "";
                episode = null;
                tvShowName = "MOVIES";
            }
        } else {
            season = "";
            episode = null;
            tvShowName = "MOVIES";
        }*/
        }

        StringBuilder sb = new StringBuilder();
        sb.append(tvShowName).append("#").append(season);
        String tvShowAndSeason = sb.toString();
        List<File> multimediaFiles = arrangeMap.get(tvShowAndSeason);
        if (multimediaFiles == null) {
            multimediaFiles = new ArrayList<>();
            arrangeMap.put(tvShowAndSeason, multimediaFiles);
        }
        multimediaFiles.add(currentDir);

    }

    private String getDragonBallSuperSeason(String fileName, String episode) {
        String season = "";
        if (!isDragonBallSuper(fileName)) {
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

    private boolean isDragonBallSuper(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "DBS") || StringUtils.containsIgnoreCase(lowerFileName, "DBSuper"))
            return true;
        if (StringUtils.containsIgnoreCase(lowerFileName, "Dragon") && StringUtils.containsIgnoreCase(lowerFileName, "ball") && StringUtils.containsIgnoreCase(lowerFileName, "super"))
            return true;
        return false;
    }

    private String getOnePieceSeason(String fileName, String episode) {
        String season = "";
        if (!isOnePieceFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep < 9) {
            season = "01";
        } else if (ep < 31) {
            season = "02";
        } else if (ep < 48) {
            season = "03";
        } else if (ep < 61) {
            season = "04";
        } else if (ep < 70) {
            season = "05";
        } else if (ep < 92) {
            season = "06";
        } else if (ep < 131) {
            season = "07";
        } else if (ep < 144) {
            season = "08";
        } else if (ep < 196) {
            season = "09";
        } else if (ep < 227) {
            season = "10";
        } else if (ep < 326) {
            season = "11";
        } else if (ep < 382) {
            season = "12";
        } else if (ep < 482) {
            season = "13";
        } else if (ep < 517) {
            season = "14";
        } else if (ep < 579) {
            season = "15";
        } else if (ep < 629) {
            season = "16";
        } else if (ep < 747) {
            season = "17";
        } else if (ep < 780) {
            season = "18";
        } else if (ep < 878) {
            season = "19";
        } else if (ep < 982) {
            season = "20";
        } else {
            season = "21";
        }


        return season;

    }


    private boolean isOnePieceFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "one") && StringUtils.containsIgnoreCase(lowerFileName, "piece"))
            return true;
        else
            return false;
    }

    private boolean isBorutoFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "boruto") && StringUtils.containsIgnoreCase(lowerFileName, "next"))
            return true;
        else
            return false;
    }

    private String getBorutoSeason(String fileName, String episode) {
        String season = "";
        if (!isBorutoFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);
        if (ep < 160) {
            season = "01";
        }
        return season;
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

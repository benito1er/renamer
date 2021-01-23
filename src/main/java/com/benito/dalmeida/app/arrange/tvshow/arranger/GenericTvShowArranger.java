package com.benito.dalmeida.app.arrange.tvshow.arranger;

import com.benito.dalmeida.app.arrange.tvshow.PatternInvoker;
import com.benito.dalmeida.app.arrange.tvshow.TvShowArrangerProccess;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GenericTvShowArranger extends AbstractTvShowArranger {
    private GenericTvShowArranger() {
    }

    private static GenericTvShowArranger INSTANCE = null;

    public static GenericTvShowArranger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new GenericTvShowArranger();
        return INSTANCE;
    }

    @Override
    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        return false;
    }

    @Override
    public String getTvShowSeason(String fileName, String episode) {
        return null;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return this.getTvShowSeasonAndEpisodeAsMapValues(currentDir).get("tvShowName");
    }

    public Map<String, String> getTvShowSeasonAndEpisodeAsMapValues(File currentDir) {
        String fileName = currentDir.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        String tvShowName = null;
        String season = null;
        String episode = null;
        Set<String> tempFilesPattens = new HashSet<>();
        tempFilesPattens.addAll(Arrays.asList(TvShowArrangerProccess.PATTERNS));

        tempFilesPattens.addAll(Arrays.asList(TvShowArrangerProccess.SHORT_PATTERNS));
        List<String> filesPatterns = tempFilesPattens.stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        filesPatterns.add("\\p{Punct}(\\d{1,3})\\p{Punct}");
        PatternInvoker.build(filesPatterns);
        for (String currentPatternValue : filesPatterns) {
            int nbGroup = StringUtils.countMatches(currentPatternValue, "\\d{");
            Pattern currentPattern = PatternInvoker.get(currentPatternValue);
            Matcher currentPatternMatcher = currentPattern.matcher(lowerFileName);
            if (currentPatternMatcher.find() && currentPatternMatcher.groupCount() >= nbGroup) {
                System.out.println(lowerFileName + "  matched with   " + currentPattern);
                episode = nbGroup > 1 ? currentPatternMatcher.group(2) : currentPatternMatcher.group(1);
                season = nbGroup > 1 ? currentPatternMatcher.group(1) : "01";
                String temp = nbGroup > 1 ? (currentPatternValue.startsWith("s") ? "s" + season : season) : episode;
                tvShowName = StringUtils.removeEnd(StringUtils.substringBefore(lowerFileName.trim(), temp), ".");
                if (("- (\\d{1,3})".trim().equalsIgnoreCase(currentPatternValue.trim()) || " (\\d{1,3})".trim().equalsIgnoreCase(currentPatternValue.trim())) && (StringUtils.startsWithIgnoreCase(episode, "19") || StringUtils.startsWithIgnoreCase(episode, "20"))) {
                    season = "";
                    tvShowName = "MOVIES";
                }
                if (StringUtils.isBlank(tvShowName)) {
                    String parentDirsPath = currentDir.getParent();
                    String[] dirs = StringUtils.split(parentDirsPath, File.separator);
                    tvShowName = dirs.length > 0 ? dirs[dirs.length - 1] : "Unknown";
                }
                break;
            }
        }
        if (tvShowName == null) {
            season = "";
            tvShowName = "MOVIES";
        }
        Map<String, String> result = new HashMap<>();
        result.put("tvShowName", tvShowName);
        result.put("season", season);
        result.put("episode", episode);
        return result;
    }
}

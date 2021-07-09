package com.benito.dalmeida.app.arrange.tvshow;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TvShowKnownPatternName {

    private final String lowerFileName;
    private String season;
    private String episode;

    public TvShowKnownPatternName(String lowerFileName) {
               this.lowerFileName = lowerFileName;
    }

    public String getSeason() {
        return season;
    }

    public String getEpisode() {
        return episode;
    }

    public TvShowKnownPatternName invoke() {
        List<String> filesPatterns = Arrays.asList(TvShowArrangerProccess.SHORT_PATTERNS).stream().sorted(Comparator.comparingInt(String::length).reversed()).collect(Collectors.toList());
        PatternInvoker.build(filesPatterns);
        for (String currentPatternValue : filesPatterns) {
            int nbGroup = StringUtils.countMatches(currentPatternValue, "\\d{");
            Pattern currentPattern = PatternInvoker.get(currentPatternValue);
            Matcher currentPatternMatcher = currentPattern.matcher(lowerFileName);
            if (currentPatternMatcher.find() && currentPatternMatcher.groupCount() >= nbGroup) {
                System.out.println(lowerFileName + "  matched with   " + currentPattern);

                episode = nbGroup > 1 ? currentPatternMatcher.group(2) : currentPatternMatcher.group(1);
                season = nbGroup > 1 ? currentPatternMatcher.group(1) : null;

                break;
            }
        }
        return this;
    }
}

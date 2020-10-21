package com.benito.dalmeida.app.arrange.tvshow;

import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.regex.Pattern;

public class PatternInvoker {
    static Map<String, Pattern> patternMap = new HashMap<>();

    public static Pattern get(String currentPatternValue) {
        return patternMap.get(currentPatternValue);
    }

    public static void build(List<String> filesPatterns) {
        if (CollectionUtils.isEmpty(patternMap) || !patternMap.keySet().containsAll(filesPatterns)) {
            for (String currentPattern : filesPatterns) {
                patternMap.put(currentPattern, Pattern.compile(currentPattern));
            }
        }
    }
}

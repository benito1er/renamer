package com.benito.dalmeida.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.benito.dalmeida.app.arrange.tvshow.TvShowArrangerProccess;
import com.benito.dalmeida.app.rename.FileRenamer;

public class RemoveBadWordMain {
    private static final Log LOGGER = LogFactory.getLog(RemoveBadWordMain.class);


    public static void main(final String[] argrs) {
        LinkedHashSet<String> directories = new LinkedHashSet<>();
        directories.addAll(Arrays.asList(Constant.DOWNLOAD_DIR));
        directories.addAll(Arrays.asList(Constant.CONTENT_DIRECTORIES));

        String[] rootDirectories = directories.stream().toArray(String[]::new);
        final FileRenamer rename = new FileRenamer(rootDirectories);
        TvShowArrangerProccess tvShowArrangerProccess = new TvShowArrangerProccess();
        for (int i = 0; i < 2; i++) {
            renameDownloadedFile(rename);
            tvShowArrangerProccess.arrange(Constant.DOWNLOAD_DIR);
        }
        LOGGER.debug("fini");

    }

    private static void renameDownloadedFile(final FileRenamer rename) {
        final String fileName = "wordToRemoveInFile.txt";
        final ClassLoader classLoader = RemoveBadWordMain.class.getClassLoader();
        final StringBuilder stringBuilder = new StringBuilder();
        try {
            final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(classLoader
                    .getResource(fileName).getFile())));
            String line = null;
            try {
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(",");
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        } catch (final FileNotFoundException e1) {
            e1.printStackTrace();
        }

        final Set<String> tempWord = new HashSet<>();
        final String[] wordtoRemoveArray = StringUtils.stripAll(StringUtils.split(stringBuilder.toString(), ","));
        tempWord.addAll(Arrays.asList(wordtoRemoveArray));
        final Map<Integer, List<String>> wordToRemoveMap = new TreeMap<>();

        for (final String removeWord : tempWord) {
            final Integer size = removeWord.length();
            if (wordToRemoveMap.get(size) == null) {
                final List<String> wordsToRemove = new ArrayList<>();
                wordToRemoveMap.put(size, wordsToRemove);
            }
            final String newRemoveWord = StringUtils.strip(removeWord, "'");
            wordToRemoveMap.get(size).add(newRemoveWord);
        }
        final List<String> sortWordsToRemoves = new ArrayList<>();
        final int keySize = wordToRemoveMap.keySet().size();
        final List<Integer> reverseKey = new ArrayList<>();
        reverseKey.addAll(wordToRemoveMap.keySet());
        Collections.sort(reverseKey);
        System.out.println(reverseKey);

        for (int i = keySize - 1; i > -1; i--) {
            final Integer key = reverseKey.get(i);
            final List<String> wordsToRemove = wordToRemoveMap.get(key);
            Collections.reverse(wordsToRemove);
            sortWordsToRemoves.addAll(wordsToRemove);

        }

        for (final String sortWordsToRemove : sortWordsToRemoves) {
            System.out.println(sortWordsToRemove);
        }
        try {
            rename.rename(sortWordsToRemoves);
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
}

package com.benito.dalmeida.app;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

import com.benito.dalmeida.app.rename.FileRenamer;

public class RemoveBadWordMainClass {

    public static void main(final String[] argrs) {
        final String[] rootDirectories = { /* "Y:" + FileRenamer.fileSeparator + "Téléchargements", */
               "J:" + FileRenamer.fileSeparator + "Movies",

                "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "TVShows",
                "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "TVShows Archived",
                "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "Movies"+ FileRenamer.fileSeparator+"MyMovies",
                "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "Movies" + FileRenamer.fileSeparator + "To_Import",

                "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "Animations",
                "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "Manga",
                "Z:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "TVShows Archived",
                "Z:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "XXX_Adult" };
        final FileRenamer rename = new FileRenamer(rootDirectories);

        renameDownloadedFile(rename);

        System.out.println("fini");
    }

    private static void renameDownloadedFile(final FileRenamer rename) {
        final String fileName = "wordToRemoveInFile.txt";
        final ClassLoader classLoader = RemoveBadWordMainClass.class.getClassLoader();
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

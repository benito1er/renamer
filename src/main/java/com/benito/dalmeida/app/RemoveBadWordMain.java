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
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.benito.dalmeida.app.arrange.TvShowArranger;
import com.benito.dalmeida.app.rename.FileRenamer;

public class RemoveBadWordMain {
    private static final Log LOGGER = LogFactory.getLog(RemoveBadWordMain.class);

   private static final String TO_IMPORT = "To_Import";
   private static final String MANGA = "Manga";

   private static final String MOVIES = "Movies";
   private static final String VIDEOS_DIR = "Videos";
   private static final String TVSHOWS = "TVShows";
   private static final String TVSHOWS_ARCHIVED = "TVShows Archived";
   private static final String MY_MOVIES = "MyMovies";
    public static final String TÉLÉCHARGÉ = "Téléchargé";
    public static final String TÉLÉCHARGEMENTS = "Téléchargements";

    public static void main(final String[] argrs) {

        final String[] rootDirectories = { /* "Y:" + FileRenamer.fileSeparator + "Téléchargements", */
                //"K:" + FileRenamer.fileSeparator + "Movies",
                "J:" + FileRenamer.fileSeparator + MOVIES,
                "G:" + FileRenamer.fileSeparator + MOVIES,
                "H:" + FileRenamer.fileSeparator + MOVIES,
                "Y:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "Z:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "V:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "W:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "W:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS,
                "T:" + FileRenamer.fileSeparator + "Telechargement",

                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator+ MY_MOVIES,
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
                "Y:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",

                "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
                "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
                "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
                "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator+ MY_MOVIES,
                "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,

                "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
                "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,

                "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,

                "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator+ MY_MOVIES,
                "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,

                "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
                "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
                "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
                "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator+ MY_MOVIES,
                "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,

                 // "T:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "XXX_Adult" ,
                "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
                 //"T:" + FileRenamer.fileSeparator + "torrents"

        };
        final FileRenamer rename = new FileRenamer(rootDirectories);

        renameDownloadedFile(rename);
        String [] sampleDirs = {"Y:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "Y:" + FileRenamer.fileSeparator + "Téléchargé",
                "Y:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS ,
                "T:" + FileRenamer.fileSeparator + "Telechargement",
                "Y:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "Z:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "V:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                "W:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
                // "T:"+ FileRenamer.fileSeparator+"torrents"+ FileRenamer.fileSeparator + "_old"
                //"W:"+ FileRenamer.fileSeparator +"Videos"+ FileRenamer.fileSeparator +"Manga"+ FileRenamer.fileSeparator +"Dragon.Ball.Super",
                //"T:"+ FileRenamer.fileSeparator+"Videos"+ FileRenamer.fileSeparator+"XXX_Adult"+ FileRenamer.fileSeparator+"Hentai"
        };
        TvShowArranger tvShowArranger =  new TvShowArranger();
        tvShowArranger.arrange(sampleDirs);
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

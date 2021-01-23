package com.benito.dalmeida.app;

import com.benito.dalmeida.app.rename.FileRenamer;

public class Constant {

    private static final String TO_IMPORT = "To_Import";
    private static final String MANGA = "Manga";

    private static final String MOVIES = "Movies";
    private static final String VIDEOS_DIR = "Videos";
    private static final String TVSHOWS = "TVShows";
    private static final String TVSHOWS_ARCHIVED = "TVShows Archived";
    private static final String MY_MOVIES = "MyMovies";
    public static final String TÉLÉCHARGÉ = "Téléchargé";
    public static final String TÉLÉCHARGEMENTS = "Téléchargements";
    public static final String TELECHARGEMENT = "Telechargement";

    public static final String MOVIES_MAIN_DIR =  "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES ;
    public static final String MY_MOVIES_MAIN_DIR =  MOVIES_MAIN_DIR + FileRenamer.fileSeparator + MY_MOVIES;
    public static final String MY_MOVIES_DB_FILE =  MOVIES_MAIN_DIR + FileRenamer.fileSeparator + "MyCinemaData" + FileRenamer.fileSeparator+"data" + FileRenamer.fileSeparator+"db.myc";

    public static final String WINDOWS_PATH_J = "J:" + FileRenamer.fileSeparator;
    public static final String WINDOWS_PATH_G = "G:" + FileRenamer.fileSeparator;
    public static final String WINDOWS_PATH_H = "H:" + FileRenamer.fileSeparator;
    public static final String WINDOWS_PATH_Y = "Y:" + FileRenamer.fileSeparator;
    public  static final String[] CONTENT_DIRECTORIES = { /* WINDOWS_PATH_Y + "Téléchargements", */
            //"K:" + FileRenamer.fileSeparator + "Movies",
            WINDOWS_PATH_J + MOVIES,
            WINDOWS_PATH_G + MOVIES,
            WINDOWS_PATH_H + MOVIES,


            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            WINDOWS_PATH_Y + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",


            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            "X:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",


            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            "V:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",

            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            "W:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",

            "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            "Z:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,

            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            "U:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",
            // "T:" + FileRenamer.fileSeparator + VIDEOS_DIR + FileRenamer.fileSeparator + "XXX_Adult" ,

            //"T:" + FileRenamer.fileSeparator + "torrents"

    };

    public   static String[] DOWNLOAD_DIR = {

            "Z:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "Z:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "Z:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS,
            "Z:" + FileRenamer.fileSeparator + TELECHARGEMENT,

            WINDOWS_PATH_Y + TÉLÉCHARGÉ,
            WINDOWS_PATH_Y + TÉLÉCHARGÉ,
            WINDOWS_PATH_Y + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_Y + TELECHARGEMENT,

            "U:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "U:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "U:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS,
            "U:" + FileRenamer.fileSeparator + TELECHARGEMENT,

            "X:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "X:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "X:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS,
            "X:" + FileRenamer.fileSeparator + TELECHARGEMENT,

            "W:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "W:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "W:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS,
            "W:" + FileRenamer.fileSeparator + TELECHARGEMENT,

            "V:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "V:" + FileRenamer.fileSeparator + TÉLÉCHARGÉ,
            "V:" + FileRenamer.fileSeparator + TÉLÉCHARGEMENTS,
            "V:" + FileRenamer.fileSeparator + TELECHARGEMENT,


            // "T:"+ FileRenamer.fileSeparator+"torrents"+ FileRenamer.fileSeparator + "_old"
            //"W:"+ FileRenamer.fileSeparator +"Videos"+ FileRenamer.fileSeparator +"Manga"+ FileRenamer.fileSeparator +"Dragon.Ball.Super",
            //"T:"+ FileRenamer.fileSeparator+"Videos"+ FileRenamer.fileSeparator+"XXX_Adult"+ FileRenamer.fileSeparator+"Hentai"
    };
}

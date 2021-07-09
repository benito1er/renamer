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
    private static final String TÉLÉCHARGÉ = "Téléchargé";
    private static final String TÉLÉCHARGEMENTS = "Téléchargements";
    private static final String TELECHARGEMENT = "Telechargement";
    private static final String WINDOWS_PATH_J = "J:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_G = "G:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_H = "H:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_Y = "Y:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_X = "X:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_V = "V:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_W = "W:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_Z = "Z:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_U = "U:" + FileRenamer.fileSeparator;
    private static final String WINDOWS_PATH_T = "T:" + FileRenamer.fileSeparator;
    public static final String MOVIES_MAIN_DIR =  WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES ;
    public static final String MY_MOVIES_MAIN_DIR =  MOVIES_MAIN_DIR + FileRenamer.fileSeparator + MY_MOVIES;
    public static final String MY_MOVIES_DB_FILE =  MOVIES_MAIN_DIR + FileRenamer.fileSeparator + "MyCinemaData" + FileRenamer.fileSeparator+"data" + FileRenamer.fileSeparator+"db.myc";



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


            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            WINDOWS_PATH_X + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",


            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            WINDOWS_PATH_V + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",

            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            WINDOWS_PATH_W + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",

            WINDOWS_PATH_Z + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            WINDOWS_PATH_Z + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_Z + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_Z + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            WINDOWS_PATH_Z + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            WINDOWS_PATH_Z + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,

            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS,
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + TVSHOWS_ARCHIVED,
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + MANGA,
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + TO_IMPORT,
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + MOVIES + FileRenamer.fileSeparator + MY_MOVIES,
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + "Animations",
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsMovies",
            WINDOWS_PATH_U + VIDEOS_DIR + FileRenamer.fileSeparator + "AnimationsTvShows",
            WINDOWS_PATH_T + VIDEOS_DIR + FileRenamer.fileSeparator + "XXX_Adult" ,
            WINDOWS_PATH_T + "torrentsNew",
            WINDOWS_PATH_T + "torrents"

    };

    public   static String[] DOWNLOAD_DIR = {

            WINDOWS_PATH_Z + TÉLÉCHARGÉ,
            WINDOWS_PATH_Z + TÉLÉCHARGÉ,
            WINDOWS_PATH_Z + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_Z + TELECHARGEMENT,

            WINDOWS_PATH_Y + TÉLÉCHARGÉ,
            WINDOWS_PATH_Y + TÉLÉCHARGÉ,
            WINDOWS_PATH_Y + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_Y + TELECHARGEMENT,

            WINDOWS_PATH_U + TÉLÉCHARGÉ,
            WINDOWS_PATH_U + TÉLÉCHARGÉ,
            WINDOWS_PATH_U + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_U + TELECHARGEMENT,

            WINDOWS_PATH_X + TÉLÉCHARGÉ,
            WINDOWS_PATH_X + TÉLÉCHARGÉ,
            WINDOWS_PATH_X + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_X + TELECHARGEMENT,

            WINDOWS_PATH_W + TÉLÉCHARGÉ,
            WINDOWS_PATH_W + TÉLÉCHARGÉ,
            WINDOWS_PATH_W + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_W + TELECHARGEMENT,

            WINDOWS_PATH_V + TÉLÉCHARGÉ,
            WINDOWS_PATH_V + TÉLÉCHARGÉ,
            WINDOWS_PATH_V + TÉLÉCHARGEMENTS,
            WINDOWS_PATH_V + TELECHARGEMENT,


            // "T:"+ FileRenamer.fileSeparator+"torrents"+ FileRenamer.fileSeparator + "_old"
            //"W:"+ FileRenamer.fileSeparator +"Videos"+ FileRenamer.fileSeparator +"Manga"+ FileRenamer.fileSeparator +"Dragon.Ball.Super",
            //"T:"+ FileRenamer.fileSeparator+"Videos"+ FileRenamer.fileSeparator+"XXX_Adult"+ FileRenamer.fileSeparator+"Hentai"
    };
}

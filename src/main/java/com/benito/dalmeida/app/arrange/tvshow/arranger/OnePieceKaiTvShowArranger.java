package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class OnePieceKaiTvShowArranger  extends AbstractTvShowArranger {

    private OnePieceKaiTvShowArranger (){           }
    private static OnePieceKaiTvShowArranger INSTANCE = null;

    public static OnePieceKaiTvShowArranger getInstance(){
        if(INSTANCE == null)
            INSTANCE =  new OnePieceKaiTvShowArranger();
        return INSTANCE;
    }
    public String getTvShowSeason(String fileName, String episode) {
        String season = "01";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep < 7) {
            season = "01";
        } else if (ep < 12) {
            season = "02";
        } else if (ep < 14) {
            season = "03";
        } else if (ep < 20) {
            season = "04";
        } else if (ep < 25) {
            season = "05";
        } else if (ep < 30) {
            season = "06";
        } else if (ep < 38) {
            season = "07";
        } else if (ep < 45) {
            season = "08";
        } else if (ep < 65) {
            season = "09";
        } else if (ep < 69) {
            season = "10";
        } else {
            season = "11";
        }
        return season;

    }

    @Override
    public String getTvShowName(File currentDir) {
        return "One.Piece.Kai";
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        OnePieceTvShowArranger onePieceTvShowArranger = OnePieceTvShowArranger.getInstance();
        return ((onePieceTvShowArranger.isThisTvShowArrangerFile(lowerFileName) && StringUtils.containsIgnoreCase(lowerFileName, "kai") && !StringUtils.containsIgnoreCase(lowerFileName, "sekai") && !StringUtils.containsIgnoreCase(lowerFileName, "kaid"))
                || StringUtils.containsIgnoreCase(lowerFileName, "OPK Saga"));
    }


}

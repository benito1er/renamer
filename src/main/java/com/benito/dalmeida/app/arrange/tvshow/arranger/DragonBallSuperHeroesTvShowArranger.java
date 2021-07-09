package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class DragonBallSuperHeroesTvShowArranger  extends AbstractTvShowArranger {
    private DragonBallSuperHeroesTvShowArranger (){           }
    private static DragonBallSuperHeroesTvShowArranger INSTANCE = null;

    public static DragonBallSuperHeroesTvShowArranger getInstance(){
        if(INSTANCE == null)
            INSTANCE =  new DragonBallSuperHeroesTvShowArranger();
        return INSTANCE;
    }


    public String getTvShowSeason(String fileName, String episode) {
        String season = "01";
        if (!this.isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        return season;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Dragon.Ball.Super.Heroes";
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        DragonBallSuperTvShowArranger dragonBallSuper =  DragonBallSuperTvShowArranger.getInstance();
        return dragonBallSuper.isThisTvShowArrangerFile(lowerFileName) && StringUtils.containsIgnoreCase(lowerFileName, "heroes");
    }


}

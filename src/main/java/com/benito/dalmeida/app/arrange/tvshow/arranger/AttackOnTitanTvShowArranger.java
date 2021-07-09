package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;
import java.io.File;


public class AttackOnTitanTvShowArranger extends AbstractTvShowArranger {
    private AttackOnTitanTvShowArranger (){           }
    private static AttackOnTitanTvShowArranger INSTANCE = null;

    public static AttackOnTitanTvShowArranger getInstance(){
        if(INSTANCE == null)
            INSTANCE =  new AttackOnTitanTvShowArranger();
        return INSTANCE;
    }
    public boolean isThisTvShowArrangerFile(String lowerFileName){
        return (StringUtils.containsIgnoreCase(lowerFileName, "attack") && StringUtils.containsIgnoreCase(lowerFileName, "on") && StringUtils.containsIgnoreCase(lowerFileName, "titan"))
                || ((StringUtils.containsIgnoreCase(lowerFileName, "attaque") && StringUtils.containsIgnoreCase(lowerFileName, "des") && StringUtils.containsIgnoreCase(lowerFileName, "titan")))
                || ((StringUtils.containsIgnoreCase(lowerFileName, "shingeki") && StringUtils.containsIgnoreCase(lowerFileName, "no") && StringUtils.containsIgnoreCase(lowerFileName, "kyojin")));
    }

    @Override
    public String getTvShowName(File currentDir) {
        return  "Attack.On.Titan";
    }

    public  String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep <26) {
            season = "01";
        } else if (ep < 38) {
            season = "02";
        } else if (ep < 60) {
            season = "03";
        } else {
            season = "04";
        }
        return season;

    }

}

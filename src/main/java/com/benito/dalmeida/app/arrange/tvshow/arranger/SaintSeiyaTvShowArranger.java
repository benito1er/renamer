package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class SaintSeiyaTvShowArranger extends AbstractTvShowArranger {
    private SaintSeiyaTvShowArranger() {
    }

    private static SaintSeiyaTvShowArranger INSTANCE = null;

    public static SaintSeiyaTvShowArranger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SaintSeiyaTvShowArranger();
        return INSTANCE;
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "saint")
                && StringUtils.containsIgnoreCase(lowerFileName, "seiya")
        )
            return true;
        else
            return false;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Saint.Seiya";
    }

    public String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);
//omega
        if (StringUtils.containsIgnoreCase(fileName, "omega")) {
            if (ep < 52) {
                season = "09";
            } else {
                season = "10";
            }
        }
        // hades sanctuary
        else if (StringUtils.containsIgnoreCase(fileName, "hades") && StringUtils.containsIgnoreCase(fileName, "sanctuary")) {
            season = "04";
        } // hades inferno
        else if (StringUtils.containsIgnoreCase(fileName, "hades") && StringUtils.containsIgnoreCase(fileName, "inferno")) {
            season = "05";
        }// hades elysion
        else if (StringUtils.containsIgnoreCase(fileName, "hades") && StringUtils.containsIgnoreCase(fileName, "elysion")) {
            season = "06";
        }// lost canvas
        else if (StringUtils.containsIgnoreCase(fileName, "lost") && StringUtils.containsIgnoreCase(fileName, "canvas")) {
            if (ep < 14) {
                season = "07";
            } else {
                season = "08";
            }
        }// soul of gold
        else if (StringUtils.containsIgnoreCase(fileName, "soul") && StringUtils.containsIgnoreCase(fileName, "gold")) {
            season = "11";
        } else if (ep < 74) {
            season = "01";
        } else if (ep < 100) {
            season = "02";
        } else if (ep < 115) {
            season = "03";
        }else if (ep < 128) {
            season = "04";
        } else if (ep < 140) {
            season = "05";
        } else if (ep < 146) {
            season = "06";
        }else if (ep < 159) {
            season = "07";
        } else if (ep < 172) {
            season = "08";
        } else  {
            season = "12";
        }
        return season;

    }

    public File renameFile(File currentFile, Map<String, String> tvShowinfo) {
        String parentPath = currentFile.getParent();
        Path source = currentFile.toPath();
        String extension = StringUtils.substringAfterLast(currentFile.getName(), ".");
        final String episode = tvShowinfo.get("episode");
        String fileComplement = StringUtils.substringBetween(currentFile.getName(), episode, "." + extension);
        String chapter;
        String season =tvShowinfo.get("season");
        switch (season){
            case "04":
                chapter=".Hades.Sanctuary.";
                break;
            case "05":
                chapter=".Hades.Inferno.";
                break;
            case "06":
                chapter=".Hades.Elysion.";
                break;
            case "07":
            case "08":
                chapter=".Lost.Canvas.";
                break;
            case "09":
            case "10":
                chapter=".Omega.";
                break;
            case "11":
                chapter=".Soul.Of.Gold.";
                break;
            case "12":
                chapter=".Saintia.Sho.";
                break;
            default:
                chapter="";
                break;
        }


        StringBuilder sb = new StringBuilder();
        sb.append(parentPath).append(File.separator)
                .append(tvShowinfo.get("tvShowName"))
                .append(".S").append(season)
                .append(".E").append(episode)
                .append(chapter)
                .append(fileComplement).append(".").append(extension);
        File newCurrentFile = new File(sb.toString());
        try {
            Files.move(source, newCurrentFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            return currentFile;
        }
        return newCurrentFile;
    }
}

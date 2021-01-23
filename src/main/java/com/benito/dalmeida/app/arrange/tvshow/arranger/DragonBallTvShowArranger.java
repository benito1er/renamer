package com.benito.dalmeida.app.arrange.tvshow.arranger;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class DragonBallTvShowArranger extends AbstractTvShowArranger {
    private DragonBallTvShowArranger() {
    }

    private static DragonBallTvShowArranger INSTANCE = null;

    public static DragonBallTvShowArranger getInstance() {
        if (INSTANCE == null)
            INSTANCE = new DragonBallTvShowArranger();
        return INSTANCE;
    }

    public boolean isThisTvShowArrangerFile(String lowerFileName) {
        if (StringUtils.containsIgnoreCase(lowerFileName, "dragonball -")
                || StringUtils.startsWithIgnoreCase(lowerFileName, "ghf2.z.t.d.b")
                || StringUtils.startsWithIgnoreCase(lowerFileName, "ghf.z.t.d.b")
        )
            return true;
        else
            return false;
    }

    @Override
    public String getTvShowName(File currentDir) {
        return "Dragon.Ball";
    }

    public String getTvShowSeason(String fileName, String episode) {
        String season = "";
        if (!isThisTvShowArrangerFile(fileName)) {
            return season;
        }
        Integer ep = Integer.parseInt(episode);

        if (ep < 9) {
            season = "01";
        } else if (ep < 31) {
            season = "02";
        } else if (ep < 48) {
            season = "03";
        } else if (ep < 61) {
            season = "04";
        } else if (ep < 70) {
            season = "05";
        } else if (ep < 92) {
            season = "06";
        } else if (ep < 131) {
            season = "07";
        } else if (ep < 144) {
            season = "08";
        } else if (ep < 196) {
            season = "09";
        } else if (ep < 227) {
            season = "10";
        } else if (ep < 326) {
            season = "11";
        } else if (ep < 382) {
            season = "12";
        } else if (ep < 482) {
            season = "13";
        } else if (ep < 517) {
            season = "14";
        } else if (ep < 579) {
            season = "15";
        } else if (ep < 629) {
            season = "16";
        } else if (ep < 747) {
            season = "17";
        } else if (ep < 780) {
            season = "18";
        } else if (ep < 878) {
            season = "19";
        } else if (ep < 982) {
            season = "20";
        } else {
            season = "21";
        }
        return season;

    }

    public File renameFile(File currentFile, Map<String, String> tvShowinfo) {
        String parentPath = currentFile.getParent();
        Path source = currentFile.toPath();
        if (StringUtils.containsIgnoreCase(source.toString(), "ghf2.z.t.d.b"))
        {
            String destinationPath = StringUtils.replace(source.toString(), "ghf2.Z.T.D.B", "ghf.Z.T.D.B");
            File newCurrentFile = new File(destinationPath);
            source = newCurrentFile.toPath();
        }
        String extension = StringUtils.substringAfterLast(currentFile.getName(), ".");
        String fileComplement = StringUtils.substringBetween(currentFile.getName(), tvShowinfo.get("episode"), "." + extension);
        StringBuilder sb = new StringBuilder();
        sb.append(parentPath).append(File.separator)
                .append(tvShowinfo.get("tvShowName"))
                .append(".S").append(tvShowinfo.get("season"))
                .append(".E").append(tvShowinfo.get("episode"))
                .append(fileComplement).append(".").append(extension);
        File newCurrentFile = new File(sb.toString());
        try {
            Files.move(source, newCurrentFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            return currentFile;
        }
        return newCurrentFile;
    }

    public Map<String, String> getTvShowSeasonAndEpisodeAsMapValues(File currentFile) {
        String fileName = currentFile.getName();
        String lowerFileName = StringUtils.lowerCase(fileName);
        if (StringUtils.startsWithIgnoreCase(lowerFileName, "ghf2.z.t.d.b")) {
            Path source = currentFile.toPath();
            String destinationPath = StringUtils.replace(source.toString(), "ghf2.Z.T.D.B", "ghf.Z.T.D.B");
            File newCurrentFile = new File(destinationPath);
            try {
                Files.move(source, newCurrentFile.toPath(), StandardCopyOption.ATOMIC_MOVE);
                return super.getTvShowSeasonAndEpisodeAsMapValues(newCurrentFile);
            } catch (IOException e) {
                //  return super.getTvShowSeasonAndEpisodeAsMapValues(currentFile);
            }

        }

        return super.getTvShowSeasonAndEpisodeAsMapValues(currentFile);
    }
}

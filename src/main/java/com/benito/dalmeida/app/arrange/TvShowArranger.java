package com.benito.dalmeida.app.arrange;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TvShowArranger {
	Pattern normalPattern = Pattern.compile("s(\\d{1,2})e(\\d{1,2})");
	Pattern saisonCrossEpPattern = Pattern.compile("(\\d{1,2})x(\\d{1,2})");
	Pattern epOnlyPattern = Pattern.compile("(\\d{1,2,3})");
	

	public void arrange(){
		String tvShowName;
		String season;
		String tvShowAndSeason;
		String [] dirs =  {};
		
		Map<String, Map<String,List<String>>> existingTvShowDirs=  new HashMap<>();
		
		for(String dir : dirs){
			File currentDir =  new File(dir);
			if(!currentDir.isDirectory()) continue;
			for(File tvShowDir : currentDir.listFiles()){
				Map<String,List<String>> existingSeason = existingTvShowDirs.get(tvShowDir.getName());
				if(existingSeason ==null){
					existingSeason =  new HashMap<>();
					existingTvShowDirs.put(tvShowDir.getName(), existingSeason);
				} 
				for(File tvShowSeasonDir : tvShowDir.listFiles()){
					
				}
			}
		}
		
	}
	
	
	class DownloadedFileInfo{
		private File currentFile;
		String tvShowName;
		String season;
		String tvShowAndSeason;
		public DownloadedFileInfo(File currentFile) {
			super();
			this.currentFile = currentFile;
		}
		public String getTvShowName() {
			return tvShowName;
		}
		public void setTvShowName(String tvShowName) {
			this.tvShowName = tvShowName;
		}
		public String getSeason() {
			return season;
		}
		public void setSeason(String season) {
			this.season = season;
		}
		public String getTvShowAndSeason() {
			return tvShowAndSeason;
		}
		public void setTvShowAndSeason(String tvShowAndSeason) {
			this.tvShowAndSeason = tvShowAndSeason;
		}
		public File getCurrentFile() {
			return currentFile;
		}
		
	}
	/*
	private Map<String,> findSeasonEpisode(String nameToSearch) {
		Assert.notNull(nameToSearch);
        LOGGER.debug("Find season and episode in:{}", nameToSearch);
		SeasonEpisode se = null;
		Matcher matcher = normalPattern.matcher(nameToSearch);
		if(matcher.find() && matcher.groupCount() >=2) {
			se = new SeasonEpisode();
			se.setSeason(Integer.parseInt(matcher.group(1),10));
			se.setEpisode(Integer.parseInt(matcher.group(2),10));
			
        } else {
            String[] showNameAsArray = StringUtils.split(nameToSearch);
            for (String token : showNameAsArray) {
                Integer sAndE = null;
                try {
                    sAndE = Integer.parseInt(token);
                } catch (NumberFormatException e) {
                    LOGGER.debug("Not the Saison and the Episode " + token);
                }
                if (sAndE != null) {
                    se = new SeasonEpisode();
                    se.setSeason(0);
                    se.setEpisode(sAndE);
                    break;
                }
            }
		}
		return se;
	}*/
	
	private boolean downloadIsfinished(String downloadDirectoryName) {
		boolean isDownloaded;
		int index = 0;
		int MAX = 3 * 60;
		do {
			isDownloaded = isDownloding(downloadDirectoryName);
			index++;
		} while (isDownloaded == true || index == MAX);
		
		if (index <= MAX)
			return true;
		else
			return false;
	}
	
	private boolean isDownloding(String downloadDirectoryName) {
		File downloadDirectory = new File(downloadDirectoryName);
		if (!downloadDirectory.isFile() && !downloadDirectory.isDirectory())
			throw new RuntimeException(downloadDirectoryName + " Is not a valide directory");

		Map<String, Long> firstDirContentParsing = dirContentToMap(downloadDirectory);

		// Wait for 1 minute
		try {
			Thread.sleep(60000l);
		} catch (InterruptedException e) {
			System.out.println("cannot wait during " + 60000l);
		}
		Map<String, Long> anotherDirContentParsing = dirContentToMap(downloadDirectory);

		int initialSize = firstDirContentParsing.size();

		int anotherSize = anotherDirContentParsing.size();
		boolean isSame;
		if (initialSize == anotherSize) {
			isSame = firstDirContentParsing.equals(anotherDirContentParsing);
		} else {
			isSame = false;
		}
		return isSame ? false : true;

	}

	
	
	

	private Map<String, Long> dirContentToMap(File downloadDirectory) {
		Map<String, Long> dirContentParsing = new HashMap<>();
		for (File contentFile : downloadDirectory.listFiles()) {
			try {
				dirContentParsing.put(contentFile.getCanonicalPath(), contentFile.lastModified());
			} catch (IOException e) {
				System.out.println("cannot get cannonicalPath of " + contentFile);
			}
		}
		return dirContentParsing;
	}
}

package com.benito.dalmeida.app.arrange.tvshow;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TvShowArrangerProccessTest {

	private TvShowArrangerProccess tvShowArrangerProccess =  new TvShowArrangerProccess();
	@Test
	public void testGetMapOfFiles(){
		String fileName ="W://T�l�charg�";
		File currentDir =  new File(fileName);
		Map<String, List<File>> arrangeMap = new HashMap<>();
		Map<String, List<File>> mapOfFiles = tvShowArrangerProccess.getMapOfFiles(currentDir,arrangeMap);
		
	}
	
	@Test
	public void testArrange(){
		String [] sampleDirs = {"W:\\T�l�charg�"};
		tvShowArrangerProccess.arrange(sampleDirs);
	}
}
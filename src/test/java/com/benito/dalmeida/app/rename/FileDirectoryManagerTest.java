package com.benito.dalmeida.app.rename;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class FileDirectoryManagerTest {

	private FileDirectoryManager fileDirectoryManager;
	private final String tempDirPath = System.getProperty("java.io.tmpdir");
	private final String fileSeparator = System.getProperty("file.separator");
	private File newDir;

	@Before
	public void init() {
		// Prepare
		final String directoryToRemoveName = "emptyDirectoy";
		newDir = new File(tempDirPath + fileSeparator + directoryToRemoveName);
		if (!newDir.exists() && !newDir.isDirectory()) {
			newDir.mkdirs();
		}
		fileDirectoryManager = new FileDirectoryManager();
	}

	@Test
	public void testFolderSize() throws Exception {
		// prepare

		// perform
		final long newDirSize = FileDirectoryManager.folderSize(newDir);
		System.out.println(newDirSize);

		//
		Assert.assertTrue(newDirSize < 10);

	}

	@Test
	public void testRemoveEmptyDirectory() throws IOException {
		// prepare
		final File rootDir = new File(tempDirPath);
		fileDirectoryManager.removeEmptyDirectory(rootDir.getCanonicalPath(), newDir.getCanonicalPath() + "New");

	}

	@Test
	public void test() {
		String s = "he had had quite";
		int l = s != null ? s.length() : 0;
		if (0 < l || l > 1024) {
			new RuntimeException("Length is ot ine range 0 :1024");
		}

		Map<String, Integer> map = new HashMap<>();
		List<String> delimiters =Arrays.asList(new String []{" ","\t",",",";","\n","-","."});
		
		List<String> words = new ArrayList<>();
		for(String delimeter : delimiters){
			if(s.contains(delimeter)){
			String[] tab = s.split(delimeter);
			words.addAll(Arrays.asList(tab));}
		}
		for (String currentString : words) {
			Integer count = map.get(currentString);
			if (count == null) {
				count = 1;
			} else {
				count += 1;
			}
			map.put(currentString, count);
		}
		String result = null;
		Integer max = Collections.max(map.values());
		for(Map.Entry<String, Integer> mapEntry : map.entrySet()){
			if(mapEntry.getValue().equals(max)){
				result = mapEntry.getKey();
                break;
			}
		}
		
	}
	@Test
	public void test2() {
		int[] a = { 1, 2, 8 };
		int[] b = { 3, 4, 4 };
		Integer m1 = a.length;
		Integer m2 = b.length;
		if (!m1.equals(m2))
			new RuntimeException("A and B not same lenght");
		if (m1 < 1 || m1 > 500000) {
			new RuntimeException("out of range");
		}
		int[] c = Arrays.copyOf(a, m1 * 2);
		for (int i = (0 + m1); i < (b.length + m1); i++) {
			c[i] = b[i - m1];
		}
		Arrays.sort(c);
		

	}
	@Test
	public void test3(){
		Scanner scanner =  new Scanner(System.in);
		String [] nandM = scanner.nextLine().split(" ");
		Integer N = Integer.parseInt(nandM[0]);
		if(N < 3 || N> Math.pow(10, 7)){
			new RuntimeException("N out of range");
		}
		Integer M = Integer.parseInt(nandM[1]);
		if(M < 1 || M> 2* Math.pow(10, 5)){
			new RuntimeException("M out of range");
		}
		int [] result =new int [N];
		for(int i =0 ;i< M; i++){
			String[] next = scanner.nextLine().split(" ");
			Integer a = Integer.parseInt(next[0]);
			Integer b = Integer.parseInt(next[1]);
			if(a < 1 ||  a> N){
				new RuntimeException("a out of range");
			}
			if(b < a ){
				new RuntimeException("b out of range or condition");
			}
			Integer k = Integer.parseInt(next[2]);
			if(k < 0 ||  k> Math.pow(10, 9)){
				new RuntimeException("k out of range");
			}
			System.out.println();
			for(int j=0; i<M; i++){
				result[a]+=k;
				result[b]+=k;
			}
		}
		scanner.close();
		
	}
}

package com.benito.dalmeida.app.rename;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class FileRenamerTest {

    private FileRenamer fileRenamer;

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
        final String[] roots = { directoryToRemoveName };
        fileRenamer = new FileRenamer(roots);
    }

    @Test
    public void testForceRenameFile() throws IOException {
        final File anyFile = new File(tempDirPath).listFiles()[0];
        final boolean result = fileRenamer.forceRenameFile(anyFile, newDir.getCanonicalPath() + Math.random());
        Assert.assertTrue(result);
    }

    @Test
    public void testRenameOnFile() throws IOException {
        final List<String> wordToRemoves = Arrays.asList(new String[] { "[cestpasbien.com]", "[cestpasbien.me]",
                "[cestpasbien.pe]", "[cestpasbien.fr]" });
        final File testFile = new File(newDir + fileSeparator + "[cestpasbien.pe] someFile.txt");
        testFile.createNewFile();
        fileRenamer.renameOneFile(testFile, wordToRemoves);
    }

    @Test
    public void testFuzzy() {
        final int distance = StringUtils.getLevenshteinDistance("[cpasbien.com]", "[ceasbien.com]");
        System.out.println(distance);
    }
}

package com.benito.dalmeida.app.rename;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.benito.dalmeida.app.rename.FileDirectoryManager;

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
}

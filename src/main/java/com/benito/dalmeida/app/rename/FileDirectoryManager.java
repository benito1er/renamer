package com.benito.dalmeida.app.rename;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class FileDirectoryManager {
    public void removeEmptyDirectory(final String directoryPath, final String removableBufferDirPath)
            throws IOException {
        final File rootDirectory = new File(directoryPath);
        final File[] dFiles = rootDirectory.listFiles();

        final File removableBufferDir = new File(removableBufferDirPath);
        if (!removableBufferDir.exists() && !removableBufferDir.isDirectory()) {
            removableBufferDir.mkdirs();
        }
        if (dFiles != null) {
            for (final File file : dFiles) {
                if (file.isDirectory()) {
                    final long fileSize = folderSize(file);
                    final String fileName = file.getCanonicalPath();
                    System.out.println(fileName + "  " + fileSize);
                    if (StringUtils.startsWith(fileName, "\\.")) {
                        continue;
                    }
                    if (fileSize < 1) {
                        // file.renameTo(newFile);
                        FileUtils.moveToDirectory(file, removableBufferDir, true);
                    }
                }
                continue;
            }
        }
    }

    protected static long folderSize(final File directory) {
        long length = 0;
        if (!directory.isDirectory())
            throw new RuntimeException(directory.getPath() + "It is not a directory");
        for (final File file : directory.listFiles()) {
            if (length > 10) {
                continue;
            }
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
}

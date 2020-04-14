package com.benito.dalmeida.app.rename;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class FileRenamer {

    public final String trashDir = "$trashable";
    public static final String javaFileTempDir = System.getProperty("java.io.tmpdir");
    public static final String fileSeparator = System.getProperty("file.separator");
    public static final String renamedFilesLog = "renameFiles.log";
    private final String[] rootDirectories;


    public FileRenamer(final String[] rootDirectories) {
        this.rootDirectories = rootDirectories;
    }

    public void rename(final List<String> wordsToRemoves) throws IOException {
        for (final String directoryPath : rootDirectories) {
            try{
            System.out.println("start renaming file in " + directoryPath);
            final File rootDirectory = new File(directoryPath);
            final String rootDirectoryName = rootDirectory.getCanonicalPath();
            final boolean skipable = skipableFiles(rootDirectoryName);
            if (skipable) {
                return;
            }
            if (!skipable) {
                if (rootDirectory.isDirectory() && !skipable) {
                    final File[] includeFiles = rootDirectory.listFiles();
                    renameFiles(includeFiles, wordsToRemoves);
                } else {
                    renameOneFile(rootDirectory, wordsToRemoves);
                }
            }
            System.out.println("ENd");
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    private boolean skipableFiles(final String rootDirectoryName) {
        final boolean skipable = StringUtils.containsIgnoreCase(rootDirectoryName, fileSeparator + ".")
                || StringUtils.containsIgnoreCase(rootDirectoryName, fileSeparator + "$RECYCLE.BIN")
                || StringUtils.containsIgnoreCase(rootDirectoryName, fileSeparator + "MyCinemaData")
                || StringUtils.containsIgnoreCase(rootDirectoryName, fileSeparator + ".@__thumb")
                || StringUtils.containsIgnoreCase(rootDirectoryName, fileSeparator + trashDir);
        return skipable;
    }

    protected boolean forceRenameFile(final File file, final String realNewFileName) throws IOException {
        System.err.print(file.getCanonicalPath());
        System.out.println(" to ---> " + realNewFileName);
        final File newFile = new File(realNewFileName);
        boolean result = file.renameTo(newFile);
        boolean trashable = false;
        File parentFile = newFile.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        if (!result) {
            try {
                FileUtils.moveFile(file, newFile);
            } catch (final Exception e) {
                System.err.println(newFile.getCanonicalPath());
                final String errorMessage = e.getMessage();
                System.err.println(errorMessage + " -------------  " + file.getCanonicalPath());
                final String driveDirectory = StringUtils.substringBefore(file.getCanonicalPath(), ":");
                final String trashDirectory = (StringUtils.isNotBlank(driveDirectory) ? driveDirectory + ":" : "Y:")
                        + FileRenamer.fileSeparator + trashDir + FileRenamer.fileSeparator;
                trashable = moveToTemp(trashDirectory, errorMessage, file);
            }
            result = trashable ? true : newFile.exists();
            if (!result) {

                try {
                    FileUtils.moveToDirectory(file, parentFile, false);
                } catch (final Exception e) {

                    System.out.println(e);
                    final String errorMessage =e.getMessage();
                    if (StringUtils.containsIgnoreCase(errorMessage, "already exists")
                            || StringUtils.containsIgnoreCase(errorMessage, "is a directory")
                            || StringUtils.containsIgnoreCase(errorMessage, "is not a directory")) {
                        try {
                            FileUtils.moveFileToDirectory(file, parentFile, true);
                        } catch (final Exception ee) {

                            System.out.println(ee);
                        }
                    }
                }
            }

        }
        return result;
    }

    private boolean moveToTemp(final String tempDirectory, final String errorMessage, final File file)
            throws IOException {

        if (StringUtils.containsIgnoreCase(errorMessage, "already exists")
                || StringUtils.containsIgnoreCase(errorMessage, "is a directory")
                || StringUtils.containsIgnoreCase(errorMessage, "is not a directory")
                || StringUtils.containsIgnoreCase(errorMessage, "directory cannot be")) {
            final String oldFileName = file.getCanonicalPath();
            String tempSubDirectory = new String(StringUtils.substringBeforeLast(oldFileName,"."));
            for (final String rootName : rootDirectories) {
                if (StringUtils.containsIgnoreCase(tempSubDirectory, rootName)) {
                    tempSubDirectory = StringUtils.substringAfter(tempSubDirectory, rootName);
                    break;
                }
            }
            final String newTrashableFileName = tempDirectory + FileRenamer.fileSeparator + tempSubDirectory;
            System.out.println("################" + newTrashableFileName + "##########################");
            try {
                final File trashFile = new File(newTrashableFileName);
                try {
                    FileUtils.forceDelete(trashFile);
                } catch (final Exception ex) {

                }
                FileUtils.moveToDirectory(file, new File(newTrashableFileName), true);
                return true;
            } catch (final Exception e) {
                return false;
            }
        }
        return false;
    }

    public boolean renameOneFile(final File file, final List<String> wordToRemoves) throws IOException {
        if (file.isDirectory()) {
            final File[] filesToRemanes = file.listFiles();
            if (filesToRemanes.length > 0)
                return renameFiles(filesToRemanes, wordToRemoves);
            else {
                return renameMatchingFileOrDirectoryName(file, wordToRemoves);
            }
        } else {
            return renameMatchingFileOrDirectoryName(file, wordToRemoves);
        }
    }

    private boolean renameMatchingFileOrDirectoryName(final File file, final List<String> wordToRemoves)
            throws IOException {
        final String canonicalFileName = file.getCanonicalPath();
        final boolean skipable = skipableFiles(canonicalFileName);
        if (skipable) {
            System.out.println("Is skipable " + canonicalFileName);
            return true;
        }
        String newFileNameTemp = canonicalFileName;
        System.out.println(newFileNameTemp);
        for (final String wordToRemove : wordToRemoves) {
            if (StringUtils.containsIgnoreCase(newFileNameTemp, wordToRemove)) {
                newFileNameTemp = StringUtils.remove(newFileNameTemp, wordToRemove);
            }
        }
        final String realNewFileName = StringUtils.replace(newFileNameTemp, fileSeparator + " ", fileSeparator);
        if (!StringUtils.equalsIgnoreCase(canonicalFileName, realNewFileName)) {
            final String resume = "****** Rename : " + canonicalFileName + " in " + realNewFileName;
            final String tempFile = javaFileTempDir + fileSeparator + renamedFilesLog;
            FileReaderAndWriter.writeInFile(tempFile, resume);
            return forceRenameFile(file, realNewFileName);
        }
        return false;

    }

    public boolean renameFiles(final File[] filesToRemanes, final List<String> wordToRemoves) throws IOException {
        boolean result = true;
        for (final File file : filesToRemanes) {
            if (skipableFiles(file.getCanonicalPath()))
                continue;
            result &= renameOneFile(file, wordToRemoves);
        }
        return result;
    }
}

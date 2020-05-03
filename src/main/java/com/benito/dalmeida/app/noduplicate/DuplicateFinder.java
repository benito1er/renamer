package com.benito.dalmeida.app.noduplicate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.benito.dalmeida.app.rename.FileRenamer;

public class DuplicateFinder {

    private static final String SPACE = " ";
    private static final String UNDER_SCORE = "_";
    private static final String CLOSE = ")";
    private static final String OPEN = "(";

    public static List<String> lookOver(final File rootDirectory) throws IOException {
        final List<String> files = new ArrayList<>();
        boolean isDirectoy = rootDirectory.isDirectory();
        if (isDirectoy) {
            final File[] currentDirectoryFiles = rootDirectory.listFiles();
            for (final File currentFile : currentDirectoryFiles) {
                isDirectoy = currentFile.isDirectory();
                if (isDirectoy) {
                    files.addAll(lookOver(currentFile));
                } else {
                    files.add(currentFile.getCanonicalPath());
                }
            }
        } else {

            files.add(rootDirectory.getCanonicalPath());
        }
        return files;
    }

    public static Map<String, Set<String>> fileAsKeyList(final List<String> tempFileNames) throws IOException {
        final Set<String> fileNames = new TreeSet<>();
        fileNames.addAll(tempFileNames);
        final Map<String, Set<String>> map = new HashMap<>();
        for (final String fileName : fileNames) {
            final File current = new File(fileName);
            final String currentFileName = current.getCanonicalPath();
            final String leastFileName = basicName(currentFileName, true);
            Set<String> similarFiles = map.get(leastFileName);
            if (similarFiles == null) {
                similarFiles = new HashSet<>();
                similarFiles.add(fileName);
                map.put(leastFileName, similarFiles);
            } else {
                similarFiles.add(fileName);
            }
        }
        return map;
    }

    public static Set<String> getTrashableFiles(final Map<String, Set<String>> mapFiles) {
        final Set<String> toTrash = new HashSet<>();
        for (final Map.Entry<String, Set<String>> sortName : mapFiles.entrySet()) {
            final String key = sortName.getKey();
            final Set<String> fileToRenames = sortName.getValue();
            if (fileToRenames.size() == 1) {
                continue;
            } else {
                final String fileToKeep;
                final boolean hasBestFile = fileToRenames.contains(key);
                final Set<String> sortSet = new TreeSet<String>();
                sortSet.addAll(fileToRenames);

                final Iterator<String> fileIterator = sortSet.iterator();
                if (hasBestFile) {
                    fileToKeep = key;
                } else {
                    fileToKeep = fileIterator.next();
                    if (!StringUtils.equalsIgnoreCase(key, fileToKeep))
                        try {
                            FileUtils.moveFile(new File(fileToKeep), new File(key));
                        } catch (final IOException e) {
                            // toTrash.remove(key);
                        }
                }
                while (fileIterator.hasNext()) {

                    final String toTrashFile = fileIterator.next();
                    if (!StringUtils.equalsIgnoreCase(fileToKeep, toTrashFile)) {
                        toTrash.add(toTrashFile);
                    }
                }
            }

        }
        return toTrash;
    }

    public static void moveToTrash(final Set<String> toTrashes, final String trashDirectory) {
        for (final String trashFile : toTrashes) {
            final String partTrashFileName = StringUtils.substringAfter(trashFile, "iTunes");
            final StringBuilder sb = new StringBuilder();
            sb.append(trashDirectory);
            sb.append(FileRenamer.fileSeparator);
            sb.append(partTrashFileName);
            final String newFileName = sb.toString();
            System.err.println(newFileName);
            try {
                FileUtils.moveFile(new File(trashFile), new File(newFileName));
            } catch (final IOException e) {
                System.err.println(e);
            }
        }
    }

    public static String basicName(final String processingFileName, final boolean withExtension) {
        final String fileNameWithoutExt = StringUtils.substringBeforeLast(processingFileName, ".");
        final String ext = StringUtils.substringAfterLast(processingFileName, ".");
        String newfileNameWithoutExt = fileNameWithoutExt;
        String incrementId = getIncrementIdFromEndOfFileName(newfileNameWithoutExt);
        while (hasIncrementId(incrementId) && StringUtils.isNotBlank(incrementId)) {
            String wordToRemove = "";
            if (hasOpen(newfileNameWithoutExt)) {
                wordToRemove += OPEN;
            }
            wordToRemove += incrementId;
            if (hasClose(newfileNameWithoutExt)) {
                wordToRemove += CLOSE;
            }
            final int beforeRemove = newfileNameWithoutExt.length();
            newfileNameWithoutExt = StringUtils.strip(StringUtils.removeEnd(newfileNameWithoutExt, wordToRemove));
            final int afterRemove = newfileNameWithoutExt.length();
            if (beforeRemove == afterRemove && StringUtils.containsIgnoreCase(newfileNameWithoutExt, wordToRemove)) {
                if (StringUtils.containsIgnoreCase(newfileNameWithoutExt, SPACE + wordToRemove))
                    newfileNameWithoutExt = StringUtils.strip(StringUtils.remove(newfileNameWithoutExt, SPACE
                            + wordToRemove));
                else if (StringUtils.containsIgnoreCase(newfileNameWithoutExt, UNDER_SCORE + wordToRemove))
                    newfileNameWithoutExt = StringUtils.strip(StringUtils.remove(newfileNameWithoutExt, SPACE
                            + wordToRemove));
                else {
                    newfileNameWithoutExt = StringUtils.strip(StringUtils.remove(newfileNameWithoutExt, wordToRemove));
                }
            }
            incrementId = getIncrementIdFromEndOfFileName(newfileNameWithoutExt);
            System.out.println(fileNameWithoutExt + " ---> " + newfileNameWithoutExt);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.strip(newfileNameWithoutExt));
        if (withExtension) {
            sb.append(".");
            sb.append(ext);
        }
        return StringUtils.strip(sb.toString());

    }

    private static boolean hasOpen(final String fileNameWithoutExt) {
        return StringUtils.containsIgnoreCase(fileNameWithoutExt, OPEN);
    }

    private static boolean hasClose(final String fileNameWithoutExt) {
        return StringUtils.containsIgnoreCase(fileNameWithoutExt, CLOSE);
    }

    private static boolean isInBracket(final String fileNameWithoutExt) {
        return hasOpen(fileNameWithoutExt) || hasClose(fileNameWithoutExt);
    }

    private static boolean hasUnderscore(final String fileNameWithoutExt) {
        return StringUtils.containsIgnoreCase(fileNameWithoutExt, UNDER_SCORE);
    }

    private static String getIncrementIdFromEndOfFileName(final String fileNameWithoutExt) {
        final String incrementId;
        if (isInBracket(fileNameWithoutExt)) {
            incrementId = StringUtils.strip(StringUtils.substringBetween(fileNameWithoutExt, OPEN, CLOSE));
        } else if (hasUnderscore(fileNameWithoutExt)) {
            incrementId = StringUtils.strip(StringUtils.substringAfterLast(fileNameWithoutExt, UNDER_SCORE));
        } else {
            incrementId = StringUtils.strip(StringUtils.substringAfterLast(fileNameWithoutExt, SPACE));
        }
        return incrementId;
    }

    private static boolean hasIncrementId(final String incrementId) {
        return StringUtils.isNumeric(incrementId);
    }
}

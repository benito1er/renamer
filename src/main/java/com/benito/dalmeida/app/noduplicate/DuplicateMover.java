package com.benito.dalmeida.app.noduplicate;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class DuplicateMover {

    public static Set<String> moveDuplicateTo(final Set<String> movableFiles, final String trashDirectory) {
        final Set<String> unMovableFiles = new HashSet<>();
        for (final String movableFile : movableFiles) {
            try {
                FileUtils.moveToDirectory(new File(movableFile), new File(trashDirectory), true);
            } catch (final IOException e) {
                unMovableFiles.add(movableFile);
            }
        }
        int index = 0;
        final int limit = 1000;
        while (unMovableFiles.size() > 0 && index < limit) {
            final String newTrashDirectory = trashDirectory + index;
            moveDuplicateTo(unMovableFiles, newTrashDirectory);
            index++;
        }

        return unMovableFiles;
    }

    protected static Set<String> fileToMove(final Map<String, Set<String>> movableFileMap, final boolean withExtension) {
        final Set<String> toKeep = new HashSet<String>();
        final Set<String> toDelete = new HashSet<String>();

        for (final Map.Entry<String, Set<String>> fileMap : movableFileMap.entrySet()) {
            final Set<String> currentEntryToKeep = new HashSet<>();
            final Set<String> currentEntryToDelete = new HashSet<>();
            final String toKeepFileName = fileMap.getKey();
            for (final String currentFile : fileMap.getValue()) {
                final boolean foundFile = withExtension?StringUtils.equalsIgnoreCase(toKeepFileName, StringUtils.substringBeforeLast(currentFile, ".")):StringUtils.equalsIgnoreCase(toKeepFileName, currentFile);
                if (foundFile) {
                    currentEntryToKeep.add(currentFile);
                } else {
                    currentEntryToDelete.add(currentFile);
                }
            }
            if (currentEntryToKeep.isEmpty()) {
                final String randomKeeping = currentEntryToDelete.iterator().next();
                currentEntryToKeep.add(randomKeeping);
                currentEntryToDelete.remove(randomKeeping);
            }
            toKeep.addAll(currentEntryToKeep);
            toDelete.addAll(currentEntryToDelete);
        }
        return toDelete;
    }
}

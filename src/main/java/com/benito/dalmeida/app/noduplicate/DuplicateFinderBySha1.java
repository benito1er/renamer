package com.benito.dalmeida.app.noduplicate;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.benito.dalmeida.app.rename.FileRenamer;

public class DuplicateFinderBySha1 {

    private static final String TRASHABLE = "$Trashable";
    private static final int PART_OF_MODULO = 11;
    private final String[] searchPaths;
    private String trashBaseDir;
    private long index;
    private static final int MODULO = 1000;
    private long startTime;
    final int ROLLING_TIME = 300000;

    public DuplicateFinderBySha1(final String[] searchPaths, final String trashBaseDir) {
        this.searchPaths = searchPaths;
        this.trashBaseDir = trashBaseDir;
        startTime = 0L;
        index = 0l;
    }

    public void run() {
        startTime = System.currentTimeMillis();
        System.out.println("started at " + startTime);
        final Map<String, List<File>> sha1KeyMaps = new HashMap<String, List<File>>();
        for (final String searchPath : searchPaths) {
            final Path path = Paths.get(searchPath);
            if (Files.exists(path)) {
                recursiveIndexSha1(path, sha1KeyMaps);
            }
        }
        final StringBuilder sb = new StringBuilder();
        if (!sha1KeyMaps.isEmpty()) {
            System.out.println();
            System.out.println("Finding Duplicate");
            final Map<String, List<File>> duplicateFileMaps = new HashMap<String, List<File>>();
            for (final Map.Entry<String, List<File>> sha1KeyEntry : sha1KeyMaps.entrySet()) {
                if (sha1KeyEntry.getValue().size() > 1) {
                    duplicateFileMaps.put(sha1KeyEntry.getKey(), sha1KeyEntry.getValue());
                }
            }

            System.out.println("duplicate found : " + duplicateFileMaps.size());
            for (final Map.Entry<String, List<File>> duplicateFileEntry : duplicateFileMaps.entrySet()) {
                final List<File> duplicates = duplicateFileEntry.getValue();
                final Iterator<File> duplicateIt = duplicates.iterator();
                final File fileToKeep = duplicateIt.next();
                String currentFileName;
                try {
                    currentFileName = fileToKeep.getCanonicalPath();
                } catch (final IOException e1) {
                    currentFileName = null;
                }
                if (currentFileName != null) {

                }
                final String leastFileName = DuplicateFinder.basicName(currentFileName, true);
                sb.append(duplicateFileEntry.getKey() + " => ").append("\t" + fileToKeep).append("\r\n");
                while (duplicateIt.hasNext()) {
                    final File toRemove = duplicateIt.next();
                    sb.append(duplicateFileEntry.getKey() + " => ").append("\t" + toRemove).append("\r\n");
                    String fileToRemoveName;
                    try {
                        fileToRemoveName = toRemove.getCanonicalPath();
                    } catch (final IOException e) {
                        fileToRemoveName = toRemove.getName();
                    }
                    for (final String rootDir : searchPaths) {
                        if (StringUtils.containsIgnoreCase(fileToRemoveName, rootDir)) {
                            fileToRemoveName = StringUtils.remove(fileToRemoveName, rootDir);
                            if (trashBaseDir == null) {
                                trashBaseDir = rootDir + FileRenamer.fileSeparator + TRASHABLE;
                            }
                            break;
                        }
                    }
                    final String fileToRemoveNewName = trashBaseDir + fileToRemoveName;
                    try {
                        FileUtils.moveToDirectory(toRemove, new File(fileToRemoveNewName), true);
                    } catch (final IOException e) {
                        sb.append(e.getMessage()).append("\r\n").append("\r\n");
                    }
                }
                if (currentFileName != null && !currentFileName.equalsIgnoreCase(leastFileName)) {
                    try {
                        FileUtils.moveFile(fileToKeep, new File(leastFileName));
                    } catch (final IOException e) {
                        sb.append(e.getMessage()).append("\r\n").append("\r\n");
                    }
                }

            }
        }
        final File result = new File(FileRenamer.javaFileTempDir + FileRenamer.fileSeparator + "DuplicateFinder.log");
        try {
            FileUtils.writeStringToFile(result, sb.toString());
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void indexSha1File(final Path path, final Map<String, List<File>> sha1KeyMaps) {
        final File file = path.toFile();
        index++;
        if (startTime == 0L)
            startTime = System.currentTimeMillis();

        final long freeze = startTime;
        final boolean isRollingFile = index % MODULO == 0;
        final boolean isTimeOverDue = System.currentTimeMillis() - freeze > ROLLING_TIME;
        if (isTimeOverDue || isRollingFile) {

            System.out.println();
            System.out.println(freeze + ": and " + (System.currentTimeMillis() - startTime));
            System.out.println("processing file " + index + "   : " + file);
            if (isTimeOverDue) {
                startTime = System.currentTimeMillis();
            }
        } else {
            System.out.print(".");
            if (index % (MODULO / PART_OF_MODULO) == 0)
                System.out.println();
        }
        if (!file.canRead() || !file.isFile()) {
            return;
        }
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            final byte[] sha = DigestUtils.sha256(in);
            final String key = Hex.encodeHexString(sha);
            List<File> files = sha1KeyMaps.get(key);
            if (files == null) {
                files = new ArrayList<File>();
                sha1KeyMaps.put(key, files);
            }
            files.add(file);
        } catch (final IOException e) {
            IOUtils.closeQuietly(in);
        }

    }

    private void recursiveIndexSha1(final Path path, final Map<String, List<File>> sha1KeyMaps) {
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(final Path path, final BasicFileAttributes attr) throws IOException {
                    indexSha1File(path, sha1KeyMaps);
                    return FileVisitResult.CONTINUE;
                }

            });
        } catch (final IOException e) {

        }

    }
}

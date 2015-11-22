package com.benito.dalmeida.app.rename;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.commons.lang.StringUtils;

public class FileReaderAndWriter {

    public static String readFile(final String filePathInput) {

        Scanner scanner = null;
        String line = null;
        final StringBuilder str = new StringBuilder();
        try {
            scanner = new Scanner(new File(filePathInput));
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line != null)
                    str.append(line).append("\r\n");
            }
            scanner.close();
        } catch (final FileNotFoundException e) {
            System.err.println("Erreur levée de type FileNotFoundException" + " au niveau de la méthode "
                    + "readFile(...) : ");
            e.printStackTrace();
        }
        return str.toString();
    }

    public static void safteWriteInFile(final String fileName, final String fileContent) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            final String shortFileName = StringUtils.substringAfterLast(StringUtils.substringAfterLast(fileName, "\\"),
                    "/");
            final String tempDir = System.getProperty("java.io.tmpdir");
            file = new File(tempDir + System.getProperty("file.separator") + shortFileName);
        }
        final StringBuilder sb = new StringBuilder();

        sb.append(readFile(file.getCanonicalPath()));

        sb.append("\r\n").append(fileContent);

        writeInFile(fileName, fileContent);

    }

    public static void writeInFile(final String fileName, final String fileContent) {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(fileName, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("\r\n" + fileContent);
        } catch (final IOException e) {

        } finally {
            if (bufferedWriter != null)
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (final IOException e) {

                }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (final IOException e) {

                }

            }

        }

    }

}

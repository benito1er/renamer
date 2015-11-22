package com.benito.dalmeida.app;

import com.benito.dalmeida.app.noduplicate.DuplicateFinderBySha1;
import com.benito.dalmeida.app.rename.FileRenamer;

public class FindDuplicateMainClass {
    public static void main(final String[] argrs) {
        final String[] rootDirectories = { /* "Y:" + FileRenamer.fileSeparator + "Téléchargements", */
                /*
                 * "M:" + FileRenamer.fileSeparator + "temp" + FileRenamer.fileSeparator + "music",
                 */
                /*
                 * "M:" + FileRenamer.fileSeparator + "iTunes" + FileRenamer.fileSeparator + "iTunes Media" +
                 * FileRenamer.fileSeparator + "Music" + FileRenamer.fileSeparator,
                 */

                "E:" + FileRenamer.fileSeparator + "Users" + FileRenamer.fileSeparator + "benito1er"
                + FileRenamer.fileSeparator
                + "Pictures", /*
                 * "L:" + FileRenamer.fileSeparator + "Movies",
                 * 
                 * "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator +
                 * "TVShows", "Y:" + FileRenamer.fileSeparator + "Videos" +
                 * FileRenamer.fileSeparator + "TVShows Archived",
                 * 
                 * "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator +
                 * "Movies", "Y:" + FileRenamer.fileSeparator + "Videos" +
                 * FileRenamer.fileSeparator + "Movies" + FileRenamer.fileSeparator + "To_Import",
                 * 
                 * "Y:" + FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator +
                 * "Animations", "Y:" + FileRenamer.fileSeparator + "Videos" +
                 * FileRenamer.fileSeparator + "Manga", "Z:" + FileRenamer.fileSeparator +
                 * "Videos" + FileRenamer.fileSeparator + "TVShows Archived", "Z:" +
                 * FileRenamer.fileSeparator + "Videos" + FileRenamer.fileSeparator + "XXX_Adult"
                 */ };

        final DuplicateFinderBySha1 duplicateFinderBySha1 = new DuplicateFinderBySha1(rootDirectories, null);
        duplicateFinderBySha1.run();
    }
}

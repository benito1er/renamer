package com.benito.dalmeida.app.noduplicate;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.benito.dalmeida.app.rename.FileRenamer;

public class DuplicateMoverTest {

    @Test
    public void testMoveDuplicateTo() throws Exception {

        final List<String> looks = DuplicateFinder.lookOver(new File("m:" + FileRenamer.fileSeparator + "itunes"
                + FileRenamer.fileSeparator + "iTunes Media" + FileRenamer.fileSeparator + "Music"));
        final Map<String, Set<String>> looksMap = DuplicateFinder.fileAsKeyList(looks);
        final Set<String> trashableFiles = DuplicateFinder.getTrashableFiles(looksMap);
        DuplicateFinder.moveToTrash(trashableFiles, "M:/$trash");
        // DuplicateFinder.renameAtLeastShortAsMap(sortLooksMap);
        // final Set<String> movables = DuplicateMover.fileToMove(sortLooksMap, true);
        // System.out.println("Prepare move");
        // final Set<String> files = DuplicateMover.moveDuplicateTo(movables, "E:" + FileRenamer.fileSeparator
        // + "trashable");
        // System.out.println(files);
        // Assert.assertTrue(files.isEmpty());

    }
}

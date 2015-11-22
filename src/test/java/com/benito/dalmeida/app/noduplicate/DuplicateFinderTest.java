package com.benito.dalmeida.app.noduplicate;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.benito.dalmeida.app.rename.FileRenamer;

public class DuplicateFinderTest {

    @Test
    public void testLookOver() throws Exception {
        final List<String> files = DuplicateFinder.lookOver(new File("m:" + FileRenamer.fileSeparator + "itunes"
                + FileRenamer.fileSeparator + "iTunes Media" + FileRenamer.fileSeparator + "Music"));
        System.out.println(files);
    }

    @Test
    public void testBasicFileName() {
        final String inputFileName = "M:/iTunes/iTunes Media/Music/50 Cent/Nouveau titre (1)/14 50.mp3";
        final String expectedFileName = "M:/iTunes/iTunes Media/Music/50 Cent/Nouveau titre/14.mp3";
        final String calculatedFileName = DuplicateFinder.basicName(inputFileName, true);
        Assert.assertEquals(expectedFileName, calculatedFileName);
    }
}

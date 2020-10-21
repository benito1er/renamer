package com.benito.dalmeida.app.arrange.tvshow.arranger;

import com.benito.dalmeida.app.arrange.tvshow.TvShowArranger;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.util.Map;

public class TvShowArrangerTest {


    private TvShowArranger tvShowArranger;

    @Test
    public void testGetTvShowSeasonAndEpisodeAsMapValues(){
        String dowloandDirName = this.getClass().getClassLoader().getResource("BlenetoxS01E00.mp4").getFile();
        File dowloandDir = new File(dowloandDirName);
        tvShowArranger = GenericTvShowArranger.getInstance();
        Map<String,String> tvShowSeasonAndEpisodeAsMapValues= tvShowArranger.getTvShowSeasonAndEpisodeAsMapValues(dowloandDir);
        Assert.assertEquals(3, tvShowSeasonAndEpisodeAsMapValues.size());
        Assert.assertEquals("blenetox", tvShowSeasonAndEpisodeAsMapValues.get("tvShowName"));
    }
}

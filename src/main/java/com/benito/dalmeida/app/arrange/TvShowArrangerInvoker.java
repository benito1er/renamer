package com.benito.dalmeida.app.arrange;

import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;

public class TvShowArrangerInvoker {

    private static LinkedList<TvShowArranger> executionOrderTvShowArranger() {
        LinkedList<TvShowArranger> tvShowArrangers = new LinkedList<>();

        tvShowArrangers.add(new OnePieceKaiTvShowArranger());

        tvShowArrangers.add( new DragonBallSuperHeroesTvShowArranger());

        tvShowArrangers.add(new DragonBallSuperTvShowArranger());

        tvShowArrangers.add( new BorutoTvShowArranger());

        tvShowArrangers.add(new OnePieceTvShowArranger());

        tvShowArrangers.add( new TorikoTvShowArranger());

        tvShowArrangers.add(new AttackOnTitanTvShowArranger());

        tvShowArrangers.add(new GenericTvShowArranger());

        return tvShowArrangers;
    }

    public static TvShowArranger getTvShowArranger(String lowerFileName) {
        boolean matching = false;
        TvShowArranger selectedTvShowArranger = null;
        for (TvShowArranger current : executionOrderTvShowArranger()) {
            matching = current.isThisTvShowArrangerFile(lowerFileName);
            if (matching) {
                selectedTvShowArranger = current;
                break;
            }
        }
        if (selectedTvShowArranger == null) {
            selectedTvShowArranger = executionOrderTvShowArranger().getLast();

        }
        return selectedTvShowArranger;
    }
}

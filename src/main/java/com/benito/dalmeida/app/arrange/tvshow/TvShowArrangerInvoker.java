package com.benito.dalmeida.app.arrange.tvshow;

import com.benito.dalmeida.app.arrange.tvshow.arranger.*;

import java.util.LinkedList;

public class TvShowArrangerInvoker {

    private static LinkedList<TvShowArranger> executionOrderTvShowArranger() {
        LinkedList<TvShowArranger> tvShowArrangers = new LinkedList<>();

        tvShowArrangers.add(OnePieceKaiTvShowArranger.getInstance());
        tvShowArrangers.add(DragonBallSuperHeroesTvShowArranger.getInstance());
        tvShowArrangers.add(DragonBallSuperTvShowArranger.getInstance());
        tvShowArrangers.add(BorutoTvShowArranger.getInstance());
        tvShowArrangers.add(OnePieceTvShowArranger.getInstance());
        tvShowArrangers.add(TorikoTvShowArranger.getInstance());
        tvShowArrangers.add(AttackOnTitanTvShowArranger.getInstance());
        tvShowArrangers.add(OverlordTvShowArranger.getInstance());
        tvShowArrangers.add(DragonBallTvShowArranger.getInstance());
        tvShowArrangers.add(SaintSeiyaTvShowArranger.getInstance());
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
            selectedTvShowArranger = GenericTvShowArranger.getInstance();

        }
        return selectedTvShowArranger;
    }
}

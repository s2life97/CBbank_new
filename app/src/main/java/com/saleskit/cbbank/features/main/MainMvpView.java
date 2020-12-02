package com.saleskit.cbbank.features.main;

import com.saleskit.cbbank.features.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showPokemon(List<String> pokemon);

    void showProgress(boolean show);

    void showError(Throwable error);
}

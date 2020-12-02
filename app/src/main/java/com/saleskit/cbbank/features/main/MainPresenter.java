package com.saleskit.cbbank.features.main;

import com.saleskit.cbbank.features.base.BasePresenter;
import com.saleskit.cbbank.injection.ConfigPersistent;

import javax.inject.Inject;

import com.saleskit.cbbank.data.DataManager;
import com.saleskit.cbbank.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getView().showProgress(true);
        dataManager
                .getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemons -> {
                            getView().showProgress(false);
                            getView().showPokemon(pokemons);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}

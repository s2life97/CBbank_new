package com.saleskit.cbbank.features.main;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import com.saleskit.cbbank.R;
import com.saleskit.cbbank.features.base.BaseActivity;
import com.saleskit.cbbank.features.common.ErrorView;
import com.saleskit.cbbank.injection.component.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView, ErrorView.ErrorListener {

    private static final int POKEMON_COUNT = 20;
    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.view_error)
    ErrorView errorView;

    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.recycler_pokemon)
    RecyclerView pokemonRecycler;

    @BindView(R.id.swipe_to_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);

        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.primary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> mainPresenter.getPokemon(POKEMON_COUNT));

        pokemonRecycler.setLayoutManager(new LinearLayoutManager(this));
//        pokemonRecycler.setAdapter(pokemonAdapter);
        errorView.setErrorListener(this);

        mainPresenter.getPokemon(POKEMON_COUNT);
    }



    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void showPokemon(List<String> pokemon) {

    }

    @Override
    public void showProgress(boolean show) {


    }

    @Override
    public void showError(Throwable error) {
        pokemonRecycler.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onReloadData() {
        mainPresenter.getPokemon(POKEMON_COUNT);
    }
}

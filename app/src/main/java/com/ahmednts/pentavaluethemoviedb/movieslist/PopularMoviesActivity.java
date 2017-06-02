package com.ahmednts.pentavaluethemoviedb.movieslist;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.R;
import com.ahmednts.pentavaluethemoviedb.data.ApiClient;
import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;
import com.ahmednts.pentavaluethemoviedb.moviedetails.MovieDetailsActivity;
import com.ahmednts.pentavaluethemoviedb.utils.UIUtils;

import java.util.List;

import javax.inject.Inject;

public class PopularMoviesActivity extends AppCompatActivity implements View, PopularMoviesAdapter.OnItemClickListener {
    private static final String TAG = PopularMoviesActivity.class.getSimpleName();

    @Inject
    ApiClient apiClient;

    private Presenter popularMoviesPresenter;

    private TextView toolbarTitle;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        App.getInstance().getAppComponent().inject(this);

        initUI();

        popularMoviesPresenter = new PopularMoviesPresenter(this, apiClient);
    }

    void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }

        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getResources().getString(R.string.popular_movies));

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        UIUtils.setProgressBarColor(this, progressBar, R.color.colorAccent);
        recyclerView = (RecyclerView) findViewById(R.id.popularMoviesRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        popularMoviesPresenter.loadPopularMovies();
    }

    @Override
    public void showIndicator() {
        recyclerView.setVisibility(android.view.View.GONE);
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideIndicator() {
        progressBar.setVisibility(android.view.View.GONE);
        recyclerView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showPopularMoviesList(List<PopularMovie> popularMovies) {
        PopularMoviesAdapter rcAdapter = new PopularMoviesAdapter(popularMovies, this);
        recyclerView.setAdapter(rcAdapter);
    }

    @Override
    public void showMovieDetails(int movieId) {
        MovieDetailsActivity.open(this, movieId);
    }

    @Override
    public void onClick(PopularMovie popularMovie) {
        popularMoviesPresenter.openMovieDetails(popularMovie);
    }
}

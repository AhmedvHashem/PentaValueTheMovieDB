package com.ahmednts.pentavaluethemoviedb.movieslist;

import android.support.annotation.NonNull;

import com.ahmednts.pentavaluethemoviedb.data.ApiClient;
import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;
import com.ahmednts.pentavaluethemoviedb.utils.Logger;

/**
 * Created by AhmedNTS on 6/1/2017.
 */
public class PopularMoviesPresenter implements Presenter {
    private static final String TAG = PopularMoviesPresenter.class.getSimpleName();

    private final View mPopularMoviesView;
    private final ApiClient mApiClient;

    public PopularMoviesPresenter(@NonNull View popularMoviesView, @NonNull ApiClient apiClient) {
        mPopularMoviesView = popularMoviesView;
        mApiClient = apiClient;
    }

    @Override
    public void loadPopularMovies() {
        mPopularMoviesView.showIndicator();

        mApiClient.getPopularMovies().subscribe(popularMoviesResponse -> {
           Logger.withTag(TAG).log(popularMoviesResponse.toString());

            mPopularMoviesView.hideIndicator();
            mPopularMoviesView.showPopularMoviesList(popularMoviesResponse.results);
        });
    }

    @Override
    public void openMovieDetails(PopularMovie popularMovie) {
        mPopularMoviesView.showMovieDetails(popularMovie.getId());
    }
}

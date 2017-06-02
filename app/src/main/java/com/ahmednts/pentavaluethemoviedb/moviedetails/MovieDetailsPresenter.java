package com.ahmednts.pentavaluethemoviedb.moviedetails;

import android.support.annotation.NonNull;

import com.ahmednts.pentavaluethemoviedb.data.ApiClient;
import com.ahmednts.pentavaluethemoviedb.utils.Logger;

/**
 * Created by AhmedNTS on 6/1/2017.
 */
public class MovieDetailsPresenter implements Presenter {
    private static final String TAG = MovieDetailsPresenter.class.getSimpleName();

    private final View mMovieDetailsView;
    private final ApiClient mApiClient;

    public MovieDetailsPresenter(@NonNull View movieDetailsView, @NonNull ApiClient apiClient) {
        mMovieDetailsView = movieDetailsView;
        mApiClient = apiClient;
    }

    @Override
    public void loadMovieDetails(int movieId) {
        mMovieDetailsView.showIndicator();

        mApiClient.getMovieDetails(movieId).subscribe(movie -> {
            Logger.withTag(TAG).log(movie.toString());

            mMovieDetailsView.hideIndicator();
            mMovieDetailsView.showMovieDetails(movie);
        });
    }
}

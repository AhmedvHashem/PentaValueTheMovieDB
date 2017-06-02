package com.ahmednts.pentavaluethemoviedb.movieslist;

import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;

/**
 * Created by AhmedNTS on 6/1/2017.
 */

public interface Presenter {
    void loadPopularMovies();
    void openMovieDetails(PopularMovie popularMovie);
}

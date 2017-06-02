package com.ahmednts.pentavaluethemoviedb.moviedetails;

import com.ahmednts.pentavaluethemoviedb.data.models.Movie;
import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;

import java.util.List;

/**
 * Created by AhmedNTS on 6/1/2017.
 */
public interface View {
    void showIndicator();
    void hideIndicator();
    void showMovieDetails(Movie movie);
}

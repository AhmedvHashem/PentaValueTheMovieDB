package com.ahmednts.pentavaluethemoviedb.di;

import com.ahmednts.pentavaluethemoviedb.moviedetails.MovieDetailsActivity;
import com.ahmednts.pentavaluethemoviedb.movieslist.PopularMoviesActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AhmedNTS on 2016-12-11.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(PopularMoviesActivity activity);

    void inject(MovieDetailsActivity activity);
}

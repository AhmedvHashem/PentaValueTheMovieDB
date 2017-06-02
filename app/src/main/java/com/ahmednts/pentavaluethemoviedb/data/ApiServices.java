package com.ahmednts.pentavaluethemoviedb.data;

import com.ahmednts.pentavaluethemoviedb.data.models.Movie;
import com.ahmednts.pentavaluethemoviedb.data.responses.PopularMoviesResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by AhmedNTS on 6/1/2017.
 */
public interface ApiServices {

    @GET("movie/popular")
    Observable<PopularMoviesResponse> popularMovies(@Query("api_key") String apiKey);

    @GET("movie/{movieId}")
    Observable<Movie> movieDetails(@Path("movieId") int movieId, @Query("api_key") String apiKey);
}

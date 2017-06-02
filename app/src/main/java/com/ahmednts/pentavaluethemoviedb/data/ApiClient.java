package com.ahmednts.pentavaluethemoviedb.data;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.data.responses.PopularMoviesResponse;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private ApiServices apiServices;

    public ApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(App.BASE_URL)
                .build();

        apiServices = retrofit.create(ApiServices.class);
    }

    public Observable<PopularMoviesResponse> getPopularMovies() {
        return apiServices.popularMovies(App.API_KEY);
    }
}

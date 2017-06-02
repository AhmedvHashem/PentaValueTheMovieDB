package com.ahmednts.pentavaluethemoviedb;

import android.app.Application;

import com.ahmednts.pentavaluethemoviedb.di.AppComponent;
import com.ahmednts.pentavaluethemoviedb.di.AppModule;
import com.ahmednts.pentavaluethemoviedb.di.DaggerAppComponent;

/**
 * Created by AhmedNTS on 2016-12-11.
 */
public class App extends Application {
    public static final String API_KEY = "88a1a66dae742b640c93333a899cca36";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGES_BASE_URL = "https://image.tmdb.org/t/p/w500/";

    private static volatile App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}

package com.ahmednts.pentavaluethemoviedb;

import android.app.Application;

import com.ahmednts.pentavaluethemoviedb.di.AppComponent;
import com.ahmednts.pentavaluethemoviedb.di.AppModule;
import com.ahmednts.pentavaluethemoviedb.di.DaggerAppComponent;

/**
 * Created by AhmedNTS on 2016-12-11.
 */
public class App extends Application {

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

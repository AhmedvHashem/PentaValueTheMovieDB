package com.ahmednts.pentavaluethemoviedb.movieslist;

import android.app.Application;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.R;

import javax.inject.Inject;

public class PopularMoviesActivity extends AppCompatActivity {
    private static final String TAG = PopularMoviesActivity.class.getSimpleName();

    @Inject
    Application app;

    @Inject
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);

        App.getInstance().getAppComponent().inject(this);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", "Vivantor");
        editor.apply();

        Log.d(TAG, sharedPreferences.getString("Username", ""));

    }
}

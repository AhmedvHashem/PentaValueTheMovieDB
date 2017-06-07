package com.ahmednts.pentavaluethemoviedb.moviedetails;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.R;
import com.ahmednts.pentavaluethemoviedb.data.ApiClient;
import com.ahmednts.pentavaluethemoviedb.data.models.Movie;
import com.ahmednts.pentavaluethemoviedb.utils.UIUtils;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import javax.inject.Inject;

public class MovieDetailsActivity extends AppCompatActivity implements View {
    private static final String TAG = MovieDetailsActivity.class.getSimpleName();
    public static final String EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID";

    @Inject
    ApiClient apiClient;

    private Presenter movieDetailsPresenter;

//    private TextView toolbarTitle;

    private ProgressBar progressBar,imageProgressBar;
    private android.view.View movieDetails;
    private TextView movieName;
    private ImageView moviePoster;
    private TextView movieReleaseDate;
    private RatingBar movieRating;
    private TextView movieOverview;

    private int movieId;

    public static void open(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE_ID, movieId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        App.getInstance().getAppComponent().inject(this);

        movieId = getIntent().getIntExtra(EXTRA_MOVIE_ID, 0);
        if (movieId == 0) {
            finish();
            return;
        }

        initUI();

        movieDetailsPresenter = new MovieDetailsPresenter(this, apiClient);
        movieDetailsPresenter.loadMovieDetails(movieId);
    }

    void initUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setTitle("");
        }

//        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
//        toolbarTitle.setText("");

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        UIUtils.setProgressBarColor(this, progressBar, R.color.colorAccent);
        imageProgressBar = (ProgressBar) findViewById(R.id.imageProgressBar);
        UIUtils.setProgressBarColor(this, imageProgressBar, R.color.colorAccent);
        movieDetails = findViewById(R.id.movieDetails);
        movieName = (TextView) findViewById(R.id.movieName);
        moviePoster = (ImageView) findViewById(R.id.moviePoster);
        movieReleaseDate = (TextView) findViewById(R.id.movieReleaseDate);
        movieRating = (RatingBar) findViewById(R.id.movieRating);
        movieOverview = (TextView) findViewById(R.id.movieOverview);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void showIndicator() {
        movieDetails.setVisibility(android.view.View.GONE);
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideIndicator() {
        progressBar.setVisibility(android.view.View.GONE);
        movieDetails.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showMovieDetails(Movie movie) {
        getSupportActionBar().setTitle(movie.getOriginalTitle());
        movieName.setText(movie.getOriginalTitle());

        Picasso.with(this).load(App.IMAGES_BASE_URL + movie.getPosterImage())
                .placeholder(R.drawable.dr_loading)
                .error(R.drawable.icon)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageProgressBar.setVisibility(android.view.View.GONE);
                        moviePoster.setVisibility(android.view.View.VISIBLE);
                        moviePoster.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {
                        imageProgressBar.setVisibility(android.view.View.VISIBLE);
                        moviePoster.setVisibility(android.view.View.INVISIBLE);
                    }
                });

        String releaseDate = "Release Date: " + movie.getReleaseDate();
        movieReleaseDate.setText(releaseDate);

        movieRating.setRating((float) (movie.getVoteAverage() / 2));

        movieOverview.setText(movie.getOverview());
    }
}

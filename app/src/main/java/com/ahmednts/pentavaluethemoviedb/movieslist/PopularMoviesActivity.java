package com.ahmednts.pentavaluethemoviedb.movieslist;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.R;
import com.ahmednts.pentavaluethemoviedb.data.ApiClient;
import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;
import com.ahmednts.pentavaluethemoviedb.moviedetails.MovieDetailsActivity;
import com.ahmednts.pentavaluethemoviedb.utils.UIUtils;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class PopularMoviesActivity extends AppCompatActivity implements View {
    private static final String TAG = PopularMoviesActivity.class.getSimpleName();

    @Inject
    ApiClient apiClient;

    private Presenter popularMoviesPresenter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popular_movies);
        App.getInstance().getAppComponent().inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        TextView toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.popular_movies));

        popularMoviesPresenter = new PopularMoviesPresenter(this, apiClient);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        UIUtils.setProgressBarColor(this, progressBar, R.color.colorAccent);
        recyclerView = (RecyclerView) findViewById(R.id.popularMoviesRecyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        popularMoviesPresenter.loadPopularMovies();
    }

    @Override
    public void showIndicator() {
        recyclerView.setVisibility(android.view.View.GONE);
        progressBar.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void hideIndicator() {
        progressBar.setVisibility(android.view.View.GONE);
        recyclerView.setVisibility(android.view.View.VISIBLE);
    }

    @Override
    public void showPopularMoviesList(List<PopularMovie> popularMovies) {
        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(popularMovies);
        recyclerView.setAdapter(rcAdapter);
    }

    @Override
    public void showMovieDetails(int movieId) {
        MovieDetailsActivity.open(this, movieId);
    }

    private static class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.PopularMovieViewHolder> {

        private List<PopularMovie> itemList;

        RecyclerViewAdapter(List<PopularMovie> itemList) {
            this.itemList = itemList;
        }

        @Override
        public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            android.view.View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_popular_movie, null);
            return new PopularMovieViewHolder(layoutView);
        }

        @Override
        public void onBindViewHolder(PopularMovieViewHolder holder, int position) {
            PopularMovie popularMovie = itemList.get(position);
            holder.movieName.setText(popularMovie.getTitle());

            Picasso.with(holder.itemView.getContext()).load(App.IMAGES_BASE_URL + popularMovie.getPoster_path()).fit().centerCrop().into(holder.moviePoster);
        }

        @Override
        public int getItemCount() {
            return this.itemList.size();
        }

        static class PopularMovieViewHolder extends RecyclerView.ViewHolder implements android.view.View.OnClickListener {

            TextView movieName;
            ImageView moviePoster;

            PopularMovieViewHolder(android.view.View itemView) {
                super(itemView);
                movieName = (TextView) itemView.findViewById(R.id.movieName);
                moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);

                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(android.view.View view) {
                Toast.makeText(view.getContext(), "Clicked Country Position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

package com.ahmednts.pentavaluethemoviedb.movieslist;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.R;
import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;
import com.ahmednts.pentavaluethemoviedb.utils.UIUtils;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

/**
 * Created by AhmedNTS on 6/2/2017.
 */
public class PopularMoviesAdapter extends RecyclerView.Adapter<PopularMoviesAdapter.PopularMovieViewHolder> {

    private List<PopularMovie> itemList;
    private OnItemClickListener onItemClickListener;

    PopularMoviesAdapter(List<PopularMovie> itemList, OnItemClickListener onItemClickListener) {
        this.itemList = itemList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public PopularMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        android.view.View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_popular_movie, null);
        return new PopularMovieViewHolder(layoutView, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(PopularMovieViewHolder holder, int position) {
        holder.onBindView(this, position);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    static class PopularMovieViewHolder extends RecyclerView.ViewHolder {

        TextView movieName;
        ImageView moviePoster;
        private ProgressBar progressBar;

        private OnItemClickListener onItemClickListener;

        PopularMovieViewHolder(android.view.View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progressBar);
            UIUtils.setProgressBarColor(itemView.getContext(), progressBar, R.color.colorAccent);

            this.onItemClickListener = onItemClickListener;
        }

        void onBindView(PopularMoviesAdapter adapter, int position) {
            PopularMovie popularMovie = adapter.itemList.get(position);
            movieName.setText(popularMovie.getTitle());

            Picasso.with(itemView.getContext())
                    .load(App.IMAGES_BASE_URL + popularMovie.getPoster_path())
                    .placeholder(R.drawable.dr_loading)
                    .error(R.drawable.icon)
                    .into(target);

            itemView.setOnClickListener(v -> onItemClickListener.onClick(popularMovie));
        }

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                progressBar.setVisibility(View.GONE);
                moviePoster.setVisibility(View.VISIBLE);
                moviePoster.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                progressBar.setVisibility(View.VISIBLE);
                moviePoster.setVisibility(View.INVISIBLE);
            }
        };
    }

    interface OnItemClickListener {
        void onClick(PopularMovie popularMovie);
    }
}

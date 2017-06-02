package com.ahmednts.pentavaluethemoviedb.movieslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmednts.pentavaluethemoviedb.App;
import com.ahmednts.pentavaluethemoviedb.R;
import com.ahmednts.pentavaluethemoviedb.data.models.PopularMovie;
import com.squareup.picasso.Picasso;

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

        private OnItemClickListener onItemClickListener;

        PopularMovieViewHolder(android.view.View itemView, OnItemClickListener onItemClickListener) {
            super(itemView);
            movieName = (TextView) itemView.findViewById(R.id.movieName);
            moviePoster = (ImageView) itemView.findViewById(R.id.moviePoster);
            this.onItemClickListener = onItemClickListener;
        }

        void onBindView(PopularMoviesAdapter adapter, int position) {
            PopularMovie popularMovie = adapter.itemList.get(position);
            movieName.setText(popularMovie.getTitle());

            Picasso.with(itemView.getContext()).load(App.IMAGES_BASE_URL + popularMovie.getPoster_path()).fit().centerCrop().into(moviePoster);

            itemView.setOnClickListener(v -> onItemClickListener.onClick(popularMovie));
        }
    }

    interface OnItemClickListener {
        void onClick(PopularMovie popularMovie);
    }
}

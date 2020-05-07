package com.dicoding.moviecatalogmade.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.model.Movie;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFavoriteAdapter extends ListAdapter<Movie, MovieFavoriteAdapter.MovieFavoriteHolder> {

    private OnItemClickListener listener;
    private OnDeleteClickListener deleteListener;

    public MovieFavoriteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Movie> DIFF_CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getUid() == newItem.getUid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getOverview().equals(newItem.getOverview());
        }
    };

    @NonNull
    @Override
    public MovieFavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new MovieFavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieFavoriteHolder holder, int position) {
        Movie movie = getItem(position);
        String urlPoster = BuildConfig.API_POSTER_PATH + movie.getPoster();

        holder.tvTitle.setText(movie.getTitle());
        holder.tvReleased.setText(movie.getRelease_date());
        holder.tvOverview.setText(movie.getOverview());
        Glide.with(holder.itemView.getContext())
                .load(urlPoster)
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPoster);
    }

    class MovieFavoriteHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_movie_poster)
        ImageView imgPoster;
        @BindView(R.id.tv_movie_title)
        TextView tvTitle;
        @BindView(R.id.tv_movie_released)
        TextView tvReleased;
        @BindView(R.id.tv_movie_overview)
        TextView tvOverview;
        @BindView(R.id.iv_delete)
        ImageView ivDelete;

        private MovieFavoriteHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (deleteListener != null && position != RecyclerView.NO_POSITION) {
                        deleteListener.onDeleteClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Movie movie);
    }

    public interface OnDeleteClickListener {
        void onDeleteClick(Movie movie);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteListener = listener;
    }
}

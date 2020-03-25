package com.dicoding.moviecatalogmade.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.activity.DetailMovieActivity;
import com.dicoding.moviecatalogmade.activity.DetailTvShowActivity;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.TvShow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFavoriteAdapter extends RecyclerView.Adapter<TvShowFavoriteAdapter.CardViewViewHolder>{

    private Context context;
    private List<TvShow> mData = new ArrayList<>();

    public TvShowFavoriteAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<TvShow> tvShows) {
        this.mData = tvShows;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        TvShow data = mData.get(position);
        String urlPoster = BuildConfig.API_POSTER_PATH + data.getPoster();

        holder.tvTitle.setText(data.getTitle());
        holder.tvReleased.setText(data.getRelease_date());
        holder.tvOverview.setText(data.getOverview());
        Glide.with(holder.itemView.getContext())
                .load(urlPoster)
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.iv_movie_poster)
        ImageView imgPoster;
        @BindView(R.id.tv_movie_title)
        TextView tvTitle;
        @BindView(R.id.tv_movie_released)
        TextView tvReleased;
        @BindView(R.id.tv_movie_overview)
        TextView tvOverview;

        private CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TvShow data = getTvShows().get(position);

            Intent intent = new Intent(context, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, data);
            intent.putExtra(DetailTvShowActivity.EXTRA_FROM, "tv_show_favorite");
            context.startActivity(intent);
        }
    }

    private List<TvShow> getTvShows() {
        return mData;
    }
}

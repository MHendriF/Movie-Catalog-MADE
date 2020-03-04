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
import com.dicoding.moviecatalogmade.DetailTvShowActivity;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.model.TvShow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<TvShow> mData = new ArrayList<>();

    public void setData(ArrayList<TvShow> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public TvShowAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public TvShowAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_show, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowAdapter.CardViewViewHolder holder, int position) {
        TvShow tvShow = mData.get(position);
        String urlPoster = BuildConfig.API_POSTER_PATH + tvShow.getPoster();

        holder.tvTitle.setText(tvShow.getTitle());
        holder.tvReleased.setText(tvShow.getRelease_date());
        holder.tvOverview.setText(tvShow.getOverview());
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
            TvShow tvShow = getTvShows().get(position);

            Intent intent = new Intent(context, DetailTvShowActivity.class);
            intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow);
            context.startActivity(intent);
        }
    }

    private ArrayList<TvShow> getTvShows() {
        return mData;
    }
}

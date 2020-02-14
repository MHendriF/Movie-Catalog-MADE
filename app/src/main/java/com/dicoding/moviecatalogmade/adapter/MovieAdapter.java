package com.dicoding.moviecatalogmade.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.DetailMovieActivity;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Movie movie = movies.get(position);

        holder.txtName.setText(movie.getTitle());
        holder.txtRilis.setText(movie.getRelease_date());
        holder.txtDescription.setText(movie.getOverview());
        Glide.with(holder.itemView.getContext())
                .load(movie.getPoster())
                .apply(new RequestOptions().override(200, 300))
                .into(holder.imgPoster);

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtName;
        TextView txtRilis;
        TextView txtDescription;
        ImageView imgPoster;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.tv_movie_name);
            txtRilis = itemView.findViewById(R.id.tv_movie_rilis);
            txtDescription = itemView.findViewById(R.id.tv_movie_description);
            imgPoster = itemView.findViewById(R.id.iv_movie_poster);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = getMovies().get(position);

            Intent intent = new Intent(context, DetailMovieActivity.class);
            intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
            context.startActivity(intent);
        }
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }
}

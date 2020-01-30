package com.dicoding.moviecatalogmade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.model.Movie;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context mcontext;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies){
        this.mcontext = context;
        this.movies = movies;
    }

    public void setListMovie(ArrayList<Movie> listMovie) {
        this.movies = listMovie;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup parent) {
        if (itemView == null){
            itemView = LayoutInflater.from(mcontext).inflate(R.layout.item_movie, parent, false);
        }
        ViewHolder viewHolder = new  ViewHolder(itemView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);

        return itemView;
    }

    private class ViewHolder {
        private TextView txtName;
        private TextView txtRilis;
        private TextView txtDescription;
        private ImageView imgPoster;
        ViewHolder(View view) {
            txtName = view.findViewById(R.id.tv_movie_name);
            txtRilis = view.findViewById(R.id.tv_movie_rilis);
            txtDescription = view.findViewById(R.id.tv_movie_description);
            imgPoster = view.findViewById(R.id.iv_movie_poster);
        }
        void bind(Movie movie) {
            txtName.setText(movie.getJudul());
            txtRilis.setText(movie.getTayang());
            txtDescription.setText(movie.getDescription());
            imgPoster.setImageResource(movie.getPoster());
        }
    }

}

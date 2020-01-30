package com.dicoding.moviecatalogmade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dicoding.moviecatalogmade.adapter.MovieAdapter;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.MovieData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter movieAdapter;
    private ArrayList<Movie> movies;
    private String title = "Movie Catalog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle(title);

        ListView listMovie = findViewById(R.id.lv_movie);

        movies = MovieData.getListData();

        movieAdapter = new MovieAdapter(this, movies);
        listMovie.setAdapter(movieAdapter);

        listMovie.setOnItemClickListener(listener);
    }

    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            Intent detailIntent = new Intent(MainActivity.this, DetailMovieActivity.class);
            detailIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movies.get(position));
            startActivity(detailIntent);
        }
    };

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

}

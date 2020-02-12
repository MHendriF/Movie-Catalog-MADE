package com.dicoding.moviecatalogmade.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.MovieAdapter;
import com.dicoding.moviecatalogmade.adapter.TvShowAdapter;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.MovieData;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.model.TvShowData;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private RecyclerView rvTvShow;
    private ArrayList<TvShow> tvShows = new ArrayList<>();
    TvShowAdapter tvShowAdapter;

    private RecyclerView rvMovie;
    private ArrayList<Movie> movies = new ArrayList<>();
    MovieAdapter movieAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);
        tvShows.addAll(TvShowData.getListData());

        Log.d("Trace", String.valueOf(tvShows));

        showRecycleList();

//        View view = inflater.inflate(R.layout.fragment_movie, container, false);
//        rvMovie = view.findViewById(R.id.rv_movie);
//        rvMovie.setHasFixedSize(true);
//        movies.addAll(MovieData.getListData());
//        showRecycleList2();

        Log.d("Trace TvShowFragment", "onCreateView: ");
        return view;
    }

    private void showRecycleList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShowAdapter = new TvShowAdapter(getContext(), tvShows);
        rvTvShow.setAdapter(tvShowAdapter);
    }

    private void showRecycleList2() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        movieAdapter = new MovieAdapter(getContext(), movies);
        rvMovie.setAdapter(movieAdapter);
    }

}

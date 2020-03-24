package com.dicoding.moviecatalogmade.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.MovieAdapter;
import com.dicoding.moviecatalogmade.adapter.MovieFavoriteAdapter;
import com.dicoding.moviecatalogmade.database.MovieDAO;
import com.dicoding.moviecatalogmade.database.MovieRoomDatabase;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {

    private RecyclerView rvMovie;
    private MovieFavoriteAdapter mAdapter;
    private ProgressBar progressBar;
    private MovieViewModel movieViewModel;

    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBar);
        showRecycleList();
    }

    private void showRecycleList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new MovieFavoriteAdapter(getActivity());
        rvMovie.setAdapter(mAdapter);

//        ArrayList<Movie> data = (ArrayList<Movie>) loadFavMovies();
//        Log.d("Trace", "size: "+data.size());
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);

//        if(getActivity() != null){
//            movieViewModel.setFavMovie(data);
//            movieViewModel.getMovies().observe(getActivity(), getMovie);
//            showLoading(true);
//        }

        if(getActivity() != null){
            movieViewModel.getMoviesFav().observe(getActivity(), getMovie);
            movieViewModel.setMovies(getActivity());
            showLoading(true);
        }
    }

    private Observer<List<Movie>> getMovie = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            if (movies != null) {
                mAdapter.setData(movies);
            }
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}

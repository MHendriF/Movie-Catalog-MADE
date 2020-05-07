package com.dicoding.moviecatalogmade.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.MovieAdapter;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.viewmodel.MovieViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView rvMovie;
    private MovieAdapter adapter;
    private ShimmerFrameLayout mShimmer;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvMovie = view.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);
        mShimmer = view.findViewById(R.id.shimmer_view_container);
        showRecycleList();
    }

    private void showRecycleList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(getContext());
        rvMovie.setAdapter(adapter);

        MovieViewModel movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        if(getActivity() != null){
            movieViewModel.getMovies().observe(getActivity(), getAllData);
            movieViewModel.setMovies();
            showLoading(true);
        }
    }

    private Observer<ArrayList<Movie>> getAllData = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setData(movies);
            }
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            mShimmer.startShimmer();
        } else {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
        }
    }
}

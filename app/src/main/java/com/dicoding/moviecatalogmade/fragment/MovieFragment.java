package com.dicoding.moviecatalogmade.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.MovieAdapter;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.presenter.MoviePresenter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements MoviePresenter.View {

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

        MoviePresenter moviePresenter = new MoviePresenter(this);
        moviePresenter.getMovies();
        showRecycleList();
    }

    private void showRecycleList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MovieAdapter(getContext());
        rvMovie.setAdapter(adapter);
    }

    @Override
    public void loadData(ArrayList<Movie> movies) {
        showLoading(false);
        adapter.setData(movies);
    }

    @Override
    public void showLoading(Boolean state) {
        if (state) {
            mShimmer.startShimmer();
        } else {
            mShimmer.stopShimmer();
            mShimmer.setVisibility(View.GONE);
        }
    }
}

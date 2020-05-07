package com.dicoding.moviecatalogmade.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.activity.DetailMovieActivity;
import com.dicoding.moviecatalogmade.adapter.MovieFavoriteAdapter;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.viewmodel.MovieFavoriteViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {

    private MovieFavoriteViewModel viewModel;
    private RecyclerView rvMovie;
    private MovieFavoriteAdapter adapter;
    private ShimmerFrameLayout mShimmer;

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
        mShimmer = view.findViewById(R.id.shimmer_view_container);
        showRecycleList();
    }

    private void showRecycleList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MovieFavoriteAdapter();
        rvMovie.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MovieFavoriteViewModel.class);
        if(getActivity() != null){
            viewModel.getMoviesFavorite().observe(getActivity(), getAllData);
            showLoading(true);
        }

        adapter.setOnItemClickListener(new MovieFavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                Intent intent = new Intent(getActivity(), DetailMovieActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetailMovieActivity.EXTRA_MOVIE, movie);
                bundle.putString(DetailMovieActivity.EXTRA_FROM, "movie_favorite");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter.setOnDeleteClickListener(new MovieFavoriteAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(Movie movie) {
                viewModel.delete(movie);
                Toast.makeText(getActivity(), "Favorite item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Observer<List<Movie>> getAllData = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            if (movies != null) {
                adapter.submitList(movies);
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

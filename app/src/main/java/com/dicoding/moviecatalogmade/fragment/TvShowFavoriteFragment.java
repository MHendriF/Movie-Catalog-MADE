package com.dicoding.moviecatalogmade.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.TvShowFavoriteAdapter;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.viewmodel.TvShowFavoriteViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {

    private RecyclerView rvTvShow;
    private TvShowFavoriteAdapter adapter;
    private ProgressBar progressBar;

    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);
        progressBar = view.findViewById(R.id.progressBar);
        showRecycleList();
    }

    private void showRecycleList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TvShowFavoriteAdapter(getContext());
        rvTvShow.setAdapter(adapter);

        TvShowFavoriteViewModel viewModel = ViewModelProviders.of(this).get(TvShowFavoriteViewModel.class);
        if(getActivity() != null){
            viewModel.getAllData().observe(getActivity(), getAllData);
            showLoading(true);
        }

    }

    private Observer<List<TvShow>> getAllData = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(List<TvShow> tvShows) {
            if (tvShows != null) {
                adapter.setData(tvShows);
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

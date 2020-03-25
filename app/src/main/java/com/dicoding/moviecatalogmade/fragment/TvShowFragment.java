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
import com.dicoding.moviecatalogmade.adapter.TvShowAdapter;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.viewmodel.TvShowViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private RecyclerView rvTvShow;
    private TvShowAdapter tvShowAdapter;
    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
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
        tvShowAdapter = new TvShowAdapter(getContext());
        rvTvShow.setAdapter(tvShowAdapter);

        TvShowViewModel viewModel = ViewModelProviders.of(this).get(TvShowViewModel.class);
        if(getActivity() != null){
            viewModel.getTvShows().observe(getActivity(), getTvShow);
            viewModel.setTvShows();
            showLoading(true);
        }

    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            if (tvShows != null) {
                tvShowAdapter.setData(tvShows);
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

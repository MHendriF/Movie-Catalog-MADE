package com.dicoding.moviecatalogmade.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    private TvShowAdapter tvShowAdapter;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTvShow = view.findViewById(R.id.rv_tv_show);
        rvTvShow.setHasFixedSize(true);
        tvShows.addAll(TvShowData.getListData());
        showRecycleList();
    }

    private void showRecycleList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        tvShowAdapter = new TvShowAdapter(getContext(), tvShows);
        rvTvShow.setAdapter(tvShowAdapter);
    }

}

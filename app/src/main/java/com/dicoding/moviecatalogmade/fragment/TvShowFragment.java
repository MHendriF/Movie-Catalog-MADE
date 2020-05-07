package com.dicoding.moviecatalogmade.fragment;

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

import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.TvShowAdapter;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.viewmodel.TvShowViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    private RecyclerView rvTvShow;
    private TvShowAdapter adapter;
    private ShimmerFrameLayout mShimmer;

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
        mShimmer = view.findViewById(R.id.shimmer_view_container);
        showRecycleList();
    }

    private void showRecycleList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TvShowAdapter(getContext());
        rvTvShow.setAdapter(adapter);

        TvShowViewModel viewModel = new ViewModelProvider(this).get(TvShowViewModel.class);
        if(getActivity() != null){
            viewModel.getTvShows().observe(getActivity(), getAllData);
            viewModel.setTvShows();
            showLoading(true);
        }
    }

    private Observer<ArrayList<TvShow>> getAllData = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(ArrayList<TvShow> tvShows) {
            if (tvShows != null) {
                adapter.setData(tvShows);
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

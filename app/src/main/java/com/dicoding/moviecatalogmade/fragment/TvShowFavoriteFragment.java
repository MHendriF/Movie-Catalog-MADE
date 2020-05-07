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
import com.dicoding.moviecatalogmade.activity.DetailTvShowActivity;
import com.dicoding.moviecatalogmade.adapter.TvShowFavoriteAdapter;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.viewmodel.TvShowFavoriteViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {

    private TvShowFavoriteViewModel viewModel;
    private RecyclerView rvTvShow;
    private TvShowFavoriteAdapter adapter;
    private ShimmerFrameLayout mShimmer;

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
        mShimmer = view.findViewById(R.id.shimmer_view_container);
        showRecycleList();
    }

    private void showRecycleList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TvShowFavoriteAdapter();
        rvTvShow.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(TvShowFavoriteViewModel.class);
        if(getActivity() != null){
            viewModel.getAllData().observe(getActivity(), getAllData);
            showLoading(true);
        }

        adapter.setOnItemClickListener(new TvShowFavoriteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(TvShow tvShow) {
                Intent intent = new Intent(getActivity(), DetailTvShowActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow);
                bundle.putString(DetailTvShowActivity.EXTRA_FROM, "tv_show");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter.setOnDeleteClickListener(new TvShowFavoriteAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(TvShow tvShow) {
                viewModel.delete(tvShow);
                Toast.makeText(getActivity(), "Favorite item deleted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Observer<List<TvShow>> getAllData = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(List<TvShow> tvShows) {
            if (tvShows != null) {
                adapter.submitList(tvShows);
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

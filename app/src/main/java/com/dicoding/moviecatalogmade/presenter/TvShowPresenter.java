package com.dicoding.moviecatalogmade.presenter;

import android.util.Log;

import com.dicoding.moviecatalogmade.activity.MainActivity;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.model.networking.TvShowResponse;
import com.dicoding.moviecatalogmade.networking.ApiClient;
import com.dicoding.moviecatalogmade.networking.ApiInterface;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TvShowPresenter {
    private View view;

    public TvShowPresenter(View view) {
        this.view = view;
    }

    public void getTvShows() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TvShowResponse> call = apiService.getTvShows("tv");

        call.enqueue(new Callback<TvShowResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@EverythingIsNonNull Call<TvShowResponse> call, @EverythingIsNonNull Response<TvShowResponse> response) {
                try {
                    if (response.body() != null){
                        ArrayList<TvShow> tvShows = response.body().getResults();
                        view.loadData(tvShows);
                    }

                } catch (Exception e) {
                    Log.e(MainActivity.class.getSimpleName(), Objects.requireNonNull(e.getLocalizedMessage()));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(@EverythingIsNonNull Call<TvShowResponse> call, @EverythingIsNonNull Throwable t) {
                Log.e(MainActivity.class.getSimpleName(), Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    public interface View{
        void loadData(ArrayList<TvShow> tvShows);
        void showLoading(Boolean state);
    }
}

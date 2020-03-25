package com.dicoding.moviecatalogmade.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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

public class TvShowViewModel extends ViewModel {

    private MutableLiveData<ArrayList<TvShow>> listTvShows = new MutableLiveData<>();

    public void setTvShows() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TvShowResponse> call = apiService.getTvShows("tv");

        call.enqueue(new Callback<TvShowResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@EverythingIsNonNull Call<TvShowResponse> call, @EverythingIsNonNull Response<TvShowResponse> response) {
                try {
                    assert response.body() != null;
                    ArrayList<TvShow> tvShows = response.body().getResults();
                    listTvShows.postValue(tvShows);
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

    public LiveData<ArrayList<TvShow>> getTvShows() {
        return listTvShows;
    }
}

package com.dicoding.moviecatalogmade.viewmodel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogmade.activity.MainActivity;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.networking.MovieResponse;
import com.dicoding.moviecatalogmade.networking.ApiClient;
import com.dicoding.moviecatalogmade.networking.ApiInterface;
import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();

    public void setMovies() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getMovies("movie");

        call.enqueue(new Callback<MovieResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(@EverythingIsNonNull Call<MovieResponse> call, @EverythingIsNonNull Response<MovieResponse> response) {
                try {
                    assert response.body() != null;
                    ArrayList<Movie> movies = response.body().getResults();
                    movieList.postValue(movies);
                } catch (Exception e) {
                    Log.e(MainActivity.class.getSimpleName(), Objects.requireNonNull(e.getLocalizedMessage()));
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(@EverythingIsNonNull Call<MovieResponse> call, @EverythingIsNonNull Throwable t) {
                Log.e(MainActivity.class.getSimpleName(), Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return movieList;
    }
}

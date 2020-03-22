package com.dicoding.moviecatalogmade.networking;

import com.dicoding.moviecatalogmade.model.networking.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("discover/{type}")
    Call<MovieResponse> getMovies(@Path("type") String movieType);
}

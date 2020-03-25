package com.dicoding.moviecatalogmade.networking;

import com.dicoding.moviecatalogmade.model.networking.MovieResponse;
import com.dicoding.moviecatalogmade.model.networking.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("discover/{type}")
    Call<MovieResponse> getMovies(@Path("type") String movieType);

    @GET("discover/{type}")
    Call<TvShowResponse> getTvShows(@Path("type") String movieType);
}

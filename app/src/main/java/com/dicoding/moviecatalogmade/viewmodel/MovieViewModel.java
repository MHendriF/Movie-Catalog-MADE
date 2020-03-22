package com.dicoding.moviecatalogmade.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.activity.MainActivity;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.Movie2;
import com.dicoding.moviecatalogmade.model.networking.MovieResponse;
import com.dicoding.moviecatalogmade.networking.ApiClient;
import com.dicoding.moviecatalogmade.networking.ApiInterface;
import com.dicoding.moviecatalogmade.repository.MovieRepository;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

//    private LiveData<ArrayList<Movie2>> movies;
//    private MovieRepository movieRepository;
//
//    public MovieViewModel(Application application){
//        super(application);
//        movieRepository = new MovieRepository(application);
//        movies = movieRepository.getAllMovies();
//    }

    public void setMovies(final Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getMovies("movie");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                try {
                    ArrayList<Movie> movies = response.body().getResults();
                    listMovies.postValue(movies);
                } catch (Exception e) {
                    Log.d(MainActivity.class.getSimpleName(), e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(MainActivity.class.getSimpleName(), t.getLocalizedMessage());
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }
//
//    public void insert(Movie2 movie) {
//        movieRepository.insert(movie);
//    }
}

package com.dicoding.moviecatalogmade.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogmade.activity.DetailMovieActivity;
import com.dicoding.moviecatalogmade.activity.MainActivity;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.networking.MovieResponse;
import com.dicoding.moviecatalogmade.networking.ApiClient;
import com.dicoding.moviecatalogmade.networking.ApiInterface;
import com.dicoding.moviecatalogmade.repository.MovieRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> movies;
    private MovieRepository movieRepository;
    private MutableLiveData<ArrayList<Movie>> movieList = new MutableLiveData<>();
    private static final String TAG="Trace "+ MovieViewModel.class.getSimpleName();

    public MovieViewModel(Application application){
        super(application);
        movieRepository = new MovieRepository(application);
        movies = movieRepository.getAllMovies();
    }

    public void setMovies(final Context context) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieResponse> call = apiService.getMovies("movie");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                try {
                    ArrayList<Movie> movies = response.body().getResults();
                    movieList.postValue(movies);
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
        return movieList;
    }

    public LiveData<List<Movie>> getMoviesFav() {
        return movies;
    }

    public void insert(Movie movie) {
        movieRepository.insert(movie);
    }

    public void delete(Movie movie) { movieRepository.delete(movie); }

//    public void update(Movie movie) { movieRepository.update(movie); }

    public boolean checkMovie (String title) {
        try {
            return movieRepository.checkMovie(title);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

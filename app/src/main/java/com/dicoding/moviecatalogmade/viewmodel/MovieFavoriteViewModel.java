package com.dicoding.moviecatalogmade.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.repository.MovieRepository;

import java.util.List;

public class MovieFavoriteViewModel extends AndroidViewModel {

    private LiveData<List<Movie>> movies;
    private MovieRepository movieRepository;

    public MovieFavoriteViewModel(@NonNull Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        movies = movieRepository.getAllData();
    }

    public LiveData<List<Movie>> getMoviesFavorite() {
        return movies;
    }

    public void insert(Movie movie) { movieRepository.insert(movie); }

    public void delete(Movie movie) { movieRepository.delete(movie); }

    public boolean checkMovie (String title) {
        try {
            return movieRepository.checkData(title);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

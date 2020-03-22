package com.dicoding.moviecatalogmade.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogmade.database.MovieDAO;
import com.dicoding.moviecatalogmade.database.MovieRoomDatabase;
import com.dicoding.moviecatalogmade.model.Movie2;

import java.util.ArrayList;

public class MovieRepository {

    private MovieDAO mMovieDAO;
    private LiveData<ArrayList<Movie2>> mAllMovies;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDAO = db.movieDAO();
        //mAllMovies = mMovieDAO.getAllMovies();
    }

    public LiveData<ArrayList<Movie2>> getAllMovies() {
        return mAllMovies;
    }

    public void insert (Movie2 movie) {
        new insertAsyncTask(mMovieDAO).execute(movie);
    }

    private static class insertAsyncTask extends AsyncTask<Movie2, Void, Void> {

        private MovieDAO mAsyncTaskDao;

        insertAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie2... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

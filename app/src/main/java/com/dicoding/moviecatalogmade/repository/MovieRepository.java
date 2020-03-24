package com.dicoding.moviecatalogmade.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.dicoding.moviecatalogmade.database.MovieDAO;
import com.dicoding.moviecatalogmade.database.MovieRoomDatabase;
import com.dicoding.moviecatalogmade.model.Movie;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieRepository {

    private MovieDAO mMovieDAO;
    private LiveData<List<Movie>> mAllMovies;
    private static final String TAG="Trace "+MovieRepository.class.getSimpleName();
    public boolean isFavorite =  false;

    public MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        mMovieDAO = db.movieDAO();
        mAllMovies = mMovieDAO.getAllMovies();
    }

    public LiveData<List<Movie>> getAllMovies() {
        return mAllMovies;
    }

    public void insert (Movie movie) {
        new insertMovieAsyncTask(mMovieDAO).execute(movie);
    }
    public void update (Movie movie) {
        new updateMovieAsyncTask(mMovieDAO).execute(movie);
    }
    public void delete (Movie movie) {
        new deleteMovieAsyncTask(mMovieDAO).execute(movie);
    }

    private static class insertMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO mAsyncTaskDao;
        insertMovieAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateMovieAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDAO mAsyncTaskDao;
        public updateMovieAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteMovieAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO mAsyncTaskDao;
        deleteMovieAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    public boolean checkMovie(String title) throws ExecutionException, InterruptedException {
        checkMovieAsyncTask task = new checkMovieAsyncTask(mMovieDAO);
        task.delegate = this;
        task.execute(title);
        boolean test = task.get();
        Log.d(TAG, "checkMovie: "+test);
        return test;
    }

    private static class checkMovieAsyncTask extends AsyncTask<String, Void, Boolean> {
        private MovieDAO mAsyncTaskDao;
        private MovieRepository delegate = null;

        checkMovieAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final String... params) {
            int result = mAsyncTaskDao.getMovieByTitle(params[0]);
            if (result > 0){
                //Log.d(TAG, "doInBackground: true");
                return true;
            }
            else{
                //Log.d(TAG, "doInBackground: false");
                return false;
            }
        }

//        @Override
//        protected void onPostExecute(Boolean aBoolean) {
//            delegate.setIsFavorite(aBoolean);
//            Log.d(TAG, "onPostExecute: "+aBoolean);
//        }


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //Log.d(TAG, "onPostExecute: "+aBoolean);
        }
    }
}


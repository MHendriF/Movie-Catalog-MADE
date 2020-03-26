package com.dicoding.moviecatalogmade.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogmade.database.MovieDAO;
import com.dicoding.moviecatalogmade.database.MoviesRoomDatabase;
import com.dicoding.moviecatalogmade.model.Movie;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MovieRepository {

    private MovieDAO mDAO;
    private LiveData<List<Movie>> mData;

    public MovieRepository(Application application) {
        MoviesRoomDatabase db = MoviesRoomDatabase.getDatabase(application);
        mDAO = db.movieDAO();
        mData = mDAO.getAllMovies();
    }

    public LiveData<List<Movie>> getAllData() {
        return mData;
    }

    public void insert (Movie movie) {
        new insertDataAsyncTask(mDAO).execute(movie);
    }
    public void update (Movie movie) {
        new updateDataAsyncTask(mDAO).execute(movie);
    }
    public void delete (Movie movie) {
        new deleteDataAsyncTask(mDAO).execute(movie);
    }
    public boolean checkData(String title) throws ExecutionException, InterruptedException {
        checkDataAsyncTask task = new checkDataAsyncTask(mDAO);
        task.execute(title);
        return task.get();
    }
    private static class insertDataAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO mAsyncTaskDao;
        insertDataAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateDataAsyncTask extends AsyncTask<Movie, Void, Void>{
        private MovieDAO mAsyncTaskDao;
        updateDataAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteDataAsyncTask extends AsyncTask<Movie, Void, Void> {
        private MovieDAO mAsyncTaskDao;
        deleteDataAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Movie... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class checkDataAsyncTask extends AsyncTask<String, Void, Boolean> {
        private MovieDAO mAsyncTaskDao;
        checkDataAsyncTask(MovieDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final String... params) {
            int result = mAsyncTaskDao.getMovieByTitle(params[0]);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            //Log.d(TAG, "onPostExecute: "+aBoolean);
        }
    }
}


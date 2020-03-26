package com.dicoding.moviecatalogmade.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogmade.database.MoviesRoomDatabase;
import com.dicoding.moviecatalogmade.database.TvShowDAO;
import com.dicoding.moviecatalogmade.model.TvShow;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class TvShowRepository {

    private TvShowDAO mDAO;
    private LiveData<List<TvShow>> mData;

    public TvShowRepository(Application application) {
        MoviesRoomDatabase db = MoviesRoomDatabase.getDatabase(application);
        mDAO = db.tvShowDAO();
        mData = mDAO.getAllTvShows();
    }

    public LiveData<List<TvShow>> getAllData() {
        return mData;
    }

    public void insert (TvShow tvShow) {
        new TvShowRepository.insertDataAsyncTask(mDAO).execute(tvShow);
    }
    public void update (TvShow tvShow) {
        new TvShowRepository.updateDataAsyncTask(mDAO).execute(tvShow);
    }
    public void delete (TvShow tvShow) {
        new TvShowRepository.deleteDataAsyncTask(mDAO).execute(tvShow);
    }
    public boolean checkData(String title) throws ExecutionException, InterruptedException {
        TvShowRepository.checkDataAsyncTask task = new TvShowRepository.checkDataAsyncTask(mDAO);
        task.execute(title);
        return task.get();
    }

    private static class insertDataAsyncTask extends AsyncTask<TvShow, Void, Void> {
        private TvShowDAO mAsyncTaskDao;
        insertDataAsyncTask(TvShowDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TvShow... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateDataAsyncTask extends AsyncTask<TvShow, Void, Void>{
        private TvShowDAO mAsyncTaskDao;
        updateDataAsyncTask(TvShowDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TvShow... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

    private static class deleteDataAsyncTask extends AsyncTask<TvShow, Void, Void> {
        private TvShowDAO mAsyncTaskDao;
        deleteDataAsyncTask(TvShowDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TvShow... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }

    private static class checkDataAsyncTask extends AsyncTask<String, Void, Boolean> {
        private TvShowDAO mAsyncTaskDao;
        checkDataAsyncTask(TvShowDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Boolean doInBackground(final String... params) {
            int result = mAsyncTaskDao.getTvShowByTitle(params[0]);
            return result > 0;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }
}

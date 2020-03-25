package com.dicoding.moviecatalogmade.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.repository.TvShowRepository;

import java.util.List;

public class TvShowFavoriteViewModel extends AndroidViewModel {

    private LiveData<List<TvShow>> liveData;
    private TvShowRepository tvShowRepository;

    public TvShowFavoriteViewModel(@NonNull Application application) {
        super(application);
        tvShowRepository = new TvShowRepository(application);
        liveData = tvShowRepository.getAllData();
    }

    public LiveData<List<TvShow>> getAllData() {
        return liveData;
    }

    public void insert(TvShow tvShow) { tvShowRepository.insert(tvShow); }

    public void delete(TvShow tvShow) { tvShowRepository.delete(tvShow); }

    public boolean checkData(String title) {
        try {
            return tvShowRepository.checkData(title);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

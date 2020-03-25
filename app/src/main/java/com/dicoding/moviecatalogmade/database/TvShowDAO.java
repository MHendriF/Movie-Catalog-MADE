package com.dicoding.moviecatalogmade.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dicoding.moviecatalogmade.model.TvShow;

import java.util.List;

@Dao
public interface TvShowDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TvShow... tvShows);

    @Query("SELECT * from tv_show_table ORDER BY title ASC")
    LiveData<List<TvShow>> getAllTvShows();

    @Query("SELECT COUNT(uid) FROM tv_show_table WHERE title = :title")
    int getTvShowByTitle(String title);

    @Delete
    void delete(TvShow tvShow);

    @Update
    void update(TvShow... tvShow);
}

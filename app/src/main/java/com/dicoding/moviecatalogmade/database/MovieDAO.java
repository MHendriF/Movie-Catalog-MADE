package com.dicoding.moviecatalogmade.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.dicoding.moviecatalogmade.model.Movie2;

import java.util.ArrayList;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie2... movies);

//    @Query("DELETE FROM Movie2 WHERE uid = :uid")
//    void deleteByUid(int uid);
//
//    @Query("DELETE FROM Movie2")
//    void deleteAll();

//    @Query("SELECT * from Movie2 ORDER BY title ASC")
//    LiveData<ArrayList<Movie2>> getAllMovies();
}

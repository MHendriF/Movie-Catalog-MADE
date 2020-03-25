package com.dicoding.moviecatalogmade.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.dicoding.moviecatalogmade.model.Movie;

import java.util.List;

@Dao
public interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie... movies);

    @Query("SELECT * from movie_table ORDER BY title ASC")
    LiveData<List<Movie>> getAllMovies();

    @Query("SELECT COUNT(uid) FROM movie_table WHERE title = :title")
    int getMovieByTitle(String title);

    @Delete
    void delete(Movie movie);

    @Update
    void update(Movie... movie);
}

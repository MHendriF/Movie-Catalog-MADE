package com.dicoding.moviecatalogmade.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.TvShow;

@Database(entities = {Movie.class, TvShow.class}, version = 1, exportSchema = false)
public abstract class MoviesRoomDatabase extends RoomDatabase {

    public abstract MovieDAO movieDAO();
    public abstract TvShowDAO tvShowDAO();
    private static MoviesRoomDatabase INSTANCE;

    public static MoviesRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (MoviesRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MoviesRoomDatabase.class, "movies_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}

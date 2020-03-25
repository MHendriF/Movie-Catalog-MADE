package com.dicoding.moviecatalogmade.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dicoding.moviecatalogmade.model.TvShow;

@Database(entities = {TvShow.class}, version = 1, exportSchema = false)
public abstract class TvShowRoomDatabase extends RoomDatabase {

    public abstract TvShowDAO tvShowDAO();
    private static TvShowRoomDatabase INSTANCE;

    public static TvShowRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TvShowRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TvShowRoomDatabase.class, "tv_database")
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

package com.dicoding.moviecatalogmade.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.dicoding.moviecatalogmade.model.Movie2;

@Database(entities = {Movie2.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDAO movieDAO();
    private static MovieRoomDatabase INSTANCE;

    public static MovieRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (MovieRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MovieRoomDatabase.class, "movie_database")
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
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final MovieDAO mDao;
        private static String [] words = {"dolphin", "crocodile", "cobra", "elephant", "goldfish", "tiger", "snake"};

        PopulateDbAsync(MovieRoomDatabase db) {
            mDao = db.movieDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // If we have no words, then create the initial list of words
//            if (mDao.getAnyWord().length < 1) {
//                for (int i = 0; i <= words.length - 1; i++) {
//                    Word word = new Word(words[i]);
//                    mDao.insert(word);
//                }
//            }
            return null;
        }
    }
}

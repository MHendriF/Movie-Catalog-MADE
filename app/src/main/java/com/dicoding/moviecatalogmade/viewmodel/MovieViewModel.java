package com.dicoding.moviecatalogmade.viewmodel;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.Movie2;
import com.dicoding.moviecatalogmade.repository.MovieRepository;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

//    private LiveData<ArrayList<Movie2>> movies;
//    private MovieRepository movieRepository;
//
//    public MovieViewModel(Application application){
//        super(application);
//        movieRepository = new MovieRepository(application);
//        movies = movieRepository.getAllMovies();
//    }

    public void setMovies(final Context context) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<Movie> listItems = new ArrayList<>();

        String url = BuildConfig.TMDB_BASE_URL + "discover/movie?api_key=" + BuildConfig.TMDB_API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray results = responseObject.getJSONArray("results");

                    for (int i = 0; i < results.length(); i++) {
                        JSONObject jsonObject = results.getJSONObject(i);
                        Movie movieItems = new Movie(jsonObject);
                        listItems.add(movieItems);
                        //insert(movieItems);
                    }
                    listMovies.postValue(listItems);

                    //ArrayList<Movie2> movies = results.get.getJSONArray();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }
//
//    public void insert(Movie2 movie) {
//        movieRepository.insert(movie);
//    }
}

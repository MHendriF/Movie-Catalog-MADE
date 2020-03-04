package com.dicoding.moviecatalogmade.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.model.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

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
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject jsonObject = list.getJSONObject(i);
                        Movie movieItems = new Movie(jsonObject);
                        listItems.add(movieItems);
                    }
                    listMovies.postValue(listItems);
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.d("Exception", e.getMessage());
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //Log.d("onFailure", error.getMessage());
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovies() {
        return listMovies;
    }
}

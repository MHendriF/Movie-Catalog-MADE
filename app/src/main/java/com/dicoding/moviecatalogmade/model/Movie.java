package com.dicoding.moviecatalogmade.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

public class Movie implements Parcelable {

    @SerializedName(value = "title", alternate = {"name"})
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private String score;

    @SerializedName("poster_path")
    private String poster;

    @SerializedName("popularity")
    private String popularity;

    @SerializedName(value = "release_date", alternate = {"first_air_date"})
    private String release_date;

    @SerializedName("original_language")
    private String language;

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public String getScore() {
        return score;
    }

    public String getPoster() {
        return poster;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getLanguage() {
        return language;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeString(score);
        dest.writeString(poster);
        dest.writeString(popularity);
        dest.writeString(language);
    }

    private Movie(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        overview = in.readString();
        score = in.readString();
        poster = in.readString();
        popularity = in.readString();
        language = in.readString();
    }
}
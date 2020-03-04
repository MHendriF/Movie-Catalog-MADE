package com.dicoding.moviecatalogmade.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {

    private String title;
    private String overview;
    private Double score;
    private String poster;
    private String popularity;
    private String release_date;
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

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
        dest.writeValue(score);
        dest.writeString(poster);
        dest.writeString(popularity);
        dest.writeString(language);
    }

    private Movie(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        overview = in.readString();
        score = (Double) in.readValue(Double.class.getClassLoader());
        poster = in.readString();
        popularity = in.readString();
        language = in.readString();
    }

    public Movie(JSONObject object) {
        try {
            Double score = object.getDouble("vote_average");
            String title = object.getString("title");
            String language = object.getString("original_language");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            String poster = object.getString("poster_path");
            String popularity = object.getString("popularity");

            this.title = title;
            this.score = score;
            this.release_date = release_date;
            this.overview = overview;
            this.release_date = release_date;
            this.poster = poster;
            this.language = language;
            this.popularity = popularity;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

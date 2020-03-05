package com.dicoding.moviecatalogmade.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TvShow implements Parcelable {

    private String title;
    private String overview;
    private Double score;
    private String poster;
    private String popularity;
    private String release_date;
    private String language;
    public TvShow(){}

    public static final Creator<TvShow> CREATOR = new Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel in) {
            return new TvShow(in);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
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

    public Double getScore() {
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
        dest.writeValue(score);
        dest.writeString(poster);
        dest.writeString(popularity);
        dest.writeString(language);
    }

    private TvShow(Parcel in) {
        title = in.readString();
        release_date = in.readString();
        overview = in.readString();
        score = (Double) in.readValue(Double.class.getClassLoader());
        poster = in.readString();
        popularity = in.readString();
        language = in.readString();
    }

    public TvShow(JSONObject object) {
        try {
            Double score = object.getDouble("vote_average");
            String title = object.getString("name");
            String language = object.getString("original_language");
            String overview = object.getString("overview");
            String release_date = object.getString("first_air_date");
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

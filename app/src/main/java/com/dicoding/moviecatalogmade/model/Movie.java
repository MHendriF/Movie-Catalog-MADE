package com.dicoding.moviecatalogmade.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "movie_table", indices = @Index(value = {"title"}, unique = true))
public class Movie implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "title")
    @SerializedName(value = "title", alternate = {"name"})
    private String title;

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    private String overview;

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    private String score;

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    private String poster;

    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    private String popularity;

    @ColumnInfo(name = "release_date")
    @SerializedName(value = "release_date", alternate = {"first_air_date"})
    private String release_date;

    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    private String language;

    public Movie(){ }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
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
}
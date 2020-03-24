package com.dicoding.moviecatalogmade.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table", indices = @Index(value = {"title"}, unique = true))
public class Movie implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(title);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeString(score);
        dest.writeString(poster);
        dest.writeString(popularity);
        dest.writeString(language);
    }

    private Movie(Parcel in) {
        uid = in.readInt();
        title = in.readString();
        release_date = in.readString();
        overview = in.readString();
        score = in.readString();
        poster = in.readString();
        popularity = in.readString();
        language = in.readString();
    }
}
package com.dicoding.moviecatalogmade.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.json.JSONObject;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "movie_table", indices = @Index(value = {"title"}, unique = true))
public class Movie2 implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @SerializedName("title")
    private String title;
    @SerializedName("overview")
    private String overview;
    @SerializedName("score")
    private String score;
    @SerializedName("poster")
    private String poster;
    @SerializedName("popularity")
    private String popularity;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("language")
    private String language;

    public Movie2() {

    }

//    @Ignore
//    public Movie2(String title, String score, String release_date, String overview, String poster, String language, String popularity){
//        this.title = title;
//        this.score = score;
//        this.release_date = release_date;
//        this.overview = overview;
//        this.release_date = release_date;
//        this.poster = poster;
//        this.language = language;
//        this.popularity = popularity;
//    }

    public static final Creator<Movie2> CREATOR = new Creator<Movie2>() {
        @Override
        public Movie2 createFromParcel(Parcel in) {
            return new Movie2(in);
        }

        @Override
        public Movie2[] newArray(int size) {
            return new Movie2[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.uid);
        dest.writeString(this.title);
        dest.writeString(this.release_date);
        dest.writeString(this.overview);
        dest.writeString(this.score);
        dest.writeString(this.poster);
        dest.writeString(this.popularity);
        dest.writeString(this.language);
    }

    private Movie2(Parcel in) {
        this.uid = in.readInt();
        this.title = in.readString();
        this.release_date = in.readString();
        this.overview = in.readString();
        this.score = in.readString();
        this.poster = in.readString();
        this.popularity = in.readString();
        this.language = in.readString();
    }

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

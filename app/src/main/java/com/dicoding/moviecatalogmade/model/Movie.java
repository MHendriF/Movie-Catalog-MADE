package com.dicoding.moviecatalogmade.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String judul;
    private String tayang;
    private String description;
    private int score;
    private int poster;
    private String durasi;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    private String language;

    public String getDurasi() {
        return durasi;
    }

    public void setDurasi(String durasi) {
        this.durasi = durasi;
    }


    public Movie() {

    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTayang() {
        return tayang;
    }

    public void setTayang(String tayang) {
        this.tayang = tayang;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    protected Movie(Parcel in) {
        judul = in.readString();
        tayang = in.readString();
        description = in.readString();
        score = in.readInt();
        poster = in.readInt();
        durasi = in.readString();
        language = in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(tayang);
        dest.writeString(description);
        dest.writeInt(score);
        dest.writeInt(poster);
        dest.writeString(durasi);
        dest.writeString(language);
    }
}

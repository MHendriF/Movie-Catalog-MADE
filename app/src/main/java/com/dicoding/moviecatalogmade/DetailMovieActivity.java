package com.dicoding.moviecatalogmade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.model.Movie;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private String title = "Detail Movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        setActionBarTitle(title);

        Movie selectedMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (selectedMovie != null){

            ImageView imgPoster = findViewById(R.id.iv_img_poster_detail);
            Glide.with(this)
                    .load(selectedMovie.getPoster())
                    .apply(new RequestOptions().override(600, 900))
                    .into(imgPoster);

            TextView txtJudul = findViewById(R.id.tv_movie_name_detail);
            txtJudul.setText(selectedMovie.getJudul());

            TextView txtDurasi = findViewById(R.id.tv_movie_durasi_detail);
            txtDurasi.setText(selectedMovie.getDurasi());

            TextView txtRilis = findViewById(R.id.tv_movie_tgl_detail);
            txtRilis.setText(selectedMovie.getTayang());

            TextView txtDeskripsi = findViewById(R.id.tv_movie_description_detail);
            txtDeskripsi.setText(selectedMovie.getDescription());

            TextView txtSkor = findViewById(R.id.tv_movie_score_detail);
            txtSkor.setText(String.valueOf(selectedMovie.getScore())+"%");
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }
}

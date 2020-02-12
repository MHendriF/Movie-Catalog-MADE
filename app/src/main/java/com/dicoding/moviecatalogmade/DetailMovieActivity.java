package com.dicoding.moviecatalogmade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.model.Movie;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private String title = "Detail Movie";
    private String customData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        setActionBarTitle(title);

        Movie item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        if (item != null){

            ImageView imgPoster = findViewById(R.id.iv_movie_poster);
            Glide.with(this)
                    .load(item.getPoster())
                    .apply(new RequestOptions().override(600, 900))
                    .into(imgPoster);

            TextView txtJudul = findViewById(R.id.tv_movie_title);
            txtJudul.setText(item.getJudul());

            TextView txtDurasi = findViewById(R.id.tv_movie_runtime);
            txtDurasi.setText(item.getDurasi());

            TextView txtRilis = findViewById(R.id.tv_movie_released);
            txtRilis.setText(item.getTayang());

            TextView txtDeskripsi = findViewById(R.id.tv_movie_description);
            txtDeskripsi.setText(item.getDescription());

            TextView txtSkor = findViewById(R.id.tv_movie_score);
            customData = item.getScore()+"%";
            txtSkor.setText(customData);

            TextView tvLanguage = findViewById(R.id.tv_movie_language);
            tvLanguage.setText(item.getLanguage());
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

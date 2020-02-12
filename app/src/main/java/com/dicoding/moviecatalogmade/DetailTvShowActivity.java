package com.dicoding.moviecatalogmade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.TvShow;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private String title = "Detail Tv Show";
    private String customData;
    private String TAG = "Trace DetailTvShow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        setActionBarTitle(title);

        TvShow item = getIntent().getParcelableExtra(EXTRA_MOVIE);
        Log.d(TAG, "onCreate: "+item);
        if (item != null){
            ImageView imgPoster = findViewById(R.id.iv_movie_poster);
            Glide.with(this)
                    .load(item.getPoster())
                    .apply(new RequestOptions().override(600, 900))
                    .into(imgPoster);

            TextView txtJudul = findViewById(R.id.tv_movie_title);
            txtJudul.setText(item.getTitle());

            TextView txtDurasi = findViewById(R.id.tv_movie_runtime);
            txtDurasi.setText(item.getRuntime());

            TextView txtRilis = findViewById(R.id.tv_movie_released);
            txtRilis.setText(item.getRelease_date());

            TextView txtDeskripsi = findViewById(R.id.tv_movie_description);
            txtDeskripsi.setText(item.getOverview());

            TextView txtSkor = findViewById(R.id.tv_movie_score);
            customData = item.getScore()+"%";
            txtSkor.setText(customData);
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

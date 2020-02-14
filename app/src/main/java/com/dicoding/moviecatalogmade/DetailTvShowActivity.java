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

import org.w3c.dom.Text;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private String customTitle;
    private String customData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        customTitle = getString(R.string.title_tv_show);
        setActionBarTitle("Detail "+customTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TvShow item = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        if (item != null){
            ImageView imgPoster = findViewById(R.id.iv_movie_poster);
            Glide.with(this)
                    .load(item.getPoster())
                    .apply(new RequestOptions().override(600, 900))
                    .into(imgPoster);

            TextView tvTitle = findViewById(R.id.tv_movie_title);
            tvTitle.setText(item.getTitle());

            TextView tvRuntime = findViewById(R.id.tv_movie_runtime);
            tvRuntime.setText(item.getRuntime());

            TextView tvReleased = findViewById(R.id.tv_movie_released);
            tvReleased.setText(item.getRelease_date());

            TextView tvOverview = findViewById(R.id.tv_movie_description);
            tvOverview.setText(item.getOverview());

            TextView tvScore = findViewById(R.id.tv_movie_score);
            customData = item.getScore()+"%";
            tvScore.setText(customData);

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

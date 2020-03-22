package com.dicoding.moviecatalogmade.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.model.Movie2;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private ProgressBar progressBar;

    @BindView(R.id.iv_movie_poster)
    ImageView imgPoster;
    @BindView(R.id.tv_movie_title)
    TextView tvTitle;
    @BindView(R.id.tv_movie_popularity)
    TextView tvPopularity;
    @BindView(R.id.tv_movie_released)
    TextView tvReleased;
    @BindView(R.id.tv_movie_description)
    TextView tvOverview;
    @BindView(R.id.tv_movie_score)
    TextView tvScore;
    @BindView(R.id.tv_movie_language)
    TextView tvLanguage;
    @BindString(R.string.title_tv_show)
    String customTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        setActionBarTitle("Detail "+customTitle);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progressBarGeneral);

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(2200);
                }
                catch (Exception e) {
                    Log.d("Thread", "run: "+e);
                }

                handler.post(new Runnable() {
                    public void run() {
                        Movie item = getIntent().getParcelableExtra(EXTRA_MOVIE);
                        if (item != null){
                            String urlPoster = BuildConfig.API_POSTER_PATH + item.getPoster();

                            Glide.with(DetailMovieActivity.this)
                                    .load(urlPoster)
                                    .apply(new RequestOptions().override(600, 900))
                                    .into(imgPoster);

                            tvTitle.setText(item.getTitle());
                            tvPopularity.setText(item.getPopularity());
                            tvReleased.setText(item.getRelease_date());
                            tvOverview.setText(item.getOverview());
                            tvScore.setText(item.getScore());
                            tvLanguage.setText(item.getLanguage());

                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
            }
        }).start();

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

package com.dicoding.moviecatalogmade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.model.TvShow;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    private String customData;

    @BindView(R.id.iv_movie_poster)
    ImageView imgPoster;
    @BindView(R.id.tv_movie_title)
    TextView tvTitle;
    @BindView(R.id.tv_movie_runtime)
    TextView tvRuntime;
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
        setContentView(R.layout.activity_detail_tv_show);
        ButterKnife.bind(this);
        setActionBarTitle("Detail "+customTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TvShow item = getIntent().getParcelableExtra(EXTRA_TV_SHOW);
        if (item != null){
            Glide.with(this)
                    .load(item.getPoster())
                    .apply(new RequestOptions().override(600, 900))
                    .into(imgPoster);

            tvTitle.setText(item.getTitle());
            tvRuntime.setText(item.getRuntime());
            tvReleased.setText(item.getRelease_date());
            tvOverview.setText(item.getOverview());
            customData = item.getScore()+"%";
            tvScore.setText(customData);
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

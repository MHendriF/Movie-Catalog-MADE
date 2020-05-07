package com.dicoding.moviecatalogmade.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.model.TvShow;
import com.dicoding.moviecatalogmade.viewmodel.TvShowFavoriteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTvShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV_SHOW = "extra_tv_show";
    public static final String EXTRA_FROM = "extra_from";

    @BindView(R.id.loading_progress)
    ProgressBar progressBar;
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
    @BindView(R.id.fab_favorite)
    FloatingActionButton fabFavorite;

    private TvShowFavoriteViewModel viewModel;
    private boolean isFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);
        ButterKnife.bind(this);
        setActionBarTitle("Detail "+customTitle);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(1000);
                }
                catch (Exception e) { e.printStackTrace(); }

                handler.post(new Runnable() {
                    public void run() {
                        try{
                            Bundle bundle = getIntent().getExtras();
                            TvShow tvShow = (TvShow) bundle.getSerializable(EXTRA_TV_SHOW);
                            String type = bundle.getString(EXTRA_FROM);

                            if (tvShow != null && type != null){
                                setViewModel(tvShow, type);
                                showData(tvShow);
                            }
                        }catch (NullPointerException ne){
                            ne.printStackTrace();
                        }
                    }
                });
            }
        }).start();
    }

    private void setViewModel(final TvShow data, String type){
        viewModel = new ViewModelProvider(this).get(TvShowFavoriteViewModel.class);
        viewModel.getAllData().observe(this, getAllData);

        isFavorite = viewModel.checkData(data.getTitle());
        setFabFavorite(isFavorite);

        if (type.equals("tv_show")){
            fabFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (!isFavorite){
                        viewModel.insert(data);
                        Snackbar.make(v, R.string.add_favorite, Snackbar.LENGTH_SHORT).show();
                        setFabFavorite(true);
                        isFavorite = true;
                    }
                }
            });
        }

        else {
            fabFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (isFavorite) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailTvShowActivity.this);
                        builder.setTitle(R.string.confirm_title);
                        builder.setMessage(R.string.confirm_message);
                        builder.setCancelable(false);
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                viewModel.delete(data);
                                Snackbar.make(v, R.string.remove_favorite, Snackbar.LENGTH_SHORT).show();
                                setFabFavorite(false);
                                isFavorite = false;
                            }
                        });
                        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                builder.setCancelable(true);
                            }
                        });
                        builder.show();

                    } else {
                        viewModel.insert(data);
                        Snackbar.make(v, R.string.add_favorite, Snackbar.LENGTH_SHORT).show();
                        setFabFavorite(true);
                        isFavorite = true;
                    }
                }
            });
        }
    }

    private void showData(final TvShow data){
        String urlPoster = BuildConfig.API_POSTER_PATH + data.getPoster();
        Glide.with(DetailTvShowActivity.this)
                .load(urlPoster)
                .apply(new RequestOptions().override(600, 900))
                .into(imgPoster);
        tvTitle.setText(data.getTitle());
        tvPopularity.setText(data.getPopularity());
        tvReleased.setText(data.getRelease_date());
        tvOverview.setText(data.getOverview());
        tvScore.setText(data.getScore());
        tvLanguage.setText(data.getLanguage());
        progressBar.setVisibility(View.INVISIBLE);
    }

    private Observer<List<TvShow>> getAllData = new Observer<List<TvShow>>() {
        @Override
        public void onChanged(List<TvShow> tvShows) {
        }
    };

    private void setFabFavorite(boolean isFavorite){
        if (isFavorite) {
            fabFavorite.setImageResource(R.drawable.ic_favorite_red_24dp);
        }else {
            fabFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
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

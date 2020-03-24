package com.dicoding.moviecatalogmade.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dicoding.moviecatalogmade.BuildConfig;
import com.dicoding.moviecatalogmade.R;
import com.dicoding.moviecatalogmade.adapter.MovieAdapter;
import com.dicoding.moviecatalogmade.database.MovieDAO;
import com.dicoding.moviecatalogmade.database.MovieRoomDatabase;
import com.dicoding.moviecatalogmade.model.Movie;
import com.dicoding.moviecatalogmade.repository.MovieRepository;
import com.dicoding.moviecatalogmade.viewmodel.MovieViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
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

    private MovieViewModel movieViewModel;
    private boolean isFavorite;
    private static final String TAG="Trace "+ DetailMovieActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        setActionBarTitle("Detail "+customTitle);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMoviesFav().observe(this, getMovie);

        final Movie data = getIntent().getParcelableExtra(EXTRA_MOVIE);
        assert data != null;
        isFavorite = movieViewModel.checkMovie(data.getTitle());
        Log.d(TAG, "before: "+isFavorite);
        setFabFavorite(isFavorite);

        String tipe = getIntent().getStringExtra(EXTRA_FROM);
        Log.d(TAG, "from: "+tipe);
        if (tipe.equals("movie")){
            fabFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                  if (!isFavorite){
                        movieViewModel.insert(data);
                        Snackbar.make(v, R.string.add_favorite, Snackbar.LENGTH_SHORT).show();
                        setFabFavorite(true);
                        isFavorite = true;
                        Log.d(TAG, "after: "+isFavorite);
                    }

                }
            });
        }

        else {
            fabFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    if (isFavorite) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(DetailMovieActivity.this);
                        builder.setTitle(R.string.confirm_title);
                        builder.setMessage(R.string.confirm_message);
                        builder.setCancelable(false);
                        builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                movieViewModel.delete(data);
                                Snackbar.make(v, R.string.remove_favorite, Snackbar.LENGTH_SHORT).show();
                                setFabFavorite(false);
                                isFavorite = false;
                                //Log.d(TAG, "after: "+isFavorite);
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
                        movieViewModel.insert(data);
                        Snackbar.make(v, R.string.add_favorite, Snackbar.LENGTH_SHORT).show();
                        setFabFavorite(true);
                        isFavorite = true;
                        //Log.d(TAG, "after: "+isFavorite);
                    }
                }
            });
        }

        final Handler handler = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(1000);
                }
                catch (Exception e) {
                    Log.d("Thread", "run: "+e);
                }

                handler.post(new Runnable() {
                    public void run() {
                        if (data != null){
                            String urlPoster = BuildConfig.API_POSTER_PATH + data.getPoster();

                            Glide.with(DetailMovieActivity.this)
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
                    }
                });
            }
        }).start();
    }


    private Observer<List<Movie>> getMovie = new Observer<List<Movie>>() {
        @Override
        public void onChanged(List<Movie> movies) {
            if (movies != null) {
                Log.d(TAG, "onChanged 2: ");
                //movieAdapter.setData(movies);
            }
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

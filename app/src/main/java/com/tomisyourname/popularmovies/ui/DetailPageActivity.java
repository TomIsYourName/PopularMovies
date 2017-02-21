package com.tomisyourname.popularmovies.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomisyourname.popularmovies.R;
import com.tomisyourname.popularmovies.bean.MovieObject;

public class DetailPageActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    setContentView(R.layout.activity_detail_page);
    Intent intent = getIntent();
    MovieObject movie = intent.getParcelableExtra("movie");
    ImageView ivPoster = (ImageView) findViewById(R.id.iv_poster);
    TextView tvTitle = (TextView) findViewById(R.id.tv_title);
    TextView tvRate = (TextView) findViewById(R.id.tv_rate);
    TextView tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
    TextView tvOverview = (TextView) findViewById(R.id.tv_overview);

    Picasso.with(this).load(movie.posterPath).placeholder(R.mipmap.ic_launcher).into(ivPoster);
    tvTitle.setText(movie.title);
    tvRate.setText(movie.voteAverage + "/10");
    tvReleaseDate.setText(movie.releaseDate);
    tvOverview.setText(movie.overview);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
      default:
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}

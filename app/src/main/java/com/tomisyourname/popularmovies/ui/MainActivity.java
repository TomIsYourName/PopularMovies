package com.tomisyourname.popularmovies.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.tomisyourname.popularmovies.BuildConfig;
import com.tomisyourname.popularmovies.R;
import com.tomisyourname.popularmovies.bean.MovieObject;
import com.tomisyourname.popularmovies.net.HttpRequest;
import com.tomisyourname.popularmovies.net.HttpRequestListener;
import com.tomisyourname.popularmovies.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpRequestListener, AdapterView.OnItemClickListener {

  private MoviesListAdapter mAdapter;
  private GridView gvMovies;
  private TextView tvEmpty;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tvEmpty = (TextView) findViewById(R.id.tv_empty);
    gvMovies = (GridView) findViewById(R.id.gl_movies);
    gvMovies.setOnItemClickListener(this);
    if(NetworkUtils.isNetworkAvailable(this)) {
      loadData();
    } else {
      showEmptyView();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_order_by_popularity:
        updateOrder("popularity");
        break;
      case R.id.action_order_by_rate:
        updateOrder("rate");
        break;
      default:
        break;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    Intent intent = new Intent(this, DetailPageActivity.class);
    MovieObject movie = (MovieObject) mAdapter.getItem(position);
    intent.putExtra("movie", movie);
    startActivity(intent);
  }

  @Override
  public void onResponse(String response) {
    List<MovieObject> movies = parseMovies(response);
    updateList(movies);
  }

  @Override
  public void onError(final String error) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
        showEmptyView();
      }
    });
  }

  private void updateList(final List<MovieObject> movies) {
    if (movies == null || movies.isEmpty()) {
      showEmptyView();
      return;
    }
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        mAdapter = new MoviesListAdapter(movies);
        gvMovies.setAdapter(mAdapter);
      }
    });
  }

  private List<MovieObject> parseMovies(String jsonString) {
    List<MovieObject> movies = new ArrayList<>();
    try {
      JSONObject data = new JSONObject(jsonString);
      JSONArray results = data.getJSONArray("results");
      for (int i = 0; i < results.length(); i++) {
        JSONObject obj = results.getJSONObject(i);
        MovieObject movie = new MovieObject();
        movie.setId(obj.optInt("id"));
        movie.setTitle(obj.optString("original_title"));
        movie.setReleaseDate(obj.optString("release_date"));
        movie.setOverview(obj.optString("overview"));
        movie.setPosterPath(obj.optString("poster_path"));
        movie.setVoteCount(obj.optInt("vote_count"));
        movie.setVoteAverage(obj.optDouble("vote_average"));
        movies.add(movie);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return movies;
  }

  private void loadData() {
    SharedPreferences sharedPreferences = getSharedPreferences("shared_order", MODE_PRIVATE);
    String orderBy = sharedPreferences.getString("order", "popularity");
    if("rate".equals(orderBy)) {
      getTopRatedMovies();
    } else {
      getPopularMovies();
    }
  }

  private void updateOrder(String orderBy) {
    SharedPreferences sharedPreferences = getSharedPreferences("shared_order", MODE_PRIVATE);
    String oldOrderBy = sharedPreferences.getString("order", "popularity");
    if(oldOrderBy.equals(orderBy)) return;
    sharedPreferences.edit().putString("order", orderBy).commit();
    loadData();
  }

  private void showEmptyView() {
    tvEmpty.setVisibility(View.VISIBLE);
    tvEmpty.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loadData();
      }
    });
    tvEmpty.setVisibility(View.GONE);
  }

  private void getPopularMovies() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        String url = String.format(
            "https://api.themoviedb.org/3/movie/popular?api_key=%s&language=en-US&page=1",
            BuildConfig.THE_MOVIE_DATABASE_API_KEY);
        HttpRequest.get(url, MainActivity.this);
      }
    }).start();
  }

  private void getTopRatedMovies() {
    new Thread(new Runnable() {
      @Override
      public void run() {
        String url = String.format(
            "https://api.themoviedb.org/3/movie/top_rated?api_key=%s&language=en-US&page=1",
            BuildConfig.THE_MOVIE_DATABASE_API_KEY);
        HttpRequest.get(url, MainActivity.this);
      }
    }).start();
  }

}

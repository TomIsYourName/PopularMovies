package com.tomisyourname.popularmovies.ui;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tomisyourname.popularmovies.R;
import com.tomisyourname.popularmovies.bean.MovieObject;

import java.util.List;

/**
 * Created by zain on 20/02/2017.
 */

public class MoviesListAdapter extends BaseAdapter {

  private List<MovieObject> movies;

  public MoviesListAdapter(List<MovieObject> movies) {
    this.movies = movies;
  }

  @Override
  public int getCount() {
    return movies == null ? 0 : movies.size();
  }

  @Override
  public Object getItem(int position) {
    return movies == null || movies.isEmpty() ? null : movies.get(position);
  }

  @Override
  public long getItemId(int position) {
    return 0;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    if(convertView == null) {
      convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, null);
    }
    ImageView ivPoster = getAdapterView(convertView, R.id.iv_poster);
    TextView tvTitle = getAdapterView(convertView, R.id.tv_title);
    TextView tvRate = getAdapterView(convertView, R.id.tv_rate);
    MovieObject movie = movies.get(position);
    Picasso.with(parent.getContext())
        .load(movie.posterPath)
        .placeholder(R.mipmap.ic_launcher)
        .into(ivPoster);
    tvTitle.setText(movie.title);
    tvRate.setText(movie.voteAverage + "/10");
    return convertView;
  }

  public static <T extends View> T getAdapterView(View convertView, int id) {
    SparseArray<View> viewHolder = (SparseArray<View>) convertView.getTag();
    if (viewHolder == null) {
      viewHolder = new SparseArray<>();
      convertView.setTag(viewHolder);
    }
    View childView = viewHolder.get(id);
    if (childView == null) {
      childView = convertView.findViewById(id);
      viewHolder.put(id, childView);
    }
    return (T) childView;
  }

}
